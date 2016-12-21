/*
 * TCSS 305 - Easy Street
 *  Car class.
 */
package model;

import java.util.Map;

/**
 *  Car travels through streets and lights.
 * 
 * @author Munkhbayar Ganbold
 * @version October 2015
 */
public class Car extends AbstractVehicle {
    
    /**
     *  Constant value for ATV's death time.
     */
    private static final int DEATH_TIME = 5;
    
    /**
     * Constructs a car with given x and y coordinates and initial direction.
     * @param theX coordinate of Car
     * @param theY coordinate of Car
     * @param theDir Initial Direction of Car
     */
    public Car(final int theX, final int theY, final Direction theDir) {
        
        super(theX, theY, theDir, DEATH_TIME, "car");
    }
    
    /**
     *  Determines the car can pass with given Terrain and light.
     * @param theTerrain that car is facing
     * @param theLight of street
     * @return boolean Either Car can pass or not.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean isPassed = false;
        
        if (theLight == Light.RED && theTerrain == Terrain.LIGHT) {
            isPassed = false;
        } else if ((theTerrain == Terrain.STREET || theTerrain == Terrain.LIGHT)
                        && (theLight == Light.GREEN || theLight == Light.YELLOW)) {
            isPassed = true;
               
        } else if (theLight == Light.RED && (theTerrain == Terrain.STREET)) {
            isPassed = true;
        } else {
            isPassed = false;
        }
        return isPassed;
    }
    
    /**
     *  Determines the next direction with given the neighbors.
     *  Car prefers to move straight ahead, if it cannot, then left. if can't go left, 
     *  then right and as a last resort, it turns around
     * @param Map (Direction, Terrain)
     * @return Direction the next Direction of car.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        //local fields
        Direction newDir;

        if (canPass(theNeighbors.get(this.getDirection()), Light.GREEN)) {
            newDir = this.getDirection();
        } else if (canPass(theNeighbors.get(this.getDirection().left()), Light.GREEN)) {
                
            newDir = this.getDirection().left();
        } else if (canPass(theNeighbors.get(this.getDirection().right()), Light.GREEN)) {
            newDir = this.getDirection().right();
                
        } else {
            newDir = this.getDirection().reverse();
        }
        
        return newDir;
    }
}
