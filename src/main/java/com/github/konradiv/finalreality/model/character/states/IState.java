package com.github.konradiv.finalreality.model.character.states;

import com.github.konradiv.finalreality.model.character.ICharacter;

import java.beans.PropertyChangeSupport;

/**
 * This represents the a state of any character of the game
 */
public interface IState {

    /**
     * Sets the character that is in the state
     */
    void setCharacter(ICharacter character);

    /**
     * The character gets attacked or not depending of their state
     */
    void attacked(int damage);

    /**
     * The character attacks another character or not depending of their state
     */
    void attack(ICharacter attackedCharacter);

    /**
     * The character takes their turn or not depending of their state.
     * If they take it they notify the listener observing it
     */
    void takeTurn(PropertyChangeSupport property,String name);
}
