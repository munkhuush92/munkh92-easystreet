/*
 * TCSS 305 - Easy Street
 *  ATV class.
 */
package model;

import java.util.Map;

/**
 *  All terrrain vehicle travels through anything except WALL.
 * 
 * @author Munkhbayar Ganbold
 * @version October 2015
 */

public final class Atv extends AbstractVehicle {
    
    /** constant integer that used for creating random direction from array of Direction. */
    private static final int CONSTANT_NUM = 3;
    
    /**
     *  Constant value for ATV's death time.
     */
    private static final int DEATH_TIME = 15;
    
    /**
     *  A constructor initializes ATV with necessary fields.
     * @param theX coordinate of ATV
     * @param theY coordinate of ATV
     * @param theDir current direction of ATV
     */
    public Atv(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME, "ATV");
        
    }
    
    /**
     *  Checks if whether ATV can pass through the given type of terrain with a given light.
     * @param theTerrain that gets checked.
     * @param theLight Street Light
     * @return boolean Whether ATV can pass or not.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
       
        return theTerrain != Terrain.WALL;
    }

    /**
     *  Determines ATV's next direction depending on the Neighbors
     *  that is provided. 
     * @param Map ATV's neighbors
     * @return Direction in which ATV prefers to move
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction newDir = null;
        boolean isFound = false;
        
        while (!isFound) {
            newDir = randomizeDirection();
            if (canPass(theNeighbors.get(newDir), Light.GREEN)) {
                isFound = true;
                
            } 
        }
        return newDir;  
    }
    
    /**
     * Helper method for canPass 
     * It shuffles three favorite directions of ATV by using Math.random method.
     * @return Direction random Direction of ATV
     */
    private Direction randomizeDirection() {
        final Direction[] directions = {this.getDirection(),
                        this.getDirection().left() , this.getDirection().right()};

        return directions[(int) (Math.random() * CONSTANT_NUM)]; 
    }

}
