package com.github.konradiv.finalreality.model.character.player;

import com.github.konradiv.finalreality.model.character.ICharacter;
import com.github.konradiv.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.concurrent.BlockingQueue;

/**
 * Class that holds the behaviour of a Knight character
 *
 * @author Konrad Ivelic
 */
public class Knight extends AbstractCommonCharacter{

    /**
     * Creates a knight.
     *
     * @param name
     *     the knight's name
     * @param turnsQueue
     *     the queue with the characters waiting for their turn
     * @param defense
     *     the defense of the knight
     * @param health
     *     health of the knight
     */
    public Knight(@NotNull String name,
                  @NotNull BlockingQueue<ICharacter> turnsQueue, int health, int defense) {
        super(name, turnsQueue,health,defense);
    }

    /**
     * Returns the hash code of the Knight class
     */
    @Override
    public int hashCode() {
        return Objects.hashCode(Knight.class);
    }

    /**
     * Equips a weapon to the character if possible
     * @param Weapon
     *     weapon to be equipped
     */
    @Override
    public void equip(IWeapon Weapon) {
        if (Weapon.equipToKnight()){
            equippedWeapon = Weapon;
        }
    }
}
