package com.comfortly.tripprocessor.models.entities.analyzedtrip;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table(name = "analyzed_location_data")
public class AnalyzedLocationDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "location_lat")
    private Double locationLat;

    @Column(name = "snapped_location_lat")
    private Double snappedLocationLat;

    @Column(name = "location_lng")
    private Double locationLng;

    @Column(name = "snapped_location_lng")
    private Double snappedLocationLng;

    @Column(name = "speed")
    private Double speed;

    @Column(name = "acceleration")
    private Double acceleration;

    @Column(name = "orientation")
    private Double orientation;

    @Column(name = "timestamp")
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