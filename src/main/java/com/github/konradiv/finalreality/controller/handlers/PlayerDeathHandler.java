package com.github.konradiv.finalreality.controller.handlers;

import com.github.konradiv.finalreality.controller.GameController;
import com.github.konradiv.finalreality.model.character.player.IPlayerCharacter;

import java.beans.PropertyChangeEvent;

/**
 * Class that handles the death of a player character
 */
public class PlayerDeathHandler implements IEventHandler {
    private final GameController controller;

    public PlayerDeathHandler(GameController controller) {
        this.controller = controller;
    }

    /**
     * Notifies the controller that a player character has died
     * @param evt
     *     event that a player character has died
     */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        controller.playerDeath((IPlayerCharacter) evt.getNewValue());
    }
}
