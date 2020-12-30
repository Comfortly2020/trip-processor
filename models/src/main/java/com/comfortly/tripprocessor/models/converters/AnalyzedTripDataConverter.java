package com.comfortly.tripprocessor.models.converters;

import com.comfortly.tripprocessor.lib.analyzedtrip.AnalyzedTripData;
import com.comfortly.tripprocessor.lib.analyzedtrip.EmotionLevel;
import com.comfortly.tripprocessor.models.entities.analyzedtrip.AnalyzedTripDataEntity;

import java.util.stream.Collectors;

public class AnalyzedTripDataConverter {

    public static AnalyzedTripData toDto(AnalyzedTripDataEntity entity) {

        AnalyzedTripData dto = new AnalyzedTripData();
        dto.setId(entity.getId());
        dto.setUserId(entity.getUserId());
        dto.setStartLocationName(entity.getStartLocationName());
        dto.setStartLocationLat(entity.getStartLocationLat());
        dto.setStartLocationLng(entity.getStartLocationLng());
        dto.setEndLocationName(entity.getEndLocationName());
        dto.setEndLocationLat(entity.getEndLocationLat());
        dto.setEndLocationLng(entity.getEndLocationLng());
        dto.setStartTime(entity.getStartTime());
        dto.setEndTime(entity.getEndTime());
        dto.setAverageSpeed(entity.getAverageSpeed());
        dto.setMaxSpeed(entity.getMaxSpeed());
        dto.setAverageAcceleration(entity.getAverageAcceleration());
        dto.setMaxAcceleration(entity.getMaxAcceleration());
        dto.setDistance(entity.getDistance());
        dto.setComfortLevel(entity.getComfortLevel());
        EmotionLevel emotionLevel = EmotionLevel.UNKNOWN;
        for (EmotionLevel type : EmotionLevel.values()) {
            if (type.toString().equals(entity.getEmotions())) {
                emotionLevel = type;
                break;
            }
        }
        dto.setEmotions(emotionLevel);
        dto.setLocations(entity.getLocations().stream().map(AnalyzedLocationDataConverter::toDto).collect(Collectors.toList()));

        return dto;
    }

    public static AnalyzedTripDataEntity toEntity(AnalyzedTripData dto) {

        AnalyzedTripDataEntity entity = new AnalyzedTripDataEntity();
        entity.setUserId(dto.getUserId());
        entity.setStartLocationName(dto.getStartLocationName());
        entity.setStartLocationLat(dto.getStartLocationLat());
        entity.setStartLocationLng(dto.getStartLocationLng());
        entity.setEndLocationName(dto.getEndLocationName());
        entity.setEndLocationLat(dto.getEndLocationLat());
        entity.setEndLocationLng(dto.getEndLocationLng());
        entity.setStartTime(dto.getStartTime());
        entity.setEndTime(dto.getEndTime());
        entity.setAverageSpeed(dto.getAverageSpeed());
        entity.setMaxSpeed(dto.getMaxSpeed());
        entity.setAverageAcceleration(dto.getAverageAcceleration());
        entity.setMaxAcceleration(dto.getMaxAcceleration());
        entity.setDistance(dto.getDistance());
        entity.setComfortLevel(dto.getComfortLevel());
        entity.setEmotions(dto.getEmotions().toString());
        entity.setLocations(dto.getLocations().stream().map(AnalyzedLocationDataConverter::toEntity).collect(Collectors.toList()));

        return entity;
    }

}
