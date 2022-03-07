package com.github.konradiv.finalreality.model.character;

import com.github.konradiv.finalreality.model.character.player.Engineer;
import com.github.konradiv.finalreality.model.character.player.Knight;
import com.github.konradiv.finalreality.model.character.player.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class EngineerTest extends AbstractCharacterTest {

    private static final String ENGINEER_NAME = "Emma";

    private Engineer TestEngineer;

    /**
     * Setup method.
     * Creates a new engineer character
     */
    @BeforeEach
    void setUp() {
        super.basicSetUp();
        TestEngineer = new Engineer(ENGINEER_NAME, turns,health,defense);
    }

    /**
     * Checks that the class' constructor,equals, setters and getters
     * methods work properly.
     */
    @Test
    void BasicTest() {
        checkConstruction(new Engineer(ENGINEER_NAME, turns,health,defense), TestEngineer, new Engineer("Different",turns,health,defense), new Knight("Test",turns,health,defense) );

        NameTest(TestEngineer,ENGINEER_NAME);

        HealthTest(TestEngineer,health);

        DefenseTest(TestEngineer,defense);
    }

    /**
     * Checks that the weapon is equipped properly
     */
    @Test
    void equipWeaponTest() {
        assertNull(TestEngineer.getEquippedWeapon());

        TestEngineer.tryEquip(testSword);

        assertNull(TestEngineer.getEquippedWeapon());

        TestEngineer.tryEquip(testStaff);

        assertNull(TestEngineer.getEquippedWeapon());

        TestEngineer.tryEquip(testKnife);

        assertNull(TestEngineer.getEquippedWeapon());

        TestEngineer.tryEquip(testAxe);

        assertEquals(testAxe, TestEngineer.getEquippedWeapon());

        TestEngineer.tryEquip(testBow);

        assertEquals(testBow, TestEngineer.getEquippedWeapon());
    }

    /**
     * Tests that the attack methods work properly
     */
    @Test
    void attackTest(){
        var testEnemy = new Enemy("Troll",10,30,10,100,turns);
        /* Enemy attacks engineer */
        testEnemy.tryAttack(TestEngineer);
        TestEngineer.tryEquip(testAxe);
        assertEquals(TestEngineer.getEquippedWeapon(),testAxe);

        /* Engineer attacks enemy */
        TestEngineer.tryAttack(testEnemy);
        assertEquals(testEnemy.getHealth(),25);

        /* Enemy kills engineer */
        testEnemy.tryAttack(TestEngineer);
        /* Engineer can't equip weapon because he is dead */
        TestEngineer.tryEquip(testBow);
        assertEquals(TestEngineer.getEquippedWeapon(),testAxe);

        /* Engineer can't attack because he is dead */
        TestEngineer.tryAttack(testEnemy);
        assertEquals(testEnemy.getHealth(),25);

        /* Engineer can't be attacked because he is dead */
        TestEngineer.tryAttacked(0);
    }
}
