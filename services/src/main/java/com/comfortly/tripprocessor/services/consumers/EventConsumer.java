package com.comfortly.tripprocessor.services.consumers;

import com.comfortly.tripprocessor.config.AppProperties;
import com.comfortly.tripprocessor.config.StreamingProperties;
import com.comfortly.tripprocessor.services.beans.AnalyzedTripDataBean;
import com.comfortly.tripprocessor.services.beans.AnswerDataBean;
import com.comfortly.tripprocessor.services.beans.TripDataBean;
import com.comfortly.tripprocessor.services.clients.ReverseGeocodingClient;
import com.comfortly.tripprocessor.services.clients.SnapToRoadClient;
import com.kumuluz.ee.streaming.common.annotations.StreamListener;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.json.JsonReader;
import java.io.StringReader;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;

@ApplicationScoped
public class EventConsumer {

    private static final Logger log = Logger.getLogger(EventConsumer.class.getName());

    @Inject
    private TripDataBean tripDataBean;

    @Inject
    private AnswerDataBean answerDataBean;

    @Inject
    private AnalyzedTripDataBean analyzedTripDataBean;

    @Inject
    private AppProperties appProperties;

    @Inject
    @RestClient
    private SnapToRoadClient snapToRoadClient;

    @Inject
    @RestClient
    private ReverseGeocodingClient reverseGeocodingClient;

    @Inject
    private StreamingProperties streamingProperties;

    private static ExecutorService executor = Executors.newFixedThreadPool(10);

    private static final String TOPIC = "hus39ssl-trips_processing";

    @StreamListener(topics = {TOPIC})
    public void onMessage(ConsumerRecord<String, String> record) {

        log.info(String.format("Consumed message offset = %d, key = %s, value = %s%n", record.offset(), record.key(), record.value()));

        JsonReader jsonReader = Json.createReader(new StringReader(record.value()));
        JsonObject data = jsonReader.readObject();
        String userId = data.getString("userId");
        int tripId = data.getInt("tripId");

        TripProcessorRunnable tripProcessorRunnable = new TripProcessorRunnable(
                tripId,
                userId,
                tripDataBean,
                answerDataBean,
                analyzedTripDataBean,
                appProperties.getGoogleApiKey(),
                snapToRoadClient,
                reverseGeocodingClient
        );
        executor.submit(tripProcessorRunnable);
    }
}
