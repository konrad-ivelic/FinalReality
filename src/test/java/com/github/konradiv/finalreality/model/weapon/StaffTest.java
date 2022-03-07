package com.github.konradiv.finalreality.model.weapon;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StaffTest {

    private static final String STAFF_NAME = "Test Staff";
    private static final int DAMAGE = 15;
    private static final int SPEED = 10;
    private static final int MAGIC_DAMAGE = 20;

    private Staff TestStaff;

    /**
     * Setup method.
     * Creates a new weapon for each class
     */
    @BeforeEach
    void setUp() {
        TestStaff = new Staff(STAFF_NAME, DAMAGE, SPEED,MAGIC_DAMAGE);
    }

    /**
     * Checks that the class' constructor and equals methods work properly.
     */
    @Test
    void constructorTest() {
        var expectedStaff = new Staff(STAFF_NAME, DAMAGE, SPEED,MAGIC_DAMAGE);

        assertEquals(expectedStaff, TestStaff);
        assertEquals(expectedStaff.hashCode(), TestStaff.hashCode());
        assertNotEquals(new Staff("Different", DAMAGE, SPEED,MAGIC_DAMAGE), TestStaff);
        assertNotEquals(new Bow("Different",DAMAGE,SPEED),TestStaff);
    }

    /**
     * Checks that the setters and getters methods work properly
     */
    @Test
    void BasicTest() {
        assertEquals(TestStaff.getName(), STAFF_NAME);

        assertEquals(TestStaff.getDamage(), DAMAGE);

        assertEquals(TestStaff.getWeight(), SPEED);

        assertEquals(TestStaff.getMagicDamage(),MAGIC_DAMAGE);
    }
}
