package com.comfortly.tripprocessor.models.entities.trip;

import com.comfortly.tripprocessor.models.entities.trip.LocationDataEntity;

import javax.persistence.*;
import java.time.Instant;
import java.util.List;

@Entity
@Table(name = "trip_data")
@NamedQueries(value =
        {
                @NamedQuery(name = "TripDataEntity.getAll",
                        query = "SELECT trip FROM TripDataEntity trip")
        })
public class TripDataEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "user_id")
    private String userId;

    @Column(name = "start_location_lat")
    private Double startLocationLat;

    @Column(name = "start_location_lng")
    private Double startLocationLng;

    @Column(name = "end_location_lat")
    private Double endLocationLat;

    @Column(name = "end_location_lng")
    private Double endLocationLng;

    @Column(name = "start_time")
    private Instant startTime;

    @Column(name = "end_time")
    private Instant endTime;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "trip_id")
    private List<LocationDataEntity> locations;

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

    public List<LocationDataEntity> getLocations() {
        return locations;
    }

    public void setLocations(List<LocationDataEntity> locations) {
        this.locations = locations;
    }
}