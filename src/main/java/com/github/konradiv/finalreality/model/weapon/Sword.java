package com.github.konradiv.finalreality.model.weapon;

import java.util.Objects;

public class Sword extends AbstractWeapon {

    /**
     * Creates a Sword.
     *
     * @param name
     *     the sword's name
     * @param damage
     *     the damage of the sword
     * @param weight
     *     the weight of the sword
     */

    public Sword(String name, int damage, int weight) {
        super(name, damage, weight);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(Sword.class);
    }

    @Override
    public boolean equipToKnight() {
        return true;
    }

    @Override
    public boolean equipToThief() {
        return true;
    }

    @Override
    public boolean equipToEngineer() {
        return false;
    }

    @Override
    public boolean equipToWhiteMage() {
        return false;
    }

    @Override
    public boolean equipToBlackMage() {
        return false;
    }
}
