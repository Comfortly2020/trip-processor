package com.comfortly.tripprocessor.services.consumers;

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

import javax.enterprise.context.ApplicationScoped;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.logging.Logger;

@ApplicationScoped
public class TripProcessorRunnable implements Runnable {

    private static final Logger log = Logger.getLogger(TripProcessorRunnable.class.getName());

    private final Integer tripId;
    private final String userId;
    private final TripDataBean tripDataBean;
    private final AnswerDataBean answerDataBean;
    private final AnalyzedTripDataBean analyzedTripDataBean;
    private final String googleApiKey;
    private final Integer scoreMultiplier;
    private final SnapToRoadClient snapToRoadClient;
    private final ReverseGeocodingClient reverseGeocodingClient;

    public TripProcessorRunnable(
            Integer tripId,
            String userId,
            TripDataBean tripDataBean,
            AnswerDataBean answerDataBean,
            AnalyzedTripDataBean analyzedTripDataBean,
            String googleApiKey,
            Integer scoreMultiplier,
            SnapToRoadClient snapToRoadClient,
            ReverseGeocodingClient reverseGeocodingClient
    ) {
        this.tripId = tripId;
        this.userId = userId;
        this.tripDataBean = tripDataBean;
        this.answerDataBean = answerDataBean;
        this.analyzedTripDataBean = analyzedTripDataBean;
        this.googleApiKey = googleApiKey;
        this.scoreMultiplier = scoreMultiplier;
        this.snapToRoadClient = snapToRoadClient;
        this.reverseGeocodingClient = reverseGeocodingClient;
    }

