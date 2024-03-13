package cz.cvut.fel.pjv.model.Origin;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 
 * Unit test for class Player
 */
public class PlayerTest {
    
    /**
     *
     */
    public PlayerTest() {
    }

    /**
     * Test of getPositionX method, of class Player.
     */
    @Test
    public void testGetPositionX() {
        Player instance = new Player(0,0,0,0,0,0,0);
        int expResult = 0;
        int result = instance.getPositionX();
        assertEquals(expResult, result);
    }

    /**
     * Test of getPositionY method, of class Player.
     */
    @Test
    public void testGetPositionY() {
        Player instance = new Player(0,0,0,0,0,0,0);
        int expResult = 0;
        int result = instance.getPositionY();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSpeed method, of class Player.
     */
    @Test
    public void testGetSpeed() {
        System.out.println("getSpeed");
        Player instance = new Player(0,0,0,0,0,1,0);
        int expResult = 1;
        int result = instance.getSpeed();
        assertEquals(expResult, result);
    }

    /**
     * Test of getExplosionSize method, of class Player.
     */
    @Test
    public void testGetExplosionSize() {
        Player instance = new Player(0,0,3,0,0,0,0);
        int expResult = 3;
        int result = instance.getExplosionSize();
        assertEquals(expResult, result);
    }

    /**
     * Test of setPositionX method, of class Player.
     */
    @Test
    public void testSetPositionX() {
        int positionX = 45;
        Player instance = new Player(0,0,0,0,0,0,0);
        instance.setPositionX(positionX);
        assertEquals(positionX, instance.getPositionX());
    }

    /**
     * Test of setPositionY method, of class Player.
     */
    @Test
    public void testSetPositionY() {
        int positionY = 45;
        Player instance = new Player(0,0,0,0,0,0,0);
        instance.setPositionY(positionY);
        assertEquals(positionY, instance.getPositionY());
    }

    /**
     * Test of putBomb method, of class Player.
     */
    @Test
    public void testPutBomb() {
        Player instance = new Player(0,0,0,0,0,0,0);
        Bomb expResult = null;
        Bomb result = instance.putBomb();
        assertNotEquals(expResult, result);
    }

    /**
     * Test of setLifes method, of class Player.
     */
    @Test
    public void testSetLifes() {
        int lifes = 2;
        Player instance = new Player(0,0,0,0,0,0,0);
        instance.setLifes(lifes);
        assertEquals(lifes, instance.getLifes());
    }

    /**
     * Test of setNumberOfBombs method, of class Player.
     */
    @Test
    public void testSetNumberOfBombs() {
        int numberOfBombs = 2;
        Player instance = new Player(0,0,0,0,0,0,0);
        instance.setNumberOfBombs(numberOfBombs);
        assertEquals(numberOfBombs, instance.getNumberOfBombs());
    }

    /**
     * Test of setSpeed method, of class Player.
     */
    @Test
    public void testSetSpeed() {
        int speed = 2;
        Player instance = new Player(0,0,0,0,0,0,0);
        instance.setSpeed(speed);
        assertEquals(speed, instance.getSpeed());
    }

    /**
     * Test of setExplosionSize method, of class Player.
     */
    @Test
    public void testSetExplosionSize() {
        int explosionSize = 2;
        Player instance = new Player(0,0,0,0,0,0,0);
        instance.setExplosionSize(explosionSize);
        assertEquals(explosionSize, instance.getExplosionSize());
    }

    /**
     * Test of getLifes method, of class Player.
     */
    @Test
    public void testGetLifes() {
        Player instance = new Player(3,0,0,0,0,0,0);
        int expResult = 3;
        int result = instance.getLifes();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumberOfBombs method, of class Player.
     */
    @Test
    public void testGetNumberOfBombs() {
        Player instance = new Player(0,0,0,0,0,0,0);
        int expResult = 0;
        int result = instance.getNumberOfBombs();
        assertEquals(expResult, result);
    }
}
