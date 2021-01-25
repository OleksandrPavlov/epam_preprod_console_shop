package com.epam.preprod.Pavlov.service;

import com.epam.preprod.Pavlov.entity.Order;
import com.epam.preprod.Pavlov.exception.CheckoutException;

import java.time.LocalDateTime;
import java.util.List;


public interface IOrderService<P> {

    void submitAnOrder(List<Integer> identifierList, LocalDateTime offerTime) throws CheckoutException;

    List<Order<P>> OrderListForRange(LocalDateTime before, LocalDateTime after);

    Order<P> theMostRelevantOrder(LocalDateTime localDateTime);
}
