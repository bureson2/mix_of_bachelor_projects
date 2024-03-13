package cz.cvut.fel.pjv.model.Block;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 
 * Unit test for class DestructbileBlock
 */
public class DestructibleBlockTest {
    
    /**
     * Test of decraseLifes method, of class DestructibleBlock.
     */
    @Test
    public void testDecraseLifes() {
        DestructibleBlock instance = new DestructibleBlock(2, 45, 45);
        instance.decraseLifes();
        assertEquals(1, instance.getLifes());
    }

    /**
     * Test of setLifes method, of class DestructibleBlock.
     */
    @Test
    public void testSetLifes() {
        DestructibleBlock instance = new DestructibleBlock(2, 45, 45);
        instance.setLifes(3);
        assertEquals(3, instance.getLifes());
    }

    /**
     * Test of getLifes method, of class DestructibleBlock.
     */
    @Test
    public void testGetLifes() {
        DestructibleBlock instance = new DestructibleBlock(2, 45, 45);
        int result = instance.getLifes();
        assertEquals(2, result);
    }
    
}
