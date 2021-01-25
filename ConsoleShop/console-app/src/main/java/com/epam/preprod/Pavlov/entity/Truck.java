package com.epam.preprod.Pavlov.entity;

import com.epam.preprod.Pavlov.annotation.Invitation;
import com.epam.preprod.Pavlov.util.constants.Constants;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

public class Truck extends Car {

    private double cargoCompartmentVolume;

    public Truck() {

    }

    public Truck(String name, int id, double carrying, double price, double mileage, double maxSpeed, double engineCapacity, double cargoCompartmentVolume) {
        super(name, id, carrying, price, mileage, maxSpeed, engineCapacity);
        this.cargoCompartmentVolume = cargoCompartmentVolume;
    }

    public double getCargoCompartmentVolume() {
        return cargoCompartmentVolume;
    }

    @Invitation(keyName = Constants.TRANSPORT_CARGO_COM_VOL, order = 8)
    public void setCargoCompartmentVolume(double cargoCompartmentVolume) {
        this.cargoCompartmentVolume = cargoCompartmentVolume;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Truck truck = (Truck) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(cargoCompartmentVolume, truck.cargoCompartmentVolume)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), cargoCompartmentVolume);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .appendSuper(super.toString())
                .append("cargo compartment volume", cargoCompartmentVolume)
                .toString();
    }
}
