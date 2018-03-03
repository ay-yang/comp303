/** COMP303
 * ASG2Q2 
 */
package asg2;

import java.awt.*;
import java.awt.event.*;

import javax.swing.JButton;
import javax.swing.JPanel;

import asg2.broker;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import java.util.concurrent.locks.*;
import javax.swing.JOptionPane;

/**
 * This class takes care of the display and all the actions tied to the display buttons. 
 * @author 260694712
 */
public class airplaneSeatsGUI{
    
    private int brokerID;
    private int seatID;
    private String buttonID;
    private Map<Integer, Integer> buttonList= new HashMap<Integer, Integer>();
    private ArrayList reservedSeats;
    private ArrayList unsuccessful;
    
    private Lock listLock;
    
    /**No-arg Constructor that initializes variables to their default value
     */
    public airplaneSeatsGUI(){
        brokerID = -1;
        seatID = -1;
        buttonID = "";
        listLock = new ReentrantLock();
        reservedSeats=new ArrayList<Integer>();
        unsuccessful = new ArrayList<Integer>();
    }
    
    /** A setter method for the method, useful for data synch
     * Precondition: the Map passed must contain object of type Integer: Integer
     * @param map: the map of the currentThread
     */
    public void setMap(Map<Integer, Integer> map){
        this.buttonList = map;
    }
    
    /**
     * Return the current size of the map, useful for knowing when to terminate the threads
     * @return the Map size
     */
    public int getMapSize(){
        return this.buttonList.size();
    }
    
    /**
     * Returns the contents of reservedSeats, and clears it
     * @return a new pointer to the contents of reservedSeats
     */
    public ArrayList getSeatNum(){  
        return this.reservedSeats;
    }
    
    /**This method creates the display of the seats. It creates a 2 x 50 seats display with 100 buttons
     * Precondition: the start number must be between 1 and 101, else we'll obtain an invalid(out of range) seat number when looping
     * @param start: the number at which we're starting to count the seats
     * @return a clone of the panel with all the seat display buttons
     */
    public JPanel buildSeats(int start){
        JPanel seatsRow = new JPanel();
        GridLayout manageDisplay = new GridLayout(2, 50);
        seatsRow.setLayout(manageDisplay);
        
        if(start > 101 || start < 0){
            System.out.println("Invalid start number");
            System.exit(0);
        }
        
        //creates JButtons in a loop, along with their actionListener
        for (int counter = start; counter <= start+99; counter++){
            buttonID = "seatNo"+String.valueOf(counter); //this way we can assign a unique name to every button dynamically 
            seatID = counter;
            //String button = buttonID;
            JButton button = new JButton(String.valueOf(seatID));
            
            //for JFrame refreshing consistency
            if(buttonList.containsKey(seatID)){ //this means that the button has been previously pressed
                button.setEnabled(false);
                button.setText("R:" +buttonList.get(seatID));
            }
            
            else if(reservedSeats.contains(seatID)){
                button.setEnabled(false); //it's only for the visuals, so when the window refreshes, whatever buttons was pressed remain pressed
            }
            
            button.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    reservedSeats.add(Integer.parseInt(button.getText())); // to keep track of pressed buttons
                    button.setEnabled(false);
                }
            });
            seatsRow.add(button);   
        }    
        return seatsRow;
    }
    
    /** 
     * Also updates the instance's buttonList to reflect upon changes done by others instances
     * Precondition: we want to make sure the seatID is between 1 and 200
     * @param b: the current broker we're dealing with
     */
    public void reserveSeat(broker b) throws InterruptedException{
        listLock.lock();
        
        ArrayList<Integer> temp = new ArrayList<Integer>();
        temp = b.getSeatsList();
        
        try{
            this.brokerID = b.getID();
        
            if(brokerID == 2 || brokerID == 3){
                temp = b.getSeatsList();
            }
        
            //we loop through the arraylist to determine which seats and matching broker ID to add to the buttonList
            for(int i = 0; i < temp.size(); i++){
                seatID = temp.get(i);
                if(!buttonList.containsKey(seatID) && (seatID >=1 && seatID <=200)){
                    buttonList.put(seatID, this.brokerID);
                } 
                else unsuccessful.add(seatID); //if already reserved
            } 
            //Helps debugging
            System.out.println("Map" + buttonList);
        }
        finally{
            listLock.unlock();
        }      
    }
    
    /**This method serves to build the JFrame shown, and also serves to refresh it
     * @param frame : the frame affected by the changes
     * @return: the frame with the necessary components added
     */
    public JFrame buildDisplay(JFrame frame){
        
            frame.getContentPane().removeAll();
            
            JButton exitBtn = new JButton("Exit");
            JButton reserveBtn = new JButton("Reserve my seats!");
            
            exitBtn.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    JOptionPane.showMessageDialog(null, "Goodbye", "byee",
                             JOptionPane.INFORMATION_MESSAGE);//show goodbye message
                    System.exit(0);
                }
            });
            
            //when user clicks the "reserve seat" button, forces a refresh of the JFrame even if timer not reached
            reserveBtn.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){                
                    buildDisplay(frame);
                    if(!unsuccessful.isEmpty()){
                        JOptionPane.showMessageDialog(null, "We're sorry, but the following seats have already been reserved :( :" + unsuccessful.toString(), "Our deepest apologies", JOptionPane.INFORMATION_MESSAGE);
                    }
                    unsuccessful.clear();
                }
            });
            
            JPanel main = new JPanel();
            JPanel seatPanelOne = this.buildSeats(1);
            JPanel seatPanelTwo = this.buildSeats(101);
        
            main.add(seatPanelOne);
            main.add(seatPanelTwo);
            main.add(exitBtn);
            main.add(reserveBtn);
            frame.add(main);
            
            frame.pack();
            frame.setSize(2500, 400);
            frame.setResizable(false);
            frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
            
            frame.getContentPane().validate();
            frame.getContentPane().repaint();
            
            return frame;
    }
     
}
