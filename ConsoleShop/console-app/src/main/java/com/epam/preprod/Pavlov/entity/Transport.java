package com.epam.preprod.Pavlov.entity;

import com.epam.preprod.Pavlov.annotation.Invitation;
import com.epam.preprod.Pavlov.util.constants.Constants;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.io.Serializable;
import java.util.Objects;

public abstract class Transport implements Serializable {

    private String name;

    private int id;

    private double carrying;

    private double price;

    public int getId() {
        return id;
    }

    @Invitation(keyName = Constants.TRANSPORT_ID, order = 1)
    public void setId(int id) {
        this.id = id;
    }


    public Transport() {
    }

    public Transport(String name, int id, double carrying, double price) {
        this.name = name;
        this.id = id;
        this.carrying = carrying;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    @Invitation(keyName = Constants.TRANSPORT_NAME, order = 2)
    public void setName(String name) {
        this.name = name;
    }

    public double getCarrying() {
        return carrying;
    }

    @Invitation(keyName = Constants.TRANSPORT_CARRYING, order = 3)
    public void setCarrying(double carrying) {
        this.carrying = carrying;
    }


    @Override
    public boolean equals(Object o) {
        boolean result;
        if (this == o) {
            result = true;
        } else if (o == null || getClass() != o.getClass()) {
            result = false;
        } else {
            Transport transport = (Transport) o;
            return new EqualsBuilder()
                    .append(carrying, transport.carrying)
                    .append(id, transport.id)
                    .append(name, transport.name)
                    .append(price, transport.price)
                    .isEquals();
        }
        return result;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, carrying);
    }

    public double getPrice() {
        return price;
    }

    @Invitation(keyName = Constants.TRANSPORT_PRICE, order = 4)
    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("name", name)
                .append("carrying", carrying)
                .append("price", price)
                .toString();
    }

}
