package com.comfortly.tripprocessor.lib.trip;

import java.time.Instant;
import java.util.List;

public class TripData {
    private Integer id;
    private String userId;
    private Double startLocationLat;
    private Double startLocationLng;
    private Double endLocationLat;
    private Double endLocationLng;
    private Instant startTime;
    private Instant endTime;
    private List<LocationData> locations;

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

    public List<LocationData> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationData> location) {
        this.locations = location;
    }
}
