package com.github.konradiv.finalreality.controller.turnPhases;

import com.github.konradiv.finalreality.model.character.Enemy;
import com.github.konradiv.finalreality.model.character.player.IPlayerCharacter;

import java.util.Objects;

/**
 * Holds the behaviour of a wait phase
 */
public class WaitPhase extends AbstractTurnPhase{

    public WaitPhase() {
        super();
    }

    /**
     * Main activity of the phase, if the queue is not empty, it goes to initial phase
     */
    @Override
    public void phaseActivity() {
        if (!controller.emptyQueue()){
            toInitialPhase();
        }
    }

    /**
     * Tries to go to player turn phase
     * @throws InvalidTransactionException
     *     throws the exception because it cannot happen
     */
    @Override
    public void toPlayerTurnPhase(IPlayerCharacter character) throws InvalidTransactionException {
        throw new InvalidTransactionException("Can't change to player turn phase");

    }

    /**
     * Tries to go to enemy turn phase
     * @throws InvalidTransactionException
     *     throws the exception because it cannot happen
     */
    @Override
    public void toEnemyTurnPhase(Enemy enemy) throws InvalidTransactionException {
        throw new InvalidTransactionException("Can't change to enemy turn phase");
    }

    /**
     * Tries to go to wait phase
     * @throws InvalidTransactionException
     *     throws the exception because it cannot happen
     */
    @Override
    public void toWaitPhase() throws InvalidTransactionException {
        throw new InvalidTransactionException("Can't change to wait phase");
    }

    /**
     * Changes to initial phase
     */
    @Override
    public void toInitialPhase() {
        controller.setPhase(new InitialPhase());
    }

    /**
     * Returns the hash code of this class
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(WaitPhase.class);
    }
}
