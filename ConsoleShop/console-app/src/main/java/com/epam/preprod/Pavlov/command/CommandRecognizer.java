package com.epam.preprod.Pavlov.command;

import com.epam.preprod.Pavlov.command.impl.AbstractCommand;
import com.epam.preprod.Pavlov.exception.UserInputException;

/**
 * This interface provides methods to deal with Commands.
 * If be more precise it can recognize, which command will be invoked in particular case
 * depending on Request that contain some specific code.
 */
public interface CommandRecognizer {
    /**
     * Accepts the request as a parameter. Retrieves information from it to identify a specific command.
     * If no one Command does not mapped on specific code from Request then UserInputException will be thrown.
     *
     * @param command
     * @throws UserInputException
     */
    void recognizeAndExecute(String command) throws UserInputException;

    /**
     * This method adds new Command to list of Commands of CommandRecognizer,mapping on particular key in the same time.
     *
     * @param key     specific key
     * @param command particular command
     */
    void addCommand(String key, AbstractCommand command);

    /**
     * This method removes command from CommandRecognizer's list of commands through belonging key to it.
     * And returns Command object that was removed.
     *
     * @param key
     */
    ICommand removeCommand(String key);
}
