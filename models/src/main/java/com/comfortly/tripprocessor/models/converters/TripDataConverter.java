package com.comfortly.tripprocessor.models.converters;

import com.comfortly.tripprocessor.lib.trip.TripData;
import com.comfortly.tripprocessor.models.entities.trip.TripDataEntity;

import java.util.stream.Collectors;

public class TripDataConverter {

    public static TripData toDto(TripDataEntity entity) {

        TripData dto = new TripData();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setStartLocationLat(entity.getStartLocationLat());
        dto.setStartLocationLng(entity.getStartLocationLng());
        dto.setEndLocationLat(entity.getEndLocationLat());
        dto.setEndLocationLng(entity.getEndLocationLng());
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());
        dto.setLocations(entity.getLocations().stream().map(LocationDataConverter::toDto).collect(Collectors.toList()));

        return dto;
    }

    public static TripDataEntity toEntity(TripData dto) {

        TripDataEntity entity = new TripDataEntity();
        entity.setUserId(dto.getUserId());
        entity.setStartLocationLat(dto.getStartLocationLat());
        entity.setStartLocationLng(dto.getStartLocationLng());
        entity.setEndLocationLat(dto.getEndLocationLat());
        entity.setEndLocationLng(dto.getEndLocationLng());
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());
        entity.setLocations(dto.getLocations().stream().map(LocationDataConverter::toEntity).collect(Collectors.toList()));

        return entity;
    }

}
