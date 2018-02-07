/*
 * File: Project1
 * Author: David Robbins
 * Date: March 26, 2017
 * Purpose: This file is creates the GUI for Project1 
 */

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Project1 extends JFrame {
    
    //Make class variables for various Swing objects
    private JLabel userInputLabel = new JLabel("Enter Infix Expression");
    private JTextField userInput = new JTextField("", 20);
    private JButton evaluate = new JButton("Evaluate");
    private JLabel resultLabel = new JLabel("Result");
    private JTextField result = new JTextField("", 10);
        
    public Project1(String title, int width, int height){
        super(title);
        setFrame(width, height);
        
        //Create panels for swing objects to be placed
        JPanel inputPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel resultPanel = new JPanel();
        
        //Add swing objects to panels
        inputPanel.add(userInputLabel);
        inputPanel.add(userInput);
        buttonPanel.add(evaluate);
        resultPanel.add(resultLabel);
        resultPanel.add(result);
        
        //Add panels to main frame with layout
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);
        
        //Adjust text fields ablility to be edited
        userInput.setEditable(true);
        result.setEditable(false);
        
        //Add an action listener and handler to the evaluate button 
        evaluate.addActionListener((ActionEvent e) -> {
            //Try catch statement to catch if a number has been divided by 0
            try {
                String test = userInput.getText();
                Token token = new Token(test);
                result.setText(token.toStr());
            } catch (ArithmeticException x1) {
                JFrame parent1 = new JFrame();
                JOptionPane.showMessageDialog(parent1, "Error: Please do not divide by zero");
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
        Project1 p1 = new Project1("Infix Expression Evaluator", 400, 140);
        p1.display();
    }
    
}