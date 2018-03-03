/*
 * This is the class that takes care of the Immovable Object
 */
package asg3_4;

/**
 * Yue Yang
 * @author 260694712
 */
public class ImmovableObject extends Obj{
    
    private final boolean isMovable;
    private final boolean isAutonomous;
    
    /**
     * A constructor that accepts a char symbol representing the object itself
     * @param tok: the symbol representing the Object
     */
    public ImmovableObject(char tok){
        //there is technically no limitations as to what the char for the token of the object can be,
        //as no conditions have been specified in the problem, so only blank space is invalid
        super.setToken(tok);
        this.isMovable = false;
        this.isAutonomous = false;
    }

    /**
     * A getter for the token
     * @return the token of the current object
     */
    @Override
    public char getToken(){
        return this.token;
    }
    
    /**
     * Getter for a ImmovableObject's isMovable condition, which is false. This status determines if Object can move or not
     * @return is Movable
     */
    @Override
    public boolean getMovable(){
        return isMovable;
    }
    
    /**
     * Getter for an ImmovableObject's isAutonomous condition, which is false. 
     * This will allow World class to know when to call step();
     * @return: isAutonmous
     */
    @Override
    public boolean getAutonomous(){
        return this.isAutonomous;
    }
    
}
