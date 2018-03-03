/** COMP303
 * ASG2Q2
 */
package asg2;

import asg2.airplaneSeatsGUI;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFrame;
import javax.swing.Timer;

/**
 * The main class to run the app
 * @author 260694712
 */
public class launchApp {
        public static void main(String args[]){
            
            Map<Integer, Integer> buttonList = new HashMap<Integer, Integer>(); // to serve as the shared data structures of all 3 threads
            
            airplaneSeatsGUI airplane = new airplaneSeatsGUI();
            JFrame display = new JFrame("Your Best Super Friend the Airplane Seats App =)");
            airplane.setMap(buttonList);
            airplane.buildDisplay(display);
            display.setVisible(true);
            
            //This is the timer that refreshes the display so it reflects upon the changes made by all brokers
            Timer time = new Timer(3000, new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e){
                    airplane.buildDisplay(display);
                }
            });
            
            time.start();
               
            Runnable broker1 = new broker(1, buttonList, airplane); // the non-automated threa
            Runnable broker2 = new broker(2, buttonList);
            Runnable broker3 = new broker(3, buttonList);
            
            Thread brokerThread1 = new Thread(broker1);
            Thread brokerThread2 = new Thread(broker2);
            Thread brokerThread3 = new Thread(broker3);
            
            brokerThread1.start();
            brokerThread2.start();
            brokerThread3.start();
    }
    
}
