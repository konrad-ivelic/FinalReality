package com.github.konradiv.finalreality.model.character;

import com.github.konradiv.finalreality.model.character.player.BlackMage;
import com.github.konradiv.finalreality.model.character.player.WhiteMage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class BlackMageTest extends AbstractCharacterTest{

    private static final String BLACK_MAGE_NAME = "Vivi";

    private final int mana = 100;

    private BlackMage TestBlackMage;

    /**
     * Setup method.
     * Creates a new character for each class in mage characters
     */
    @BeforeEach
    void setUp() {
        super.basicSetUp();

        TestBlackMage = new BlackMage(BLACK_MAGE_NAME, turns,health,defense,mana);
    }

    /**
     * Checks that the class' constructor,equals, setters and getters
     * methods work properly.
     */
    @Test
    void BasicTest() {
        checkConstruction(new BlackMage(BLACK_MAGE_NAME,turns,health,defense,mana),TestBlackMage,new BlackMage("Different",turns,health,defense,mana),new WhiteMage("Gandalf",turns,health,defense,mana));

        assertEquals(TestBlackMage.getMana(),100);

        TestBlackMage.setMana(200);

        assertEquals(TestBlackMage.getMana(),200);

        NameTest(TestBlackMage,BLACK_MAGE_NAME);

        HealthTest(TestBlackMage,health);

        DefenseTest(TestBlackMage,defense);
    }

    /**
     * Checks that the weapon is equipped properly
     */
    @Test
    void equipWeaponTest() {
        assertNull(TestBlackMage.getEquippedWeapon());

        TestBlackMage.tryEquip(testAxe);

        assertNull(TestBlackMage.getEquippedWeapon());

        TestBlackMage.tryEquip(testSword);

        assertNull(TestBlackMage.getEquippedWeapon());

        TestBlackMage.tryEquip(testBow);

        assertNull(TestBlackMage.getEquippedWeapon());

        TestBlackMage.tryEquip(testKnife);

        assertEquals(testKnife, TestBlackMage.getEquippedWeapon());

        TestBlackMage.tryEquip(testStaff);

        assertEquals(testStaff, TestBlackMage.getEquippedWeapon());
    }

    /**
     * Tests that the attack methods work properly
     */
    @Test
    void attackTest(){
        var testEnemy = new Enemy("Troll",10,30,10,100,turns);
        /* Enemy attacks black mage */
        testEnemy.tryAttack(TestBlackMage);
        TestBlackMage.tryEquip(testStaff);
        assertEquals(TestBlackMage.getEquippedWeapon(),testStaff);

        /* Black mage attacks enemy */
        TestBlackMage.tryAttack(testEnemy);
        assertEquals(testEnemy.getHealth(),25);

        /* Enemy kills black mage */
        testEnemy.tryAttack(TestBlackMage);
        /* Black mage can't equip weapon because he is dead */
        TestBlackMage.tryEquip(testKnife);
        assertEquals(TestBlackMage.getEquippedWeapon(),testStaff);

        /* Black mage can't attack because he is dead */
        TestBlackMage.tryAttack(testEnemy);
        assertEquals(testEnemy.getHealth(),25);

        /* Black mage can't be attacked because he is dead */
        TestBlackMage.tryAttacked(0);
    }
}
