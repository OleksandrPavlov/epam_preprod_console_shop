package com.epam.preprod.Pavlov.repository;

import com.epam.preprod.Pavlov.entity.Order;

import java.time.LocalDateTime;
import java.util.TreeMap;

public interface IOrderRepository<P> {
    void addOrder(Order<P> order);

    TreeMap<LocalDateTime, Order<P>> getAllOrders();
}
