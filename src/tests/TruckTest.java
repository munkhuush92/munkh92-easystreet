/*
 * TCSS 305 - Easy Street
 *  Test class for Truck.
 */
package tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import model.Direction;
import model.Human;
import model.Light;
import model.Terrain;
import model.Truck;



/**
 * Unit tests for class Truck.
 * 
 * @author  Munkhbayar Ganbold
 * @version October 2015
 */
public class TruckTest {
    
    
    
    /**
     * The number of times to repeat a test to have a high probability that all
     * random possibilities have been explored.
     */
    private static final int TRIES_FOR_RANDOMNESS = 50;
    
    /** Truck variable we will use for test methods.    */
    private Truck myTruck;
   
    
    
    
    /** 
     * Sets up the vehicle for test methods.
     */
    @Before
    public void setUp() {
        myTruck = new Truck(1, 1, Direction.EAST);
        
    }
    
    /** a test for constructor
     *  Test method for {@link Truck#Truck(int, int, Direction)}.
     */
    
    @Test
    public void testTruck() {
        
        assertEquals("Truck x coordinate not initialized correctly!", 1, myTruck.getX());
        assertEquals("Truck y coordinate not initialized correctly!", 1, myTruck.getY());
        assertEquals("Truck direction not initialized correctly!",
                     Direction.EAST, myTruck.getDirection());
        assertEquals("Truck death time not initialized correctly!", 0, myTruck.getDeathTime());
        assertTrue("Truck isAlive() fails initially!", myTruck.isAlive());
        
       
    }
     
    /**
     *  Test method for {@link Truck#setDirection(Direction)}.
     */
    @Test 
    public void testSetDirection() {
        myTruck.setDirection(Direction.NORTH);
        assertTrue("Direction of truck should be North", 
                   myTruck.getDirection() == Direction.NORTH);
        myTruck.setDirection(Direction.EAST);
        assertFalse("Direction of truck should not be North", 
                   myTruck.getDirection() == Direction.NORTH);
    }
    
    /**
     * Test method for {@link Truck#reset()}.
     */
    @Test
    public void testReset() {
        // initial state was x=1, y=1, direction = east.
        // after modifying the state of truck and reset it 
        // it should get back to initial state
        myTruck.setX(34);
        myTruck.setY(34);
        myTruck.setDirection(Direction.SOUTH);
        myTruck.reset();
        
        assertEquals("state should same as initial state" , "Truck alive: "
                        + "true pokes 0", myTruck.toString());
        assertEquals("x and y should 1 and 1", 2, myTruck.getX() + myTruck.getY());
      
        
    }
    /**
     * Test method for {@link Truck#collide(model.Vehicle)}.
     */
    @Test
    public void testCollide() {
        final Human human = new Human(1, 2, Direction.EAST, Terrain.STREET);
        myTruck.collide(human);
        //checking if the human is dead or not. should be dead.
        assertTrue("human should be dead", human.isAlive());
        
    }
    
    
    /**
     * Test method for {@link Truck#getImageFileName()}.
     */
    @Test
    public void testGetImageFileName() {
        //truck never dies so we don't necessarily check if the dead image.
        // but just covering abstract class i'm checking the dead image.
        assertEquals("truck.gif", myTruck.getImageFileName());
        if (!myTruck.isAlive()) {
            assertEquals("truck_dead.gif", myTruck.getImageFileName());
        }
        
    }
    
    
    
