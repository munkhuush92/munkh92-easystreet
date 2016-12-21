/*
 * TCSS 305 - Easy Street
 *  Abstract Class.
 */
package model;
import java.util.Map;

/**
 *  A parent class that get inherited all vehicles.
 * 
 * @author Munkhbayar Ganbold
 * @version October 2015
 */
public abstract class AbstractVehicle implements Vehicle {
    
    //instance fields
    /** initial x value for vehicle.     */
    private final int myInitX;
    
    /** initial y value for vehicle.     */
    private final int myInitY;
    
    /** current x value for vehicle.     */
    private int myX;
    
    /** current y value for vehicle.     */
    private int myY;
    
    /** starting direction of the vehicle.       */
    private final Direction myInitDir;
    
    /** Hold current direction of the vehicle.       */
    private Direction myDir;
     
    /** death amount time of the vehicle is dead for.      */
    private final int myDeathTime;
    
    /** death timer that counts down the vehicle is dead.    */
    private int myTimer;
    
    /** name of the vehicle.        */
    private final String myName;
    
    /** state of the vehicle whether it is dead or not.      */
    private boolean myVehicleAlive;
    
    /**
     * Constructs the vehicle with all the necessary variables to initialize the object.
     * @param theName of the Vehicle.
     * @param theDir a staring direction of vehicle.
     * @param theX initial X value of Vehicle.
     * @param theY initial Y value of Vehicle.
     * @param theDeathTime Initializes the death time for vehicle.
     */
    protected AbstractVehicle(final int theX, final int theY, final Direction theDir, 
                     final int theDeathTime, final String theName) {
        myInitX = theX;
        myInitY = theY;
        myX = theX;
        myY = theY;
        myName = theName;
        myInitDir = theDir;
        myDir = theDir;
        myDeathTime = theDeathTime;
        myVehicleAlive = true;
        
    }
    
    
    /** each child class has unique way for this method.    */
    @Override
    public abstract boolean canPass(Terrain theTerrain, Light theLight);

    /** each child class has unique way for this method.    */
    @Override
    public abstract Direction chooseDirection(Map<Direction, Terrain> theNeighbors);

    /**
     *  Notifies this vehicle that it has collided with the given other Vehicle object.
     *  Determines which vehicle dies or which one stays alive.
     *  @param theOther Vehicle that collided with.
     */
    @Override
    public void collide(final Vehicle theOther) {
        if (this.myDeathTime > theOther.getDeathTime() 
                        && (this.myVehicleAlive && theOther.isAlive())) {
        
            myVehicleAlive = false;
            this.myTimer = 0;             
        }

    }

    /**
     *  Accessor method for vehicle to check the death time.
     *  @return Integer death time of Vehicle.
     */
    @Override
    public int getDeathTime() {
        return myDeathTime;
    }

    /**
     *  A query method for vehicle to get Image file depending on 
     *  Vehicle is alive or dead.
     * @return String the name of Vehicle's image file.
     */
    @Override
    public String getImageFileName() {
        String state = getClass().getSimpleName().toLowerCase();
        if (this.myVehicleAlive) {
            
            state = state + ".gif";
        } else {
            state = state + "_dead.gif";
            
        }
        return state;   
    }
    
    /**
     * Acccessor method for Vehicle's current direction.
     * @return Direction of Vehicle.
     */
    @Override
    public Direction getDirection() {
        
        return myDir;
    }

    /**
     *  Accessor method for x coordinate of Vehicle.
     * @return Integer for X coordinate
     */
    @Override
    public int getX() {
        return myX;
    }

    /**
     * Accessor method for y coordinate of Vehicle.
     * @return Integer for Y coordinate.
     */
    @Override
    public int getY() {
       
        return myY;
    }

    /**
     *  Returns whether this vehicle is alive.
     * @return Boolean state of Vehicle is alive or not.
     */
    @Override
    public boolean isAlive() {
        
        return myVehicleAlive;
    }
    
    /**
     *  This method keeps track of how long Vehicle has been dead 
     *  and notifies Vehicle to revive.
     */
    @Override
    public void poke() {
        if (this.myTimer < this.myDeathTime - 1) {
            
            ++myTimer;
        } else {
            
            myVehicleAlive = true;
            myTimer = 0;
        }
    }
    
    /**
     * This method resets Vehicle to its original state.
     */
    @Override
    public void reset() {
        setX(myInitX);
        setY(myInitY);
        myDir = myInitDir;
        myVehicleAlive = true;  
        myTimer = 0;
    }
    
    /**
     *  Mutator method for changing Vehicle's direction.
     * @param theDir Direction of Vehicle will be set.
     */
    @Override
    public void setDirection(final Direction theDir) {
        myDir = theDir;

    }

    /** 
     * Mutator method for changing the X coordinate of Vehicle.
     * @param Integer new X coordinate.
     */
    @Override
    public void setX(final int theX) {
        myX = theX;

    }
    
    /** 
     * Mutator method for changing the Y coordinate of Vehicle.
     * @param Integer new Y coordinate.
     */
    @Override
    public void setY(final int theY) {
        myY = theY;
    }
    
    /**
     * This overridden method returns specific representation for each vehicle.
     * @return String representation of Vehicle state.
     */
    @Override
    public String toString() {
        
        return "" + myName + " alive: " + myVehicleAlive + " pokes " + myTimer;
    }

}
