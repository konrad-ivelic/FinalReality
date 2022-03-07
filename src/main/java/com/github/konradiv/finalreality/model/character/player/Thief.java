package com.github.konradiv.finalreality.model.character.player;

import com.github.konradiv.finalreality.model.character.ICharacter;
import com.github.konradiv.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * Class that holds the behaviour of a Thief character
 *
 * @author Konrad Ivelic
 */
public class Thief extends AbstractCommonCharacter{

    /**
     * Creates a new thief.
     *
     * @param name
     *      the thief's name
     * @param turnsQueue
     *      the queue with the characters waiting for their turn
     * @param defense
     *      the defense of the thief
     * @param health
     *      health of the thief
     */
    public Thief(@NotNull String name,
                 @NotNull BlockingQueue<ICharacter> turnsQueue, int health, int defense) {
        super(name, turnsQueue,health,defense);
    }

    /**
     * Returns the hash code of the Thief class
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(Thief.class);
    }

    /**
     * Equips a weapon to the character if possible
     * @param Weapon
     *     weapon to be equipped
     */
    @Override
    public void equip(IWeapon Weapon) {
        if (Weapon.equipToThief()){
            equippedWeapon = Weapon;
        }
    }
}
