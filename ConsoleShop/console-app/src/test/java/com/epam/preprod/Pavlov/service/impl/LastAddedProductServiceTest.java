package com.epam.preprod.Pavlov.service.impl;

import com.epam.preprod.Pavlov.entity.PassengerCar;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertArrayEquals;


public class LastAddedProductServiceTest {
    private static final PassengerCar PC1 = new PassengerCar("Renault", 6789, 5, 4506, 5044, 170, 1.6, 6);
    private static final PassengerCar PC2 = new PassengerCar("Cherry", 6799, 5, 6780, 5044, 170, 1.6, 6);
    private static final PassengerCar PC3 = new PassengerCar("Mersedes", 6709, 5, 10123, 5044, 170, 1.6, 6);
    private static final PassengerCar PC4 = new PassengerCar("Galushka", 6719, 5, 45678, 5044, 170, 1.6, 6);
    private LastAddedProductService< PassengerCar> lastAddedProductService;

    @Before
    public void setUp() throws Exception {
        lastAddedProductService = new LastAddedProductService<>(3);
    }

    @Test
    public void shoulBeCorrectHistoryWhenLastAddedProductServiceCalled() {
        lastAddedProductService.addProduct(PC1);
        lastAddedProductService.addProduct(PC2);
        lastAddedProductService.addProduct(PC3);
        lastAddedProductService.addProduct(PC4);
        Object[] expectedArray = new PassengerCar[]{PC2, PC3, PC4};
        Object[] actualArray = lastAddedProductService.getAll().toArray();
        assertArrayEquals(expectedArray, actualArray);
    }


}