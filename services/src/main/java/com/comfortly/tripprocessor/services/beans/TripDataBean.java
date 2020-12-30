package com.comfortly.tripprocessor.services.beans;

import com.comfortly.tripprocessor.lib.trip.TripData;
import com.comfortly.tripprocessor.models.converters.TripDataConverter;
import com.comfortly.tripprocessor.models.entities.trip.TripDataEntity;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.ws.rs.NotFoundException;
import java.util.logging.Logger;


@RequestScoped
public class TripDataBean {

    private Logger log = Logger.getLogger(TripDataBean.class.getName());

    @Inject
    @Named(value = "tripEntityManager")
    private EntityManager emTrip;

    public TripData getTripData(Integer id) {

        TripDataEntity tripDataEntity = emTrip.find(TripDataEntity.class, id);

        if (tripDataEntity == null) {
            throw new NotFoundException();
        }

        TripData tripData = TripDataConverter.toDto(tripDataEntity);

        return tripData;
    }

    private void beginTx() {
        if (!emTrip.getTransaction().isActive()) {
            emTrip.getTransaction().begin();
        }
    }

    private void commitTx() {
        if (emTrip.getTransaction().isActive()) {
            emTrip.getTransaction().commit();
        }
    }

    private void rollbackTx() {
        if (emTrip.getTransaction().isActive()) {
            emTrip.getTransaction().rollback();
        }
    }
}
