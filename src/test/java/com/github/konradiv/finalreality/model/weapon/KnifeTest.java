package com.github.konradiv.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class KnifeTest {

    private static final String KNIFE_NAME = "Test Knife";
    private static final int DAMAGE = 15;
    private static final int SPEED = 10;

    private Knife TestKnife;

    /**
     * Setup method.
     * Creates a new weapon for each class
     */
    @BeforeEach
    void setUp() {
        TestKnife = new Knife(KNIFE_NAME, DAMAGE, SPEED);
    }

    /**
     * Checks that the class' constructor and equals methods work properly.
     */
    @Test
    void constructorTest() {
        var expectedKnife = new Knife(KNIFE_NAME, DAMAGE, SPEED);

        assertEquals(expectedKnife, TestKnife);
        assertEquals(expectedKnife.hashCode(), TestKnife.hashCode());
        assertNotEquals(new Knife("Different", DAMAGE, SPEED), TestKnife);
        assertNotEquals(new Bow("Different",DAMAGE,SPEED),TestKnife);
    }

    /**
     * Checks that the setters and getters methods work properly
     */
    @Test
    void BasicTest() {
        assertEquals(TestKnife.getName(), KNIFE_NAME);

        assertEquals(TestKnife.getDamage(), DAMAGE);

        assertEquals(TestKnife.getWeight(), SPEED);
    }
}
