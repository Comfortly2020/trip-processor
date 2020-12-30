package com.comfortly.tripprocessor.models.converters;

import com.comfortly.tripprocessor.lib.analyzedtrip.AnalyzedLocationData;
import com.comfortly.tripprocessor.models.entities.analyzedtrip.AnalyzedLocationDataEntity;

public class AnalyzedLocationDataConverter {

    public static AnalyzedLocationData toDto(AnalyzedLocationDataEntity entity) {

        AnalyzedLocationData dto = new AnalyzedLocationData();
        dto.setId(entity.getId());
        dto.setLocationLat(entity.getLocationLat());
        dto.setSnappedLocationLat(entity.getSnappedLocationLat());
        dto.setLocationLng(entity.getLocationLng());
        dto.setSnappedLocationLng(entity.getSnappedLocationLng());
        dto.setSpeed(entity.getSpeed());
        dto.setAcceleration(entity.getAcceleration());
        dto.setOrientation(entity.getOrientation());
        dto.setTimestamp(entity.getTimestamp());

        return dto;
    }

    public static AnalyzedLocationDataEntity toEntity(AnalyzedLocationData dto) {

        AnalyzedLocationDataEntity entity = new AnalyzedLocationDataEntity();
        entity.setId(dto.getId());
        entity.setLocationLat(dto.getLocationLat());
        entity.setSnappedLocationLat(dto.getSnappedLocationLat());
        entity.setLocationLng(dto.getLocationLng());
        entity.setSnappedLocationLng(dto.getSnappedLocationLng());
        entity.setSpeed(dto.getSpeed());
        entity.setAcceleration(dto.getAcceleration());
        entity.setOrientation(dto.getOrientation());
        entity.setTimestamp(dto.getTimestamp());

        return entity;
    }

}
