package com.epam.preprod.Pavlov.creator.impl;

import com.epam.preprod.Pavlov.entity.PassengerCar;
import com.epam.preprod.Pavlov.maker.IMaker;
import com.epam.preprod.Pavlov.maker.impl.RandomMaker;
import com.epam.preprod.Pavlov.util.constants.LocaleConstants;
import com.epam.preprod.Pavlov.util.io.ShopReader;
import com.epam.preprod.Pavlov.util.io.ShopWriter;
import com.epam.preprod.Pavlov.util.io.impl.ConsoleShopReader;
import com.epam.preprod.Pavlov.util.io.impl.ConsoleShopWriter;
import org.junit.Before;
import org.junit.Test;

import java.util.ResourceBundle;

import static org.junit.Assert.assertTrue;

public class RandomPassengerCarCreatorTest {
    private static final String RESOURCE_NAME = "invitation";
    private static final ResourceBundle RUS_RESOURCE_BUNDLE = ResourceBundle.getBundle(RESOURCE_NAME, LocaleConstants.RUSSIAN_LOCALE);
    private IMaker maker;
    private PassengerCarCreator creator;
    private ShopReader reader;
    private ShopWriter writer;

    @Before
    public void setUp() throws Exception {
        reader = new ConsoleShopReader(System.in);
        writer = new ConsoleShopWriter(System.out);
        maker = new RandomMaker(writer, reader);
        creator = new PassengerCarCreator(maker, RUS_RESOURCE_BUNDLE);
    }

    @Test
    public void shouldCreatePassengerCarCorrectlyWhenRandomCreatorCalled() {
        PassengerCar passengerCar = (PassengerCar) creator.create();
        assertTrue(passengerCar.getName() != null);
        assertTrue(passengerCar.getPassengerQuantity() != 0);
        assertTrue(passengerCar.getId() != 0);
        assertTrue(passengerCar.getPrice() != 0);
        assertTrue(passengerCar.getMaxSpeed() != 0);
        assertTrue(passengerCar.getCarrying() != 0);
        assertTrue(passengerCar.getEngineCapacity() != 0);
        assertTrue(passengerCar.getMileage() != 0);
    }
}