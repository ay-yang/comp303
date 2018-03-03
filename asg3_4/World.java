/*
 * This is the class that takes care of building a world of objects(Obj) 
 * The user must populate the world by using the add() method once its size has been initialized. 
 * It will take care of moving, diplaying its contents where applicable
 */
package asg3_4;

/**
 * Yue Yang
 * @author 260694712
 */
public class World {
    private Obj[][] world;
    
    private int delay;
    
    private boolean validated;
    private int stopValue;
    private String direction;
    private int columnValue;
    private int rowValue;
    
    /**
     * A constructor that accepts 3 arguments to initialize its fields
     * @param row: the first size parameter of our world array
     * @param col: the second size parameter of our world array
     */
    public World(int row, int col){
        direction = "";
        stopValue = -1;
        
        if( setSize(row, col)){
            validated = true; //to indicate that initialization was successful
        }
        else validated = false;
    }
    
    /**
     * This method checks if the user provided a valid size for the array.
     * Note, it is protected as we don't want the user to call it, as the array size should only be set once.
     * @param row: the first size field of array
     * @param col: the 2nd size field of array
     * @return: if size initilization successful, true, else false
     */
    protected boolean setSize(int row, int col){
       if((row >= 0 && col > 0)  || (col >= 0 && row >0)){ //note we can still have either row or column as 0, in that case, it just results in a boring 1D array
           rowValue = row; //these values help us in the array iteration
           columnValue = col;
           world = new Obj[rowValue][columnValue];
           return true;
       } 
       else{ 
           throw new NegativeArraySizeException();
       }
    }
    
    /**
     * Preconditions: the argument must be of type Obj, its size must be set, and the index given must be within its size boundaries
     * @param o: the object to be added to the 2D array world
     * @param rowIndex: the 1st coordinate of the Obj o where we wish to add it
     * @param colIndex: the 2nd coordinate of the Obj o where we wish to add it
     */
    public void add(Obj o, int rowIndex, int colIndex){
        if(validated == true){ // if initialization was successful, aka array size is valid
            if(( rowIndex < rowValue || rowIndex == 0 )&& (colIndex < columnValue || colIndex == 0) ){
            //adding to a cell already containing an Obj is allowed, it will replace that Obj.
                world[rowIndex][colIndex] = o;
            }
        }
        else System.err.println("Invalid index, please try again");
    }
    
    /**
     * Precondition: the world array must be non-empty: this is done by the loop, as an empty world array will do nothing, since no Objects are to be found
     * step will rely on validateDirection() to verify that the direction chosen is valid, if not, calls it again, with an updated position, if
     * the result still is false, skip the current Object and move on
     * else, calls update() to update the world in the desired direction
     */
    public void step(){
        //an array made entirely of Movable and Immovable Objects, without autonomous Objects to bump the, will not do anything

        for (int r = 0; r < rowValue; r++){
            for(int c = 0; c < columnValue; c++){ //no programming pun intended here
                if(world[r][c] != null){
                    if(world[r][c].getAutonomous() ==true){
                        //pick a direction
                        direction = world[r][c].step();
                        
                        char temp = world[r][c].getToken();
                    
                        //validate the direction
                        if (validateDirection(r, c)){ //note: validateDirection will automatically set direction to its opposite if not valid
                            System.out.print(temp);
                            System.out.println(" " + direction + " ");
                            //move all objects in range in that direction if valid
                            this.update(r, c);
                            display();
                        }
                        else{ //if not validate, try validating the opposite direction to that which was picked
                            if (validateDirection(r,c)){
                                System.out.print(temp + " ");
                                System.out.println(direction + " ");
                                this.update(r, c);
                                display();
                            }
                        }                              
                    //if still not valid, skip and move on.                  
                    }
               }
            }
        }
        //this is to ensure that the world is re-displayed after every iteration. May take this out if you don't want it. 
        display();
    }
    
