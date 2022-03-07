package com.github.konradiv.finalreality.model.weapon;

import java.util.Objects;

public class Axe extends AbstractWeapon {

    /**
     * Creates a Axe.
     *
     * @param name
     *     the axe's name
     * @param damage
     *     the damage of the axe
     * @param weight
     *     the weight of the axe
     */
    public Axe(String name, int damage, int weight) {
        super(name, damage, weight);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(Axe.class);
    }

    @Override
    public boolean equipToKnight() {
        return true;
    }

    @Override
    public boolean equipToThief() {
        return false;
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
