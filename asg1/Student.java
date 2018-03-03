/**
 * COMP302 ASG1 A1
 * @author Yue Yang
 * ID: 260694712
 */
import static java.lang.Character.isLetter;

public class Student {
 
    /**FIELDS----------------------------------------------*/ 
    private int studentAge;
    private String studentName; 
    private int studentID;
    
    /**CONSTRUCTOR----------------------------------------*/
    /**
     * This is a constructor that takes in all 3 params of a student and sets them(if they are valid)
     * It first initiates the 3 fields
     * @param age: current student's age
     * @param name: current student's name
     * @param ID: current student's student ID
     */
    public Student(int age, String name, int ID){
        //initialization
        studentAge = -1;
        studentName = "";
        studentID = -1; 
        
        setAge(age);
        setName(name);
        setID(ID);
    }
    
    /**METHODS---------------------------------------------------**/
    
    /**
     * This method checks if the student's age is between 6 and 99. 
     * If it is, then sets the studentAge to age. Else returns error message
     * We assume anyone under 6 is too young, and anyone above 99 is too old.
     * @param age: current student's age
     */
    public void setAge(int age){
        
        if (age > 5 && age < 100){
            this.studentAge = age; 
        }
        
        else 
            System.out.print("You have entered an invalid age");
    }
    
    /**
     * This method calls isValidName to see if the name entered contains invalid characters. 
     * If isValidName returns true, then sets studentName to name. Else returns error message.
     * Note, it is not specified that person must enter a first name and a last name so we assume 
     * anything that contains only characters(a to z) is a valid name
     * @param name: the student's name
     */
    public void setName(String name){
        if(isValidName(name)){
            this.studentName = name;
        }
        
        else {
            System.out.println("You entered a name with invalid characters");
        }
    }
    
    /**
     * Checks if the name entered is valid
     * @param name: the student name to validate
     * @return true if name contains only characters(a to z) or spaces, false otherwise
     */
    public boolean isValidName(String name){
        for (int i = 0; i < name.length(); i++){
            if (!isLetter(name.charAt(i)) || name.charAt(i) != ' '){ //checks if the entered name contains only letters and spaces
                return false;
            }
        }     
        return true;
    }
    
    /**
     * We want the student's ID to be 5 digits long and to begin with the number 22
     * If that is the case, sets studentID to ID
     * @param ID: the student's ID 
     */
    public void setID(int ID){
        
        if (ID >= 22000 && ID <= 22999){
            this.studentID = ID; 
        }
        
        else{ 
            System.out.println("You have entered an invalid student ID");
        }
        
    }
    
    /**
     * A getter method for the student's age
     * @return current student's age
     */
    public int getAge(){
        return this.studentAge;
    }
    
    /**
     * A getter method for the student's name
     * @return current student's name
     */
    public String getName(){
        return this.studentName;
    }
    
    /**
     * A getter method for the student's ID
     * @return current student's student ID
     */
    public int getID(){
        return this.studentID; 
    }
}
