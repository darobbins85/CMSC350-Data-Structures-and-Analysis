package project3;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

/*
 * File: Fraction
 * Author: David Robbins
 * Date: April 20, 2017 
 * Purpose: This class defines a fraction
 */

public class Fraction implements Comparable<Fraction>{
    
    private int numerator;
    private int denominator;
    
    public Fraction(int numerator, int denominator){        
        this.numerator = numerator;
        this.denominator = denominator;
    }

    @Override
    public int compareTo(Fraction other) {
        if(denominator<0 && numerator>0){
            numerator = numerator * (-1);
            denominator = denominator * (-1);
        }
        if(other.denominator<0 && other.numerator>0){
            other.numerator = other.numerator * (-1);
            other.denominator = other.denominator * (-1);
        }
        if(denominator < 0 && numerator < 0){
            denominator = denominator * (-1);
            numerator = numerator * (-1);
        }
        if(other.denominator < 0 && other.numerator < 0){
            other.denominator = other.denominator * (-1);
            other.numerator = other.numerator * (-1);
        }
        if(denominator == 0 || other.denominator == 0){
            JFrame parent1 = new JFrame();
            JOptionPane.showMessageDialog(parent1, "Invalid fraction with 0 as denominator : Restart program");
            System.exit(numerator);
        }
        int crossProduct = numerator * other.denominator - denominator * other.numerator;
        if(crossProduct >= 0){
            return 1;
        }else{
            return -1;
        }        
    }
    
    public String toString(){
        return numerator + "/" + denominator;
    }
}
