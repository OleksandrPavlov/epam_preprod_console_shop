package com.epam.preprod.Pavlov.util;

import com.epam.preprod.Pavlov.entity.Transport;

import java.io.ByteArrayOutputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.zip.GZIPOutputStream;

public class SerializeTransportUtils {
    private static final String END_OF_FILE_MESSAGE = "End of file as been reached";
    private static final String FILE_CREATION_ERROR = "Input output error occurs during file creating.";
    private static final String FILE_NOT_FOUND = "Requested file name does not exist or you entered incorrect name!";
    private static final String IO_EXCEPTION_IN_COMPRESS_TO_GZIP_METHOD = "An input/output exception has occurred during creation of input/output streams.";

    private SerializeTransportUtils() {

    }

    /**
     * This method takes fileName parameter as an name of file that will be read and through will be extracted all objects it has. As result will be returned the
     * list of particular Transports.
     *
     * @param fileName name of file that will be processed for availability of T type objects.
     * @param <T>      type of particular Transport of which will be returned as list
     * @return list of elements type T
     * @throws ClassNotFoundException in case of class of a serialized object cannot be found.
     */
    public static <T extends Transport> List<T> extractTransports(String fileName) throws ClassNotFoundException {
        checkIfExistsAndCreate(fileName);
        List<T> transportList = new ArrayList<>();
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(fileName))) {
            T transport;
            while (Objects.nonNull(transport = (T) objectInputStream.readObject())) {
                transportList.add(transport);
            }
        } catch (IOException ex) {
            if (!(ex instanceof EOFException)) {
                System.out.println(END_OF_FILE_MESSAGE);
            }
        }
        return transportList;
    }

    /**
     * This method writes all elements from "objectList" to file with name "fileName".
     *
     * @param fileName   name of file where "objectList" will be recorded.
     * @param objectList list should be recorded to file
     * @param append     flag that defines mode of recording. If True - objects will appended to the end of file, if False -file will be rerecorded;
     * @return true if operation succeeded, and false in other case.
     */
    public static boolean insertList(String fileName, List objectList, boolean append) {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(fileName, append))) {
            for (Object element : objectList) {
                objectOutputStream.writeObject(element);
            }
        } catch (IOException ex) {
            return false;
        }
        return true;
    }

    /**
     * This method is an test to show how changes file size after appending same lists of objects to the same file.
     *
     * @param fileName       name of file into which will write.
     * @param objectList     list of objects.
     * @param repeatQuantity number of appending's reiteration.
     * @return file's sizes in particular moment of time between appends.
     */
    public static List<Integer> insertListRepeatably(String fileName, List objectList, int repeatQuantity) {
        List<Integer> sizeReportList = new ArrayList<>();
        File file = new File(fileName);
        for (int i = 0; i < repeatQuantity; i++) {
            insertList(fileName, objectList, true);
            sizeReportList.add((int) file.length());
        }
        return sizeReportList;
    }

    /**
     * This method compresses "objectList" to Zip format using GZIPOutputStream
     *
     * @param output     the file to which "objectList" will be written
     * @param objectList the list which will be written
     */
    public static void compressListToGZip(File output, List objectList) {
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(byteArrayOutputStream);
             GZIPOutputStream gzipOutputStream = new GZIPOutputStream(new FileOutputStream(output))) {
            for (Object ob : objectList) {
                objectOutputStream.writeObject(ob);
            }
            gzipOutputStream.write(byteArrayOutputStream.toByteArray());
        } catch (FileNotFoundException e) {
            System.out.println(FILE_NOT_FOUND);
        } catch (IOException e) {
            System.out.println(IO_EXCEPTION_IN_COMPRESS_TO_GZIP_METHOD);
        }
    }

    private static void checkIfExistsAndCreate(String fileName) {
        File file = getFileFromString(fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.out.println(FILE_CREATION_ERROR);
            }
        }
    }

    private static File getFileFromString(String fileName) {
        return new File(fileName);
    }
}
