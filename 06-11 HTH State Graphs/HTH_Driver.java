// Name: Kushaan Vardhan
// Date: 5/31/2022
 
import java.util.*;
import java.io.*;

/* For use with Graphs11: State Graphs,
   Heads-Tails-Heads
 */

class HTH_Driver
{
   public static void main(String[] args) throws FileNotFoundException
   {
      System.out.print("Enter the initial state, three H and/or T:  ");
      Scanner in = new Scanner(System.in);
      String initial = in.next().toUpperCase();
      Vertex v = makeGraph(initial);
      System.out.println("The state graph has been made.");
      
      while(true)
      {
         System.out.print("Enter the final state, three H and/or T:  ");
         String finalState = in.next().toUpperCase();;
         if( finalState.equals("-1") )
            break;
         v = findBreadth(v, finalState);
         System.out.println("The shortest path from "+initial+" to "+ finalState+ " is: ");
         System.out.println(initial);
         String s = "";
         while(v.previous != null)
         {
            s = v+"\n"+s;
            v = v.previous;
         }
         System.out.println(s);
      }
   }
   
   public static Vertex makeGraph(String s)
   {
      List<Vertex> everyV = new ArrayList<Vertex>(); 
      everyV.add(new Vertex("HHH"));
      everyV.add(new Vertex("HHT"));
      everyV.add(new Vertex("HTH"));
      everyV.add(new Vertex("HTT"));
      everyV.add(new Vertex("THH"));
      everyV.add(new Vertex("THT"));
      everyV.add(new Vertex("TTH"));
      everyV.add(new Vertex("TTT"));
      for(Vertex v: everyV){
         boolean[] b = v.getState();
         for(Vertex v2: everyV){
            boolean[] c = v2.getState();
            if(b.equals(c))
               continue;
            else if(b[0] == c[0] && b[2] == c[2])
               v.addEdge(v2);
            else if(b[1] == b[2] && c[1] == c[2] && b[1] == c[1] && b[2] == c[2])
               v.addEdge(v2);     
            else if(b[0] == b[1] && c[0] == c[1] && b[0] == c[0] && b[1] == c[1])
               v.addEdge(v2);         
         }
      }
      
      Vertex toRet = new Vertex(s);
      for(Vertex v: everyV){
         if(v.toString().equals(toRet.toString()))
            return v;
      }
      
      return null;   
   }
   
   public static Vertex findBreadth(Vertex v, String goal)
   {
      List<Vertex> been = new ArrayList<Vertex>();  
      Queue<Vertex> temp = new LinkedList<Vertex>(); 
      temp.add(v);
      been.add(v);        
      while(!temp.isEmpty()){
         Vertex t = temp.remove();
         if(t.check(goal))
            return t; 
         for(Vertex g: t.getEdges())
         {
            boolean b = true;
            for(Vertex n: been){
               if(n.check(g.toString()))
                  b = false;
            }
            
            if(b){
               g.setPrevious(t);
               been.add(g);  
               temp.add(g);
            }
         }      
         
      }
      
      return null; 
      
   }
}

class Vertex
{
   private final boolean[] state;
   private List<Vertex> edges = new ArrayList<Vertex>();
   public Vertex previous;
   public String name = ""; 
    
   public Vertex(String s){
      name = s; 
      state = new boolean[3]; 
      for(int i = 0; i < state.length; i++){
         if(s.charAt(i) == 'H')
            state[i] = true;         
         else
            state[i] = false;
      }
      previous = null;  
   }
   
   public Vertex(boolean[] bool, Vertex v){
      state = bool; 
      previous = v;
      for(boolean b: state){
         if(b)
            name += "H"; 
         else 
            name += "T"; 
      }    
   }
   
   
   
   public void setPrevious(Vertex v){
      previous = v; 
   }
   
   public void addEdge(Vertex v){
      if(!edges.contains(v))
         edges.add(v); 
   }
   
   public Vertex getPrevious(){
      return previous; 
   }
   
   public List<Vertex> getEdges(){
      return edges; 
   }
   
   public boolean[] getState(){
      return state; 
   }
   
   public String toString(){
      return name; 
   }
   
   public boolean check(String s){
      return name.equals(s);
   }
   
   
}

/************************
 Enter the initial state, three H and/or T:  HTH
 The state graph has been made.
 Enter the final state, three H and/or T:  THT
 The shortest path from HTH to THT is: 
 HTH
 HHH
 HHT
 HTT
 TTT
 THT
 
 Enter the final state, three H and/or T:  HHH
 The shortest path from HTH to HHH is: 
 HTH
 HHH
 
 Enter the final state, three H and/or T:  -1
 

 *************************************/