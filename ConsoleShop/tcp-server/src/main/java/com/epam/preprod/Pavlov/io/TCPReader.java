package com.epam.preprod.Pavlov.io;

import java.io.BufferedReader;
import java.io.Reader;

public class TCPReader extends BufferedReader {
    public TCPReader(Reader in) {
        super(in);
    }
}
