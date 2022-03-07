package com.github.konradiv.finalreality.model.character.player;

import com.github.konradiv.finalreality.model.character.AbstractCharacter;
import com.github.konradiv.finalreality.model.character.ICharacter;
import com.github.konradiv.finalreality.model.weapon.AbstractWeapon;
import com.github.konradiv.finalreality.model.weapon.IWeapon;
import org.jetbrains.annotations.NotNull;

import java.util.Hashtable;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * An abstract class that holds the behaviour of a mage character of the game
 *
 * @author Konrad Ivelic
 */
public abstract class AbstractMage extends PlayerCharacter {
    private int mana;

    /**
     *  Creates a new Mage with the parameters listed below
     * @param name
     *    name of the new mage
     * @param turnsQueue
     *    the queue with the characters waiting for their turn
     * @param health
     *    health of the new mage
     * @param defense
     *    defense of the new mage
     * @param mana
     *    mana of the new mage
     */
    public AbstractMage(@NotNull String name,
                                   @NotNull BlockingQueue<ICharacter> turnsQueue, int health, int defense, int mana) {
        super(turnsQueue, name, health, defense);
        this.mana = mana;
    }

    /**
     * Sets the mage mana to the value received
     * @param mana
     *    new mana of the mage
     */
    public void setMana(int mana) { this.mana = mana; }

    /**
     * Returns the mana of the mage
     */
    public int getMana() { return mana; }

    /**
     * Returns the data of a mage
     */
    @Override
    public Hashtable<String, Object> getData() {
        Hashtable<String,Object> data = new Hashtable<>();
        data.put("Name:",name);
        data.put("Health:",health);
        data.put("Defense:",defense);
        data.put("Mana:",mana);
        return data;
    }
}
