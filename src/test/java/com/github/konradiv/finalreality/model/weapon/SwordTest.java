package com.github.konradiv.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SwordTest {

    private static final String SWORD_NAME = "Test Sword";
    private static final int DAMAGE = 15;
    private static final int SPEED = 10;

    private Sword TestSword;

    /**
     * Setup method.
     * Creates a new weapon for each class
     */
    @BeforeEach
    void setUp() {
        TestSword = new Sword(SWORD_NAME, DAMAGE, SPEED);
    }

    /**
     * Checks that the class' constructor and equals methods work properly.
     */
    @Test
    void constructorTest() {
        var expectedSword = new Sword(SWORD_NAME, DAMAGE, SPEED);

        assertEquals(expectedSword, TestSword);
        assertEquals(expectedSword.hashCode(), TestSword.hashCode());
        assertNotEquals(new Sword("Different", DAMAGE, SPEED), TestSword);
        assertNotEquals(new Bow("Different",DAMAGE,SPEED), TestSword);
    }

    /**
     * Checks that the setters and getters methods work properly
     */
    @Test
    void BasicTest() {
        assertEquals(TestSword.getName(), SWORD_NAME);

        assertEquals(TestSword.getDamage(), DAMAGE);

        assertEquals(TestSword.getWeight(), SPEED);
    }
}
