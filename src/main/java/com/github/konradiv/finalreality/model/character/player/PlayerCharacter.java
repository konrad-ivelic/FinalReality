package com.github.konradiv.finalreality.model.character.player;

import com.github.konradiv.finalreality.controller.handlers.IEventHandler;
import com.github.konradiv.finalreality.model.character.AbstractCharacter;
import com.github.konradiv.finalreality.model.character.ICharacter;
import com.github.konradiv.finalreality.model.character.states.IPlayerState;
import com.github.konradiv.finalreality.model.character.states.PlayerAlive;
import com.github.konradiv.finalreality.model.character.states.PlayerDead;
import com.github.konradiv.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.beans.PropertyChangeSupport;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * An abstract class that holds the behaviour of a player character of the game
 *
 * @author Konrad Ivelic
 */
public abstract class PlayerCharacter extends AbstractCharacter implements IPlayerCharacter {
    protected IWeapon equippedWeapon = null;

    protected final PropertyChangeSupport playerTurnEvent = new PropertyChangeSupport(this);
    protected final PropertyChangeSupport playerDeathEvent = new PropertyChangeSupport(this);
    /**
     *  Creates a new Player Character with the parameters listed below
     * @param name
     *    name of the new player character
     * @param turnsQueue
     *    the queue with the characters waiting for their turn
     * @param health
     *    health of the new character
     * @param defense
     *    defense of the new character
     */
    public PlayerCharacter(@NotNull BlockingQueue<ICharacter> turnsQueue, @NotNull String name, int health, int defense) {
        super(turnsQueue, name, health, defense);
        setState(new PlayerAlive());
    }

    /**
     * Returns the character's equipped weapon
     */
    @Override
    public IWeapon getEquippedWeapon() { return equippedWeapon; }

    /**
     * Tries to equip a weapon to the character
     * @param weapon
     *     weapon that is trying to be equipped
     */
    @Override
    public void tryEquip(IWeapon weapon) { ((IPlayerState) state).equip(weapon); }

    /**
     * Method that makes a character wait for their turn depending on the
     * weight of their weapon
     */
    @Override
    public void waitTurn() {
        scheduledExecutor = Executors.newSingleThreadScheduledExecutor();
        scheduledExecutor.schedule(this::addToQueue, equippedWeapon.getWeight() / 10, TimeUnit.SECONDS);
    }

    /**
     * Attacks the specified character with their weapon
     * @param character
     *    Character to be attacked
     */
    @Override
    public void attack(ICharacter character) {
        character.tryAttacked(equippedWeapon.getDamage());
    }

    /**
     * The player character has died, notify the observers and change its state to dead
     */
    @Override
    public void die(){
        setState(new PlayerDead());
        playerDeathEvent.firePropertyChange(name+"'s has died",null,this);};

    /**
     * Fire the event that the turn holder is a player character
     */
    @Override
    public void tryTakeTurn() {
        state.takeTurn(playerTurnEvent,name);
    }

    /**
     * Adds a listener to the event of taking a turn of a player character
     * @param handler
     *      handles the turn of player character
     */
    @Override
    public void addTurnListener(IEventHandler handler) {
        playerTurnEvent.addPropertyChangeListener(handler);
    }

    /**
     * Adds a listener to the event of the death of the character
     * @param handler
     *      handles the death of player character
     */
    @Override
    public void addDeathListener(IEventHandler handler) {
        playerDeathEvent.addPropertyChangeListener(handler);
    }

    /**
     * @param obj
     *        object to check equality with
     * @return if two objects are equal, same class and same name
     */
    @Override
    public boolean equals(final Object obj){
        return obj.hashCode() == this.hashCode() && ((PlayerCharacter) obj).getName() == this.name;
    }
}
