package com.github.konradiv.finalreality.model.weapon;

/**
 * A class that holds all the information of a weapon.
 *
 * @author Ignacio Slater Muñoz.
 * @author Konrad Ivelic
 */
public abstract class AbstractWeapon implements IWeapon {

  private final String name;
  private final int damage;
  private final int weight;

  /**
   * Creates a weapon with parameters named below
   *
   * @param name
   *     name of the new weapon
   * @param damage
   *     damage of the new weapon
   * @param weight
   *     weight of the new weapon
   */
  protected AbstractWeapon(String name, int damage, int weight) {
    this.name = name;
    this.damage = damage;
    this.weight = weight;
  }

  /**
   * Returns this weapon's name
   */
  public String getName() {
    return name;
  }

  /**
   * Returns this weapon's damage
   */
  public int getDamage() {
    return damage;
  }

  /**
   * Returns this weapon's weight
   */
  public int getWeight() {
    return weight;
  }

  /**
   *
   * @param obj
   *      object to check equality with
   * @return
   *      if two objects are equal, same class and same name
   */
  @Override
  public boolean equals(final Object obj) {
    return obj.hashCode() == this.hashCode() && ((AbstractWeapon) obj).name == this.name;
  }
}
