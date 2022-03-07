package com.github.konradiv.finalreality.controller.handlers;

import com.github.konradiv.finalreality.controller.GameController;
import com.github.konradiv.finalreality.controller.turnPhases.InvalidTransactionException;
import com.github.konradiv.finalreality.model.character.player.IPlayerCharacter;

import java.beans.PropertyChangeEvent;

/**
 * Class that handles the turn for a player character
 */
public class PlayerTurnHandler implements IEventHandler {
    private final GameController controller;

    public PlayerTurnHandler(GameController controller) {
        this.controller = controller;
    }

    /**
     * Notifies the controller that the character holding the turn is a player character
     * @param evt
     *     event that the player holding the turn is a player character
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            controller.playerTurn((IPlayerCharacter) evt.getNewValue());
        } catch (InvalidTransactionException e) {
            e.printStackTrace();
        }
    }
}
