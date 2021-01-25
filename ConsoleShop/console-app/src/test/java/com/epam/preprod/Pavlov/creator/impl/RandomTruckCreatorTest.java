package com.epam.preprod.Pavlov.creator.impl;

import com.epam.preprod.Pavlov.entity.Truck;
import com.epam.preprod.Pavlov.maker.IMaker;
import com.epam.preprod.Pavlov.maker.impl.RandomMaker;
import com.epam.preprod.Pavlov.util.constants.LocaleConstants;
import com.epam.preprod.Pavlov.util.io.ShopReader;
import com.epam.preprod.Pavlov.util.io.ShopWriter;
import com.epam.preprod.Pavlov.util.io.impl.ConsoleShopReader;
import com.epam.preprod.Pavlov.util.io.impl.ConsoleShopWriter;
import org.junit.Before;
import org.junit.Test;

import java.util.Objects;
import java.util.ResourceBundle;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RandomTruckCreatorTest {
    private static final String RESOURCE_NAME = "invitation";
    private static final ResourceBundle RUS_RESOURCE_BUNDLE = ResourceBundle.getBundle(RESOURCE_NAME, LocaleConstants.RUSSIAN_LOCALE);
    private IMaker maker;
    private TruckCreator creator;
    private ShopReader reader;
    private ShopWriter writer;

    @Before
    public void setUp() throws Exception {
        reader = new ConsoleShopReader(System.in);
        writer = new ConsoleShopWriter(System.out);
        maker = new RandomMaker(writer, reader);
        creator = new TruckCreator(maker, RUS_RESOURCE_BUNDLE);
    }

    @Test
    public void shouldCreateTruckCorrectlyWhenRandomTruckCreatorCalled() {
        Truck truck = (Truck) creator.create();
        assertTrue(truck.getMileage() != 0);
        assertEquals(true, Objects.nonNull(truck.getName()));
        assertEquals(true, truck.getId() != 0);
        assertEquals(true, truck.getPrice() != 0);
        assertEquals(true, truck.getCargoCompartmentVolume() != 0);
        assertEquals(true, truck.getMaxSpeed() != 0);
        assertEquals(true, truck.getCarrying() != 0);
        assertEquals(true, truck.getEngineCapacity() != 0);
        assertEquals(true, truck.getMileage() != 0);
    }
}