package com.github.konradiv.finalreality.model.character.player;

import com.github.konradiv.finalreality.model.character.ICharacter;
import com.github.konradiv.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * Class that holds the behaviour of a White Mage character
 *
 * @author Konrad Ivelic
 */
public class WhiteMage extends AbstractMage {

    /**
     * Creates a new white mage.
     *
     * @param name
     *      the white mage's name
     * @param turnsQueue
     *      the queue with the characters waiting for their turn
     * @param defense
     *      the defense of the white mage
     * @param health
     *      health of the white mage
     * @param mana
     *      mana of the white mage
     */
    public WhiteMage(@NotNull String name,
                     @NotNull BlockingQueue<ICharacter> turnsQueue, int health, int defense, int mana) {
        super(name, turnsQueue,health,defense, mana);
    }

    /**
     * Returns the hash code of the WhiteMage class
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(WhiteMage.class);
    }

    /**
     * Equips a weapon to the character if possible
     * @param Weapon
     *     weapon to be equipped
     */
    @Override
    public void equip(IWeapon Weapon) {
        if (Weapon.equipToWhiteMage()){
            equippedWeapon = Weapon;
        }
    }
}
