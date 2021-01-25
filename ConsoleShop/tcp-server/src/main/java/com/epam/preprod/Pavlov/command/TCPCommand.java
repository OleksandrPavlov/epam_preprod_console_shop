package com.epam.preprod.Pavlov.command;

import com.epam.preprod.Pavlov.io.TCPReader;
import com.epam.preprod.Pavlov.io.TCPWriter;

public interface TCPCommand {
    void execute(TCPWriter writer, TCPReader reader);
    String getDescription();
}
