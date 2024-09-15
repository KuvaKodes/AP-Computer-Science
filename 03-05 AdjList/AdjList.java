// Name: Kushaan Vardhan 
// Date: 05/12/2022
 
import java.util.*;
import java.io.*;

/* Resource classes and interfaces
 * for use with Graphs3: EdgeList,
 *              Graphs4: DFS-BFS
 *          and Graphs5: EdgeListCities
 */

/**************** Graphs 3: EdgeList *****/
interface VertexInterface
{
   public String getName();
   public TreeSet<Vertex> getAdjacencies();
   
   /*
     postcondition: if the set already contains a vertex with the same name, the vertex v is not added
                    this method should be O(1)
   */
   public void addAdjacent(Vertex v);
   /*
     postcondition:  returns as a string one vertex with its adjacencies, without commas.
                     for example, D [C A]
     */
   public String toString(); 
 
} 
 
/*************************************************************/
class Vertex implements VertexInterface, Comparable<Vertex> //2 vertexes are equal if and only if they have the same name
{
   private final String name;
   private TreeSet<Vertex> adjacencies;
  /* enter your code here  */
   public Vertex(String s){
      name = s; 
      adjacencies = new TreeSet<Vertex>();
   }
   public String getName(){
      return name; 
   }
   public TreeSet<Vertex> getAdjacencies(){
      return adjacencies; 
   }
   
   /*
     postcondition: if the set already contains a vertex with the same name, the vertex v is not added
                    this method should be O(1)
   */
   public void addAdjacent(Vertex v){
      adjacencies.add(v); 
   }
   /*
     postcondition:  returns as a string one vertex with its adjacencies, without commas.
                     for example, D [C A]
     */
   public String toString(){
      String toRet = name + " [";
      Iterator<Vertex> it = adjacencies.iterator(); 
      while(it.hasNext()){
         toRet += it.next().getName();
         if(it.hasNext())
            toRet += " ";
      }
      toRet += "]";
      return toRet; 
   }
   
   public int compareTo(Vertex v){
      return name.compareTo(v.getName()); 
   }
  
  
}   

/*************************************************************/
interface AdjListInterface 
{
   public Set<Vertex> getVertices();
   public Vertex getVertex(String vName);
   public Map<String, Vertex> getVertexMap();  //this is just for codepost testing
   
   /*      
      postcondition: if a Vertex with the name v exists, then the map is unchanged.
                     addVertex should work in O(log n)
   */
   public void addVertex(String vName);
   
   /*
      precondition:  both Vertexes, source and target, are already stored in the graph.
      postcondition:  addEdge should work in O(1)
   */
   public void addEdge(String source, String target); 
   
   /*
       returns the whole graph as one string, e.g.:
       A [C]
       B [A]
       C [C D]
       D [C A]
     */
   public String toString(); 

}

  
/********************** Graphs 4: DFS and BFS *****/
interface DFS_BFS
{
   public List<Vertex> depthFirstSearch(String name);
   public List<Vertex> breadthFirstSearch(String name);
   /*   extra credit methods */
//    public List<Vertex> depthFirstRecur(String name);
//    public List<Vertex> depthFirstRecurHelper(Vertex v, ArrayList<Vertex> reachable);
}

/****************** Graphs 5: Edgelist with Cities *****/
interface EdgeListWithCities
{
   public void readData(String cities, String edges) throws FileNotFoundException;
   public int edgeCount();
   public int vertexCount();
   public boolean isReachable(String source, String target);
   public boolean isStronglyConnected(); //return true if every vertex is reachable from every 
                                          //other vertex, otherwise false 
}


/*************  start the Adjacency-List graph  *********/
public class AdjList implements AdjListInterface, DFS_BFS, EdgeListWithCities
{
   //we want our map to be ordered alphabetically by vertex name
   private Map<String, Vertex> vertexMap = new TreeMap<String, Vertex>();
      
   /* constructor is not needed because of the instantiation above */
  
