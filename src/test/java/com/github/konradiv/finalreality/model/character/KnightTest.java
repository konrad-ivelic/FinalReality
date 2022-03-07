package com.github.konradiv.finalreality.model.character;

import com.github.konradiv.finalreality.model.character.player.Engineer;
import com.github.konradiv.finalreality.model.character.player.Knight;
import com.github.konradiv.finalreality.model.character.player.Thief;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class KnightTest extends AbstractCharacterTest{

    private static final String KNIGHT_NAME = "Vinko";


    private Knight TestKnight;

    /**
     * Setup method.
     * Creates a new knight character
     */
    @BeforeEach
    void setUp() {
        super.basicSetUp();
        TestKnight = new Knight(KNIGHT_NAME,turns,health,defense);
    }

    /**
     * Checks that the class' constructor,equals, setters and getters
     * methods work properly.
     */
    @Test
    void BasicTest() {
        checkConstruction(new Knight(KNIGHT_NAME, turns,health,defense), TestKnight, new Knight("Different",turns,health,defense), new Thief("Pinera",turns,health,defense) );

        NameTest(TestKnight,KNIGHT_NAME);

        HealthTest(TestKnight,health);

        DefenseTest(TestKnight,defense);
    }

    /**
     * Checks that the weapon is equipped properly
     */
    @Test
    void equipWeaponTest() {
        assertNull(TestKnight.getEquippedWeapon());

        TestKnight.tryEquip(testStaff);

        assertNull(TestKnight.getEquippedWeapon());

        TestKnight.tryEquip(testBow);

        assertNull(TestKnight.getEquippedWeapon());

        TestKnight.tryEquip(testAxe);

        assertEquals(testAxe, TestKnight.getEquippedWeapon());

        TestKnight.tryEquip(testSword);

        assertEquals(testSword, TestKnight.getEquippedWeapon());

        TestKnight.tryEquip(testKnife);

        assertEquals(testKnife, TestKnight.getEquippedWeapon());
    }

    /**
     * Tests that the attack methods work properly
     */
    @Test
    void attackTest(){
        var testEnemy = new Enemy("Troll",10,30,10,100,turns);
        /* Enemy attacks knight */
        testEnemy.tryAttack(TestKnight);
        TestKnight.tryEquip(testSword);
        assertEquals(TestKnight.getEquippedWeapon(),testSword);

        /* Knight attacks enemy */
        TestKnight.tryAttack(testEnemy);
        assertEquals(testEnemy.getHealth(),25);

        /* Enemy kills knight */
        testEnemy.tryAttack(TestKnight);
        /* Knight can't equip weapon because he is dead */
        TestKnight.tryEquip(testKnife);
        assertEquals(TestKnight.getEquippedWeapon(),testSword);

        /* Knight can't attack because he is dead */
        TestKnight.tryAttack(testEnemy);
        assertEquals(testEnemy.getHealth(),25);

        /* Knight can't be attacked because he is dead */
        TestKnight.tryAttacked(0);
    }
}
