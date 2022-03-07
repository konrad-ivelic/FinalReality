package com.github.konradiv.finalreality.controller.turnPhases;

import com.github.konradiv.finalreality.model.character.Enemy;
import com.github.konradiv.finalreality.model.character.ICharacter;
import com.github.konradiv.finalreality.model.character.player.IPlayerCharacter;

import java.util.Objects;
import java.util.Random;

/**
 * Holds the behaviour of the phase of a enemy turn
 */
public class EnemyTurnPhase extends AbstractTurnPhase{
    ICharacter enemy;

    public EnemyTurnPhase(ICharacter enemy) {
        super();
        this.enemy = enemy;
    }

    /**
     * Does the activity of the phase, in this case attacks a random player character
     */
    @Override
    public void phaseActivity() {
        Random rand = new Random();
        enemy.tryAttack(controller.getPlayerCharacter(rand.nextInt(controller.getPlayersAlive())));
        enemy.waitTurn();
        controller.resetTurn();
        toWaitPhase();
    }

    /**
     * Tries to change to player turn phase
     * @throws InvalidTransactionException
     *     throws the exception because it cannot happen
     */
    @Override
    public void toPlayerTurnPhase(IPlayerCharacter character) throws InvalidTransactionException {
        throw new InvalidTransactionException("Can't change to player turn phase");
    }

    /**
     * Tries to change to enemy turn phase
     * @throws InvalidTransactionException
     *     throws the exception because it cannot happen
     */
    @Override
    public void toEnemyTurnPhase(Enemy enemy) throws InvalidTransactionException {
        throw new InvalidTransactionException("Can't change to enemy turn phase");
    }

    /**
     * Changes to wait phase
     */
    @Override
    public void toWaitPhase() {
        controller.setPhase(new WaitPhase());
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
     * Returns the hash code of the enemy turn phase class
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(EnemyTurnPhase.class);
    }

    /**
     * Checks if two objects of this class are equal, this being of the same
     * class AND having the same enemy stored
     * @param obj
     *    obj to check equality with
     */
    @Override
    public boolean equals(final Object obj){
        return obj.hashCode() == this.hashCode() && enemy.equals(((EnemyTurnPhase)obj).enemy);
    }
}