   /* enter your code here  */
   public Set<Vertex> getVertices(){
      Set<Vertex> ste = new TreeSet<Vertex>(); 
      for(String s: vertexMap.keySet()){
         ste.add(vertexMap.get(s)); 
      }
      return ste; 
      
   }
   public Vertex getVertex(String vName){
      return vertexMap.get(vName); 
   }
   public Map<String, Vertex> getVertexMap(){  //this is just for codepost testing
      return vertexMap; 
   }
   /*      
      postcondition: if a Vertex with the name v exists, then the map is unchanged.
                     addVertex should work in O(log n)
   */
   public void addVertex(String vName){
      if(vertexMap.get(vName) == null){
         vertexMap.put(vName, new Vertex(vName)); 
      }
   }
   
   /*
      precondition:  both Vertexes, source and target, are already stored in the graph.
      postcondition:  addEdge should work in O(1)
   */
   public void addEdge(String source, String target){
      Vertex v = vertexMap.get(source);
      v.addAdjacent(vertexMap.get(target));
   }
   
   /*
       returns the whole graph as one string, e.g.:
       A [C]
       B [A]
       C [C D]
       D [C A]
     */
   public String toString(){
      String toRet = "";
      for(String s: vertexMap.keySet()){
         toRet += vertexMap.get(s).toString() + "\n";
      }
      return toRet; 
   }
   public List<Vertex> depthFirstSearch(String name){
      List<Vertex> toRet = new ArrayList<Vertex>();
      Stack<Vertex> temp = new Stack<Vertex>();
      Stack<Vertex> dremp = new Stack<Vertex>(); 

      for(Vertex v: vertexMap.get(name).getAdjacencies()){
         dremp.push(v);
      }
      while(!dremp.isEmpty()){
         temp.push(dremp.pop()); 
      }
      Vertex te; 
      while(!temp.isEmpty()){
         te = temp.pop();
         if(!toRet.contains(te)){
            toRet.add(te);
            for(Vertex v: te.getAdjacencies())
               temp.push(v);
         } 
      }
      return toRet; 
      
   }
   public List<Vertex> breadthFirstSearch(String name){
      List<Vertex> toRet = new ArrayList<Vertex>();
      Queue<Vertex> temp = new LinkedList<Vertex>();
      Stack<Vertex> dremp = new Stack<Vertex>(); 

      for(Vertex v: vertexMap.get(name).getAdjacencies()){
         dremp.push(v); 
      }
      while(!dremp.isEmpty()){
         temp.add (dremp.pop()); 
      }
      Vertex te; 
      while(!temp.isEmpty()){
         te = temp.remove();
         if(!toRet.contains(te)){
            toRet.add(te);
            for(Vertex v: te.getAdjacencies())
               temp.add(v);
         } 
      }
      return toRet;
      
   }

   public void readData(String cities, String edges) throws FileNotFoundException{
      Scanner cityS = new Scanner(new File(cities)); 
      Scanner edgesS = new Scanner(new File(edges)); 
      
      while(cityS.hasNext()){
         addVertex(cityS.next());
      }
      
      while(edgesS.hasNext()){
         addEdge(edgesS.next(), edgesS.next()); 
      }
          
   }
   public int edgeCount(){
      int edgeCount = 0;
      for(String s: vertexMap.keySet()){
         Vertex temp = vertexMap.get(s);
         edgeCount += temp.getAdjacencies().size();         
      }
      return edgeCount;    
      
   }
   public int vertexCount(){
      return vertexMap.keySet().size();    
   }
   public boolean isReachable(String source, String target){
      List<Vertex> dfs = depthFirstSearch(source);
      for(Vertex v: dfs){
         if(v.getName().equals(target))
            return true; 
      }
      return false;
   }
   public boolean isStronglyConnected(){ //return true if every vertex is reachable from every 
     for(String s: vertexMap.keySet()){
         for(String str: vertexMap.keySet()){
            if(!isReachable(s, str))
               return false; 
         }
     }
     
     return true;                                      //other vertex, otherwise false
                                          
   }

 
 
 
 
 
 
}


