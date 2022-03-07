package com.github.konradiv.finalreality.controller.turnPhases;

import com.github.konradiv.finalreality.model.character.Enemy;
import com.github.konradiv.finalreality.model.character.ICharacter;
import com.github.konradiv.finalreality.model.character.player.IPlayerCharacter;

import java.util.Objects;

/**
 * Holds the behaviour of the initial turn phase
 */
public class InitialPhase extends AbstractTurnPhase {
    ICharacter turnHolder;

    public InitialPhase() {
        super();
    }

    /**
     * Main activity of the phase, takes the first character in the queue and
     * takes their turn
     */
    @Override
    public void phaseActivity() {
        turnHolder = controller.pollCharacter();
        turnHolder.tryTakeTurn();
    }

    /**
     * Changes to player turn phase
     * @param character
     *     character that will take the turn
     */
    @Override
    public void toPlayerTurnPhase(IPlayerCharacter character) {
        controller.setPhase(new PlayerTurnPhase(character));
    }

    /**
     * Changes to enemy turn phase
     * @param enemy
     *     enemy that will take the turn
     */
    @Override
    public void toEnemyTurnPhase(Enemy enemy) {
        controller.setPhase(new EnemyTurnPhase(enemy));
    }

    /**
     * Tries to change to wait phase
     * @throws InvalidTransactionException
     *     throws the exception because it cannot happen
     */
    @Override
    public void toWaitPhase() throws InvalidTransactionException {
        throw new InvalidTransactionException("Can't change to wait phase");
    }

    /**
     * Tries to change to initial phase
     * @throws InvalidTransactionException
     *     throws the exception because it cannot happen
     */
    @Override
    public void toInitialPhase() throws InvalidTransactionException {
        throw new InvalidTransactionException("Can't change to initial phase");
    }

    /**
     * Returns the hash code of this class
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(InitialPhase.class);
    }

}
