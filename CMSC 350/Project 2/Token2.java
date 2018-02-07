package project2;

/*
 * File: Token2
 * Author: David Robbins
 * Date: April 8, 2017
 * Purpose: This file creates the tokenizer to tokenize the given expression as 
 *          well as translate a postfix instruction to infix and print assembly
 *          to a text file
 */

import java.util.Stack;
import java.util.StringTokenizer;

public class Token2 {
        
    //Make stacks and class variables
    private Stack<Node> operand = new Stack<>();
    private Stack<String> operator = new Stack<>();
    private String token, infixResult, assembly;
    private Node tree, leaf1, leaf2;
    
    public Token2(String expression)  throws BadTokenException {                
        //Tokenize the string containing the expression
        StringTokenizer tokens = new StringTokenizer(expression);
        
        //While there are more tokens, Get the next token
        while(tokens.hasMoreTokens()){
            token = tokens.nextToken();
            
            //Push the token operands into the operand stack after converting them to Nodes
            if(token.matches("[0-9]+")){
                operand.push(new OperandNode(Integer.parseInt(token)));
            }
            
            //Push the operator tokens into the operator stack
            else if(token.matches("[+-/*]")){
                operator.push(token);
            }
            
            //Clear operand stack and set tokens to empty if the token is incompatible 
            //so that the BadTokenException can be thrown 
            else if(!token.matches("[0-9]+") || !token.matches("[+-/*]")){
                operand.clear();
                tokens = new StringTokenizer("");
            }
                
                //Perform calculations
                while(!operator.empty() && operand.size()>1){    
                    leaf2 = operand.pop();
                    leaf1 = operand.pop();
                    String oper = operator.pop();
                    if(null != oper)switch (oper) {
                    case "+":
                        operand.push(new OperatorNode(new AddOperator(), leaf1, leaf2));
                        break;
                    case "-":
                        operand.push(new OperatorNode(new SubOperator(), leaf1, leaf2));
                        break;
                    case "*":
                        operand.push(new OperatorNode(new MulOperator(), leaf1, leaf2));
                        break;
                    case "/":
                        operand.push(new OperatorNode(new DivOperator(), leaf1, leaf2));
                        break;
                    default:
                        break;
                }
                    
                }
                                
        }
        //Create tree as well as infix expression and assembly 
        if(!operand.empty()){
            tree = operand.pop();
            infixResult = tree.inOrderWalk();
            StringBuilder builder = new StringBuilder();
            tree.assembly(builder);
            assembly = builder.toString();
        }
        else{
            BadTokenException badToken = new BadTokenException(token);
            throw badToken;
        }
       
    }
    
    public String toStr(){        
        return infixResult;
    }
    
    public String getAssembly(){
        return assembly;
    }

}

