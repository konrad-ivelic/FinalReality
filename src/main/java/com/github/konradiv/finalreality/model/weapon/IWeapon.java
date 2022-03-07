package com.github.konradiv.finalreality.model.weapon;

/**
 *
 * This represents a weapon from the game.
 * A weapon can be equipped by a Player Character.
 *
 */
public interface IWeapon {

    /**
     * Returns this weapon's name
     */
    String getName();

    /**
     * Returns this weapon's damage
     */
    int getDamage();

    /**
     * Returns this weapon's weight
     */
    int getWeight();

    /**
     * Returns if the weapon is able to be equipped by the knight
     */
    boolean equipToKnight();

    /**
     * Returns if the weapon is able to be equipped by the thief
     */
    boolean equipToThief();

    /**
     * Returns if the weapon is able to be equipped by the engineer
     */
    boolean equipToEngineer();

    /**
     * Returns if the weapon is able to be equipped by the white mage
     */
    boolean equipToWhiteMage();

    /**
     * Returns if the weapon is able to be equipped by the black mage
     */
    boolean equipToBlackMage();

}
