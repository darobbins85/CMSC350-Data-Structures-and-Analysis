package project2;

/*
 * File: OperandNode
 * Author: David Robbins
 * Date: April 8, 2017
 * Purpose: OperandNode behavior
 */

public class OperandNode implements Node {
    private int value;
    
    public OperandNode(int value) {
        this.value = value;
    }
    
    public int evaluate() {
        return value;
    }

    public String inOrderWalk() {
        return String.valueOf(value);
    }
    
    public String assembly(StringBuilder builder){
        return String.valueOf(value);
    }
}