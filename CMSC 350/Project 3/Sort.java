package project3;

/*
 * File: Sort
 * Author: David Robbins
 * Date: 
 * Purpose: This class creates the Sort object which sorts and displays a 
 *          string of integers
 */

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Stack;
import java.util.StringTokenizer;

public class Sort {
    private String token;
    private BinaryTree tree = new BinaryTree();
    
    public Sort(String input){
        //Tokenize the string containing the expression
        StringTokenizer tokens = new StringTokenizer(input);
        
        //While there are more tokens, Get the next token
        while(tokens.hasMoreTokens()){            
            token = tokens.nextToken();
            try{
            if(Integer.parseInt(token)/1 == Integer.parseInt(token)){
            tree.addNode(Integer.parseInt(token));
            }
            }catch(NumberFormatException e){
                StringTokenizer tester = new StringTokenizer(token);                
                while(tester.hasMoreTokens()){
                StringTokenizer tester2 = new StringTokenizer(tester.nextToken(), "/", false);
                    while(tester2.hasMoreTokens()){
                        int numerator = Integer.parseInt(tester2.nextToken());
                        int denominator = Integer.parseInt(tester2.nextToken());
                        Fraction fractionNode = new Fraction(numerator, denominator);
                        tree.addNode(fractionNode);
                    }
                }
            }    
        }
    }
    
    public String ascend(){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        tree.inOrder(tree.root);
        System.out.flush();
        System.setOut(old);
        return baos.toString();
    }       

    public String descend(){
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream ps = new PrintStream(baos);
        PrintStream old = System.out;
        System.setOut(ps);
        tree.inOrder(tree.root);
        System.out.flush();
        System.setOut(old);
        String x = baos.toString();
        StringTokenizer tokens = new StringTokenizer(x);
        Stack<String> stackStrings = new Stack();
        String tokenD,finalString = "";
        while(tokens.hasMoreTokens()){
            tokenD = tokens.nextToken();
            stackStrings.push(tokenD);
        }
        while(!stackStrings.isEmpty()){
            String y = stackStrings.pop();
            finalString = finalString + y + " ";
        }
        return finalString;
    }
    
    public static void main(String[] args){
        Sort p1 = new Sort("2/2 6/3 3/4");        
    }
    
}