    /**
     * Preconditions: row and col must be both bigger than 0, and the direction must be set to one of
     * "up", "down", "right" or "left" 
     * @param row: the 1st coordinate of the Obj
     * @param col: the 2nd coordinate of the Obj
     * @return true/or false depending on if the direction is valid
     */
    public boolean validateDirection(int row, int col){
        
        if(row < 0 || col < 0){//invalid indexes
            return false;
        }
        
        if(!this.direction.equals("up") && !this.direction.equals("down") && !this.direction.equals("right") && !this.direction.equals("left")){
            return false;
        }
        
        if(this.direction.equals("up")){
            if(row == 0 || rowValue == 0){
                direction = "down";
                return false;
            }
            else{
               for (int r = row; r >=0; r-- ){ //we're going up, so decrease row value
                   
                   if(world[r][col]== null){ //there's an empty cell in the way, so operation is valid
                       stopValue = r;
                       return true;
                   }
                   else if(!world[r][col].getMovable()){
                       direction = "down";
                       return false;
                   }
               }
               return false; 
            }
        }
        
        else if(this.direction.equals("down")){
            if(rowValue == 0 || (row == rowValue-1)){
                direction = "up";
                return false;
            }
            else{
               for (int r = row; r < rowValue; r++){ //we're going down, so we increase row
                  
                   if(world[r][col]== null){
                       stopValue = r;
                       return true;
                   }
                   else if(!world[r][col].getMovable()){
                       direction = "up";
                       return false;
                   }
               }
               return false;
            }
        }
        
        else if(this.direction.equals("right")){
            if(columnValue == 0 || (col == columnValue-1)){
                direction = "left";
                return false;
            }
            else{
                for(int c = col; c < columnValue; c++){
                    if (world[row][c] == null){
                        stopValue = c;
                        return true;
                    }
                    else if(!world[row][c].getMovable()){
                        direction = "left";
                        return false;
                    }
                }
                return false;
            }
        }
        
        else if(this.direction.equals("left")){
            if(col == 0 || columnValue == 0){
                return false;
            }
            else{
                for(int c = col; c >= 0 ; c--){
                    if (world[row][c] == null){
                        stopValue = c;
                        return true;
                    }
                    if(!world[row][c].getMovable()){
                        direction = "right";
                        return false;
                    }
                }
                return false;
            }
        }       
        return true;
    }

    /**
     * This method updates the world array, in the desired direction. 
     * Precondition: The direction must be first validated by validateDirection() (meaning stopValue i s not -1), and the direction must be one of 
     * "up", "down", "right", "left"
     * @param row: the 1st coordinate of the current Obj
     * @param col : the 2nd coordinate of the current Obj
     */
    protected void update(int row, int col){
        Obj temp = world[row][col];
        world[row][col]= null;
        Obj tempNext = null;
        
        if(!this.direction.equals("up") && !this.direction.equals("down") && !this.direction.equals("right") && !this.direction.equals("left") && this.stopValue != -1){
            System.err.println("invalid direction or stopValue ");
        }
        
        if(this.direction.equals("up")){
            for (int r = row-1; r >=stopValue; r-- ){ //we're going up, so decrease row value
                tempNext = this.world[r][col];
                this.world[r][col] = temp;
                temp = tempNext;
                
            }
        }
        
        else if(this.direction.equals("down")){
            for (int r = row+1; r <= stopValue; r++){ //we're going down, so we increase row
                tempNext = this.world[r][col];
                this.world[r][col] = temp;
                temp = tempNext;
               
            } 
        }
        
        else if(this.direction.equals("right")){
            for (int c = col+1; c <= stopValue; c++){
                tempNext = this.world[row][c];
                this.world[row][c] = temp;
                temp = tempNext;

            }
        }
        
        else if(this.direction.equals("left")){
           for(int c = col-1; c >= stopValue ; c--){
                tempNext = this.world[row][c];
                this.world[row][c] = temp;
                temp = tempNext;
           }
        }        

    }
    
    /**
     * Prints the world array in a viewable format for user
     */
    public void display(){
        
        for(int r = 0; r < rowValue; r++){
            for (int c = 0; c < columnValue; c++){
                if(this.world[r][c] == null){
                    System.out.print("- ");
                }
                else {
                    System.out.print(this.world[r][c].getToken() + " ");
                }
            }
            System.out.println("");
        }
        System.out.println("-----------------------------------");
    }
    
}
