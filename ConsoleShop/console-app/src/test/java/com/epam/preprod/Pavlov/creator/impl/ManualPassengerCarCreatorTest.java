package com.epam.preprod.Pavlov.creator.impl;

import com.epam.preprod.Pavlov.entity.PassengerCar;
import com.epam.preprod.Pavlov.maker.IMaker;
import com.epam.preprod.Pavlov.maker.impl.ManualMaker;
import com.epam.preprod.Pavlov.util.constants.LocaleConstants;
import com.epam.preprod.Pavlov.util.io.ShopReader;
import com.epam.preprod.Pavlov.util.io.ShopWriter;
import com.epam.preprod.Pavlov.util.io.impl.ConsoleShopReader;
import com.epam.preprod.Pavlov.util.io.impl.ConsoleShopWriter;
import org.junit.Before;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.ResourceBundle;

import static org.junit.Assert.assertEquals;

public class ManualPassengerCarCreatorTest {
    private static final String RESOURCE_NAME = "invitation";
    private static final ResourceBundle RUS_RESOURCE_BUNDLE = ResourceBundle.getBundle(RESOURCE_NAME, LocaleConstants.RUSSIAN_LOCALE);

    private IMaker maker;
    private PassengerCarCreator creator;
    private ShopReader reader;
    private ShopWriter writer;

    @Before
    public void setUp() throws Exception {

    }

    @Test
    public void shouldCreatePassengerCarCorrectlyWhenManualCreatorCalled() {
        int id = 2345;
        String name = "Gruzovik";
        double mileage = 3456;
        double engine = 2.2;
        double speed = 234;
        double carrying = 345;
        double price = 3456;
        int passengerQuantity = 5;
        String answers = id + System.lineSeparator()
                + name + System.lineSeparator()
                + mileage + System.lineSeparator()
                + engine + System.lineSeparator()
                + speed + System.lineSeparator()
                + carrying + System.lineSeparator()
                + price + System.lineSeparator()
                + passengerQuantity;
        InputStream is = new ByteArrayInputStream(answers.getBytes());
        reader = new ConsoleShopReader(is);
        writer = new ConsoleShopWriter(System.out);
        maker = new ManualMaker(writer, reader);
        creator = new PassengerCarCreator(maker, RUS_RESOURCE_BUNDLE);
        PassengerCar passengerCar = (PassengerCar) creator.create();
        assertEquals(passengerCar.getId(), id);
        assertEquals(passengerCar.getName(), name);
        assertEquals(passengerCar.getMileage(), mileage, 0.0);
        assertEquals(passengerCar.getEngineCapacity(), engine, 0.0);
        assertEquals(passengerCar.getMaxSpeed(), speed, 0.0);
        assertEquals(passengerCar.getCarrying(), carrying, 0.0);
        assertEquals(passengerCar.getPrice(), price, 0.0);
        assertEquals(passengerCar.getPassengerQuantity(), passengerQuantity, 0.0);
    }
}