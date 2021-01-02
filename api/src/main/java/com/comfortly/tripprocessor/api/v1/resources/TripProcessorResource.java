package com.comfortly.tripprocessor.api.v1.resources;

import com.comfortly.tripprocessor.config.AppProperties;
import com.comfortly.tripprocessor.config.RestProperties;
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
import javax.ws.rs.*;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

@ApplicationScoped
@Path("/process")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class TripProcessorResource {

    private Logger log = Logger.getLogger(TripProcessorResource.class.getName());

    @Context
    protected UriInfo uriInfo;

//    @Inject
//    private TripDataBean tripDataBean;
//
//    @Inject
//    private AnswerDataBean answerDataBean;
//
//    @Inject
//    private AnalyzedTripDataBean analyzedTripDataBean;
//
//    @Inject
//    private RestProperties restProperties;
//
//    @Inject
//    private AppProperties appProperties;
//
//    private Client client = ClientBuilder.newClient();
//
//    @Inject
//    @RestClient
//    private SnapToRoadClient snapToRoadClient;

    @Inject
    @RestClient
    private ReverseGeocodingClient reverseGeocodingClient;

//    @GET
//    @Path("/{tripDataId}")
//    public Response createTripData(@HeaderParam("UserId") String userId, @PathParam("tripDataId") Integer tripDataId) {
//
//        if (userId == null) {
//            log.info("Missing UserId header");
//        } else if (tripDataId == null) {
//            log.info("Missing tripDataId parameter");
//        }
//
//        TripData tripData = tripDataBean.getTripData(tripDataId);
//        List<AnswerData> answers = answerDataBean.getAnswersData(userId, tripDataId);
//
//        Double averageSpeedSum = 0.0;
//        Double averageAccelerationSum = 0.0;
//        Double maxSpeed = 0.0;
//        Double maxAcceleration = 0.0;
//        Double distance = 0.0;
//        StringBuilder snapToRoadPoints = new StringBuilder();
//        for (int i = 0; i < tripData.getLocations().size(); i++) {
//            LocationData location = tripData.getLocations().get(i);
//            if (snapToRoadPoints.length() > 0) {
//                snapToRoadPoints.append("|");
//            }
//            snapToRoadPoints.append(location.getLocationLat().toString()).append(",").append(location.getLocationLng());
//
//            averageSpeedSum += location.getSpeed();
//            if (location.getSpeed() > maxSpeed) {
//                maxSpeed = location.getSpeed();
//            }
//
//            averageAccelerationSum += location.getAcceleration();
//            if (location.getAcceleration() > maxAcceleration) {
//                maxAcceleration = location.getAcceleration();
//            }
//
//            if (i < tripData.getLocations().size() - 1) {
//                LocationData location2 = tripData.getLocations().get(i + 1);
//                distance += LocationUtils.distance(location.getLocationLat(), location2.getLocationLat(), location.getLocationLng(), location2.getLocationLng());
//            }
//        }
//
//        Double averageSpeed = averageSpeedSum / tripData.getLocations().size();
//        Double averageAcceleration = averageAccelerationSum / tripData.getLocations().size();
//
////        Response response = client
////                .target("https://roads.googleapis.com/v1/snapToRoads")
////                .queryParam("path", snapToRoadPoints.toString())
////                .queryParam("key", appProperties.getGoogleApiKey())
////                .request(MediaType.APPLICATION_JSON)
////                .get();
//
//        Double finalMaxAcceleration = maxAcceleration;
//        Double finalMaxSpeed = maxSpeed;
//        Double finalDistance = distance;
//
//        AnalyzedTripData analyzedTripData = new AnalyzedTripData();
//
//        ArrayList<AnalyzedLocationData> analyzedLocations = new ArrayList<>();
//        for (LocationData locationData : tripData.getLocations()) {
//            AnalyzedLocationData analyzedLocationData = new AnalyzedLocationData();
//            analyzedLocationData.setLocationLat(locationData.getLocationLat());
//            analyzedLocationData.setSnappedLocationLat(locationData.getLocationLat());
//            analyzedLocationData.setLocationLng(locationData.getLocationLng());
//            analyzedLocationData.setSnappedLocationLng(locationData.getLocationLng());
//            analyzedLocationData.setSpeed(locationData.getSpeed());
//            analyzedLocationData.setAcceleration(locationData.getAcceleration());
//            analyzedLocationData.setOrientation(locationData.getOrientation());
//            analyzedLocationData.setTimestamp(locationData.getTimestamp());
//
//            analyzedLocations.add(analyzedLocationData);
//        }
//
//        try {
//            SnapToRoadData snappedLocation = snapToRoadClient.snapToRoadPoints(snapToRoadPoints.toString(), appProperties.getGoogleApiKey()).toCompletableFuture().get();
//            if (snappedLocation.getSnappedPoints() != null) {
//                for (int i = 0; i < snappedLocation.getSnappedPoints().size(); i++) {
//                    SnappedPoint point = snappedLocation.getSnappedPoints().get(i);
//                    if (point.getOriginalIndex() != null) {
//                        analyzedLocations.get(point.getOriginalIndex()).setSnappedLocationLat(point.getLocation().getLatitude());
//                        analyzedLocations.get(point.getOriginalIndex()).setSnappedLocationLng(point.getLocation().getLongitude());
//                    }
//                }
//            }
//        } catch (InterruptedException | ExecutionException e) {
//            log.severe(e.getMessage());
//        }
//
//        try {
//            GeolocationResult startResult = reverseGeocodingClient.reverseGeocodeLatLng(tripData.getStartLocationLat().toString() + "," + tripData.getStartLocationLng().toString(), appProperties.getGoogleApiKey()).toCompletableFuture().get();
//            String startLocationName = startResult.getResults().get(0).getFormatted_address();
//            analyzedTripData.setStartLocationName(startLocationName);
//        } catch (InterruptedException | ExecutionException e) {
//            log.severe(e.getMessage());
//        }
//        try {
//            GeolocationResult endResult = reverseGeocodingClient.reverseGeocodeLatLng(tripData.getEndLocationLat().toString() + "," + tripData.getEndLocationLng().toString(), appProperties.getGoogleApiKey()).toCompletableFuture().get();
//            String endLocationName = endResult.getResults().get(0).getFormatted_address();
//            analyzedTripData.setEndLocationName(endLocationName);
//        } catch (InterruptedException | ExecutionException e) {
//            log.severe(e.getMessage());
//        }
//
//        Double comfortLevel = (1 / (averageAcceleration + 0.0001) + 1 / (averageSpeed + 0.0001)) * 10;
//
//        // TODO change this
//        EmotionLevel emotions = EmotionLevel.HAPPY;
//
//
//        analyzedTripData.setUserId(tripData.getUserId());
//        analyzedTripData.setTripId(tripData.getId());
//
//        analyzedTripData.setStartLocationLat(tripData.getStartLocationLat());
//        analyzedTripData.setStartLocationLng(tripData.getStartLocationLng());
//
//        analyzedTripData.setEndLocationLat(tripData.getEndLocationLat());
//        analyzedTripData.setEndLocationLng(tripData.getEndLocationLng());
//        analyzedTripData.setStartTime(tripData.getStartTime());
//        analyzedTripData.setEndTime(tripData.getEndTime());
//        analyzedTripData.setAverageSpeed(averageSpeed);
//        analyzedTripData.setMaxSpeed(finalMaxSpeed);
//        analyzedTripData.setAverageAcceleration(averageAcceleration);
//        analyzedTripData.setMaxAcceleration(finalMaxAcceleration);
//        analyzedTripData.setDistance(finalDistance);
//        analyzedTripData.setComfortLevel(comfortLevel);
//        analyzedTripData.setEmotions(emotions);
//        analyzedTripData.setLocations(analyzedLocations);
//
//
//        analyzedTripDataBean.createAnalyzedTripData(analyzedTripData);
//
//        return Response.status(Response.Status.OK).build();
//    }
}
