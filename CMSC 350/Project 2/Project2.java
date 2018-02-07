package project2;

/*
 * File: Project2
 * Author: David Robbins
 * Date: April 8, 2017
 * Purpose: This file creates the GUI for Project2
 */

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class Project2 extends JFrame {
    
    //Make class variables for various Swing objects
    private JLabel userInputLabel = new JLabel("Enter Postfix Expression: ");
    private JTextField userInput = new JTextField("", 20);
    private JButton construct = new JButton("Construct Tree");
    private JLabel infixLabel = new JLabel("Infix Expression: ");
    private JTextField infix = new JTextField("", 20);
        
    public Project2(String title, int width, int height){
        super(title);
        setFrame(width, height);
        
        //Create panels for swing objects to be placed
        JPanel inputPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel resultPanel = new JPanel();
        
        //Add swing objects to panels
        inputPanel.add(userInputLabel);
        inputPanel.add(userInput);
        buttonPanel.add(construct);
        resultPanel.add(infixLabel);
        resultPanel.add(infix);
        
        //Add panels to main frame with layout
        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(resultPanel, BorderLayout.SOUTH);
        
        //Adjust text fields ablility to be edited
        userInput.setEditable(true);
        infix.setEditable(false);
        
        //Add an action listener and handler to the construct tree button 
        construct.addActionListener((ActionEvent e) -> {
            //Try catch statement to catch invalid tokens            
            PrintWriter writer = null;
            String fileName = "Three Address Instructions.txt";
            
            try{
            //Output infix expression
            String test = userInput.getText();
            Token2 token = new Token2(test);
            infix.setText(token.toStr());
            
            //Writing assembly to a file
            FileWriter fileWriter = new FileWriter(fileName, true);
            writer = new PrintWriter(fileWriter);
            writer.println(token.getAssembly());            
            }
            catch (BadTokenException x) {
                JFrame parent1 = new JFrame();
                JOptionPane.showMessageDialog(parent1, "Invalid Token: " + x.getMessage() + "\nBe sure to put spaces between characters\nOnly: Integers and + - * /");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Project2.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(Project2.class.getName()).log(Level.SEVERE, null, ex);
            }
            writer.append(System.lineSeparator());            
            writer.close();
            
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
        Project2 p1 = new Project2("Three Address Generator", 400, 140);
        p1.display();  
    }
    
}