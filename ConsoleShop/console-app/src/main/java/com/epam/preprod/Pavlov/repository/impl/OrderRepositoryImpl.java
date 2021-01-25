package com.epam.preprod.Pavlov.repository.impl;

import com.epam.preprod.Pavlov.entity.Order;
import com.epam.preprod.Pavlov.repository.IOrderRepository;

import java.time.LocalDateTime;
import java.util.TreeMap;

public class OrderRepositoryImpl<P> implements IOrderRepository<P> {
    private TreeMap<LocalDateTime, Order<P>> orderTable;

    public OrderRepositoryImpl() {
        this.orderTable = new TreeMap<>();
    }

    @Override
    public void addOrder(Order<P> order) {
        orderTable.put(order.getLocalDateTime(), order);
    }

    @Override
    public TreeMap<LocalDateTime, Order<P>> getAllOrders() {
        return orderTable;
    }
}
