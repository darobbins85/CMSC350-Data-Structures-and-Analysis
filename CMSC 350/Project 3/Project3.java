package project3;

/*
 * File: Project3
 * Author: David Robbins
 * Date: 
 * Purpose: This file creates the GUI for Project3
 */

import java.awt.*;
import java.awt.event.*;
import java.util.NoSuchElementException;
import javax.swing.*;

public class Project3 extends JFrame {
    
    //Make class variables for various Swing objects
    private final JLabel originalLabel = new JLabel("Original List: ");
    private final JTextField originalField = new JTextField("", 20);
    private final JLabel sortedLabel = new JLabel("Sorted List: ");
    private final JTextField sortedField = new JTextField("", 20);
    private final JButton sortButton = new JButton("Perform Sort");
    private final ButtonGroup sortGroup = new ButtonGroup();
    private final JRadioButton ascending = new JRadioButton("Ascending");
    private final JRadioButton descending = new JRadioButton("Descending");
    private final ButtonGroup numericGroup = new ButtonGroup();
    private final JRadioButton integer = new JRadioButton("Integer");
    private final JRadioButton fraction = new JRadioButton("Fraction");
    
        
    public Project3(String title, int width, int height){
        super(title);
        setFrame(width, height);
        
        //Create panels for swing objects to be placed
        JPanel inputPanel = new JPanel();
        JPanel resultPanel = new JPanel();
        JPanel buttonPanel = new JPanel();
        JPanel sortPanel = new JPanel();
        JPanel numericPanel = new JPanel(); 
        
        //Set groups borders and titles and add buttons to them
        sortPanel.setBorder(BorderFactory.createTitledBorder("Sort Order"));
        sortGroup.add(ascending);
        sortGroup.add(descending);         
        numericPanel.setBorder(BorderFactory.createTitledBorder("Numeric Type"));
        numericGroup.add(integer);
        numericGroup.add(fraction);
        
        //Add swing objects to panels
        inputPanel.add(originalLabel);
        inputPanel.add(originalField);
        resultPanel.add(sortedLabel);
        resultPanel.add(sortedField);
        buttonPanel.add(sortButton);
        sortPanel.add(ascending);
        sortPanel.add(descending);
        numericPanel.add(integer);
        numericPanel.add(fraction);
        
         
        //Add panels to main frame with layouts
        JPanel top = new JPanel();
        add(top, BorderLayout.NORTH);
        top.setLayout(new GridLayout(3,1));
        top.add(inputPanel);        
        top.add(resultPanel);
        top.add(buttonPanel);
        
        JPanel bot = new JPanel();
        add(bot, BorderLayout.SOUTH);
        bot.add(sortPanel);
        bot.add(numericPanel);
        
        //Adjust text fields ablility to be edited
        originalField.setEditable(true);
        sortedField.setEditable(false);
        ascending.setSelected(false);
        descending.setSelected(false);
        integer.setSelected(false);
        fraction.setSelected(false);
        
        //Add an action listener and handler to the construct tree button 
        sortButton.addActionListener((ActionEvent e) -> {
        try{    
            String input = originalField.getText();            
            Sort sortedInput = new Sort(input);
            if(ascending.isSelected() && integer.isSelected() && input.indexOf('/') == -1){
            sortedField.setText(sortedInput.ascend());
            }else if(descending.isSelected() && integer.isSelected() && input.indexOf('/') == -1){
            sortedField.setText(sortedInput.descend());    
            }else if(ascending.isSelected() && fraction.isSelected() && input.indexOf('/') >= 0){
            sortedField.setText(sortedInput.ascend());
            }else if (descending.isSelected() && fraction.isSelected() && input.indexOf('/') >= 0){
            sortedField.setText(sortedInput.descend());    
            }            
        }catch(NumberFormatException x){
            JFrame parent1 = new JFrame();
            JOptionPane.showMessageDialog(parent1, "Non numberic input");
        }catch(NoSuchElementException p){
            JFrame parent1 = new JFrame();
            JOptionPane.showMessageDialog(parent1, "Non numberic input");
        }catch(ClassCastException j){
            JFrame parent1 = new JFrame();
            JOptionPane.showMessageDialog(parent1, "Enter all integers or all fractions");
        }
        }
        );
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
        Project3 p1 = new Project3("Binary Search Tree Sort", 420, 220);
        p1.display();  
    }
    
}
