package com.github.konradiv.finalreality.model.character;

import com.github.konradiv.finalreality.model.character.player.Engineer;
import com.github.konradiv.finalreality.model.character.player.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class ThiefTest extends AbstractCharacterTest{

    private static final String THIEF_NAME = "Gandhi";

    private Thief TestThief;

    /**
     * Setup method.
     * Creates a new thief character for the tests
     */
    @BeforeEach
    void setUp() {
        super.basicSetUp();
        TestThief = new Thief(THIEF_NAME, turns, health, defense);
    }

    /**
     * Checks that the class' constructor,equals, setters and getters
     * methods work properly.
     */
    @Test
    void BasicTest() {
        checkConstruction(new Thief(THIEF_NAME, turns,health,defense), TestThief, new Thief("Different",turns,health,defense), new Engineer("Konrad",turns,health,defense) );

        NameTest(TestThief,THIEF_NAME);

        HealthTest(TestThief,health);

        DefenseTest(TestThief,defense);
    }

    /**
     * Checks that the weapon is equipped properly
     */
    @Test
    void equipWeaponTest() {
        assertNull(TestThief.getEquippedWeapon());

        TestThief.tryEquip(testAxe);

        assertNull(TestThief.getEquippedWeapon());

        TestThief.tryEquip(testStaff);

        assertNull(TestThief.getEquippedWeapon());

        TestThief.tryEquip(testSword);

        assertEquals(testSword, TestThief.getEquippedWeapon());

        TestThief.tryEquip(testBow);

        assertEquals(testBow, TestThief.getEquippedWeapon());

        TestThief.tryEquip(testKnife);

        assertEquals(testKnife, TestThief.getEquippedWeapon());
    }

    /**
     * Tests that the attack methods work properly
     */
    @Test
    void attackTest(){
        var testEnemy = new Enemy("Troll",10,30,10,100,turns);
        /* Enemy attacks thief */
        testEnemy.tryAttack(TestThief);
        TestThief.tryEquip(testSword);
        assertEquals(TestThief.getEquippedWeapon(),testSword);

        /* Thief attacks enemy */
        TestThief.tryAttack(testEnemy);
        assertEquals(testEnemy.getHealth(),25);

        /* Enemy kills thief */
        testEnemy.tryAttack(TestThief);
        /* Thief can't equip weapon because he is dead */
        TestThief.tryEquip(testKnife);
        assertEquals(TestThief.getEquippedWeapon(),testSword);

        /* Thief can't attack because he is dead */
        TestThief.tryAttack(testEnemy);
        assertEquals(testEnemy.getHealth(),25);

        /* Thief can't be attacked because he is dead */
        TestThief.tryAttacked(0);
    }
}
