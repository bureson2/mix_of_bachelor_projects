package cz.cvut.fel.pjv.model.Origin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 
 * Unit test for class Gate
 */
public class GateTest {
    
    /**
     * Test of setIsOpen method, of class Gate.
     */
    @Test
    public void testSetIsOpen() {
        boolean isOpen = true;
        GateForTest instance = new GateForTest(45,45);
        instance.setIsOpen(isOpen);
        assertEquals(isOpen, instance.isIsOpen());     
    }

    /**
     * Test of setPositionX method, of class Gate.
     */
    @Test
    public void testSetPositionX() {
        int positionX = 0;
        GateForTest instance = new GateForTest(45,45);
        instance.setPositionX(positionX);
        assertEquals(positionX, instance.getPositionX());
    }

    /**
     * Test of setPositionY method, of class Gate.
     */
    @Test
    public void testSetPositionY() {
        int positionY = 0;
        GateForTest instance = new GateForTest(45,45);
        instance.setPositionY(positionY);
        assertEquals(positionY, instance.getPositionY());
    }

    /**
     * Test of isIsOpen method, of class Gate.
     */
    @Test
    public void testIsIsOpen() {
        GateForTest instance = new GateForTest(45,45);
        boolean expResult = false;
        boolean result = instance.isIsOpen();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPositionX method, of class Gate.
     */
    @Test
    public void testGetPositionX() {
        GateForTest instance = new GateForTest(45,45);
        int expResult = 45;
        int result = instance.getPositionX();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPositionY method, of class Gate.
     */
    @Test
    public void testGetPositionY() {
        GateForTest instance = new GateForTest(45,45);
        int expResult = 45;
        int result = instance.getPositionY();
        assertEquals(expResult, result);
    }   
}