    /**
     * Test method for {@link Truck#canPass(Terrain, Light)}.
     */
    @Test
    public void testCanPass() {
        for (final Terrain testTerrain : Terrain.values()) {
            if (testTerrain != Terrain.WALL && testTerrain != Terrain.GRASS
                            && testTerrain != Terrain.TRAIL) { 
                // Truck do not start on Walls, Grass, and Trail.
                
                final Truck truck = new Truck(22, 9, Direction.NORTH);
                // go to each terrain type
                for (final Terrain t : Terrain.values()) {
                    // try the test under each light condition
                    for (final Light l : Light.values()) {
                        
                        if ((t == Terrain.LIGHT && testTerrain == Terrain.STREET) 
                                    || (t == Terrain.STREET && testTerrain == Terrain.LIGHT)) {

                            assertTrue("truck started on " + testTerrain
                                                       + " should be able to pass " + t
                                                       + ", with light " + l,
                                       truck.canPass(t, l));
                        // trucks placed street also should pass thru streets. 
                        } else if (testTerrain == Terrain.STREET && t == Terrain.STREET 
                                      || testTerrain == Terrain.LIGHT && t == Terrain.LIGHT) {
                            assertTrue("truck started on " + testTerrain
                                       + " should be able to pass " + t
                                       + ", with light " + l,
                                       truck.canPass(t, l));
                        } else {
                            // other cases all should be false.
                            assertFalse("truck started on " + testTerrain
                                        + " should be able to pass " + t
                                        + ", with light " + l , truck.canPass(t, l));
                        }
                        
                    }
                }
            }
        }
    }

 
    /**
     * Test method for {@link Truck#chooseDirection(java.util.Map)}.
     */
    @Test
    public void testChooseDirection() {
        final Truck truck = new Truck(0, 0, Direction.NORTH);
        /*   W
         * W t W
         *   L
         */  
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.WEST, Terrain.WALL);
        neighbors.put(Direction.NORTH, Terrain.WALL);
        neighbors.put(Direction.EAST, Terrain.WALL);
        neighbors.put(Direction.SOUTH, Terrain.LIGHT);
        
        assertEquals("A truck should be heading South", 
                     Direction.SOUTH , truck.chooseDirection(neighbors));
        
        neighbors.put(Direction.EAST, Terrain.STREET);
        /*   W
         * W t S now we have light at South and street at East.
         *   L
         */ 
        
        int tries = 0;
        while (tries < TRIES_FOR_RANDOMNESS) {
            final Direction dir = truck.chooseDirection(neighbors);
            assertTrue("A truck should be heading South or EAST",  
                       dir == Direction.SOUTH || dir == Direction.EAST);
            tries++;
        }
        for (final Terrain t : Terrain.values()) {
            if (t == Terrain.WALL || t == Terrain.GRASS || t == Terrain.TRAIL) {
                continue; // Truck don't start on WALLs,GRASS, TRAIL
            }
            final Truck testTruck = new Truck(0, 0, Direction.NORTH);
            neighbors.put(Direction.EAST, t);
            
            // provide both STREET and LIGHT options
            if (t == Terrain.STREET) {
                neighbors.put(Direction.SOUTH, Terrain.LIGHT);
            } else if (t == Terrain.LIGHT) {
                neighbors.put(Direction.SOUTH, Terrain.STREET);
            } else {
                neighbors.put(Direction.SOUTH, t);
            }
            
            /*   W
             * W t t
             *   ?         LIGHT, STREET, or t depending on t
             */
            tries = 0;
            boolean seenSouth = false;
            boolean seenEast = false;
            while (tries < TRIES_FOR_RANDOMNESS) {
                tries = tries + 1;
                final Direction dir = testTruck.chooseDirection(neighbors);
                assertTrue("on " + t + ", should choose east or south, was " + dir,
                           dir == Direction.EAST || dir == Direction.SOUTH);                   
                    
                seenSouth = seenSouth || dir == Direction.SOUTH;
                seenEast = seenEast || dir == Direction.EAST;
            }
            assertTrue("truck randomness", seenSouth || seenEast);
            
            // now check with only one valid option    
            neighbors.put(Direction.EAST, Terrain.WALL);
            /*   W
             * W t W
             *   ?         LIGHT, STREET, or t depending on t
             */
            tries = 0;
            while (tries < TRIES_FOR_RANDOMNESS) {
                tries = tries + 1;
                final Direction dir = testTruck.chooseDirection(neighbors);
                assertSame("invalid dir chosen, should be south, was " + dir,
                           Direction.SOUTH, dir);
            }
            
            // for STREET and LIGHT also check with only the opposite type available 
            neighbors.put(Direction.EAST, t);
            if (t == Terrain.STREET || t == Terrain.LIGHT) {
                neighbors.put(Direction.SOUTH, t);
                
                /*   W
                 * W t t
                 *   t
                 */
                tries = 0;
                seenEast = false;
                while (tries < TRIES_FOR_RANDOMNESS) {
                    tries = tries + 1;
                    final Direction dir = testTruck.chooseDirection(neighbors);
                    assertTrue("on " + t + ", should choose east or south, was " + dir,
                               dir == Direction.EAST || dir == Direction.SOUTH);

                    seenEast = seenEast || dir == Direction.EAST;
                }

            }
        }
        
    }
  
}
