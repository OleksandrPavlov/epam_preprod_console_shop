package com.epam.preprod.Pavlov.io;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Writer;

public class TCPWriter extends BufferedWriter {
    private String multilineSeparator;

    public TCPWriter(Writer out, String multilineSeparator) {
        super(out);
        this.multilineSeparator = multilineSeparator;
    }

    public void multiWrite(String str) throws IOException {
        this.write(str + System.lineSeparator());
        this.write(multilineSeparator + System.lineSeparator());
        this.flush();
    }

    public void writeln(String str) throws IOException {
        this.write(str + System.lineSeparator());
    }
    public void printMenu(){

    }

}
