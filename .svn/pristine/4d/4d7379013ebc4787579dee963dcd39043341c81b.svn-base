/*
 * TCSS 305 - Easy Street
 *  Test class for Car.
 */

package tests;


import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.Map;

import org.junit.Before;
import org.junit.Test;

import model.Car;
import model.Direction;
import model.Light;
import model.Terrain;


/**
 * Unit tests for class Car.
 * 
 * @author  Munkhbayar Ganbold
 * @version October 2015
 */
public class CarTest {
    
    /** car we use for testing. */
    private Car myCar;
    
    /**
     * Sets up my car for testing methods.
     */
    @Before
    public void setUp() {
        myCar = new Car(0, 0, Direction.EAST);
    }
    
    /**
     * Test method for {@link Car#Car(int, int, Direction)}.
     */
    @Test
    public void testCar() {
        assertEquals("Truck x coordinate not initialized correctly!", 0, myCar.getX());
        assertEquals("Truck y coordinate not initialized correctly!", 0, myCar.getY());
        assertEquals("Truck direction not initialized correctly!",
                     Direction.EAST, myCar.getDirection());
        assertEquals("Truck death time not initialized correctly!", 5, myCar.getDeathTime());
        assertTrue("Truck isAlive() fails initially!", myCar.isAlive());
        
    }
    
    /**
     * Test method for {@link Car#canPass(Terrain, Light)}.
     */
    @Test
    public void testCanPass() {
        // start from each terrain type except WALL
        for (final Terrain testTerrain : Terrain.values()) {
            
            // cars do not start on Walls, Grass, Trail
            if (testTerrain != Terrain.WALL && testTerrain != Terrain.GRASS
                            && testTerrain != Terrain.TRAIL) { 
                final Car car = new Car(0, 0, Direction.NORTH);
                
                // go to each terrain type
                for (final Terrain t : Terrain.values()) {
                    
                    // try the test under each light condition
                    for (final Light l : Light.values()) {
                        // only travels thru light and street.
                        if (t == Terrain.LIGHT && (l == Light.GREEN || l == Light.YELLOW)) {
                            assertTrue("car started on " + testTerrain
                                       + " should be able to pass " + t
                                       + ", with light " + l, car.canPass(t, l));
                            
                        } else if (t == Terrain.STREET) {
                            assertTrue("car started on " + testTerrain
                                       + " should be able to pass " + t
                                       + ", with light " + l, car.canPass(t, l));
                        } else if (t == Terrain.LIGHT && l == Light.RED) {
                            assertFalse("car started on " + testTerrain
                                       + " should be able NOT to pass " + t
                                       + ", with light " + l, car.canPass(t, l));
                        } else {
                            // other cases car should not pass.
                            assertFalse("car started on " + testTerrain
                                        + " should be able NOT to pass " + t
                                        + ", with light " + l, car.canPass(t, l));
                        }
                 
                    }
                } 
            }
        }
    }
    
    /**
     * Test method for {@link Car#chooseDirection(Map)}.
     */
    @Test
    public void testChooseDirection() {
        
        final Map<Direction, Terrain> neighbors = new HashMap<Direction, Terrain>();
        neighbors.put(Direction.WEST, Terrain.WALL);
        neighbors.put(Direction.NORTH, Terrain.WALL);
        neighbors.put(Direction.EAST, Terrain.LIGHT);
        neighbors.put(Direction.SOUTH, Terrain.LIGHT);
        
        assertEquals("car should be heading EAST", Direction.EAST 
                     , myCar.chooseDirection(neighbors));
        
        neighbors.put(Direction.SOUTH, Terrain.STREET);
        int tries = 0;
        while (tries < 10) {
            final Direction dir = myCar.chooseDirection(neighbors);
            
            assertTrue("car should be heading South or EAST",  
                       dir == Direction.SOUTH ||  dir == Direction.EAST);
            tries++;
        }
        
        /*  
         * Checks the special case when car has to turn around.
         */
        
        //places WALL on the east.
        neighbors.put(Direction.EAST, Terrain.WALL);
        myCar.setDirection(Direction.NORTH);
        assertEquals("car should turn around", Direction.SOUTH 
                     , myCar.chooseDirection(neighbors));
 
    }

}
