/*
 * TCSS 305 - Easy Street
 *  ATV class.
 */
package model;

import java.util.Map;

/**
 *  Bicycle travels through light, street, and prefers to travel trails.
 * 
 * @author Munkhbayar Ganbold
 * @version October 2015
 */
public final class Bicycle extends AbstractVehicle {
  
    
    /**
     *  Constant value for Bicycle's death time.
     */
    private static final int DEATH_TIME = 25;
    
    /**
     *  Constructs a bicycle with given coordinates and direction.
     *  
     * @param theX coordinate of Bicycle
     * @param theY coordinate of Bicycle
     * @param theDir Initial Direction of Bicycle
     */
    public Bicycle(final int theX, final int theY, final Direction theDir) {
        super(theX, theY, theDir, DEATH_TIME, "Bicycle");
        
    }


    /**
     *  Determines bicycle can pass or not.
     *  Bicycles stops when theLight is RED or Yellow.
     * @param theTerrain that bicycle facing
     * @param theLight Street Light
     * @return boolean whether Bicycle can pass or not.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
        boolean isPassed = false;
        
        if (theTerrain != Terrain.GRASS && theTerrain != Terrain.WALL) {
            if (((theLight == Light.YELLOW) || (theLight == Light.RED))
                            && theTerrain == Terrain.LIGHT) {
                isPassed = false;
            } else {
                isPassed = true;
            }  
        }
        return isPassed;
    }

    /**
     * Determines which direction this bicycle would like to move.
     * Bicycle prefers to move in any direction (except reverse) where Trail exists.
     * If the bicycle is not trail, it prefers to move staight ahead, if not left, 
     * if not right, and last lesort is reverse.
     * @param Map theNeighbors of Bicycle
     * @return Direction next direction of Bicycle
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction newDir = null;
        if (theNeighbors.get(this.getDirection()) == Terrain.TRAIL) {
            if (canPass(theNeighbors.get(getDirection()), Light.GREEN)) {
                newDir = getDirection();
            }
        } else {
            //if bicycle is not on Trail.
            boolean found = false;
            
            //checks if there is any trail but not on reverse direction.
            newDir = checkTrail(theNeighbors, getDirection());
            if (newDir != null) {
                found = true;
            }
            //if there is no trail, then moves
            if (!found) {
                    
                if (canPass(theNeighbors.get(getDirection()), Light.GREEN)) {
                    newDir = getDirection();
                } else if (canPass(theNeighbors.get(getDirection().left()), Light.GREEN)) {
                    newDir = getDirection().left();
                } else if (canPass(theNeighbors.get(getDirection().right()), Light.GREEN)) {
                    newDir = getDirection().right();
                } else {
                    newDir = getDirection().reverse();
                }
            }
            
        }
        return newDir;
    }
    
    /**
     * Helper for chooseDirection to check if there is any trail around.
     * @param theNeighbors of Bicycle
     * @param theDir current pointing direction of Bicycle.
     * @return Direction in which Trail exists
     */
    private Direction checkTrail(final Map<Direction, 
                                 Terrain> theNeighbors, final Direction theDir) {
        Direction newDir = null;
        for (final Direction d: theNeighbors.keySet()) {
            if (d == theDir.reverse()) {
                continue;
            }
            if (theNeighbors.get(d) == Terrain.TRAIL) {
                
                newDir = d;
            }
            
        }
        return newDir;
    }   

    
    
}
