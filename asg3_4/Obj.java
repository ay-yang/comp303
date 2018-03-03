/*
 * This is the parent class for Movable, Immovable and Autonomous Objects
 * It is composed of abstract methods, and methods for its field, token
 */
package asg3_4;

/**
 * Yue Yang
 * @author 260694712
 */
public abstract class Obj {
    
    protected char token; 
    
    /**
     * This is the standard step() method which moves the Obj.
     * Note: this method is not required is every child. 
     * @return: the string that indicates what the Object does
     */
    public String step(){
        return "move to next";
    }
    
    /**
     * So we know if this Object can move or not
     * @return isMovable
     */
    public abstract boolean getMovable();
   
    /**
     * This method identifies if current Object is an Autonomous Object
     * @return isAutonomous
     */
    public abstract boolean getAutonomous();
    
    /**
     * Allows a token of the user's choice to be set
     * @param tok: the token we wish to set the Object
     */
    public void setToken(char tok){
        if (tok != ' '){
            this.token = tok;
        }
        else System.err.println("Token cannot be a blank space");
    }
    
    /**
     * Getter for the char symbol(token) of current Obj
     * @return token of this
     */
    public char getToken(){
        return this.token;
    }
   
}
