package com.epam.preprod.Pavlov.maker.impl;

import com.epam.preprod.Pavlov.maker.IMaker;
import com.epam.preprod.Pavlov.util.io.ShopReader;
import com.epam.preprod.Pavlov.util.io.ShopWriter;

import java.security.SecureRandom;
import java.text.DecimalFormat;

public class RandomMaker implements IMaker {
    private int MAX_RANDOM_GENERATED = 10_000;
    private int MIN_RANDOM_GENERATED = 1;
    private ShopWriter writer;
    private ShopReader reader;
    private static final String DEFAULT_TRANSPORT_NAME = "TRANSPORT";

    public RandomMaker(ShopWriter writer, ShopReader reader) {
        this.writer = writer;
        this.reader = reader;
    }

    @Override
    public int getInt(String message) {
        SecureRandom random = new SecureRandom();
        return random.nextInt(MAX_RANDOM_GENERATED - 1) + MIN_RANDOM_GENERATED;
    }

    @Override
    public double getDouble(String message) {
        SecureRandom random = new SecureRandom();
        DecimalFormat format = new DecimalFormat("#.##");
        return Double.parseDouble(format.format(random.nextDouble() * MAX_RANDOM_GENERATED));
    }

    @Override
    public String getString(String message) {
        SecureRandom random = new SecureRandom();
        return DEFAULT_TRANSPORT_NAME + random.nextInt(MAX_RANDOM_GENERATED - 1) + MIN_RANDOM_GENERATED;
    }
}
