package project4;

/*
 * File: Graph
 * Author: David Robbins
 * Date: May 5, 2017
 * Purpose: Class that creates directed graph with associated methods
 */

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Map;
import java.util.Stack;
import java.util.StringTokenizer;

public class Graph { 

    private static ArrayList<Vertex> vertexIndexArrayList = new ArrayList<>();
    private static HashMap hashMap = new HashMap();
    private String fileInput;
    private Vertex vertex;
    private String[] tokensArray;
    private StringTokenizer tokenLine;
    private Stack classStack = new Stack();
    private StringBuilder classOutputString = new StringBuilder(); 
    
    
    public void readFile(String file) throws InvalidClassName{    
        
            int hashKey = 0;
            try{
            BufferedReader br = new BufferedReader(new FileReader(file));
        
            int count = 0;
            while((fileInput = br.readLine()) != null){
                tokenLine = new StringTokenizer(fileInput, " ", false);
                tokensArray = new String[tokenLine.countTokens()];
                for(int i = count; i < tokensArray.length; i++){
                    tokensArray[i] = tokenLine.nextToken();
                    if(!hashMap.containsKey(tokensArray[i])){
                        hashMap.put(tokensArray[i], hashKey);
                        hashKey++;
                    }                    
                }
                count = tokenLine.countTokens() + count;
            }
            
            br = new BufferedReader(new FileReader(file));
            for (int i = 0; i < hashMap.size(); i++){
                vertex = new Vertex(new LinkedList());
                vertexIndexArrayList.add(vertex);
            }
            
            while((fileInput = br.readLine()) != null){
                tokenLine = new StringTokenizer(fileInput, " ", false);
                tokensArray = new String[tokenLine.countTokens()];
                boolean first = true;
                int firstIndex = 0;
                for (int i = 0; i < tokensArray.length; i++){
                    tokensArray[i] = tokenLine.nextToken();
                    if(first){
                        first = false;
                        firstIndex = (Integer)hashMap.get(tokensArray[i]);
                    } else {
                        int number = (Integer) hashMap.get(tokensArray[i]);
                        vertexIndexArrayList.get(firstIndex).addEdge(number);
                    }
                }
            }
            
            for(int i = 0; i < vertexIndexArrayList.size(); i++){
                vertex.toString(vertexIndexArrayList.get(i));
            }
            }catch(IOException x){
                InvalidClassName invalid = new InvalidClassName();
                throw invalid;
            }
            
    }
    
    
    public void depthFirstSearch(Vertex s) throws CycleDetected {
        if (s.getDiscovered()) {
            CycleDetected cycle = new CycleDetected();
            throw cycle;
        }
        if (s.getFinished()) {
            return;
        }
        s.setDiscovered();
        for (int i = 0; i < s.list.size(); i++) {
            depthFirstSearch(vertexIndexArrayList.get(s.list.get(i)));
        }
        s.setFinished();
        classStack.push(vertexIndexArrayList.indexOf(s));
    }
               
    public StringBuilder generateTopologicalOrder(String classToCompile) throws CycleDetected, InvalidClassName {
        try{
        int classCompilations = (Integer)hashMap.get(classToCompile);        
        depthFirstSearch(vertexIndexArrayList.get(classCompilations));
        System.out.print(classStack.toString());
        while(!classStack.isEmpty()){
            for (Iterator it = hashMap.entrySet().iterator(); it.hasNext();) {
                Map.Entry<String, Object> e = (Map.Entry<String, Object>) it.next();
                String key = e.getKey();
                Object value = e.getValue();
                if(!classStack.isEmpty()){                
                    if(value == classStack.peek()){
                        classStack.pop();
                        classOutputString.append(key + " ");                
                       }
                }
            }
        }        
        return classOutputString;
        } catch(Exception y){
            InvalidClassName invalid = new InvalidClassName();
            throw invalid;
        }
    }
}
    class Vertex {
        public LinkedList<Integer> list;
        private boolean discovered = false;
        private boolean finished = false;

        public Vertex(LinkedList list1){
            list = list1;
        }

        public Vertex addEdge(Integer integer) {
            this.list.add(integer);
            return this;
        }

        public void setDiscovered(){
            this.discovered = true;
        } 

        public boolean getDiscovered(){
            return this.discovered;
        }

        public void setFinished(){
            this.finished = true;
        } 

        public boolean getFinished(){
            return this.finished;
        }

        public void toString(Vertex vertex){
            System.out.print("[");
            for(int i = 0; i < vertex.list.size(); i++){
                if(i < (vertex.list.size()-1)){
                    System.out.print(vertex.list.get(i) + ",");
                }else{
                    System.out.print(vertex.list.get(i));
                }
            }
            System.out.print("]\n");
        }        
    }          





