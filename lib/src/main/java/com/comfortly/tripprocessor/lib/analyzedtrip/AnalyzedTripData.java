package com.comfortly.tripprocessor.lib.analyzedtrip;

import java.time.Instant;
import java.util.List;

public class AnalyzedTripData {
    private Integer id;
    private String userId;
    private String startLocationName;
    private Double startLocationLat;
    private Double startLocationLng;
    private String endLocationName;
    private Double endLocationLat;
    private Double endLocationLng;
    private Instant startTime;
    private Instant endTime;
    private Double averageSpeed;
    private Double maxSpeed;
    private Double averageAcceleration;
    private Double maxAcceleration;
    private Double distance;
    private Double comfortLevel;
    private EmotionLevel emotions;
    private List<AnalyzedLocationData> locations;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStartLocationName() {
        return startLocationName;
    }

    public void setStartLocationName(String startLocationName) {
        this.startLocationName = startLocationName;
    }

    public Double getStartLocationLat() {
        return startLocationLat;
    }

    public void setStartLocationLat(Double startLocationLat) {
        this.startLocationLat = startLocationLat;
    }

    public Double getStartLocationLng() {
        return startLocationLng;
    }

    public void setStartLocationLng(Double startLocationLng) {
        this.startLocationLng = startLocationLng;
    }

    public String getEndLocationName() {
        return endLocationName;
    }

    public void setEndLocationName(String endLocationName) {
        this.endLocationName = endLocationName;
    }

    public Double getEndLocationLat() {
        return endLocationLat;
    }

    public void setEndLocationLat(Double endLocationLat) {
        this.endLocationLat = endLocationLat;
    }

    public Double getEndLocationLng() {
        return endLocationLng;
    }

    public void setEndLocationLng(Double endLocationLng) {
        this.endLocationLng = endLocationLng;
    }

    public Instant getStartTime() {
        return startTime;
    }

    public void setStartTime(Instant startTime) {
        this.startTime = startTime;
    }

    public Instant getEndTime() {
        return endTime;
    }

    public void setEndTime(Instant endTime) {
        this.endTime = endTime;
    }

    public Double getAverageSpeed() {
        return averageSpeed;
    }

    public void setAverageSpeed(Double averageSpeed) {
        this.averageSpeed = averageSpeed;
    }

    public Double getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(Double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public Double getAverageAcceleration() {
        return averageAcceleration;
    }

    public void setAverageAcceleration(Double averageAcceleration) {
        this.averageAcceleration = averageAcceleration;
    }

    public Double getMaxAcceleration() {
        return maxAcceleration;
    }

    public void setMaxAcceleration(Double maxAcceleration) {
        this.maxAcceleration = maxAcceleration;
    }

    public Double getDistance() {
        return distance;
    }

    public void setDistance(Double distance) {
        this.distance = distance;
    }

    public Double getComfortLevel() {
        return comfortLevel;
    }

    public void setComfortLevel(Double comfortLevel) {
        this.comfortLevel = comfortLevel;
    }

    public EmotionLevel getEmotions() {
        return emotions;
    }

    public void setEmotions(EmotionLevel emotions) {
        this.emotions = emotions;
    }

    public List<AnalyzedLocationData> getLocations() {
        return locations;
    }

    public void setLocations(List<AnalyzedLocationData> locations) {
        this.locations = locations;
    }
}
