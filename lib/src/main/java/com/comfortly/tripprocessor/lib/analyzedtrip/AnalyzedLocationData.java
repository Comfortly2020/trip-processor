package com.comfortly.tripprocessor.lib.analyzedtrip;

import java.time.Instant;

public class AnalyzedLocationData {
    private Integer id;
    private Double locationLat;
    private Double snappedLocationLat;
    private Double locationLng;
    private Double snappedLocationLng;
    private Double speed;
    private Double acceleration;
    private Double orientation;
    private Instant timestamp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Double getLocationLat() {
        return locationLat;
    }

    public void setLocationLat(Double locationLat) {
        this.locationLat = locationLat;
    }

    public Double getSnappedLocationLat() {
        return snappedLocationLat;
    }

    public void setSnappedLocationLat(Double snappedLocationLat) {
        this.snappedLocationLat = snappedLocationLat;
    }

    public Double getLocationLng() {
        return locationLng;
    }

    public void setLocationLng(Double locationLng) {
        this.locationLng = locationLng;
    }

    public Double getSnappedLocationLng() {
        return snappedLocationLng;
    }

    public void setSnappedLocationLng(Double snappedLocationLng) {
        this.snappedLocationLng = snappedLocationLng;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(Double acceleration) {
        this.acceleration = acceleration;
    }

    public Double getOrientation() {
        return orientation;
    }

    public void setOrientation(Double orientation) {
        this.orientation = orientation;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}

