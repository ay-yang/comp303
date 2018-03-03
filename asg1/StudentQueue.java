/**
 * COMP302 ASG1 A1
 * @author Yue Yang
 * ID: 260694712
 */

import java.util.LinkedList;
import java.util.Queue;

public class StudentQueue {
    
    private Queue<Student> studentList = new LinkedList<Student>();
    
    /**
     * This method removes the first Student in the current Queue
     * But will fail if the Queue is empty
     */
    public void dequeue(){
        if(studentList.size() ==0){
            System.out.println("The list is empty, boo.");
        }
        else{
            studentList.remove(); 
        }
    }
    
    /**
     * This method adds a Student object to the current Queue of Students. 
     * It will validate the Student's age, and see if the other necessary fields are present as well before adding them to the Queue
     * @param aStudent: the Student to add to the queue
     */
    public void enqueue(Student aStudent){
        //checks if student is 15 and over and if the other fields (name and ID) are filled
        if(aStudent.getAge() >=15 && !aStudent.getName().isEmpty() && aStudent.getID() >=22000){
            studentList.add(aStudent);
        }
        else {
            System.out.println("This student is either not old enough or has unfilled fields.");
        }
    }
}
