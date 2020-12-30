package com.comfortly.tripprocessor.services.producers;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Disposes;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;

public class TripPersistenceProducer {

    @PersistenceUnit(unitName = "trip-catalog-jpa")
    private EntityManagerFactory emfTrip;

    @Produces
    @Named(value = "tripEntityManager")
    @ApplicationScoped
    public EntityManager getTripEntityManager() {
        return emfTrip.createEntityManager();
    }

    public void disposeEntityManager(@Disposes EntityManager entityManager) {

        if (entityManager.isOpen()) {
            entityManager.close();
        }
    }
}
