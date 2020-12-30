package com.comfortly.tripprocessor.models.entities.analyzedtrip;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "analyzed_trip_data")
@NamedQueries(value =
        {
                @NamedQuery(name = "AnalyzedTripDataEntity.getAll",
                        query = "SELECT analyzed_trip FROM AnalyzedTripDataEntity analyzed_trip")
        })
public class AnalyzedTripDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "start_location_name")
    private String startLocationName;

    @Column(name = "start_location_lat")
    private Double startLocationLat;

    @Column(name = "start_location_lng")
    private Double startLocationLng;

    @Column(name = "end_location_name")
    private String endLocationName;

    @Column(name = "end_location_lat")
    private Double endLocationLat;

    @Column(name = "end_location_lng")
    private Double endLocationLng;

    @Column(name = "start_time")
    private Instant startTime;

    @Column(name = "end_time")
    private Instant endTime;

    @Column(name = "average_speed")
    private Double averageSpeed;

    @Column(name = "max_speed")
    private Double maxSpeed;

    @Column(name = "average_acceleration")
    private Double averageAcceleration;

    @Column(name = "max_acceleration")
    private Double maxAcceleration;

    @Column(name = "distance")
    private Double distance;

    @Column(name = "comfort_level")
    private Double comfortLevel;

    @Column(name = "emotions")
    private String emotions;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "analyzed_trip_id")
    private List<AnalyzedLocationDataEntity> locations;

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

    public String getEmotions() {
        return emotions;
    }

    public void setEmotions(String emotions) {
        this.emotions = emotions;
    }

    public List<AnalyzedLocationDataEntity> getLocations() {
        return locations;
    }

    public void setLocations(List<AnalyzedLocationDataEntity> locations) {
        this.locations = locations;
    }
}