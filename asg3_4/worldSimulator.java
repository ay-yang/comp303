/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package asg3_4;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

/**
 * Yue Yang
 * @author 260694712
 */
public class worldSimulator {
    
    public static void main(String args[]){
        
        World simulator = new World(5,5);
        
        Obj auto = new AutonomousObject('A');
        Obj move = new MovableObject('M');
        Obj imo = new ImmovableObject('I');
        
        simulator.add(auto, 0, 0);
        simulator.add(auto, 0, 1);
        simulator.add(move, 0, 2);
        simulator.add(move, 0, 3);
        simulator.add(move, 0, 4);
        simulator.add(auto, 1, 2);
        simulator.add(move, 1, 1);
        simulator.add(imo, 2, 1);
        simulator.add(imo, 2, 2);
        simulator.add(auto, 3, 3);
        simulator.add(move, 2,3);
        
        System.out.println("Initial Array: ");
        
        for (int i = 0; i < 100; i++){
            System.out.println("Iteration no." + i);
            simulator.step();
        }
    
    }
      
}
