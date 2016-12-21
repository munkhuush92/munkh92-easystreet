/*
 * TCSS 305 - Easy Street
 *  Human class.
 */
package model;

import java.util.Map;

/**
 *  Human roams through given initial Terrain and it doesn't traverse.
 * 
 * @author Munkhbayar Ganbold
 * @version October 2015
 */
public final class Human extends AbstractVehicle {

    //static fields
    /**  Constant value for ATV's death time.
     */
    private static final int DEATH_TIME = 45;
    
    /** the name of vehicle. */
    private static final  String VEHICLE_NAME = "human";
    
    /** Initial terrain for human to be placed.  */
    private final Terrain myInitialTerrain;
    
    /**
     *  Constructs Human with given x and u coordinates and direction
     *  & initial terran.
     * @param theX coordinate of Human
     * @param theY coordinate of Human
     * @param theDir initial Direction of human
     * @param theTerrain initial terrain where human lives
     */
    public Human(final int theX, final int theY,
                 final Direction theDir, final Terrain theTerrain) {
        
        super(theX, theY, theDir, DEATH_TIME, VEHICLE_NAME);
        myInitialTerrain = theTerrain;
    }
     
    /**
     *  Determines a human can pass not or with given Terrain and light.
     * @param theTerrain that human is facing
     * @param theLight of street
     * @return boolean Either Human can pass or not.
     */
    @Override
    public boolean canPass(final Terrain theTerrain, final Light theLight) {
       
        
        return theTerrain.equals(myInitialTerrain) || (theTerrain.equals(Terrain.LIGHT) 
                        && myInitialTerrain.equals(Terrain.STREET))
              || (theTerrain.equals(Terrain.STREET) && myInitialTerrain.equals(Terrain.LIGHT));
    }
    /**
     *  Determines the human's next direction. Human doesn't traverse any terrain except
     *  its original terrain. Human travels through its terrain randomly.
     * @param Map theNeighbors of human
     * @return Direction which human is ready to move
     */
    @Override
    public Direction chooseDirection(final Map<Direction, Terrain> theNeighbors) {
        Direction newDir = null;

        
        boolean found = false;
        while (!found) {
            newDir = Direction.random();
            if (canPass(theNeighbors.get(newDir), Light.GREEN)) {
                found = true;
            }
        }

        return newDir;
    }

}
