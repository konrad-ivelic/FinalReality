package com.github.konradiv.finalreality.controller.handlers;

import com.github.konradiv.finalreality.controller.GameController;
import com.github.konradiv.finalreality.model.character.Enemy;

import java.beans.PropertyChangeEvent;

/**
 * Class that handles the death of an enemy
 */
public class EnemyDeathHandler implements IEventHandler{
    private final GameController controller;

    public EnemyDeathHandler(GameController controller) {
        this.controller = controller;
    }

    /**
     * Notifies the controller that an enemy has died
     * @param evt
     *     event that an enemy has died
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.enemyDeath((Enemy) evt.getNewValue());
    }
}
