// Name: Kushaan Vardhan   
// Date: 11/16/2021

//  implements some of the List and LinkedList interfaces: 
//	 	  size(), add(i, o), remove(i);  addFirst(o), addLast(o); 
//  This class also overrides toString().
//  the list is zero-indexed.
//  Uses DLNode.

public class DLL        //DoubleLinkedList
{
   private int size = 0;
   private DLNode head = new DLNode(); //dummy node--very useful--simplifies the code
   
   //no constructor needed
   
   /* two accessor methods  */
   public int size()
   {
      return size;
   }
   public DLNode getHead()
   {
      return head;
   }
   
   /* appends obj to end of list; increases size;
   	  @return true  */
   public boolean add(Object obj)
   {
      addLast(obj);
      return true;   
   }
   
   /* inserts obj at position index (the list is zero-indexed).  
      increments size. 
      no need for a special case when size == 0.
	   */
   public void add(int index, Object obj) throws IndexOutOfBoundsException  //this the way the real LinkedList is coded
   {
      if( index > size || index < 0 )
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode p = head;
      for(int i = 1; i <= index; i++)
         p = p.getNext();
      DLNode temp = new DLNode(obj, p, p.getNext());
      p.getNext().setPrev(temp);
      p.setNext(temp);
      size++;             
                    
   }
   
    /* return obj at position index (zero-indexed). 
    */
   public Object get(int index) throws IndexOutOfBoundsException
   { 
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      DLNode p = head;
      for(int i = 0; i <= index; i++)
         p = p.getNext();
      return p.getValue(); 
      
   }
   
   /* replaces obj at position index (zero-indexed). 
        returns the obj that was replaced.
        */
   public Object set(int index, Object obj) throws IndexOutOfBoundsException
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      DLNode p = head;
      for(int i = 0; i <= index; i++)
         p = p.getNext();
      Object val = p.getValue();
      p.setValue(obj);
      return val; 
      

      
   }
   
   /*  removes the node from position index (zero-indexed).  decrements size.
       @return the object in the node that was removed. 
        */
   public Object remove(int index) throws IndexOutOfBoundsException
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      /* enter your code below  */
      DLNode p = head;
      for(int i = 0; i <= index; i++)
         p = p.getNext();
      p.getPrev().setNext(p.getNext());
      p.getNext().setPrev(p.getPrev());
      size--;
      return p.getValue(); 

   }
   
  	/* inserts obj to front of list, increases size.
	    */ 
   public void addFirst(Object obj)
   {
      add(0, obj);
   }
   
   /* appends obj to end of list, increases size.
       */
   public void addLast(Object obj)
   {
      add(size, obj); 
   }
   
   /* returns the first element in this list  
      */
   public Object getFirst()
   {
      return get(0);
   }
   
   /* returns the last element in this list  
     */
   public Object getLast()
   {  
      return get(size-1); 
   }
   
   /* returns and removes the first element in this list, or
      returns null if the list is empty  
      */
   public Object removeFirst()
   {
      return remove(0); 
      
   }
   
   /* returns and removes the last element in this list, or
      returns null if the list is empty  
      */
   public Object removeLast()
   {
      return remove(size-1); 
   }
   
   /*  returns a String with the values in the list in a 
       friendly format, for example   [Apple, Banana, Cucumber]
       The values are enclosed in [], separated by one comma and one space.
    */
   public String toString()
   {
    DLNode current = head.getNext(); 
    String temp = "[";
    while ( current.getNext() != head )
          { 
            temp = temp + current.getValue().toString() +", ";
            current = current.getNext();
          }
    temp = temp + current.getValue().toString() + "]";
    return temp;
   }
}