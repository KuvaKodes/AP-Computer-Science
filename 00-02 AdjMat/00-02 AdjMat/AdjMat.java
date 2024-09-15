// Name: Kushaan Vardhan
// Date:5/6/2022
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graph0 AdjMat_0_Driver,
 *              Graph1 WarshallDriver,
 *          and Graph2 FloydDriver
 */

interface AdjacencyMatrix
{
   public int[][] getGrid();
   public void addEdge(int source, int target);
   public void removeEdge(int source, int target);
   public boolean isEdge(int from, int to);
   public String toString();  //returns the grid as a String
   public int edgeCount();
   public List<Integer> getNeighbors(int source);
   //public List<String> getReachables(String from);  //Warshall extension
}

interface Warshall      //User-friendly functionality
{
   public boolean isEdge(String from, String to);
   public Map<String, Integer> getVertices();     
   public void readNames(String fileName) throws FileNotFoundException;
   public void readGrid(String fileName) throws FileNotFoundException;
   public void displayVertices();
   public void allPairsReachability();   // Warshall's Algorithm
   // public List<String> getReachables(String from);  //Warshall extension
}

interface Floyd
{
   public int getCost(int from, int to);
   public int getCost(String from, String to);
   public void allPairsWeighted(); 
}

public class AdjMat implements AdjacencyMatrix, Warshall, Floyd 
{
   private int[][] grid = null;   //adjacency matrix representation
   private Map<String, Integer> vertices = null;   // name maps to index (for Warshall & Floyd)
//    /*for Warshall's Extension*/  ArrayList<String> nameList = null;  //reverses the map, index-->name
	  
   /*  write constructor, accessor method, and instance methods here  */  
   public AdjMat(int size){
      grid = new int[size][size];
      vertices = new TreeMap<String, Integer>(); 
   }
   
   public int[][] getGrid(){
      return grid; 
   }
   public void addEdge(int source, int target){
      grid[source][target] = 1;
   }
   public void removeEdge(int source, int target){
      grid[source][target] = 0; 
   }
   public boolean isEdge(int from, int to){
      int val = grid[from][to];
      if(val > 0 && val < 9999)
         return true;
      else
         return false; 
   }

   public String toString(){
      String toRet = ""; 
      for(int r = 0; r < grid.length; r++){
         for(int c = 0; c < grid[0].length; c++){
            toRet += grid[r][c] + " "; 
         }
         toRet += "\n";
      }
      return toRet; 
   }
   public int edgeCount(){
      int count = 0;
      for(int [] r: grid){
         for(int c: r){
            if(c < 9999 && c > 0)
               count++; 
         }
      }
      return count; 
   }
  public List<Integer> getNeighbors(int source){
      List<Integer> l = new ArrayList<Integer>();
      for(int c = 0; c < grid[0].length; c++){
         if(grid[source][c] == 1){
            l.add(c);
         }
      }
      return l;   
  }
   public boolean isEdge(String from, String to){
      return grid[vertices.get(from)][vertices.get(to)] == 1;
   }
   public Map<String, Integer> getVertices(){
      return vertices; 
   } 
   public void readNames(String fileName) throws FileNotFoundException{
      Scanner sc = new Scanner(new File(fileName));
      int h = sc.nextInt(); 
      for(int i = 0; i < h; i++){
         vertices.put(sc.next(), i); 
      }
   }
   public void readGrid(String fileName) throws FileNotFoundException
   {
      Scanner sc = new Scanner(new File(fileName));
      int h = sc.nextInt();
      for(int i = 0; i < h; i++){
         for(int j = 0; j < h; j++){
            grid[i][j] = sc.nextInt();
         }
      }
   }
   public void displayVertices(){
      for(String key: vertices.keySet()){
         System.out.println(vertices.get(key) + "-" + key); 
      }
   }
   public void allPairsReachability(){   // Warshall's Algorithm
      for(int v = 0; v < grid.length; v++){
         for(int r = 0; r < grid.length; r++){
            for(int c = 0; c < grid[0].length; c++){
               if(isEdge(r, v) && isEdge(v, c)){
                  addEdge(r, c); 
               }
            }
          }
       }
    }
   
   public int getCost(int from, int to){
      return grid[from][to]; 
   }
   public int getCost(String from, String to){
      return grid[vertices.get(from)][vertices.get(to)];
      
   }
   public void allPairsWeighted(){
     for(int v = 0; v < grid.length; v++){
         for(int r = 0; r < grid.length; r++){
            for(int c = 0; c < grid[0].length; c++){
               int cost = getCost (r, v) + getCost(v, c);
               if (cost < getCost (r, c))
                  grid[r][c] = cost;
           }
        }
     }
   }
  
   
   //public List<String> getReachables(String from);  //Warshall extension

   
   
}
