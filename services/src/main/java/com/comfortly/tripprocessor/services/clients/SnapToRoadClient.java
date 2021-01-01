package com.comfortly.tripprocessor.services.clients;

import com.comfortly.tripprocessor.lib.googleapi.SnapToRoadData;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import javax.ws.rs.GET;
import javax.ws.rs.QueryParam;
import java.util.concurrent.CompletionStage;

@RegisterRestClient(configKey = "snap-to-roads-api")
public interface SnapToRoadClient {

    @GET
//    @ClientHeaderParam(name="Host", value="")
    @ClientHeaderParam(name="User-Agent", value="comfortly-processor")
    @ClientHeaderParam(name="Accept", value="*/*")
    CompletionStage<SnapToRoadData> snapToRoadPoints(@QueryParam("path") String path, @QueryParam("key") String key);

}
