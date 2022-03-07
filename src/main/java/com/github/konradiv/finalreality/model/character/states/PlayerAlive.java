package com.github.konradiv.finalreality.model.character.states;

import com.github.konradiv.finalreality.controller.handlers.IEventHandler;
import com.github.konradiv.finalreality.controller.handlers.PlayerTurnHandler;
import com.github.konradiv.finalreality.model.character.ICharacter;
import com.github.konradiv.finalreality.model.weapon.IWeapon;

import java.beans.PropertyChangeSupport;

/**
 * Holds the behaviour of an alive player
 */
public class PlayerAlive extends PlayerState{
    public PlayerAlive() {
        super();
    }

    /**
     * Equips the weapon to the character
     * @param weapon
     *     weapon to be equipped
     */
    public void equip(IWeapon weapon){character.equip(weapon);}

    /**
     * The character receives the attack because he is still alive
     * @param damage
     *     damage to be taken
     */
    @Override
    public void attacked(int damage) {character.attacked(damage);}

    /**
     * The character attacks the given character because he is alive
     * @param attackedCharacter
     *     character that is attacked
     */
    @Override
    public void attack(ICharacter attackedCharacter){character.attack(attackedCharacter);}

    /**
     * Fire the event that the turn holder is a player character
     */
    @Override
    public void takeTurn(PropertyChangeSupport property,String name) {
        property.firePropertyChange(name+"'s turn",null,character);
    }
}
