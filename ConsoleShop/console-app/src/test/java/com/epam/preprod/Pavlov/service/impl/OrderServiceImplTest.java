package com.epam.preprod.Pavlov.service.impl;

import com.epam.preprod.Pavlov.entity.Order;
import com.epam.preprod.Pavlov.entity.PassengerCar;
import com.epam.preprod.Pavlov.entity.Transport;
import com.epam.preprod.Pavlov.exception.CheckoutException;
import com.epam.preprod.Pavlov.repository.IOrderRepository;
import com.epam.preprod.Pavlov.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.expectLastCall;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class OrderServiceImplTest {
    private static final PassengerCar PC1 = new PassengerCar("Renault", 6789, 5, 4506, 5044, 170, 1.6, 6);
    private static final PassengerCar PC2 = new PassengerCar("Cherry", 6799, 5, 6780, 5044, 170, 1.6, 6);
    private static final PassengerCar PC3 = new PassengerCar("Mersedes", 6709, 5, 10123, 5044, 170, 1.6, 6);
    private static final PassengerCar PC4 = new PassengerCar("Galushka", 6719, 5, 45678, 5044, 170, 1.6, 6);
    private static final PassengerCar PC5 = new PassengerCar("Niagar", 6739, 5, 7900, 5044, 170, 1.6, 6);
    private static final PassengerCar PC6 = new PassengerCar("Volga", 6739, 5, 7900, 5044, 170, 1.6, 6);
    private static final PassengerCar PC7 = new PassengerCar("Zabava", 6739, 5, 7900, 5044, 170, 1.6, 6);
    private static final LocalDateTime TIME_1 = LocalDateTime.of(2020, 4, 20, 4, 23, 23);
    private static final LocalDateTime TIME_2 = LocalDateTime.of(2020, 4, 21, 4, 23, 23);
    private static final LocalDateTime TIME_3 = LocalDateTime.of(2020, 4, 22, 4, 23, 23);
    private Order<Transport> order1;
    private Order<Transport> order2;
    private Order<Transport> order3;

    private OrderServiceImpl<Transport> orderService;
    private ProductRepository<Transport> productRepository;
    private IOrderRepository<Transport> orderRepository;


    @Before
    public void setUp() throws Exception {
        //Orders initialization
        order1 = new Order<>(TIME_1, Stream.of(PC1, PC1, PC2, PC2, PC3).collect(Collectors.toList()));
        order2 = new Order<>(TIME_2, Stream.of(PC4, PC4, PC6, PC6, PC7).collect(Collectors.toList()));
        order3 = new Order<>(TIME_3, Stream.of(PC5, PC4, PC3, PC2, PC5).collect(Collectors.toList()));
        orderRepository = getOrderRepositoryMock();
        productRepository = getProductRepositoryMock();
        orderService = new OrderServiceImpl<>(productRepository, orderRepository);
    }

    @Test
    public void shouldBeCorrectSignatureAndOrderOfMethodInvokeWhenAddOrderMethodCalled() throws CheckoutException {
        List<Integer> identifierList = order1.getList().stream().map(value -> value.getId()).collect(Collectors.toList());
        expect(productRepository.getProductById(PC1.getId())).andReturn(PC1).times(2);
        expect(productRepository.getProductById(PC2.getId())).andReturn(PC2).times(2);
        expect(productRepository.getProductById(PC3.getId())).andReturn(PC3);
        expectLastCall();
        orderRepository.addOrder(order1);
        replay(productRepository, orderRepository);
        orderService.submitAnOrder(identifierList, TIME_1);
        verify(productRepository, orderRepository);
    }

    @Test(expected = CheckoutException.class)
    public void shouldBeThrownExceptionWhenSubmitAnOrderWithWrongIdentifierCalled() throws CheckoutException {
        expect(productRepository.getProductById(1)).andReturn(null);
        replay(productRepository);
        orderService.submitAnOrder(Stream.of(1).collect(Collectors.toList()), null);
        verify(productRepository);
    }

    @Test
    public void shouldBeCorrectListOfOrdersInAdjustedRangeWhenOrderListForRangeCalled() {
        //initialization of tree map of orders
        TreeMap<LocalDateTime, Order<Transport>> treeMap = new TreeMap<>();
        treeMap.put(order1.getLocalDateTime(), order1);
        treeMap.put(order2.getLocalDateTime(), order2);
        treeMap.put(order3.getLocalDateTime(), order3);
        expect(orderRepository.getAllOrders()).andReturn(treeMap);
        replay(orderRepository);
        List<Order<Transport>> actualList = orderService.OrderListForRange(TIME_2, TIME_3);
        List<Order<Transport>> expectedList = new ArrayList<>();
        expectedList.add(order2);
        expectedList.add(order3);
        assertArrayEquals(expectedList.toArray(), actualList.toArray());
        verify(orderRepository);
    }

    @Test
    public void shouldBeTheMostRelevantOrderWhenTheMostRelevantMethodCaled() {
        TreeMap<LocalDateTime, Order<Transport>> treeMap = new TreeMap<>();
        treeMap.put(order1.getLocalDateTime(), order1);
        treeMap.put(order2.getLocalDateTime(), order2);
        treeMap.put(order3.getLocalDateTime(), order3);
        expect(orderRepository.getAllOrders()).andReturn(treeMap);
        expect(orderRepository.getAllOrders()).andReturn(treeMap);
        replay(orderRepository);

        Order<Transport> actualOrder = orderService.theMostRelevantOrder(TIME_2);
        Order<Transport> expectedOrder = order2;
        assertEquals(expectedOrder, actualOrder);
        verify(orderRepository);
    }

    @Test
    public void shouldBeNullWhenTheMostRelevantOrderCalledOnEmptyOrderRepository() {
        TreeMap<LocalDateTime, Order<Transport>> emptyOrderMap = new TreeMap<>();
        expect(orderRepository.getAllOrders()).andReturn(emptyOrderMap);
        replay(orderRepository);
        Object actualReturn = orderService.theMostRelevantOrder(null);
        assertTrue(Objects.isNull(actualReturn));
        verify(orderRepository);
    }

    @Test
    public void shouldBeNullWhenTheMostRelevantOrderCalledWithUnacceptableRange() {

        TreeMap<LocalDateTime, Order<Transport>> orderMap = new TreeMap<>();
        orderMap.put(TIME_1, order1);
        expect(orderRepository.getAllOrders()).andReturn(orderMap);
        expect(orderRepository.getAllOrders()).andReturn(orderMap);
        replay(orderRepository);
        Object actualReturn = orderService.theMostRelevantOrder(TIME_2);
        assertTrue(Objects.isNull(actualReturn));
        verify(orderRepository);
    }


    private <T> ProductRepository<T> getProductRepositoryMock() {
        return (ProductRepository<T>) mock(ProductRepository.class);
    }

    private <T> IOrderRepository<T> getOrderRepositoryMock() {
        return (IOrderRepository<T>) mock(IOrderRepository.class);
    }
}