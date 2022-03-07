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
 * An abstract class that holds the behaviour of a common character of the game
 *
 * @author Konrad Ivelic
 */
public  abstract class AbstractCommonCharacter extends PlayerCharacter {

    /**
     *  Creates a new Common Character with the parameters listed below
     * @param name
     *    name of the new common character
     * @param turnsQueue
     *    the queue with the characters waiting for their turn
     * @param health
     *    health of the new character
     * @param defense
     *    defense of the new character
     */
    protected AbstractCommonCharacter(@NotNull String name,
                           @NotNull BlockingQueue<ICharacter> turnsQueue, int health, int defense) {
        super(turnsQueue, name, health, defense);
    }

    /**
     * Returns the data of a common character
     */
    @Override
    public Hashtable<String, Object> getData() {
        Hashtable<String,Object> data = new Hashtable<>();
        data.put("Name:",name);
        data.put("Health:",health);
        data.put("Defense:",defense);
        return data;
    }
}
