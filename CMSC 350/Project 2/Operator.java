package project2;

/*
 * File: Operator
 * Author: David Robbins
 * Date: April 8, 2017
 * Purpose: Operator class basis for derived classes
 */

public abstract class Operator {
   abstract public int evaluate(int x, int y);
   public void assembly(StringBuilder builder, String leftValue, String rightValue){
        String operator = toString();
        if(null != operator)switch (operator) {
           case "+":
               operator = "Add";
               break;
           case "-":
               operator = "Sub";
               break;
           case "*":
               operator = "Mul";
               break;
           case "/":
               operator = "Div";
               break;
           default:
               break;
       }
        builder.append(operator);
        builder.append(" ");
        builder.append(leftValue);
        builder.append(", ");
        builder.append(rightValue);
        builder.append("\n");
   }
}

class AddOperator extends Operator {
    public int evaluate(int d1, int d2) {
        return d1 + d2;
    }
    
    public String toString() {
        return "+";
    }
}
   
class MulOperator extends Operator {
    public int evaluate(int d1, int d2) {
        return d1 * d2;
    }
  
    public String toString() {
        return "*";
    }
}
   
class SubOperator extends Operator {
    public int evaluate(int d1, int d2) {
        return d1 - d2;
    }

    public String toString() {
        return "-";
    }
}
   
class DivOperator extends Operator {
    public int evaluate(int d1, int d2) {
        return d1 / d2;
    }

    public String toString() {
        return "/";
    }
}
