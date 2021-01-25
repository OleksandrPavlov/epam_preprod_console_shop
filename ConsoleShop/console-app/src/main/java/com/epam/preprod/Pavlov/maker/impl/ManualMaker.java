package com.epam.preprod.Pavlov.maker.impl;

import com.epam.preprod.Pavlov.maker.IMaker;
import com.epam.preprod.Pavlov.util.ValidateInputUtils;
import com.epam.preprod.Pavlov.util.io.ShopReader;
import com.epam.preprod.Pavlov.util.io.ShopWriter;

public class ManualMaker implements IMaker {
    private ShopWriter writer;
    private ShopReader reader;

    public ManualMaker(ShopWriter writer, ShopReader reader) {
        this.writer = writer;
        this.reader = reader;
    }

    @Override
    public int getInt(String message) {
        writer.writeln(message);
        return ValidateInputUtils.getValidId(writer, reader);
    }

    @Override
    public double getDouble(String message) {
        writer.writeln(message);
        return ValidateInputUtils.getPositiveDouble(writer, reader);
    }

    @Override
    public String getString(String message) {
        writer.writeln(message);
        return ValidateInputUtils.getValidName(writer, reader);
    }
}
