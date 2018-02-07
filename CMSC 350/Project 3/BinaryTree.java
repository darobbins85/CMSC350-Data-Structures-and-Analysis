package project3;

/*
 * File: BinaryTree
 * Author: David Robbins
 * Date: 
 * Purpose: This class creates the binary tree
 */

public class BinaryTree<T extends Comparable<T>> {
    
    Node root;
    
    public void addNode(T key){
        Node newNode = new Node(key);
        
        if(root == null){
            root = newNode;
        } else {
            Node focusNode = root;
            Node parent;
            while(true){
                parent = focusNode;
                if(key.compareTo((T)focusNode.key) == -1 
                || key.compareTo((T)focusNode.key) == 0){
                    focusNode = focusNode.leftChild;
                    if(focusNode == null){
                        parent.leftChild = newNode;
                        return;
                    }
                }
                else{
                    focusNode = focusNode.rightChild;
                    if(focusNode == null){
                        parent.rightChild = newNode;
                        return;
                    }
                }
            }
        }
    }
        
    public void inOrder(Node focusNode){        
        if(focusNode != null){
            inOrder(focusNode.leftChild);
            System.out.print(focusNode + " ");
            inOrder(focusNode.rightChild);            
        }        
    }
                 
}

class Node<T>{
    
    T key;
    
    Node leftChild;
    Node rightChild;
    
    Node(T key){
        this.key = key;
    }
    
    public String toString(){
        return "" + key;
    }
}
