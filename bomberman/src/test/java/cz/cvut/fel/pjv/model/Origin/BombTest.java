package cz.cvut.fel.pjv.model.Origin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 
 * Unit test for class Bomb
 */
public class BombTest {

    /**
     * Test of getTimeToExplosion method, of class Bomb.
     */
    @Test
    public void testGetTimeToExplosion() {
        Bomb instance = new Bomb(0, 0, 1);
        int expResult = 4;
        int result = instance.getTimeToExplosion();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPositionX method, of class Bomb.
     */
    @Test
    public void testSetPositionX() {
        int positionX = 45;
        Bomb instance = new Bomb(0, 0, 1);
        instance.setPositionX(positionX);
        assertEquals(positionX, instance.getPositionX());
    }

    /**
     * Test of setPositionY method, of class Bomb.
     */
    @Test
    public void testSetPositionY() {
        int positionY = 45;
        Bomb instance = new Bomb(0, 0, 1);
        instance.setPositionX(positionY);
        assertEquals(positionY, instance.getPositionX());
    }

    /**
     * Test of getPositionX method, of class Bomb.
     */
    @Test
    public void testGetPositionX() {
        Bomb instance = new Bomb(0, 0, 1);
        int expResult = 0;
        int result = instance.getPositionX();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPositionY method, of class Bomb.
     */
    @Test
    public void testGetPositionY() {
        Bomb instance = new Bomb(0, 0, 1);
        int expResult = 0;
        int result = instance.getPositionY();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPlayer method, of class Bomb.
     */
    @Test
    public void testGetPlayerID() {
        Bomb instance = new Bomb(0, 0, 1);
        int result = instance.getPlayerID();
        assertEquals(1, result);
    }
    
}
