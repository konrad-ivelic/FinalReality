package com.github.konradiv.finalreality.model.character;

import com.github.konradiv.finalreality.model.character.player.BlackMage;
import com.github.konradiv.finalreality.model.character.player.WhiteMage;
import com.github.konradiv.finalreality.model.weapon.Staff;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class WhiteMageTest extends AbstractCharacterTest{

    private static final String WHITE_MAGE_NAME = "Eiko";

    private final int mana = 100;

    private WhiteMage TestWhiteMage;

    /**
     * Setup method.
     * Creates a new white mage character
     */
    @BeforeEach
    void setUp() {
        super.basicSetUp();

        TestWhiteMage = new WhiteMage(WHITE_MAGE_NAME, turns,health,defense,mana);
    }

    /**
     * Checks that the class' constructor,equals, setters and getters
     * methods work properly.
     */
    @Test
    void BasicTest() {
        checkConstruction(new WhiteMage(WHITE_MAGE_NAME,turns,health,defense,mana),TestWhiteMage,new WhiteMage("Different",turns,health,defense,mana),new BlackMage("Sauron",turns,health,defense,mana));

        assertEquals(TestWhiteMage.getMana(),100);

        TestWhiteMage.setMana(200);

        assertEquals(TestWhiteMage.getMana(),200);

        NameTest(TestWhiteMage,WHITE_MAGE_NAME);

        HealthTest(TestWhiteMage,health);

        DefenseTest(TestWhiteMage,defense);
    }

    /**
     * Checks that the weapon is equipped properly
     */
    @Test
    void equipWeaponTest() {
        assertNull(TestWhiteMage.getEquippedWeapon());

        TestWhiteMage.tryEquip(testAxe);

        assertNull(TestWhiteMage.getEquippedWeapon());

        TestWhiteMage.tryEquip(testBow);

        assertNull(TestWhiteMage.getEquippedWeapon());

        TestWhiteMage.tryEquip(testKnife);

        assertNull(TestWhiteMage.getEquippedWeapon());

        TestWhiteMage.tryEquip(testSword);

        assertNull(TestWhiteMage.getEquippedWeapon());

        TestWhiteMage.tryEquip(testStaff);

        assertEquals(testStaff, TestWhiteMage.getEquippedWeapon());
    }

    /**
     * Tests that the attack methods work properly
     */
    @Test
    void attackTest(){
        var testEnemy = new Enemy("Troll",10,30,10,100,turns);
        /* Enemy attacks black mage */
        testEnemy.tryAttack(TestWhiteMage);
        TestWhiteMage.tryEquip(testStaff);
        assertEquals(TestWhiteMage.getEquippedWeapon(),testStaff);

        /* White mage attacks enemy */
        TestWhiteMage.tryAttack(testEnemy);
        assertEquals(testEnemy.getHealth(),25);

        /* Enemy kills white mage */
        testEnemy.tryAttack(TestWhiteMage);
        /* White mage can't equip weapon because he is dead */
        TestWhiteMage.tryEquip(new Staff("Powerful staff",100,100,100));
        assertEquals(TestWhiteMage.getEquippedWeapon(),testStaff);

        /* White mage can't attack because he is dead */
        TestWhiteMage.tryAttack(testEnemy);
        assertEquals(testEnemy.getHealth(),25);

        /* White mage can't be attacked because he is dead */
        TestWhiteMage.tryAttacked(0);
    }
}
