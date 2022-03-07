package com.github.konradiv.finalreality.model.weapon;

import java.util.Objects;

public class Bow extends AbstractWeapon{

    /**
     * Creates a Bow.
     *
     * @param name
     *     the bow's name
     * @param damage
     *     the damage of the bow
     * @param weight
     *     the weight of the bow
     */

    public Bow(String name, int damage, int weight) {
        super(name, damage, weight);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(Bow.class);
    }

    @Override
    public boolean equipToKnight() {
        return false;
    }

    @Override
    public boolean equipToThief() {
        return true;
    }

    @Override
    public boolean equipToEngineer() {
        return true;
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
