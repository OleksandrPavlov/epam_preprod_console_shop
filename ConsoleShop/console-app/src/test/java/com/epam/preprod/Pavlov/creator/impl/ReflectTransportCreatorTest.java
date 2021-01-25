package com.epam.preprod.Pavlov.creator.impl;

import com.epam.preprod.Pavlov.entity.Car;
import com.epam.preprod.Pavlov.entity.PassengerCar;
import com.epam.preprod.Pavlov.entity.Truck;
import com.epam.preprod.Pavlov.maker.IMaker;
import com.epam.preprod.Pavlov.maker.impl.ManualMaker;
import com.epam.preprod.Pavlov.maker.impl.RandomMaker;
import com.epam.preprod.Pavlov.util.constants.Constants;
import com.epam.preprod.Pavlov.util.constants.LocaleConstants;
import com.epam.preprod.Pavlov.util.io.ShopReader;
import com.epam.preprod.Pavlov.util.io.ShopWriter;
import com.epam.preprod.Pavlov.util.io.impl.ConsoleShopReader;
import com.epam.preprod.Pavlov.util.io.impl.ConsoleShopWriter;
import org.junit.Before;
import org.junit.Test;

import java.util.ResourceBundle;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ReflectTransportCreatorTest {

    private static final String RESOURCE_NAME = "invitation";
    private static final double CARGO_VOLUME = 345;
    private static final int PASSENGER_QUANTITY = 7;
    private static final int TRANSPORT_ID = 2345;
    private static final String TRANSPORT_NAME = "TRANSPORT";
    private static final double TRANSPORT_MILEAGE = 22222;
    private static final double TRANSPORT_ENGINE = 2.2;
    private static final double TRANSPORT_SPEED = 2222;
    private static final double TRANSPORT_CARRYING = 2222.2;
    private static final double TRANSPORT_PRICE = 34.53;
    private static final int RANDOM_MAX = 10000;
    private static final int RANDOM_MIN = 0;


    private static final ResourceBundle RUSSIAN_BUNDLE = ResourceBundle.getBundle(RESOURCE_NAME, LocaleConstants.RUSSIAN_LOCALE);
    private static final ResourceBundle ENGLISH_BUNDLE = ResourceBundle.getBundle(RESOURCE_NAME, LocaleConstants.ENGLISH_LOCALE);
    private ReflectTransportCreator creator;
    private ShopReader reader;
    private ShopWriter writer;
    private IMaker randomMaker;
    private IMaker manualMaker;


    @Before
    public void setUp() throws Exception {
        reader = new ConsoleShopReader(System.in);
        writer = new ConsoleShopWriter(System.out);
        randomMaker = new RandomMaker(writer, reader);
        manualMaker = new ManualMaker(writer, reader);
    }

    @Test
    public void shouldCreatePassengerCarCorrectlyWhenReflectTransportCreatorWithRussianBundleCalled() {
        manualMaker = mock(IMaker.class);
        expectationRusEn(RUSSIAN_BUNDLE);
        expect(manualMaker.getInt(RUSSIAN_BUNDLE.getString(Constants.TRANSPORT_PASS_QUANTITY))).andReturn(PASSENGER_QUANTITY);
        replay(manualMaker);
        creator = new ReflectTransportCreator(manualMaker, RUSSIAN_BUNDLE, PassengerCar.class);
        PassengerCar passengerCar = (PassengerCar) creator.create();
        assertEquals(passengerCar.getId(), TRANSPORT_ID);
        assertEquals(passengerCar.getName(), TRANSPORT_NAME);
        assertEquals(passengerCar.getMileage(), TRANSPORT_MILEAGE, 0.0);
        assertEquals(passengerCar.getEngineCapacity(), TRANSPORT_ENGINE, 0.0);
        assertEquals(passengerCar.getMaxSpeed(), TRANSPORT_SPEED, 0.0);
        assertEquals(passengerCar.getCarrying(), TRANSPORT_CARRYING, 0.0);
        assertEquals(passengerCar.getPrice(), TRANSPORT_PRICE, 0.0);
        assertEquals(passengerCar.getPassengerQuantity(), PASSENGER_QUANTITY, 0.0);
        verify();
    }

    @Test
    public void shouldCreateCorrectlyPassengerCarWhenReflectTransportCreatorWithEnglishBundleCalled() {
        manualMaker = mock(IMaker.class);
        expectationRusEn(ENGLISH_BUNDLE);
        expect(manualMaker.getInt(ENGLISH_BUNDLE.getString(Constants.TRANSPORT_PASS_QUANTITY))).andReturn(PASSENGER_QUANTITY);
        replay(manualMaker);
        creator = new ReflectTransportCreator(manualMaker, ENGLISH_BUNDLE, PassengerCar.class);
        PassengerCar passengerCar = (PassengerCar) creator.create();
        assertEquals(passengerCar.getId(), TRANSPORT_ID);
        assertEquals(passengerCar.getName(), TRANSPORT_NAME);
        assertEquals(passengerCar.getMileage(), TRANSPORT_MILEAGE, 0.0);
        assertEquals(passengerCar.getEngineCapacity(), TRANSPORT_ENGINE, 0.0);
        assertEquals(passengerCar.getMaxSpeed(), TRANSPORT_SPEED, 0.0);
        assertEquals(passengerCar.getCarrying(), TRANSPORT_CARRYING, 0.0);
        assertEquals(passengerCar.getPrice(), TRANSPORT_PRICE, 0.0);
        assertEquals(passengerCar.getPassengerQuantity(), PASSENGER_QUANTITY, 0.0);
        verify();
    }

    @Test
    public void shouldCreateCorrectlyPassengerCarWhenRandomReflectTransportCreatorWithEnglishBundleCalled() {
        ReflectTransportCreator creator = new ReflectTransportCreator(randomMaker, ENGLISH_BUNDLE, PassengerCar.class);
        PassengerCar passengerCar = (PassengerCar) creator.create();
        carFieldAssertion(passengerCar);
        assertTrue(passengerCar.getPassengerQuantity() < RANDOM_MAX && passengerCar.getPassengerQuantity() > RANDOM_MIN);
    }

    @Test
    public void shouldCreateCorrectlyTruckWhenRandomReflectTransportCreatorCalled() {
        ReflectTransportCreator creator = new ReflectTransportCreator(randomMaker, ENGLISH_BUNDLE, Truck.class);
        Truck truck = (Truck) creator.create();
        carFieldAssertion(truck);
        assertTrue(truck.getCargoCompartmentVolume() < RANDOM_MAX && truck.getCargoCompartmentVolume() > RANDOM_MIN);
    }

    @Test
    public void shouldCreateCorrectlyTruckWhenManualReflectTransportCreatorCalled() {
        manualMaker = mock(IMaker.class);
        expectationRusEn(ENGLISH_BUNDLE);
        expect(manualMaker.getDouble(ENGLISH_BUNDLE.getString(Constants.TRANSPORT_CARGO_COM_VOL))).andReturn(CARGO_VOLUME);
        replay(manualMaker);
        creator = new ReflectTransportCreator(manualMaker, ENGLISH_BUNDLE, Truck.class);
        Truck truck = (Truck) creator.create();
        assertEquals(truck.getId(), TRANSPORT_ID);
        assertEquals(truck.getName(), TRANSPORT_NAME);
        assertEquals(truck.getMileage(), TRANSPORT_MILEAGE, 0.0);
        assertEquals(truck.getEngineCapacity(), TRANSPORT_ENGINE, 0.0);
        assertEquals(truck.getMaxSpeed(), TRANSPORT_SPEED, 0.0);
        assertEquals(truck.getCarrying(), TRANSPORT_CARRYING, 0.0);
        assertEquals(truck.getPrice(), TRANSPORT_PRICE, 0.0);
        assertEquals(truck.getCargoCompartmentVolume(), CARGO_VOLUME, 0.0);
        verify();
    }

    private void expectationRusEn(ResourceBundle resourceBundle) {
        expect(manualMaker.getInt(resourceBundle.getString(Constants.TRANSPORT_ID))).andReturn(TRANSPORT_ID);
        expect(manualMaker.getString(resourceBundle.getString(Constants.TRANSPORT_NAME))).andReturn(TRANSPORT_NAME);
        expect(manualMaker.getDouble(resourceBundle.getString(Constants.TRANSPORT_CARRYING))).andReturn(TRANSPORT_CARRYING);
        expect(manualMaker.getDouble(resourceBundle.getString(Constants.TRANSPORT_PRICE))).andReturn(TRANSPORT_PRICE);
        expect(manualMaker.getDouble(resourceBundle.getString(Constants.TRANSPORT_MILEAGE))).andReturn(TRANSPORT_MILEAGE);
        expect(manualMaker.getDouble(resourceBundle.getString(Constants.TRANSPORT_MAX_SPEED))).andReturn(TRANSPORT_SPEED);
        expect(manualMaker.getDouble(resourceBundle.getString(Constants.TRANSPORT_ENGINE_CAPACITY))).andReturn(TRANSPORT_ENGINE);
    }

    private void carFieldAssertion(Car car) {
        assertTrue(car.getId() < RANDOM_MAX && car.getId() > RANDOM_MIN);
        assertTrue(car.getName().startsWith(TRANSPORT_NAME));
        assertTrue(car.getMaxSpeed() < RANDOM_MAX && car.getId() > RANDOM_MIN);
        assertTrue(car.getMileage() < RANDOM_MAX && car.getId() > RANDOM_MIN);
        assertTrue(car.getPrice() < RANDOM_MAX && car.getId() > RANDOM_MIN);
        assertTrue(car.getCarrying() < RANDOM_MAX && car.getId() > RANDOM_MIN);
        assertTrue(car.getEngineCapacity() < RANDOM_MAX && car.getId() > RANDOM_MIN);
    }
}