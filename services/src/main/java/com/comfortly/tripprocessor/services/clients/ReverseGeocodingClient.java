package com.comfortly.tripprocessor.services.clients;

import com.comfortly.tripprocessor.lib.googleapi.GeolocationResult;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;
import java.util.concurrent.CompletionStage;

@RegisterRestClient(configKey = "reverse-geocoding-api")
public interface ReverseGeocodingClient {

    @GET
//    @ClientHeaderParam(name = "Host", value = "private-830f5-dvalicfri.apiary-mock.com")
//    @ClientHeaderParam(name = "User-Agent", value = "comfortly-processor")
//    @ClientHeaderParam(name = "Accept", value = "*/*")
    CompletionStage<GeolocationResult> reverseGeocodeLatLng(@QueryParam("latlng") String latlng, @QueryParam("key") String key);

}
