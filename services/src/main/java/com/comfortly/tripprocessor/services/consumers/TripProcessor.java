package com.comfortly.tripprocessor.services.consumers;

import com.comfortly.tripprocessor.config.AppProperties;
import com.comfortly.tripprocessor.lib.analyzedtrip.AnalyzedLocationData;
import com.comfortly.tripprocessor.lib.analyzedtrip.AnalyzedTripData;
import com.comfortly.tripprocessor.lib.analyzedtrip.EmotionLevel;
import com.comfortly.tripprocessor.lib.answer.AnswerData;
import com.comfortly.tripprocessor.lib.googleapi.GeolocationResult;
import com.comfortly.tripprocessor.lib.googleapi.SnapToRoadData;
import com.comfortly.tripprocessor.lib.googleapi.SnappedPoint;
import com.comfortly.tripprocessor.lib.trip.LocationData;
import com.comfortly.tripprocessor.lib.trip.TripData;
import com.comfortly.tripprocessor.services.beans.AnalyzedTripDataBean;
import com.comfortly.tripprocessor.services.beans.AnswerDataBean;
import com.comfortly.tripprocessor.services.beans.TripDataBean;
import com.comfortly.tripprocessor.services.clients.ReverseGeocodingClient;
import com.comfortly.tripprocessor.services.clients.SnapToRoadClient;
import com.comfortly.tripprocessor.services.utils.LocationUtils;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

@ApplicationScoped
public class TripProcessor {

    private static final Logger log = Logger.getLogger(TripProcessor.class.getName());




}
