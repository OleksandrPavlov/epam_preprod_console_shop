package com.epam.preprod.Pavlov.service.impl;

import com.epam.preprod.Pavlov.entity.Order;
import com.epam.preprod.Pavlov.exception.CheckoutException;
import com.epam.preprod.Pavlov.repository.IOrderRepository;
import com.epam.preprod.Pavlov.repository.ProductRepository;
import com.epam.preprod.Pavlov.service.IOrderService;
import com.epam.preprod.Pavlov.util.constants.InvitationConstants;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;

public class OrderServiceImpl<P> implements IOrderService<P> {
    private ProductRepository<P> productRepository;
    private IOrderRepository<P> orderRepository;

    public OrderServiceImpl(ProductRepository<P> productRepository, IOrderRepository<P> orderRepository) {
        this.productRepository = productRepository;
        this.orderRepository = orderRepository;
    }

    @Override
    public void submitAnOrder(List<Integer> identifierList, LocalDateTime offerTime) throws CheckoutException {
        List<P> productList = new ArrayList<>();
        for (Integer identifier : identifierList) {
            P product = productRepository.getProductById(identifier);
            if (Objects.isNull(product)) {
                throw new CheckoutException(InvitationConstants.INSERT_PRODUCT_ID);
            }
            productList.add(product);
        }
        Order<P> order = new Order<P>(offerTime, productList);
        orderRepository.addOrder(order);
    }

    @Override
    public List<Order<P>> OrderListForRange(LocalDateTime before, LocalDateTime after) {
        TreeMap<LocalDateTime, Order<P>> orderMap = orderRepository.getAllOrders();
        List<Order<P>> orderList = orderMap.entrySet().
                stream().
                filter(value -> {
                    if (value.getKey().compareTo(before) >= 0 && value.getKey().compareTo(after) <= 0) {
                        return true;
                    }
                    return false;
                }).map(entry -> entry.getValue()).
                collect(Collectors.toList());
        return orderList;
    }

    @Override
    public Order<P> theMostRelevantOrder(LocalDateTime localDateTime) {
        if (orderRepository.getAllOrders().size() == 0) {
            return null;
        }
        List<Order<P>> orderList = orderRepository.
                getAllOrders().entrySet().stream()
                .filter(value -> value.getKey().compareTo(localDateTime) >= 0 ? true : false)
                .map(value -> value.getValue())
                .collect(Collectors.toList());
        if (orderList.size() == 0) {
            return null;
        }
        return orderList.get(0);
    }
}
