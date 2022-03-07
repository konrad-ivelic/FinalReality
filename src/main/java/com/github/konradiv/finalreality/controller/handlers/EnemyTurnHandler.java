package com.github.konradiv.finalreality.controller.handlers;

import com.github.konradiv.finalreality.controller.GameController;
import com.github.konradiv.finalreality.controller.turnPhases.InvalidTransactionException;
import com.github.konradiv.finalreality.model.character.Enemy;

import java.beans.PropertyChangeEvent;

/**
 * Class that handles the turn for an enemy
 */
public class EnemyTurnHandler implements IEventHandler {
    private final GameController controller;

    public EnemyTurnHandler(GameController controller) {
        this.controller = controller;
    }

    /**
     * Notifies the controller that the character holding the turn is an enemy
     * @param evt
     *     event that the player holding the turn is an enemy
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        try {
            controller.enemyTurn((Enemy) evt.getNewValue());
        } catch (InvalidTransactionException e) {
            e.printStackTrace();
        }
    }
}
