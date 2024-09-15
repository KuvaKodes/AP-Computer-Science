// Name: Kushaan Vardhan
// Date: 11/30/2021

/**
 * Implements the cheat sheet's List interface.  Implements generics.
 * The backing array is an array of (E[]) new Object[10];
 * @override toString()
 */ 
public class TJArrayList<E>
{
   private int size;							//stores the number of objects
   private E[] myArray;
   public TJArrayList()                
   {
      myArray = (E[]) new Object[10]; //default constructor instantiates a raw array with 10 cells
   
      size = 0;
   }
   public int size()
   {
      return size; 
   }
 	/* appends obj to end of list; increases size;
      must be an O(1) operation when size < array.length, 
         and O(n) when it doubles the length of the array.
	  @return true  */
   public boolean add(E obj)
   {
      if(size < myArray.length)
      {
         myArray[size] = obj;
         size++;
      }
      else
      {
         E[] temp = (E[]) new Object[myArray.length * 2];
         for(int i = 0; i < size; i++)
            temp[i] = myArray[i];
         myArray = temp; 
         myArray[size] = obj;
         size++;            
      }
               return true; 

   }
   /* inserts obj at position index.  increments size. 
		*/
   public void add(int index, E obj) throws IndexOutOfBoundsException  //this the way the real ArrayList is coded
   {
      if(index > size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      if(size == myArray.length)
      {
         E[] temp = (E[]) new Object[myArray.length * 2];
         for(int i = 0; i < size; i++)
            temp[i] = myArray[i];
         myArray = temp; 
      }
      for(int i = size; i > index; i--)
      {
         myArray[i] = myArray[i-1];
      }
         myArray[index] = obj;
         size++;
   }

   /* return obj at position index.  
		*/
   public E get(int index) throws IndexOutOfBoundsException
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
     return myArray[index]; 
   
   }
   /**
    * Replaces obj at position index. 
    * @return the object is being replaced.
    */  
   public E set(int index, E obj) throws IndexOutOfBoundsException  
   { 
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      E ject = myArray[index];
      myArray[index] = obj;
      return ject; 
   
   }
 /*  removes the node from position index. shifts elements 
     to the left.   Decrements size.
	  @return the object that used to be at position index.
	 */
   public E remove(int index) throws IndexOutOfBoundsException  
   {
      if(index >= size || index < 0)
         throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + size);
      E ject = myArray[index];
      for(int i = index; i < size; i++)
      {
         myArray[i] = myArray[i+1];
      }
         size--;
         return ject; 
   }
	   /*
		   This method compares objects.
         Must use .equals(), not ==
     	*/
   public boolean contains(E obj)
   {
      int count = 0;
      while(count < myArray.length)
      {
         if(myArray[count].equals(obj))
            return true;
         count++;
      }
      return false; 
         
   }
	 /*returns a String of E objects in the array with 
       square brackets and commas.
     	*/
   public String toString()
   {
    String temp = "[";
    for(E jects: myArray)
    { 
       temp = temp + jects.toString() +", ";
    }
    temp = temp.substring(0, temp.length()-2);
    temp += "]";
    return temp;
   }
}