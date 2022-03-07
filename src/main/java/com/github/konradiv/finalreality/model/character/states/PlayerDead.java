package com.github.konradiv.finalreality.model.character.states;

import com.github.konradiv.finalreality.model.character.ICharacter;
import com.github.konradiv.finalreality.model.character.player.IPlayerCharacter;
import com.github.konradiv.finalreality.model.weapon.IWeapon;

import java.beans.PropertyChangeSupport;

/**
 * Holds the behaviour of a dead player character
 */
public class PlayerDead extends PlayerState {

    public PlayerDead() {
        super();
    }

    /**
     * The character cannot equip the weapon because he is dead
     */
    public void equip(IWeapon weapon){}

    /**
     * The character cannot be attacked because he is dead
     */
    @Override
    public void attacked(int damage) {}

    /**
     * The character cannot attack because he is dead
     */
    @Override
    public void attack(ICharacter attackedCharacter){}

    /**
     * The character cannot take his turn because he is dead
     */
    @Override
    public void takeTurn(PropertyChangeSupport property, String name) { }
}
