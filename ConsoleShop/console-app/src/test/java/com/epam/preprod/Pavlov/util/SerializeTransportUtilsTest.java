package com.epam.preprod.Pavlov.util;

import com.epam.preprod.Pavlov.entity.PassengerCar;
import com.epam.preprod.Pavlov.entity.Transport;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.zip.GZIPInputStream;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class SerializeTransportUtilsTest {
    private static final String PATH_TO_FILE = "src" + File.separator + "main" + File.separator + "resources" + File.separator + "test.txt";
    private static final PassengerCar PC1 = new PassengerCar("Renault", 6789, 5, 4506, 5044, 170, 1.6, 6);
    private static final PassengerCar PC2 = new PassengerCar("Cherry", 6799, 5, 6780, 5044, 170, 1.6, 6);
    private static final PassengerCar PC3 = new PassengerCar("Mersedes", 6709, 5, 10123, 5044, 170, 1.6, 6);
    private static final PassengerCar PC4 = new PassengerCar("Galushka", 6719, 5, 45678, 5044, 170, 1.6, 6);
    private static final PassengerCar PC5 = new PassengerCar("Niagar", 6739, 5, 7900, 5044, 170, 1.6, 6);
    private static final PassengerCar PC6 = new PassengerCar("Volga", 6734, 5, 7900, 5044, 170, 1.6, 6);
    private static final PassengerCar PC7 = new PassengerCar("Zabava", 6733, 5, 7900, 5044, 170, 1.6, 6);
    private File testFile;

    @Before
    public void setUp() throws Exception {
        testFile = new File(PATH_TO_FILE);
        testFile.createNewFile();
    }

    @After
    public void tearDown() {
        testFile.delete();
    }

    @Test
    public void shouldBeCorrectTransportListWhenExtractTransportCalled() throws IOException, ClassNotFoundException {
        List<Transport> expectedList = Stream.of(PC1, PC2, PC3).collect(Collectors.toList());
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(testFile));
        objectOutputStream.writeObject(PC1);
        objectOutputStream.writeObject(PC2);
        objectOutputStream.writeObject(PC3);
        objectOutputStream.close();
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(testFile));
        List<Transport> actualList = SerializeTransportUtils.extractTransports(testFile.getAbsolutePath());
        objectInputStream.close();
        assertThat(actualList, is(expectedList));
    }

    @Test
    public void shouldBeCorrectTransportListWhenInsertLestCalled() throws IOException, ClassNotFoundException {
        List<Transport> expectedList = Stream.of(PC1, PC2, PC3).collect(Collectors.toList());
        SerializeTransportUtils.insertList(testFile.getAbsolutePath(), expectedList, false);
        ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(testFile));
        List<Transport> actualList = SerializeTransportUtils.extractTransports(testFile.getAbsolutePath());
        objectInputStream.close();
        assertThat(actualList, is(expectedList));
    }

    @Test
    public void insertListRepeatably() throws IOException {
        List<Transport> transportList = Stream.of(PC1, PC2, PC3, PC4, PC5).collect(Collectors.toList());
        File file = new File(PATH_TO_FILE);

        for (int i = 0; i < 10; i++) {
            if (!testFile.exists()) {
                testFile.createNewFile();
            }
            int repeatNumber = i * 10;
            SerializeTransportUtils.insertListRepeatably(testFile.getAbsolutePath(), transportList, repeatNumber);
            System.out.println("Reapeat number: " + repeatNumber + " File size: " + testFile.length());
            testFile.delete();
        }
    }

    @Test
    public void shouldBeCorrectTransportListAfterZippingAndUnzippingFile() throws IOException, ClassNotFoundException {
        List<Transport> expected = Stream.of(PC1, PC2, PC3, PC4, PC5).collect(Collectors.toList());
        File file = new File(PATH_TO_FILE.substring(0, PATH_TO_FILE.lastIndexOf(File.separator) + 1) + "testZip.zip");
        SerializeTransportUtils.compressListToGZip(file, expected);
        GZIPInputStream zipInputStream = new GZIPInputStream(new FileInputStream(file));
        FileOutputStream fileOutputStream = new FileOutputStream(testFile);
        byte b;
        while ((b = (byte) zipInputStream.read()) != -1) {
            fileOutputStream.write(b);
        }
        zipInputStream.close();
        fileOutputStream.close();
        List<Transport> actualList = SerializeTransportUtils.extractTransports(testFile.getAbsolutePath());
        assertThat(actualList, is(expected));
        file.delete();
    }


}