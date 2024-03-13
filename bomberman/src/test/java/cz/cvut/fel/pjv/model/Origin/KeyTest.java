package cz.cvut.fel.pjv.model.Origin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 
 * Unit test for class Key
 */
public class KeyTest {
    
    /**
     * Test of setPositionX method, of class Key.
     */
    @Test
    public void testSetPositionX() {
        int positionX = 45;
        KeyForTest instance = new KeyForTest(0,0);
        instance.setPositionX(positionX);
        assertEquals(positionX, instance.getPositionX());
    }

    /**
     * Test of setPositionY method, of class Key.
     */
    @Test
    public void testSetPositionY() {
        int positionY = 0;
        KeyForTest instance = new KeyForTest(0,0);
        instance.setPositionY(positionY);
        assertEquals(positionY, instance.getPositionY());
    }

    /**
     * Test of getPositionX method, of class Key.
     */
    @Test
    public void testGetPositionX() {
        System.out.println("getPositionX");
        KeyForTest instance = new KeyForTest(0,0);
        int expResult = 0;
        int result = instance.getPositionX();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPositionY method, of class Key.
     */
    @Test
    public void testGetPositionY() {
        KeyForTest instance = new KeyForTest(0,0);
        int expResult = 0;
        int result = instance.getPositionY();
        assertEquals(expResult, result);
    }   
}
