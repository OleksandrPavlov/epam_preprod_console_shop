package com.epam.preprod.pavlov.transport;


public interface ITransport {
    String getName();

    void setName(String name);

    double getCarrying();

    void setCarrying(double carrying);

    double getPrice();

    void setPrice(double price);

    int getId();

    void setId(int id);
}
