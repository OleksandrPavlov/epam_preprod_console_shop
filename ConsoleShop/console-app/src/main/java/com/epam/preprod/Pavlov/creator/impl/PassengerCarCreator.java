package com.epam.preprod.Pavlov.creator.impl;

import com.epam.preprod.Pavlov.entity.PassengerCar;
import com.epam.preprod.Pavlov.entity.Transport;
import com.epam.preprod.Pavlov.maker.IMaker;
import com.epam.preprod.Pavlov.util.constants.Constants;

import java.util.ResourceBundle;

public class PassengerCarCreator extends CarCreator {
    private IMaker maker;
    private ResourceBundle resourceBundle;

    public PassengerCarCreator(IMaker maker, ResourceBundle resourceBundle) {
        super(maker, resourceBundle);
        this.maker = maker;
        this.resourceBundle = resourceBundle;
    }

    @Override
    public Transport create() {
        PassengerCar passengerCar = new PassengerCar();
        setFields(passengerCar);
        return passengerCar;
    }

    protected void setFields(PassengerCar passengerCar) {
        super.setFields(passengerCar);
        passengerCar.setPassengerQuantity(maker.getInt(resourceBundle.getString(Constants.TRANSPORT_PASS_QUANTITY)));
    }
}
