 //Name:   
 //Date:
 
import java.util.*;

/* implement the API for java.util.PriorityQueue
 *     a min-heap of objects in an ArrayList<E> in a resource class
 * test this class with HeapPriorityQueue_Driver.java.
 * test this class with LunchRoom.java.
 * add(E) and remove()  must work in O(log n) time
 */
public class HeapPriorityQueue<E extends Comparable<E>> 
{
   private ArrayList<E> myHeap;
   
   public HeapPriorityQueue()
   {
      myHeap = new ArrayList<E>();
      myHeap.add(null);
   }
   
   public ArrayList<E> getHeap()   //for Codepost
   {
      return myHeap;
   }
   
   public int lastIndex()
   {
      return myHeap.size()-1;
   }
   
   public boolean isEmpty()
   {
      if(myHeap.size() == 1)
         return true;
      else
         return false;  
      
   }
   
   public boolean add(E obj)
   {
      myHeap.add(obj);
      heapUp(lastIndex());
      return true;
   }
   
   public E remove()
   {
      swap(1, lastIndex());
      E toRet = myHeap.remove(lastIndex()); 
      heapDown(1, lastIndex());
      return toRet;     
   }
   
   public E peek()
   {
    if(isEmpty())
      return null; 
   return myHeap.get(1); 
   }
   
   //  it's a min-heap of objects in an ArrayList<E> in a resource class
   public void heapUp(int k)
   {
      if(myHeap.isEmpty())
         return; 
      int parent = k/2;
      if(parent <= 0)
         return; 
      if(myHeap.get(k).compareTo(myHeap.get(parent)) < 0){
         swap(k, parent); 
         heapUp(parent);
      }
          
   }
   
   private void swap(int a, int b)
   {
     E temp = myHeap.get(a); 
     myHeap.set(a, myHeap.get(b)); 
     myHeap.set(b, temp); 
   }
   
  //  it's a min-heap of objects in an ArrayList<E> in a resource class
   public void heapDown(int k, int lastIndex)
   {
      int left = 2 * k; 
      int right = 2 * k + 1; 
      if(left > lastIndex)
         return; 
      if(left == lastIndex)
         {
            if (myHeap.get(k).compareTo(myHeap.get(left)) > 0)
               swap(k, left); 
         }   
      else
      { 
            int max = right; 
            if (myHeap.get(right).compareTo(myHeap.get(left)) > 0)
               max = left; 
            if(myHeap.get(k).compareTo(myHeap.get(max)) > 0)
            {
               swap(k, max); 
               heapDown(max, lastIndex); 
            }
      }
   }
   
   public String toString()
   {
      return myHeap.toString();
   }  
}
