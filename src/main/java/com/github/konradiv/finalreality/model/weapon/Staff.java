package com.github.konradiv.finalreality.model.weapon;

import java.util.Objects;

public class Staff extends AbstractWeapon {
    private int magicDamage;

    /**
     * Creates a Staff.
     *
     * @param name
     *     the staff's name
     * @param damage
     *     the damage of the staff
     * @param weight
     *     the weight of the staff
     * @param magicDamage
     *     magic damage of the staff
     */
    public Staff(String name, int damage, int weight,int magicDamage)
    {
        super(name, damage, weight);
        this.magicDamage = magicDamage;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(Staff.class);
    }

    public int getMagicDamage() {
        return magicDamage;
    }

    @Override
    public boolean equipToKnight() {
        return false;
    }

    @Override
    public boolean equipToThief() {
        return false;
    }

    @Override
    public boolean equipToEngineer() {
        return false;
    }

    @Override
    public boolean equipToWhiteMage() {
        return true;
    }

    @Override
    public boolean equipToBlackMage() {
        return true;
    }
}
