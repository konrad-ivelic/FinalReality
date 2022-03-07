package com.github.konradiv.finalreality.model.character.player;

import com.github.konradiv.finalreality.model.character.ICharacter;
import com.github.konradiv.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * Class that holds the behaviour of a Engineer character
 *
 * @author Konrad Ivelic
 */
public class Engineer extends AbstractCommonCharacter{

    /**
     * Creates a new engineer.
     *
     * @param name
     *     the engineer's name
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     * @param defense
     *     the defense of the engineer
     * @param health
     *      health of the engineer
     */
    public Engineer(@NotNull String name,
                           @NotNull BlockingQueue<ICharacter> turnsQueue, int health, int defense) {
        super(name, turnsQueue,health,defense);
    }

    /**
     * Returns the hash code of the Engineer class
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(Engineer.class);
    }

    /**
     * Equips a weapon to the character if possible
     * @param Weapon
     *     weapon to be equipped
     */
    @Override
    public void equip(IWeapon Weapon) {
        if (Weapon.equipToEngineer()){
            equippedWeapon = Weapon;
        }
    }
}
