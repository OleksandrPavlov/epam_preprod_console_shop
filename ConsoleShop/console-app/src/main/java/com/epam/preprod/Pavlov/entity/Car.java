package com.epam.preprod.Pavlov.entity;

import com.epam.preprod.Pavlov.annotation.Invitation;
import com.epam.preprod.Pavlov.util.constants.Constants;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.util.Objects;

public class Car extends Transport {
    private double mileage;

    private double maxSpeed;

    private double engineCapacity;

    public Car() {

    }

    public Car(String name, int id, double carrying, double price, double mileage, double maxSpeed, double engineCapacity) {
        super(name, id, carrying, price);
        this.mileage = mileage;
        this.maxSpeed = maxSpeed;
        this.engineCapacity = engineCapacity;
    }

    public double getMileage() {
        return mileage;
    }

    @Invitation(keyName = Constants.TRANSPORT_MILEAGE, order = 5)
    public void setMileage(double mileage) {
        this.mileage = mileage;
    }

    public double getMaxSpeed() {
        return maxSpeed;
    }

    @Invitation(keyName = Constants.TRANSPORT_MAX_SPEED, order = 6)
    public void setMaxSpeed(double maxSpeed) {
        this.maxSpeed = maxSpeed;
    }

    public double getEngineCapacity() {
        return engineCapacity;
    }

    @Invitation(keyName = Constants.TRANSPORT_ENGINE_CAPACITY, order = 7)
    public void setEngineCapacity(double engineCapacity) {
        this.engineCapacity = engineCapacity;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .appendSuper(super.toString())
                .append("mileage", mileage)
                .append("maximal speed", maxSpeed)
                .append("engine capacity", engineCapacity).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Car car = (Car) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(mileage, (car).mileage)
                .append(maxSpeed, car.maxSpeed)
                .append(engineCapacity, car.engineCapacity).isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), mileage, maxSpeed, engineCapacity);
    }
}
