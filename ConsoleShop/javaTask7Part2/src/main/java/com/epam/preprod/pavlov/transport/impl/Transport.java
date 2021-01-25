package com.epam.preprod.pavlov.transport.impl;


import com.epam.preprod.pavlov.transport.ITransport;

import java.util.Objects;


public class Transport implements ITransport {

    private String name;

    private int id;

    private double carrying;

    private double price;

    public int getId() {
        return id;
    }


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


    public void setName(String name) {
        this.name = name;
    }

    public double getCarrying() {
        return carrying;
    }


    public void setCarrying(double carrying) {
        this.carrying = carrying;
    }

    @Override
    public String toString() {
        return "id: " + id + " name: " + name + " carrying: " + carrying + " price: " + price;
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
            result = Double.compare(transport.carrying, carrying) == 0 &&
                    Objects.equals(name, transport.name);
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

    public void setPrice(double price) {
        this.price = price;
    }


}
