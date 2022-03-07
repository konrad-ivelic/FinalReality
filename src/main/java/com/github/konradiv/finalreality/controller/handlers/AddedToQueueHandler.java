package com.github.konradiv.finalreality.controller.handlers;

import com.github.konradiv.finalreality.controller.GameController;
import com.github.konradiv.finalreality.controller.turnPhases.InvalidTransactionException;

import java.beans.PropertyChangeEvent;

/**
 * Class that holds the behaviour of an observer that notifies the controller
 * when a character is added to queue
 */
public class AddedToQueueHandler implements IEventHandler{
    private final GameController controller;

    public AddedToQueueHandler(GameController controller) {
        this.controller = controller;
    }

    /**
     * Notifies the controller that a character has been added to queue
     * @param evt
     *     event of a character entering the queue
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            controller.characterEntered();
        } catch (InvalidTransactionException e) {
            e.printStackTrace();
        }
    }
}
