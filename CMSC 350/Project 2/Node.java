package project2;

/*
 * File: Node
 * Author: David Robbins
 * Date: April 8, 2017 
 * Purpose: Interface for derived classes
 */

public interface Node {
    public int evaluate();
    public String inOrderWalk();
    public String assembly(StringBuilder builder);
}
