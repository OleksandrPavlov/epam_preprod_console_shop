package com.epam.preprod.Pavlov.creator.impl;

import com.epam.preprod.Pavlov.entity.Car;
import com.epam.preprod.Pavlov.maker.IMaker;
import com.epam.preprod.Pavlov.util.constants.Constants;

import java.util.ResourceBundle;

public abstract class CarCreator extends TransportCreator {
    private IMaker maker;
    protected ResourceBundle resourceBundle;

    public CarCreator(IMaker maker, ResourceBundle resourceBundle) {
        this.maker = maker;
        this.resourceBundle = resourceBundle;
    }

    public void setFields(Car car) {
        car.setId(maker.getInt(resourceBundle.getString(Constants.TRANSPORT_ID)));
        car.setName(maker.getString(resourceBundle.getString(Constants.TRANSPORT_NAME)));
        car.setMileage(maker.getDouble(resourceBundle.getString(Constants.TRANSPORT_MILEAGE)));
        car.setEngineCapacity(maker.getDouble(resourceBundle.getString(Constants.TRANSPORT_ENGINE_CAPACITY)));
        car.setMaxSpeed(maker.getDouble(resourceBundle.getString(Constants.TRANSPORT_MAX_SPEED)));
        car.setCarrying(maker.getDouble(resourceBundle.getString(Constants.TRANSPORT_CARRYING)));
        car.setPrice(maker.getDouble(resourceBundle.getString(Constants.TRANSPORT_PRICE)));
    }
}
