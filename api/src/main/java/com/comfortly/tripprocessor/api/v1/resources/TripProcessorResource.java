package com.comfortly.tripprocessor.api.v1.resources;

import com.comfortly.tripprocessor.lib.analyzedtrip.AnalyzedTripData;
import com.comfortly.tripprocessor.lib.answer.AnswerData;
import com.comfortly.tripprocessor.lib.trip.TripData;
import com.comfortly.tripprocessor.services.beans.AnalyzedTripDataBean;
import com.comfortly.tripprocessor.services.beans.AnswerDataBean;
import com.comfortly.tripprocessor.services.beans.TripDataBean;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.List;
import java.util.logging.Logger;

@ApplicationScoped
@Path("/process")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TripProcessorResource {

    private Logger log = Logger.getLogger(TripProcessorResource.class.getName());

    @Inject
    private TripDataBean tripDataBean;

    @Inject
    private AnswerDataBean answerDataBean;

    @Inject
    private AnalyzedTripDataBean analyzedTripDataBean;

    @Context
    protected UriInfo uriInfo;

    @Path("/{tripDataId}")
    public Response createTripData(@HeaderParam("UserId") String userId, @PathParam("tripDataId") Integer tripDataId) {

        if (userId == null) {
            log.info("Missing UserId header");
        } else if (tripDataId == null) {
            log.info("Missing tripDataId parameter");
        }

        TripData tripData = tripDataBean.getTripData(tripDataId);
        List<AnswerData> answers = answerDataBean.getAnswersData(userId, tripDataId);

        // TODO the analyzed trip in between here

        return Response.status(Response.Status.OK).build();
    }
}
