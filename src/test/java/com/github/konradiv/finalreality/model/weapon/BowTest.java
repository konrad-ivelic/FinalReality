package com.github.konradiv.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BowTest {

    private static final String BOW_NAME = "Test Bow";
    private static final int DAMAGE = 15;
    private static final int SPEED = 10;

    private Bow TestBow;

    /**
     * Setup method.
     * Creates a new weapon for each class
     */
    @BeforeEach
    void setUp() {
        TestBow = new Bow(BOW_NAME, DAMAGE, SPEED);
    }

    /**
     * Checks that the class' constructor and equals methods work properly.
     */
    @Test
    void constructorTest() {
        var expectedBow = new Bow(BOW_NAME, DAMAGE, SPEED);

        assertEquals(expectedBow, TestBow);
        assertEquals(expectedBow.hashCode(), TestBow.hashCode());
        assertNotEquals(new Bow("Different", DAMAGE, SPEED), TestBow);
        assertNotEquals(new Axe("Different",DAMAGE,SPEED),TestBow);
    }

    /**
     * Checks that the setters and getters methods work properly
     */
    @Test
    void BasicTest() {
        assertEquals(TestBow.getName(), BOW_NAME);

        assertEquals(TestBow.getDamage(), DAMAGE);

        assertEquals(TestBow.getWeight(), SPEED);
    }
}
