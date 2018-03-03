/*
 * This is an AutonomousObject which moves on its own
 */
package asg3_4;

import java.util.Random;

/**
 * Yue Yang
 * @author 269694712
 */
public class AutonomousObject extends Obj{
    
    private Random randGenerator = new Random();

    private final boolean isMovable;
    private final boolean isAutonomous;
    
    public AutonomousObject(char tok){
        super.setToken(tok);
        this.isMovable = true;
        this.isAutonomous = true;
    }
 
    /**
     * This generates a random direction amongst the available direction: up, down, right and left
     */
    public String step(){
        String[] directions = {"up", "down", "right", "left"};
        
        //we generate a random index for the Autonomous object to move to
        return directions[randGenerator.nextInt(3)];
    }
    
     /**
     * Getter for the char symbol(token) of current Obj
     * @return token of this
     */
    @Override
    public char getToken(){
        return this.token;
    }
    
     /**
     * Getter for an AutonomousObject's isMovable condition, which is true. This status determines if Object can move or not
     * @return is Movable
     */
    @Override
    public boolean getMovable(){
        return this.isMovable;
    }
    
    /**
     * Getter for an AutonomousObject's isAutonomous condition, which is true. 
     * This will allow World class to know when to call step();
     * @return: isAutonmous
     */
    @Override
    public boolean getAutonomous(){
        return this.isAutonomous;
    }
}
