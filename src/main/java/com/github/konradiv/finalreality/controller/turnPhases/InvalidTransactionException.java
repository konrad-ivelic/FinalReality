package com.github.konradiv.finalreality.controller.turnPhases;

/**
 * Exception to be thrown when a invalid transaction is intended
 */
public class InvalidTransactionException extends Exception {

    /**
     * Throws the exception with the message specified
     * @param message
     *     message to be shown
     */
    public InvalidTransactionException(String message){
        super(message);
    }
}
