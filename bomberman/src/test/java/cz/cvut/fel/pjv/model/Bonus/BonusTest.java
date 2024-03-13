package cz.cvut.fel.pjv.model.Bonus;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 
 * Unit test for class Bonus
 */
public class BonusTest {
    /**
     * Test of getPositionX method, of class Bonus.
     */
    @Test
    public void testGetPositionX() {
        BonusForTest instance = new BonusForTest(45,45);
        int expResult = 45;
        int result = instance.getPositionX();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPositionY method, of class Bonus.
     */
    @Test
    public void testGetPositionY() {
        BonusForTest instance = new BonusForTest(45,45);
        int expResult = 45;
        int result = instance.getPositionY();
        assertEquals(expResult, result);
    }

    /**
     * Test of getBonusType method, of class Bonus.
     */
    @Test
    public void testGetBonusType() {
        BonusForTest instance = new BonusForTest(45,45);
        String expResult = "";
        String result = instance.getBonusType();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of setPositionX method, of class Bonus.
     */
    @Test
    public void testSetPositionX() {
        int positionX = 0;
        BonusForTest instance = new BonusForTest(45,45);
        instance.setPositionX(positionX);
        assertEquals(positionX, instance.getPositionX());
    }

    /**
     * Test of setPositionY method, of class Bonus.
     */
    @Test
    public void testSetPositionY() {
        int positionY = 0;
        BonusForTest instance = new BonusForTest(45,45);
        instance.setPositionY(positionY);
        assertEquals(positionY, instance.getPositionY());
    }

    /**
     * Test of isBetween method, of class Bonus.
     */
    @Test
    public void testIsBetween() {
        int x = 2;
        int lower = 1;
        int upper = 3;
        boolean expResult = true;
        boolean result = BonusForTest.isBetween(x, lower, upper);
        assertEquals(expResult, result);
    }

    /**
     * Test of randomChooseTypeOfBonus method, of class Bonus.
     */
    @Test
    public void testRandomChooseTypeOfBonus() {
        BonusForTest instance = new BonusForTest(45,45);
        String expResult = "";
        String result = instance.randomChooseTypeOfBonus();
        assertNotEquals(expResult, result);
    }  
}
