package cz.cvut.fel.pjv.model.Block;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 
 * Unit test for class Block
 */
public class BlockTest {    
    /**
     * Test of GetPositionX method, of class Block.
     */
    @Test
    public void testGetPositionX() {
        Block block = new Block(45,45);
        int expResult = 45;
        int result = block.getPositionX();
        assertEquals(expResult, result);
    }

    /**
     * Test of GetPositionY method, of class Block.
     */
    @Test
    public void testGetPositionY() {
        Block block = new Block(45,45);
        int expResult = 45;
        int result = block.getPositionY();
        assertEquals(expResult, result);
    }
    
}
