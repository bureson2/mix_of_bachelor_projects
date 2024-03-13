package cz.cvut.fel.pjv.model.Enemy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 
 * Unit test for class Dummy
 */
public class DummyTest  {
    /**
     * Test of getPositionX method, of class Dummy.
     */
    @Test
    public void testGetPositionX() {
        DummyForTest instance = new DummyForTest(45, 45, "Dummy");
        int expResult = 45;
        int result = instance.getPositionX();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPositionY method, of class Dummy.
     */
    @Test
    public void testGetPositionY() {
        DummyForTest instance = new DummyForTest(45, 45, "Dummy");
        int expResult = 45;
        int result = instance.getPositionY();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNote method, of class Dummy.
     */
    @Test
    public void testGetNote() {
        DummyForTest instance = new DummyForTest(45, 45, "Dummy");
        String expResult = "Dummy";
        String result = instance.getNote();
        assertEquals(expResult, result);
    }   
}
