package com.epam.preprod.Pavlov.command.impl;

import com.epam.preprod.Pavlov.command.CommandRecognizer;
import com.epam.preprod.Pavlov.command.ICommand;
import com.epam.preprod.Pavlov.exception.UserInputException;
import com.epam.preprod.Pavlov.util.constants.ErrorMessageConstants;

import java.util.HashMap;
import java.util.Objects;

public class CommandRecognizerImpl implements CommandRecognizer {
    private HashMap<String, AbstractCommand> commandMap;

    public CommandRecognizerImpl() {
        commandMap = new HashMap<>();
    }


    @Override
    public void recognizeAndExecute(String command) throws UserInputException {
        AbstractCommand particularCommand = commandMap.get(command);
        if (Objects.isNull(command)) {
            throw new UserInputException(ErrorMessageConstants.UNKNOWN_IDENTIFIER);
        }
        particularCommand.execute();
    }

    @Override
    public void addCommand(String key, AbstractCommand command) {
        commandMap.put(key, command);
    }

    @Override
    public ICommand removeCommand(String key) {
        return commandMap.remove(key);
    }
}
