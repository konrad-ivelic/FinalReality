package com.github.konradiv.finalreality.controller.turnPhases;

import com.github.konradiv.finalreality.controller.GameController;
import com.github.konradiv.finalreality.model.character.Enemy;
import com.github.konradiv.finalreality.model.character.player.IPlayerCharacter;
import javafx.scene.layout.FlowPane;

/**
 * Behaviour of any turn phase
 */
public interface ITurnPhase {

    /**
     * Main activity of the phase
     */
    void phaseActivity();

    /**
     * Sets the controller that will be referred to
     * @param controller
     *     controller to be referred
     */
    void setController(GameController controller);

    /**
     * Tries to change to player turn phase
     */
    void toPlayerTurnPhase(IPlayerCharacter character) throws InvalidTransactionException;

    /**
     * Tries to change to enemy turn phase
     */
    void toEnemyTurnPhase(Enemy enemy) throws InvalidTransactionException;

    /**
     * Tries to change to wait phase
     */
    void toWaitPhase() throws InvalidTransactionException;

    /**
     * Tries to change to initial phase
     */
    void toInitialPhase() throws InvalidTransactionException;
}
