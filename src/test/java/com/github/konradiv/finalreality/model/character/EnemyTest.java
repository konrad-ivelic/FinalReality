package com.github.konradiv.finalreality.model.character;

import com.github.konradiv.finalreality.model.character.player.Knight;
import com.github.konradiv.finalreality.model.character.player.Thief;
import com.github.konradiv.finalreality.model.weapon.Sword;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Set of tests for the Enemy class.
 *
 * @author Ignacio Slater Muñoz.
 * @author Konrad Ivelic
 */

class EnemyTest extends AbstractCharacterTest {

  private static final String ENEMY_NAME = "Goblin";
  private final int attack = 10;
  private final int weight = 10;
  private Enemy TestEnemy;

  /**
   * Setup method.
   * Creates a Enemy
   */
  @BeforeEach
  void setUp() {
    basicSetUp();
    TestEnemy = new Enemy(ENEMY_NAME,weight,health,defense,attack,turns);
  }

  /**
   * Checks that the class' constructor,equals, setters and getters
   * methods work properly.
   */
  @Test
  void BasicTest() {
    checkConstruction(new Enemy(ENEMY_NAME, 10,health,defense,attack,turns), TestEnemy, new Enemy("Troll", weight,health,defense,attack,turns), new Thief(ENEMY_NAME, turns,health,defense));

    assertEquals(TestEnemy.getWeight(),10);
    assertEquals(TestEnemy.getAttack(),attack);

    NameTest(TestEnemy,ENEMY_NAME);

    HealthTest(TestEnemy,health);

    DefenseTest(TestEnemy,defense);
  }

  /**
   * Tests that the attack methods work properly
   */
  @Test
  void attackTest(){
    var testKnight = new Knight("Capricornio",turns,30,5);
    testKnight.tryEquip(new Sword("Triforce",100,10));
    /* Enemy attacks knight */
    TestEnemy.tryAttack(testKnight);
    assertEquals(testKnight.getHealth(),25);

    /* Knight attacks enemy */
    testKnight.tryAttack(TestEnemy);
    assertEquals(TestEnemy.getHealth(),10);

    /* Knight kills enemy */
    testKnight.tryAttack(TestEnemy);
    assertEquals(TestEnemy.getHealth(),0);

    /* Enemy can't attack because he is dead */
    TestEnemy.tryAttack(testKnight);
    assertEquals(testKnight.getHealth(),25);

    /* Enemy can't be attacked because he is dead */
    TestEnemy.tryAttacked(0);
  }
}