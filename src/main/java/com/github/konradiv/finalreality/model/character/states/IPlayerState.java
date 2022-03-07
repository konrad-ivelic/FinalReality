package com.github.konradiv.finalreality.model.character.states;

import com.github.konradiv.finalreality.model.weapon.IWeapon;

/**
 * This represents the state of a player character
 */
public interface IPlayerState extends IState{

    /**
     * Tries to equip a weapon to the player character
     */
    public void equip(IWeapon weapon);
}
