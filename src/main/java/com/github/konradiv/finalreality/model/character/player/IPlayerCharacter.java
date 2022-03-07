package com.github.konradiv.finalreality.model.character.player;

import com.github.konradiv.finalreality.model.character.ICharacter;
import com.github.konradiv.finalreality.model.weapon.IWeapon;

/**
 * This represents a character from the game controlled by the player.
 *
 * @author Konrad Ivelic
 */
public interface IPlayerCharacter extends ICharacter {

    /**
     * Tries to equip a weapon to the character
     */
    void tryEquip(IWeapon weapon);

    /**
     * Equips a weapon to the character.
     */
    void equip(IWeapon Weapon);

    /**
     * Returns the weapon equipped by the character
     */
    IWeapon getEquippedWeapon();

}
