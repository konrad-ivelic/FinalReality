package com.github.konradiv.finalreality.model.character.player;

import com.github.konradiv.finalreality.model.character.ICharacter;
import com.github.konradiv.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * Class that holds the behaviour of a Black Mage character
 *
 * @author Konrad Ivelic
 */
public class BlackMage extends AbstractMage {

    private int mana;

    /**
     * Creates a new black mage.
     *
     * @param name
     *      the black mage's name
     * @param turnsQueue
     *      the queue with the black mage waiting for their turn
     * @param defense
     *      the defense of the black mage
     * @param health
     *      health of the black mage
     * @param mana
     *      mana of the black mage
     */
    public BlackMage(@NotNull String name,
                     @NotNull BlockingQueue<ICharacter> turnsQueue, int health, int defense, int mana) {
        super(name, turnsQueue,health,defense, mana);
    }

    /**
     * Returns the hash code of the BlackMage class
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(BlackMage.class);
    }

    /**
     * Equips a weapon to the character
     * @param Weapon
     *    Weapon to be equipped to the character
     */
    @Override
    public void equip(IWeapon Weapon) {
        if (Weapon.equipToBlackMage()){
           equippedWeapon = Weapon;
        }
    }
}
