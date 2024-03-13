package cz.cvut.fel.pjv.model.Enemy;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 
 * Unit test for class Speedy
 */
public class SpeedyTest {
    
    /**
     * Test of GetPositionX method, of class Speedy.
     */
    @Test
    public void testGetPositionX() {
        SpeedyForTest instance = new SpeedyForTest(45,45,"Speedy");
        int expResult = 45;
        int result = instance.getPositionX();
        assertEquals(expResult, result);
    }

    /**
     * Test of GetPositionY method, of class Speedy.
     */
    @Test
    public void testGetPositionY() {
        SpeedyForTest instance = new SpeedyForTest(45,45,"Speedy");        
        int expResult = 45;
        int result = instance.getPositionY();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNote method, of class Speedy.
     */
    @Test
    public void testGetNote() {
        SpeedyForTest instance = new SpeedyForTest(45,45,"Speedy");   
        String expResult = "Speedy";
        String result = instance.getNote();
        assertEquals(expResult, result);
    } 
}