    private void process() {
        log.info("Starting to process trip");
        if (userId == null) {
            log.info("Missing UserId header");
        } else if (tripId == null) {
            log.info("Missing tripDataId parameter");
        }

        TripData tripData = tripDataBean.getTripData(tripId);
        List<AnswerData> answers = answerDataBean.getAnswersData(userId, tripId);
        if (answers == null || answers.isEmpty()) {
            log.severe("Tried to fetch answers for trip with id: " + tripId + " and userId: " + userId + " but it was null or empty");
            return;
        }

        Double averageSpeedSum = 0.0;
        Double averageAccelerationSum = 0.0;
        Double maxSpeed = 0.0;
        Double maxAcceleration = 0.0;
        Double distance = 0.0;
        StringBuilder snapToRoadPoints = new StringBuilder();
        for (int i = 0; i < tripData.getLocations().size(); i++) {
            LocationData location = tripData.getLocations().get(i);
            if (snapToRoadPoints.length() > 0) {
                snapToRoadPoints.append("|");
            }
            snapToRoadPoints.append(location.getLocationLat().toString()).append(",").append(location.getLocationLng());

            averageSpeedSum += location.getSpeed();
            if (location.getSpeed() > maxSpeed) {
                maxSpeed = location.getSpeed();
            }

            averageAccelerationSum += location.getAcceleration();
            if (location.getAcceleration() > maxAcceleration) {
                maxAcceleration = location.getAcceleration();
            }

            if (i < tripData.getLocations().size() - 1) {
                LocationData location2 = tripData.getLocations().get(i + 1);
                distance += LocationUtils.distance(location.getLocationLat(), location2.getLocationLat(), location.getLocationLng(), location2.getLocationLng());
            }
        }

        Double averageSpeed = averageSpeedSum / tripData.getLocations().size();
        Double averageAcceleration = averageAccelerationSum / tripData.getLocations().size();

        Double finalMaxAcceleration = maxAcceleration;
        Double finalMaxSpeed = maxSpeed;
        Double finalDistance = distance;

        AnalyzedTripData analyzedTripData = new AnalyzedTripData();

        ArrayList<AnalyzedLocationData> analyzedLocations = new ArrayList<>();
        for (LocationData locationData : tripData.getLocations()) {
            AnalyzedLocationData analyzedLocationData = new AnalyzedLocationData();
            analyzedLocationData.setLocationLat(locationData.getLocationLat());
            analyzedLocationData.setSnappedLocationLat(locationData.getLocationLat());
            analyzedLocationData.setLocationLng(locationData.getLocationLng());
            analyzedLocationData.setSnappedLocationLng(locationData.getLocationLng());
            analyzedLocationData.setSpeed(locationData.getSpeed());
            analyzedLocationData.setAcceleration(locationData.getAcceleration());
            analyzedLocationData.setOrientation(locationData.getOrientation());
            analyzedLocationData.setTimestamp(locationData.getTimestamp());

            analyzedLocations.add(analyzedLocationData);
        }

        try {
            SnapToRoadData snappedLocation = snapToRoadClient.snapToRoadPoints(snapToRoadPoints.toString(), googleApiKey).toCompletableFuture().get();
            if (snappedLocation.getSnappedPoints() != null) {
                for (int i = 0; i < snappedLocation.getSnappedPoints().size(); i++) {
                    SnappedPoint point = snappedLocation.getSnappedPoints().get(i);
                    if (point.getOriginalIndex() != null) {
                        analyzedLocations.get(point.getOriginalIndex()).setSnappedLocationLat(point.getLocation().getLatitude());
                        analyzedLocations.get(point.getOriginalIndex()).setSnappedLocationLng(point.getLocation().getLongitude());
                    }
                }
            }
        } catch (InterruptedException | ExecutionException e) {
            log.severe(e.getMessage());
        }

        try {
            GeolocationResult startResult = reverseGeocodingClient.reverseGeocodeLatLng(tripData.getStartLocationLat().toString() + "," + tripData.getStartLocationLng().toString(), googleApiKey).toCompletableFuture().get();
            String startLocationName = startResult.getResults().get(0).getFormatted_address();
            analyzedTripData.setStartLocationName(startLocationName);
        } catch (InterruptedException | ExecutionException e) {
            log.severe(e.getMessage());
        }
        try {
            GeolocationResult endResult = reverseGeocodingClient.reverseGeocodeLatLng(tripData.getEndLocationLat().toString() + "," + tripData.getEndLocationLng().toString(), googleApiKey).toCompletableFuture().get();
            String endLocationName = endResult.getResults().get(0).getFormatted_address();
            analyzedTripData.setEndLocationName(endLocationName);
        } catch (InterruptedException | ExecutionException e) {
            log.severe(e.getMessage());
        }

        Double comfortLevel = (1 / (averageAcceleration + 0.0001) + 1 / (averageSpeed + 0.0001)) * scoreMultiplier;

        EmotionLevel emotions = EmotionLevel.UNKNOWN;
        try {
            int emotionLevel = Integer.parseInt(answers.get(0).getAnswer());

            switch (emotionLevel) {
                case 1 -> emotions = EmotionLevel.HAPPY;
                case 2 -> emotions = EmotionLevel.CALM;
                case 3 -> emotions = EmotionLevel.STRESSED;
                case 4 -> emotions = EmotionLevel.SAD;
                case 5 -> emotions = EmotionLevel.ANGRY;
                case 6 -> emotions = EmotionLevel.FRIGHTENED;
            }
        } catch (Exception e) {
            log.severe("Invalid emotionLevel answer with the value: " + answers.get(0).getAnswer());
        }

        analyzedTripData.setUserId(tripData.getUserId());
        analyzedTripData.setTripId(tripData.getId());

        analyzedTripData.setStartLocationLat(tripData.getStartLocationLat());
        analyzedTripData.setStartLocationLng(tripData.getStartLocationLng());

        analyzedTripData.setEndLocationLat(tripData.getEndLocationLat());
        analyzedTripData.setEndLocationLng(tripData.getEndLocationLng());
        analyzedTripData.setStartTime(tripData.getStartTime());
        analyzedTripData.setEndTime(tripData.getEndTime());
        analyzedTripData.setAverageSpeed(averageSpeed);
        analyzedTripData.setMaxSpeed(finalMaxSpeed);
        analyzedTripData.setAverageAcceleration(averageAcceleration);
        analyzedTripData.setMaxAcceleration(finalMaxAcceleration);
        analyzedTripData.setDistance(finalDistance);
        analyzedTripData.setComfortLevel(comfortLevel);
        analyzedTripData.setEmotions(emotions);
        analyzedTripData.setLocations(analyzedLocations);


        analyzedTripDataBean.createAnalyzedTripData(analyzedTripData);
        log.info("Trip processed");
    }

    @Override
    public void run() {
        process();
    }
}
