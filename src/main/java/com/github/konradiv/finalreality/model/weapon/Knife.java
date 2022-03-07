package com.github.konradiv.finalreality.model.weapon;

import java.util.Objects;

public class Knife extends AbstractWeapon {

    /**
     * Creates a Knife.
     *
     * @param name
     *     the knife's name
     * @param damage
     *     the damage of the knife
     * @param weight
     *     the weight of the knife
     */

    public Knife(String name, int damage, int weight) {
        super(name, damage, weight);
    }

    @Override
    public int hashCode() { return Objects.hashCode(Knife.class); }

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
        return true;
    }
}
