/** COMP303
 * ASG2Q2
 */
package asg2;

import java.util.Random;
import asg2.airplaneSeatsGUI;
import java.util.ArrayList;
import java.util.Map;

/**
 * This class takes care of everything related to the thread, the broker 
 * @author 260694712
 */
public class broker extends airplaneSeatsGUI implements Runnable{
    
    private int ID; 
    private Random randGenerator;
    private boolean isAutomated;   
    private ArrayList seatsList;
    
    private airplaneSeatsGUI nonAutoBroker = new airplaneSeatsGUI();
   
    private final int MAX = 200; 
    private final int MIN = 1;
    
    /**The constructor for a non-automated broker Thread
     * @param id: the desired Thread ID
     * @param map: the shared map data structure between threads
     * @param aBroker: the airplaneSeatsGUI display the broker is tied to 
     */
    public broker(int id, Map<Integer, Integer> map, airplaneSeatsGUI aBroker){
        setID(id);
        randGenerator = new Random();
        seatsList = new ArrayList<Integer>();
        nonAutoBroker = aBroker;
        super.setMap(map);
        isAutomated = false; 
    }
    
    /**The constructor for an automated broker Thread
     * @param id: the desired Thread ID
     * @param map: the shared map data structure between threads
     */
    public broker(int id, Map<Integer, Integer> map){
        setID(id);
        randGenerator = new Random();
        seatsList = new ArrayList<Integer>();
        super.setMap(map);
        isAutomated = true;
    }
    
    /**A simple setter method for the broker's ID, 
     * precondition: will only succeed if passed id is positive
     * @param id: the ID we want to set the broker as
     */
    public void setID(int id){
        if(id <= 0){
            System.out.println("Invalid ID" + id);
        }
        else this.ID = id;
    }
    
    /**
     * A simple getter that returns the seatsList of the broker
     * @return: seatsList of the current broker
     */
    public ArrayList getSeatsList(){
        return this.seatsList;
    }
    
    /**
     * A simple getter that returns the ID of the broker
     * @return: the current broker's ID 
     */
    public int getID(){
        return this.ID;
    }
    
    /**This is the method that executes when this class is being instantiated as a thread.
     * If it is an automated thread, seats numbers are generated randomly and added to seatsList
     * If it is a non- automated thread, we fetch the data from the instance the thread is tied to, and puts it in seatsList
     */
    public void run(){
        try{
            while(super.getMapSize() != MAX){ //We'll stop the thread when the map reaches its limit (aka total number of seats in the plane)
            //
                if (this.isAutomated){
                    this.seatsList.add(randGenerator.nextInt((MAX-MIN)+1));
                }
                else{
                    this.seatsList = nonAutoBroker.getSeatNum();
                } 
                
                //Here to help debugging
                System.out.println(this.ID + ":" + this.seatsList);
                
                super.reserveSeat(this);
                
                Thread.sleep((int) (Math.random() * 500));
            }
        }catch (InterruptedException exp){
            
        }
    }
    
}
