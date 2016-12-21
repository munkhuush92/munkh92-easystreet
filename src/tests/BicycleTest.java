/*
 * TCSS 305 - Easy Street
 *  Test class for Bicycle.
 */
package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import model.Bicycle;
import model.Direction;
import model.Light;
import model.Terrain;

/**
 * Unit tests for class Bicycle.
 * 
 * @author  Munkhbayar Ganbold
 * @version October 2015
 */
public class BicycleTest {
    
    /**
     * The number of times to repeat a test to have a high probability that all
     * random possibilities have been explored.
     */
    private static final int TRIES_FOR_RANDOMNESS = 50;
    
    /** bicycle we use for testing. */
    private Bicycle myBi;
    
    /** Sets up the bicycle for testing method. */
    @Before
    public void setUp() {
        myBi =  new Bicycle(22, 9, Direction.NORTH);
    }
    
    /**
     * Test method for {@link Bicycle#Bicycle(int, int, Direction)}.
     */
    @Test
    public void testBicycle() {
        assertEquals("Human x coordinate not initialized correctly!", 22, myBi.getX());
        assertEquals("Human y coordinate not initialized correctly!", 9, myBi.getY());
        assertEquals("Human direction not initialized correctly!",
                     Direction.NORTH, myBi.getDirection());
        assertEquals("Human death time not initialized correctly!", 25, myBi.getDeathTime());
        assertTrue("Human isAlive() fails initially!", myBi.isAlive());
    }
    /** 
     * Test method for {@link Bicycle#canPass(Terrain, Light)}. 
     */
    @Test
    public void testCanPass() {
        for (final Terrain testTerrain : Terrain.values()) {
            if (testTerrain != Terrain.WALL && testTerrain != Terrain.GRASS) { 
                // Bicycle do not start on Walls, Grass.
                // go to each terrain type
                for (final Terrain t : Terrain.values()) {
                    // try the test under each light condition
                    for (final Light l : Light.values()) {
                        
                        if ((t == Terrain.LIGHT) && (l == Light.RED || l == Light.YELLOW)) {
                            assertFalse("truck started on " + testTerrain
                                                       + " should be able to pass " + t
                                                       + ", with light " + l,
                                                       myBi.canPass(t, l));
                           
                        } else if ((t == Terrain.LIGHT) && (l == Light.GREEN)) {
                            assertTrue("truck started on " + testTerrain
                                        + " should be able to pass " + t
                                        + ", with light " + l,
                                        myBi.canPass(t, l));
                        } else if (t == Terrain.STREET ||  t == Terrain.TRAIL) {
                            assertTrue("truck started on " + testTerrain
                                       + " should be able to pass " + t
                                       + ", with light " + l,
                                       myBi.canPass(t, l));
                        } else if (t == Terrain.WALL) {
                            assertFalse("truck started on " + testTerrain
                                      + " should be able to pass " + t
                                      + ", with light " + l,
                                      myBi.canPass(t, l));
                        } else {
                            assertFalse("truck started on " + testTerrain
                                        + " should be able to pass " + t
                                        + ", with light " + l,
                                        myBi.canPass(t, l));
                        }
                        
                    }
                }
            }
        }
    }
    
    /** 
     * Test method for {@link Bicycle#chooseDirection(Map)}. 
     */
    @Test
    public void testChooseDirection() {
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.WEST, Terrain.WALL);
        neighbors.put(Direction.NORTH, Terrain.LIGHT);
        neighbors.put(Direction.EAST, Terrain.TRAIL);       
        /*   L
         * W ? T  
         *   ?
         */
        // find the case when bicycle faces RED or Yellow light but there is trail around.
        for (final Terrain t : Terrain.values()) {
            if (t == Terrain.WALL && t == Terrain.GRASS) {
                continue; // Bicycle don't start on WALLs,GRASS.
            }
            final Bicycle bi = new Bicycle(0, 0, Direction.NORTH);
            if (t != Terrain.TRAIL) {
                neighbors.put(Direction.SOUTH, t);
            }
            /*   L
             * W t T
             *   t         LIGHT, STREET, not Trail 
             */
            int tries = 0;
            boolean seenSouth = false;
            boolean seenEast = false;
            while (tries < TRIES_FOR_RANDOMNESS) {
                tries = tries + 1;
                final Direction dir = bi.chooseDirection(neighbors);
                assertTrue("on " + t + ", should choose east or south, was " + dir,
                           dir == Direction.EAST || dir == Direction.SOUTH);                   
                    
                seenSouth = seenSouth || dir == Direction.SOUTH;
                seenEast = seenEast || dir == Direction.EAST;
            }
            
            
            assertTrue("bicycle randomness", seenSouth || seenEast);
            
            // now check with only one valid option    
            neighbors.put(Direction.EAST, Terrain.WALL);
            neighbors.put(Direction.NORTH, Terrain.WALL);
            /*   W
             * W t W
             *   t         LIGHT, STREET, but trail for this bicycle has to turn around
             */
            tries = 0;
            while (tries < TRIES_FOR_RANDOMNESS) {
                tries = tries + 1;
                final Direction dir = bi.chooseDirection(neighbors);
                assertSame("invalid dir chosen, should be south, was " + dir,
                           Direction.SOUTH, dir);
            }      
            //lets find the case when bicycle is facing the RED
            //or Yellow light but there is trail around.    
                /*   L
                 * W t T
                 *   t
                 */
            
            neighbors.put(Direction.EAST, Terrain.TRAIL);
            neighbors.put(Direction.NORTH, Terrain.LIGHT);
            // to go thru each light values.
            for (final Light l : Light.values()) {
                bi.setDirection(Direction.NORTH);
                if (l == Light.RED || l == Light.YELLOW) {
                    
                    assertTrue("a bicycle should not stop and it should just turn to trail",
                                bi.chooseDirection(neighbors) == Direction.EAST);
                }           
            }
        }

    }

}
