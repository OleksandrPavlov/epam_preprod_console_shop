package com.epam.preprod.Pavlov.util.io;


import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;


public abstract class ShopReader extends InputStream {
    private BufferedReader bufferedReader;

    public ShopReader(InputStream inputStream) {
        bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
    }

    @Override
    public int read() throws IOException {
        return bufferedReader.read();
    }


    public String readLine() {
        try {
            return bufferedReader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public BufferedReader getBufferedReader() {
        return bufferedReader;
    }
}
