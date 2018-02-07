package project2;

/*
 * File: OperatorNode
 * Author: David Robbins
 * Date: April 8, 2017
 * Purpose: OperatorNode behavior
 */

public class OperatorNode implements Node {

    Node left, right;
    Operator operator;
    static int registerCounter;
    private String operatorLabel;
   
    public OperatorNode(Operator operator, Node left,
                        Node right){
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    public int evaluate() {
        int leftValue = left.evaluate();
        int rightValue = right.evaluate();
        return operator.evaluate(leftValue, rightValue);
    } 

    public String inOrderWalk() {
        String leftValue = left.inOrderWalk();
        String rightValue = right.inOrderWalk();
        return "(" + leftValue + " " + operator + " "
                   + rightValue + ")";
    }
    
    public String assembly(StringBuilder builder) {
        String leftRegister = left.assembly(builder);
        String rightRegister = right.assembly(builder);
        String register = "R" + (registerCounter++);
        builder.append(register);
        builder.append(" ");
        operator.assembly(builder, leftRegister, rightRegister+"\t"); 
        return register;
        
    }
}


