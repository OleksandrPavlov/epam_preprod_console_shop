package com.epam.preprod.Pavlov.util.io;

import com.epam.preprod.Pavlov.entity.Order;
import com.epam.preprod.Pavlov.util.constants.Constants;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.List;

public abstract class ShopWriter extends OutputStream {
    private BufferedWriter bufferedWriter;

    public ShopReader getShopReader() {
        return shopReader;
    }

    private ShopReader shopReader;

    public ShopWriter(OutputStream outputStream) {
        bufferedWriter = new BufferedWriter(new OutputStreamWriter(outputStream));
        this.shopReader = shopReader;
    }

    @Override
    public void write(int i) throws IOException {
        bufferedWriter.write(i);
    }

    public abstract void printMenu();

    public abstract void print(String message);

    public void write(String string) {
        try {
            bufferedWriter.write(string);
            bufferedWriter.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public <T> void write(List<T> list) {
        list.forEach(value -> writeln(value.toString()));
    }

    public <T> void write(Order<T> order) {
        writeln(Constants.ORDER + "(" + order.getLocalDateTime().toString() + ")");
        order.getList().stream().forEach(value -> writeln(value.toString()));
    }

    public void writeln(String string) {
        write(string + System.lineSeparator());
    }

    public BufferedWriter getBufferedWriter() {
        return bufferedWriter;
    }
}
