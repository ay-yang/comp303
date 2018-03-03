/*
 * This is the class that makes a Movable Object, which can move, but not unless bumped into.
 */
package asg3_4;

/**
 * Yue Yang
 * @author 260694712
 */
public class MovableObject extends Obj{
    
    private final boolean isMovable;
    private final boolean isAutonomous;
    
    public MovableObject(char tok){

        super.setToken(tok);
        this.isMovable = true; //note this value cannot be changed
        this.isAutonomous = false;
    }
    
    /**
     * Getter for a MovableObject's isMovable condition, which is true. This status determines if Object can move or not
     * @return is Movable
     */
    @Override
    public boolean getMovable(){
        return this.isMovable;
    }
    
    /**
     * Getter for an MovableObject's isAutonomous condition, which is true. 
     * This will allow World class to know when to call step();
     * @return: isAutonmous
     */
    @Override
    public boolean getAutonomous(){
        return this.isAutonomous;
    }
    
}
