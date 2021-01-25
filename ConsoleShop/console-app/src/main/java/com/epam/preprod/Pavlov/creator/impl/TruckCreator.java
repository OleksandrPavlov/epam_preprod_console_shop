package com.epam.preprod.Pavlov.creator.impl;

import com.epam.preprod.Pavlov.entity.Transport;
import com.epam.preprod.Pavlov.entity.Truck;
import com.epam.preprod.Pavlov.maker.IMaker;
import com.epam.preprod.Pavlov.util.constants.Constants;

import java.util.ResourceBundle;

public class TruckCreator extends CarCreator {
    private IMaker maker;
    private ResourceBundle resourceBundle;

    public TruckCreator(IMaker maker, ResourceBundle resourceBundle) {
        super(maker, resourceBundle);
        this.maker = maker;
        this.resourceBundle = resourceBundle;
    }

    @Override
    public Transport create() {
        Truck truck = new Truck();
        setFields(truck);
        return truck;
    }

    protected void setFields(Truck truck) {
        super.setFields(truck);
        truck.setCargoCompartmentVolume(maker.getDouble(resourceBundle.getString(Constants.TRANSPORT_CARGO_COM_VOL)));
    }
}
