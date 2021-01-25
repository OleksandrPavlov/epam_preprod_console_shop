package com.epam.preprod.Pavlov.command.impl;

import com.epam.preprod.Pavlov.constants.CommandConstants;
import com.epam.preprod.Pavlov.entity.PassengerCar;
import com.epam.preprod.Pavlov.entity.Transport;
import com.epam.preprod.Pavlov.entity.Truck;
import com.epam.preprod.Pavlov.io.TCPReader;
import com.epam.preprod.Pavlov.io.TCPWriter;
import com.epam.preprod.Pavlov.util.SerializeTransportUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;
import static org.junit.Assert.assertNotNull;

public class GetItemNumberCommandTest {
    public static final String PATH_TO_FILE = "test.txt";
    public static final Transport TRANSPORT1 = new PassengerCar("GALO", 1, 1, 1, 1, 1, 1, 1);
    public static final Transport TRANSPORT2 = new Truck("VOLA", 2, 2, 2, 2, 2, 2, 2);
    private TCPReader reader;
    private TCPWriter writer;

    @Before
    public void setUp() throws Exception {
        reader = mock(TCPReader.class);
        writer = mock(TCPWriter.class);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        File file = new File(PATH_TO_FILE);
        file.delete();
    }

    @Test
    public void shouldInvokeCorrectMethodWithCorrectParametersWhenExecuteMethodCalledWithFilledTransportListAndProperIdentifier() throws IOException {
        writer.writeln(CommandConstants.AMOUNT + 2 + System.lineSeparator());
        replay(reader, writer);
        SerializeTransportUtils.insertList(PATH_TO_FILE, Stream.of(TRANSPORT1, TRANSPORT2).collect(Collectors.toList()), false);
        GetTransportNumberCommand command = new GetTransportNumberCommand(PATH_TO_FILE);
        command.execute(writer, reader);
        verify(reader, writer);
    }

    @Test
    public void shouldReturnStringWhenDescriptionMethodCalled() {
        GetTransportNumberCommand command = new GetTransportNumberCommand(PATH_TO_FILE);
        String description = command.getDescription();
        assertNotNull(description);
    }
}