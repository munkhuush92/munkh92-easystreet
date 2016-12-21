/*
 * TCSS 305 - Easy Street
 *  Truck class.
 */
package model;

import java.util.Map;

/**
 *  Truck travels through streets and lights.
 * 
 * @author Munkhbayar Ganbold
 * @version October 2015
 */
public final class Truck extends AbstractVehicle {
    /** constant integer that used for creating random direction from array of Direction. */
    private static final int CONSTANT_NUM = 3;
    
    /**
     * Constructs a truck with given x and y coordinates, direction.
     * @param theX coordinate of truck
     * @param theY coordinate of truck
     * @param theDir Initial Direction of truck
     */
    public Truck(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, 0, "Truck");
        
    }
    /**
     *  Determines a truck can pass with given Terrain and light.
     * @param theTerrain that truck is facing
     * @param theLight of street
     * @return boolean Either truck can pass or not.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
      
        return theTerrain.equals(Terrain.LIGHT) || theTerrain.equals(Terrain.STREET);
    }
    
    /**
     *  Determines the next direction with given the neighbors.
     *  Truck chooses random directions (straight, left, or right) and as a last resort, 
     *  it turns around if none of them possible.
     * @param Map (Direction, Terrain)
     * @return Direction the next Direction of Truck.
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction newDir = null;
        // for the last resort
        // if the truck cannot move straight, left, or right, 
        // then it has to be reversed
        if ((!canPass(theNeighbors.get(this.getDirection()), Light.GREEN)) 
                   && (!(canPass(theNeighbors.get(this.getDirection().left()) , Light.GREEN))) 
                && (!(canPass(theNeighbors.get(this.getDirection().right()), Light.GREEN)))) {
            return this.getDirection().reverse();
            
        } else {
            boolean found = false;
            while (!found) {
                newDir = randomizeDirection();
                if (canPass(theNeighbors.get(newDir), Light.GREEN)) {
                    found = true;
                }
            }    
        }
        return newDir;
    
    }
    
    /**
     *  Helper method which gives chooseDirection method a random Direction
     *  from three preferred direction of truck.
     * @return Random Direction 
     */
    private Direction randomizeDirection() {
        final Direction[] directions = {this.getDirection(), 
                        this.getDirection().left() , this.getDirection().right()};

        return directions[(int) (Math.random() * CONSTANT_NUM)]; 
    }

}
