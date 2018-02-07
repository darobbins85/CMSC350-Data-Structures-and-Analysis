package project4;

/*
 * File: Project4
 * Author: David Robbins
 * Date: 
 * Purpose: This file creates the GUI for Project4
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Project4 extends JFrame {
    
    //Make class variables for various Swing objects
    private final JLabel input = new JLabel("Input File Name: ");
    private final JTextField inputField = new JTextField("", 20);
    private final JButton build = new JButton("Build Directed Graph");
    private final JLabel recompile = new JLabel("Class to recompile: ");
    private final JTextField recompileField = new JTextField("", 20);
    private final JButton order = new JButton("Topological Order");
    private final JTextField recompOrder = new JTextField();
    
        
    public Project4(String title, int width, int height){
        super(title);
        setFrame(width, height);
                
        setLayout(new GridLayout(2,1));
        
        //Create panels for swing objects to be placed
        JPanel topPanel = new JPanel(); 
        JPanel botPanel = new JPanel();
        
        //Add swing objects to panels
        topPanel.setLayout(new FlowLayout());
        topPanel.add(input);
        topPanel.add(inputField);
        topPanel.add(build);        
        topPanel.add(recompile);
        topPanel.add(recompileField);
        topPanel.add(order);
        
        botPanel.setLayout(new GridLayout());
        botPanel.setBorder(BorderFactory.createTitledBorder("Recompilation Order"));
        botPanel.add(recompOrder);
         
        //Add panels to main frame with layouts
        add(topPanel);
        add(botPanel);
        
        //Adjust text fields ablility to be edited
        inputField.setEditable(true);
        recompileField.setEditable(true);
        
        //Add an action listeners and handlers to the construct tree button 
        build.addActionListener((ActionEvent e) -> {
            
            try {
                String file = inputField.getText();
                Graph graph = new Graph();
                graph.readFile(file);
                JFrame parent1 = new JFrame();
                JOptionPane.showMessageDialog(parent1, "Graph Built Successfully");
                
                order.addActionListener((ActionEvent x) -> {
                StringBuilder topOrderOutput;
                    try {
                        topOrderOutput = graph.generateTopologicalOrder(recompileField.getText());
                        recompOrder.setText(topOrderOutput.toString());
                    } catch (CycleDetected ex) {
                        JFrame parent2 = new JFrame();
                        JOptionPane.showMessageDialog(parent2, "Cycle Detected");
                    } catch (InvalidClassName ez) {
                        JFrame parent3 = new JFrame();
                        JOptionPane.showMessageDialog(parent3, "Invalid Class Name");
            }
                
                });
                
                
            } catch (Exception ez) {
                JFrame parent1 = new JFrame();
                JOptionPane.showMessageDialog(parent1, "File did not open");
            }                            
        });
        
        
    
    }
    
    //Method to set the frame to visible
    public void display(){
        setVisible(true);
    }
    
    //Method to set the size of the frame
    private void setFrame(int width, int height){
        setSize(width, height);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    
    //Main method
    public static void main(String[] args) {
        Project4 p4 = new Project4("Class Dependency Graph", 550, 300);
        p4.display();  
    }
    
}