package com.comfortly.tripprocessor.models.converters;

import com.comfortly.tripprocessor.lib.answer.AnswerData;
import com.comfortly.tripprocessor.lib.answer.QuestionType;
import com.comfortly.tripprocessor.models.entities.answer.AnswerDataEntity;

public class AnswerDataConverter {

    public static AnswerData toDto(AnswerDataEntity entity) {

        AnswerData dto = new AnswerData();
        dto.setId(entity.getId());
        dto.setTripId(entity.getTripId());
        dto.setUserId(entity.getUserId());
        dto.setQuestionId(entity.getQuestionId());
        dto.setQuestion(entity.getQuestion());
        dto.setAnswer(entity.getAnswer());
        QuestionType questionType = QuestionType.UNKNOWN;
        for (QuestionType type : QuestionType.values()) {
            if (type.toString().equals(entity.getType())) {
                questionType = type;
                break;
            }
        }
        dto.setType(questionType);
        dto.setTimestamp(entity.getTimestamp());

        return dto;
    }

    public static AnswerDataEntity toEntity(AnswerData dto) {

        AnswerDataEntity entity = new AnswerDataEntity();
        entity.setTripId(dto.getTripId());
        entity.setId(dto.getId());
        entity.setUserId(dto.getUserId());
        entity.setQuestionId(dto.getQuestionId());
        entity.setQuestion(dto.getQuestion());
        entity.setType(dto.getType().toString());
        entity.setAnswer(dto.getAnswer());
        entity.setTimestamp(dto.getTimestamp());

        return entity;
    }

}
