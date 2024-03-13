package cz.cvut.fel.pjv.model.Enemy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 
 * Unit test for class Enemy
 */
public class EnemyTest {
    /**
     * Test of getPositionX method, of class Enemy.
     */
    @Test
    public void testGetPositionX() {
        EnemyForTest instance = new EnemyForTest(45, 45, "Basic");
        int expResult = 45;
        int result = instance.getPositionX();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPositionY method, of class Enemy.
     */
    @Test
    public void testGetPositionY() {
        EnemyForTest instance = new EnemyForTest(45, 45, "Basic");
        int expResult = 45;
        int result = instance.getPositionY();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSpeed method, of class Enemy.
     */
    @Test
    public void testGetSpeed() {
        EnemyForTest instance = new EnemyForTest(45, 45, "Basic");
        int expResult = 1;
        int result = instance.getSpeed();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNote method, of class Enemy.
     */
    @Test
    public void testGetNote() {
        EnemyForTest instance = new EnemyForTest(45, 45, "Basic");
        String expResult = "Basic";
        String result = instance.getNote();
        assertEquals(expResult, result);
    }

    /**
     * Test of setLifes method, of class Enemy.
     */
    @Test
    public void testSetLifes() {
        int lifes = 0;
        EnemyForTest instance = new EnemyForTest(45, 45, "Basic");
        instance.setLifes(lifes);
        assertEquals(lifes, instance.getLifes());
    }

    /**
     * Test of getLifes method, of class Enemy.
     */
    @Test
    public void testGetLifes() {
        EnemyForTest instance = new EnemyForTest(45, 45, "Basic");
        int expResult = 1;
        int result = instance.getLifes();
        assertEquals(expResult, result);
    }
}
