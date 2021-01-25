package com.epam.preprod.Pavlov.entity;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.Objects;

public abstract class Plane extends Transport {
    private double flyHeight;

    public Plane() {

    }

    public Plane(String name, int id, double carrying, double price, double flyHeight) {
        super(name, id, carrying, price);
        this.flyHeight = flyHeight;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Plane plane = (Plane) o;
        return new EqualsBuilder()
                .appendSuper(super.equals(o))
                .append(flyHeight, plane.flyHeight)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), flyHeight);
    }

    public double getFlyHeight() {
        return flyHeight;
    }

    public void setFlyHeight(double flyHeight) {
        this.flyHeight = flyHeight;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this)
                .appendSuper(super.toString())
                .append("fly height", flyHeight)
                .toString();
    }
}
