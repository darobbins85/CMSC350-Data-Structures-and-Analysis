/*
 * File: Token
 * Author: David Robbins
 * Date: March 26, 2017
 * Purpose: This file is creates the tokenizer and implements the pseudocode
 *          from project 1 instruction.
 */

import java.util.Stack;
import java.util.StringTokenizer;

public class Token {
        
    //Make stacks and class variables
    private Stack<String> operand = new Stack<>();
    private Stack<String> operator = new Stack<>();
    private String tkn;
    
    public Token(String expression){
        //Remove Spaces from expression
        expression = expression.replaceAll("\\s+", "");        
        
        //Tokenize the string containing the expression
        StringTokenizer tokens = new StringTokenizer(expression, "()*/+-", true);
        
        //While there are more tokens, Get the next token
        while(tokens.hasMoreTokens()){
            tkn = tokens.nextToken();
            
            //If it is an operand
            if(tkn.matches("[0-9]+")){
                //Push it to the operand stack
                operand.push(tkn);
            }
            
            //Else if it is a left parenthesis
            else if(tkn.equals("(")){
                //Push it onto the operator stack
                operator.push(tkn);
            }
            
            //Else if it is a right parenthesis
            else if(tkn.equals(")")){                
                //While top of the operator stack not a left parenthesis                
                while(!"(".equals(operator.peek())){
                    //Pop two operands and an operator
                    int op2 = Integer.parseInt(operand.pop());
                    int op1 = Integer.parseInt(operand.pop());
                    String opr = operator.pop();
                    //Perform the calculation
                    int result = compute(op1, op2, opr); 
                    //Push the result onto the operand stack
                    operand.push(result+"");
                }
                //Pop the left parenthesis from the stack
                operator.pop();
            }
            
            //Else if it is an operator
            else if(tkn.matches("[+-/*]")){
                //While the operator stack is not empty and 
                //The operator at the top of the stack has higher
                //Or the same precedence than the current operator				               
                while(!operator.empty() && hasPrecence(operator.peek(), tkn)){
                     //Pop two operands and perform the calculation
                    int op2 = Integer.parseInt(operand.pop());
                    int op1 = Integer.parseInt(operand.pop());
                    String opr = operator.pop();
                    int result = compute(op1, op2, opr);
                    
                    //Push the result onto the operand stack
                    operand.push(result+"");
                }
                //Push the current operator on the operators stack
                operator.push(tkn+"");                        	
                }
            }            
        
        //While the operator stack is not empty    
        while(!operator.empty()){
            //pop two operands and an operator
            int op2 = Integer.parseInt(operand.pop());
            int op1 = Integer.parseInt(operand.pop());
            String opr = operator.pop();
            //Perform the calculation
            int result = compute(op1, op2, opr);
            //Push the result onto the operand stack
            operand.push(result+"");			            
        }
    }
    
    //Method for testing operator precedence
    private boolean hasPrecence(String operator1, String operator2) {
      if ("*".equals(operator1)) {
        return true;
      }
      if ("/".equals(operator1)) {
        return !"*".equals(operator2);
      }
      if ("-".equals(operator1)) {
        return "+".equals(operator2);
      }
      return false;      
    }
    
    //Method for performing the necessary calculation
    private int compute(int op1, int op2, String opr){
        int result=0;
        switch (opr) {
                case "*":
                    result = op1*op2;
                    break;
                case "/":
                    result = op1/op2;
                    break;
                case "+":
                    result = op1+op2;
                    break;
                case "-":
                    result = op1-op2;
                    break;
                default:
                    break;
            }
        return result;
    }
    
    //Method to return the answer in the form of a String
    public String toStr(){
        return operand.peek();
    }
    
} 