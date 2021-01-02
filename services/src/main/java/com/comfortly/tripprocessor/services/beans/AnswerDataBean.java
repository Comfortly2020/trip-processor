package com.comfortly.tripprocessor.services.beans;

import com.comfortly.tripprocessor.lib.answer.AnswerData;
import com.comfortly.tripprocessor.models.converters.AnswerDataConverter;
import com.comfortly.tripprocessor.models.entities.answer.AnswerDataEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@ApplicationScoped
public class AnswerDataBean {

    private Logger log = Logger.getLogger(AnswerDataBean.class.getName());

    @Inject
    @Named(value = "answerEntityManager")
    private EntityManager emAnswers;

    public List<AnswerData> getAnswersData(String userId, Integer tripId) {

        TypedQuery<AnswerDataEntity> query = emAnswers.createQuery("SELECT t FROM AnswerDataEntity t WHERE t.userId = :user AND t.tripId = :trip", AnswerDataEntity.class);
        query.setParameter("user", userId);
        query.setParameter("trip", tripId);

        return query.getResultList().stream()
                .map(AnswerDataConverter::toDto).collect(Collectors.toList());
    }

    private void beginTx() {
        if (!emAnswers.getTransaction().isActive()) {
            emAnswers.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (emAnswers.getTransaction().isActive()) {
            emAnswers.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (emAnswers.getTransaction().isActive()) {
            emAnswers.getTransaction().rollback();
        }
    }
}
