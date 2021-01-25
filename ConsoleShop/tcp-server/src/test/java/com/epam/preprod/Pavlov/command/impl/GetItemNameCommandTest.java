package com.epam.preprod.Pavlov.command.impl;

import com.epam.preprod.Pavlov.constants.CommandConstants;
import com.epam.preprod.Pavlov.constants.ServerConstants;
import com.epam.preprod.Pavlov.entity.PassengerCar;
import com.epam.preprod.Pavlov.entity.Transport;
import com.epam.preprod.Pavlov.entity.Truck;
import com.epam.preprod.Pavlov.io.TCPReader;
import com.epam.preprod.Pavlov.io.TCPWriter;
import com.epam.preprod.Pavlov.util.SerializeTransportUtils;
import com.epam.preprod.Pavlov.util.constants.ErrorMessageConstants;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.easymock.EasyMock.expect;
import static org.easymock.EasyMock.mock;
import static org.easymock.EasyMock.replay;
import static org.easymock.EasyMock.verify;

public class GetItemNameCommandTest {
    public static final String PATH_TO_FILE = "test.txt";
    private static final String UNKNOWABLE_IDENTIFIER = "10";
    private static final String INVALID_IDENTIFIER = "I";
    private static final String KNOWABLE_IDENTIFIER = "1";
    public static final Transport TRANSPORT1 = new PassengerCar("GALO", 1, 1, 1, 1, 1, 1, 1);
    public static final Transport TRANSPORT2 = new Truck("VOLA", 2, 2, 2, 2, 2, 2, 2);
    private TCPReader reader;
    private TCPWriter writer;

    @Before
    public void setUp() throws Exception {
        reader = mock(TCPReader.class);
        writer = mock(TCPWriter.class);
        SerializeTransportUtils.insertList(PATH_TO_FILE, Stream.of(TRANSPORT1, TRANSPORT2).collect(Collectors.toList()), false);
    }

    @AfterClass
    public static void afterClass() throws Exception {
        File file = new File(PATH_TO_FILE);
        file.delete();
    }

    @Test
    public void shouldInvokeCorrectMethodWithCorrectParametersWhenExecuteMethodCalledWithFilledTransportListAndProperIdentifier() throws IOException {
        writer.multiWrite(ServerConstants.ENTER_ITEM_ID);
        expect(reader.readLine()).andReturn(KNOWABLE_IDENTIFIER);
        writer.writeln(String.format("Item name by id->%s = %s|%s", 1, "GALO", 1.0 + System.lineSeparator()));
        replay(reader, writer);
        GetTransportNameCommand command = new GetTransportNameCommand(PATH_TO_FILE);
        command.execute(writer, reader);
        verify(reader, writer);
    }

    @Test
    public void shouldInvokeCorrectMethodWithCorrectParametersWhenExecuteMethodCalledWithFilledTransportListAndInvalidIdentifier() throws IOException {
        writer.multiWrite(ServerConstants.ENTER_ITEM_ID);
        expect(reader.readLine()).andReturn(INVALID_IDENTIFIER);
        writer.multiWrite(ErrorMessageConstants.INVALID_PRODUCT_ID);
        writer.multiWrite(ServerConstants.ENTER_ITEM_ID);
        expect(reader.readLine()).andReturn(KNOWABLE_IDENTIFIER);
        writer.writeln(String.format("Item name by id->%s = %s|%s", 1, "GALO", 1.0 + System.lineSeparator()));
        replay(reader, writer);
        GetTransportNameCommand command = new GetTransportNameCommand(PATH_TO_FILE);
        command.execute(writer, reader);
        verify(reader, writer);
    }
    @Test
    public void shouldInvokeCorrectMethodWithCorrectParametersWhenExecuteMethodCalledWithFilledTransportListAndUnknowableIdentifier() throws IOException {
        writer.multiWrite(ServerConstants.ENTER_ITEM_ID);
        expect(reader.readLine()).andReturn(UNKNOWABLE_IDENTIFIER);
        writer.writeln(CommandConstants.NO_ONE_BY_ID);
        replay(reader, writer);
        GetTransportNameCommand command = new GetTransportNameCommand(PATH_TO_FILE);
        command.execute(writer, reader);
        verify(reader, writer);
    }

    @Test
    public void getDescription() {
    }
}