package com.github.konradiv.finalreality.controller.turnPhases;

import com.github.konradiv.finalreality.model.character.Enemy;
import com.github.konradiv.finalreality.model.character.ICharacter;
import com.github.konradiv.finalreality.model.character.player.IPlayerCharacter;
import com.github.konradiv.finalreality.model.weapon.IWeapon;
import javafx.scene.layout.FlowPane;

import java.awt.*;
import java.util.Objects;

/**
 * Holds the behaviour of the phase of a player turn
 */
public class PlayerTurnPhase extends AbstractTurnPhase{
    ICharacter character;

    public PlayerTurnPhase(ICharacter character) {
        super();
        this.character = character;
    }

    /**
     * Does the activity por this phase, in this case, nothing
     */
    @Override
    public void phaseActivity() {
        toWaitPhase();
    }

    /**
     * Tries to change to player turn phase
     * @throws InvalidTransactionException
     *    throws the exception because it cannot happen
     */
    @Override
    public void toPlayerTurnPhase(IPlayerCharacter character) throws InvalidTransactionException {
        throw new InvalidTransactionException("Can't change to player turn phase");
    }

    /**
     * Tries to change to enemy turn phase
     * @throws InvalidTransactionException
     *    throws the exception because it cannot happen
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
     *    throws the exception because it cannot happen
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
        return Objects.hashCode(PlayerTurnPhase.class);
    }

    /**
     * Checks if two objects of this class are equal, this being of the same
     * class AND having the same player character stored
     * @param obj
     *    obj to check equality with
     */
    @Override
    public boolean equals(final Object obj){
        return obj.hashCode() == this.hashCode() && character.equals(((PlayerTurnPhase)obj).character);
    }

}
