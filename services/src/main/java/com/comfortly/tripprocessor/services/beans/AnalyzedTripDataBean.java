package com.comfortly.tripprocessor.services.beans;

import com.comfortly.tripprocessor.lib.analyzedtrip.AnalyzedTripData;
import com.comfortly.tripprocessor.models.converters.AnalyzedTripDataConverter;
import com.comfortly.tripprocessor.models.entities.analyzedtrip.AnalyzedTripDataEntity;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import java.util.logging.Logger;

@ApplicationScoped
public class AnalyzedTripDataBean {

    private Logger log = Logger.getLogger(AnalyzedTripDataBean.class.getName());

    @Inject
    @Named(value = "analyzedTripEntityManager")
    private EntityManager emAnalyzedTrip;

    public AnalyzedTripData createAnalyzedTripData(AnalyzedTripData analyzedTripData) {

        AnalyzedTripDataEntity analyzedTripDataEntity = AnalyzedTripDataConverter.toEntity(analyzedTripData);

        try {
            beginTx();
            emAnalyzedTrip.persist(analyzedTripDataEntity);
            commitTx();
        } catch (Exception e) {
            rollbackTx();
        }

        if (analyzedTripDataEntity.getId() == null) {
            throw new RuntimeException("Entity was not persisted");
        }

        return AnalyzedTripDataConverter.toDto(analyzedTripDataEntity);
    }

    private void beginTx() {
        if (!emAnalyzedTrip.getTransaction().isActive()) {
            emAnalyzedTrip.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (emAnalyzedTrip.getTransaction().isActive()) {
            emAnalyzedTrip.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (emAnalyzedTrip.getTransaction().isActive()) {
            emAnalyzedTrip.getTransaction().rollback();
        }
    }
}
