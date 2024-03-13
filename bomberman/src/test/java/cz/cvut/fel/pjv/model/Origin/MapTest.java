package cz.cvut.fel.pjv.model.Origin;

import java.util.HashMap;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * 
 * Unit test for class Map
 */
public class MapTest {
    private static Map instance = setUpClass();
    
    /**
     *
     * @return
     */
    public static Map setUpClass() {
        HashMap<String, Integer> mapParams = new HashMap<>();
        mapParams.put("Bonuses", 10);
        mapParams.put("Basic Enemy", 10);
        mapParams.put("Dummy Enemy", 10);
        mapParams.put("Speedy Enemy", 10);
        mapParams.put("Lenght", 10);
        mapParams.put("Height", 10);
        return new Map(mapParams);
    }
    

    /**
     * Test of getNumberOfbonuses method, of class Map.
     */
    @Test
    public void testGetNumberOfbonuses() {
        int expResult = 10;
        int result = instance.getNumberOfbonuses();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumberOfEnemies method, of class Map.
     */
    @Test
    public void testGetNumberOfEnemies() {
        int expResult = 10;
        int result = instance.getNumberOfEnemies();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumberOfDummyEnemies method, of class Map.
     */
    @Test
    public void testGetNumberOfDummyEnemies() {
        int expResult = 10;
        int result = instance.getNumberOfDummyEnemies();
        assertEquals(expResult, result);
    }

    /**
     * Test of getNumberOfSpeedyEnemies method, of class Map.
     */
    @Test
    public void testGetNumberOfSpeedyEnemies() {
        int expResult = 10;
        int result = instance.getNumberOfSpeedyEnemies();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSizeX method, of class Map.
     */
    @Test
    public void testGetSizeX() {
        int expResult = 450;
        int result = instance.getSizeX();
        assertEquals(expResult, result);
    }

    /**
     * Test of getSizeY method, of class Map.
     */
    @Test
    public void testGetSizeY() {
        int expResult = 450;
        int result = instance.getSizeY();
        assertEquals(expResult, result);
    }
    
}
