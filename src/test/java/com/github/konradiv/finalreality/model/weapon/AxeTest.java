package com.github.konradiv.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Set of tests of the Axe class
 */
public class AxeTest {
    private static final String AXE_NAME = "Test Axe";
    private static final int DAMAGE = 15;
    private static final int SPEED = 10;

    private Axe TestAxe;

    /**
     * Setup method.
     * Creates a new weapon for each class
     */
    @BeforeEach
    void setUp() {
        TestAxe = new Axe(AXE_NAME, DAMAGE, SPEED);
    }

    /**
     * Checks that the class' constructor and equals methods work properly.
     */
    @Test
    void constructorTest() {
        var expectedAxe = new Axe(AXE_NAME, DAMAGE, SPEED);

        assertEquals(expectedAxe, TestAxe);
        assertEquals(expectedAxe.hashCode(), TestAxe.hashCode());
        assertNotEquals(new Axe("Different", DAMAGE, SPEED), TestAxe);
        assertNotEquals(new Bow("Different",DAMAGE,SPEED),TestAxe);
    }

    /**
     * Checks that the setters and getters methods work properly
     */
    @Test
    void BasicTest() {
        assertEquals(TestAxe.getName(), AXE_NAME);

        assertEquals(TestAxe.getDamage(), DAMAGE);

        assertEquals(TestAxe.getWeight(), SPEED);
    }
}
