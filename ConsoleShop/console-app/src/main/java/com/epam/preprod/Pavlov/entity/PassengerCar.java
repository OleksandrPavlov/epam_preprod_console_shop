package com.epam.preprod.Pavlov.entity;

import com.epam.preprod.Pavlov.annotation.Invitation;
import com.epam.preprod.Pavlov.util.constants.Constants;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

public class PassengerCar extends Car {

    private int passengerQuantity;

    public PassengerCar() {

    }

    public PassengerCar(String name, int id, double carrying, double price, double mileage, double maxSpeed, double engineCapacity, int passengerQuantity) {
        super(name, id, carrying, price, mileage, maxSpeed, engineCapacity);
        this.passengerQuantity = passengerQuantity;
    }

    public int getPassengerQuantity() {
        return passengerQuantity;
    }

    @Invitation(keyName = Constants.TRANSPORT_PASS_QUANTITY, order = 8)
    public void setPassengerQuantity(int passengerQuantity) {
        this.passengerQuantity = passengerQuantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        PassengerCar that = (PassengerCar) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(passengerQuantity, that.passengerQuantity)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), passengerQuantity);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .appendSuper(super.toString())
                .append("passenger quantity", passengerQuantity)
                .toString();
    }
}
