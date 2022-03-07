package com.github.konradiv.finalreality.model.character;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import com.github.konradiv.finalreality.model.character.player.Knight;
import com.github.konradiv.finalreality.model.character.player.WhiteMage;
import com.github.konradiv.finalreality.model.weapon.*;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

/**
 * Abstract class containing the common tests for all the types of characters.
 *
 * @author Ignacio Slater Muñoz.
 * @author Konrad Ivelic
 * @see ICharacter
 */
public abstract class AbstractCharacterTest {

  protected BlockingQueue<ICharacter> turns;
  protected IWeapon testAxe;
  protected IWeapon testBow;
  protected IWeapon testStaff;
  protected IWeapon testSword;
  protected IWeapon testKnife;
  protected int health = 100;
  protected int defense = 10;

  /**
   * Checks the construction of an AbstractCharacter
   * @param expectedCharacter
   *      character expected to be equal to
   * @param testEqualCharacter
   *      character to test equality
   * @param sameClassDifferentCharacter
   *      character of same class but different character
   * @param differentClassCharacter
   *      character from different class
   */
  protected void checkConstruction(final ICharacter expectedCharacter,
      final ICharacter testEqualCharacter,
      final ICharacter sameClassDifferentCharacter,
      final ICharacter differentClassCharacter) {
    assertEquals(expectedCharacter, testEqualCharacter);
    assertNotEquals(sameClassDifferentCharacter, testEqualCharacter);
    assertNotEquals(testEqualCharacter, differentClassCharacter);
    assertNotEquals(expectedCharacter,differentClassCharacter);
    assertEquals(expectedCharacter.hashCode(), testEqualCharacter.hashCode());
    assertEquals(testEqualCharacter.hashCode(),expectedCharacter.hashCode());
    assertNotEquals(expectedCharacter.hashCode(),differentClassCharacter.hashCode());
    assertNotEquals(testEqualCharacter.hashCode(),differentClassCharacter.hashCode());
  }

  /**
   * Basic setup for a test
   * Creates a new queue and a new axe to test with
   */
  protected void basicSetUp() {
    turns = new LinkedBlockingQueue<>();
    testAxe = new Axe("Test", 15, 10);
    testBow = new Bow("Test",15,10);
    testStaff = new Staff("Test",15,10,20);
    testKnife = new Knife("Test",15,10);
    testSword = new Sword("Test",15,10);
  }

  /**
   * Tests that the name getters works properly
   * @param Character
   *    Character to test the name of
   * @param Name
   *    Name the character should have
   */
  protected void NameTest(ICharacter Character, String Name){
    assertEquals(Character.getName(),Name);
    assertNotEquals(Character.getName(),"Test Name");
  }

  /**
   * Tests that the health setters and getters works properly
   * @param Character
   *    character to test the health of
   * @param health
   *    health that the character should have to start with
   */
  protected void HealthTest(ICharacter Character, int health){
    assertEquals(Character.getHealth(),health);
    Character.setHealth(health + 100);
    assertEquals(Character.getHealth(),health + 100);
  }

  /**
   * Tests that the defense getter works properly
   * @param Character
   *    character to test the defense of
   * @param defense
   *    defense that the character should have
   */
  protected void DefenseTest(ICharacter Character, int defense){
    assertEquals(Character.getDefense(),defense);
    assertNotEquals(Character.getDefense(),defense+100);
  }

}
