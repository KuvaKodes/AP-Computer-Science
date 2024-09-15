// Name: Kushaan Vardhan
// Date: 2/17/200
import java.util.*;

interface BSTinterface<E>
{
   public int size();
   public boolean contains(E element);
   public E add(E element);
   //public E addBalanced(E element);
   public E remove(E element);
   public E min();
   public E max();
   public String display();
   public String toString();
   public List<E> toList();  //returns an in-order list of E
}

/*******************
  Copy your BST code.  Implement generics.
**********************/
public class BST_Generic<E extends Comparable<E>> implements BSTinterface<E>
{
   private TreeNode<E> root;
   private int size;
   public BST_Generic()
   {
      root = null;
      size = 0;
   }
   public int size()
   {
     return size;
   }
   public TreeNode<E> getRoot()   //for Grade-It
   {
      return root; 
   }
   /***************************************
   @param s -- one string to be inserted
   ****************************************/
   public E add(E s) 
   {
      size++; 
      root = add(root, s);
      return s;
   }
   private TreeNode<E> add(TreeNode<E> t, E s) //recursive helper method
   {      
      if(t==null)
         return new TreeNode<E>(s,null,null);
      if(s.compareTo(t.getValue()) <= 0)
         t.setLeft(add(t.getLeft(),s));
      else
         t.setRight(add(t.getRight(),s));
      return t; 
   }
   
   public String display()
   {
      return display(root, 0); 
   }
   private String display(TreeNode<E> t, int level) //recursive helper method
   {
      String toRet = "";
      if(t == null)
         return "";
      toRet += display(t.getRight(), level + 1); //recurse right
      for(int k = 0; k < level; k++)
         toRet += "\t";
      toRet += t.getValue() + "\n";
      toRet += display(t.getLeft(), level + 1); //recurse left
      return toRet;
   }
   
   public boolean contains(E obj)
   {
      return contains(root, obj); 
   }
   private boolean contains(TreeNode<E> t, E x) //recursive helper method
   {
      if(t == null)
         return false;
      if(x.compareTo(t.getValue()) < 0)
         return contains(t.getLeft(),x);
      else if (x.compareTo(t.getValue()) > 0)
         return contains(t.getRight(),x);
      return true; 
   }
   
   public E min()
   {
      return min(root);
   }
   private E min(TreeNode<E> t)  //use iteration
   {
      if(t==null)
         return null;
      if(t.getLeft() == null)
         return t.getValue();
      return min(t.getLeft());   
   }
   
   public E max()
   {
      return max(root);   
   }
   private E max(TreeNode<E> t)  //recursive helper method
   {
      if(t==null)
         return null;
      if(t.getRight() == null)
         return t.getValue();
      return max(t.getRight());
   }
   
   public String toString()
   {
      return toString(root);
   }
   private String toString(TreeNode<E> t)  //an in-order traversal.  Use recursion.
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += toString(t.getLeft());
      toReturn += t.getValue() + " ";
      toReturn += toString(t.getRight());  
      return toReturn;
   }
   
   public List<E> toList()
   {
      return toList(root);
   }
   
   private List<E> toList(TreeNode<E> t)
   {
      ArrayList<E> toReturn = new ArrayList<E>();
      if(t == null)
         return toReturn;
      List<E> g = toList(t.getLeft());
      for(E e: g)
         toReturn.add(e);
      toReturn.add(t.getValue());
      List<E> n = toList(t.getRight());
      for(E e: n)
         toReturn.add(e);
      return toReturn;
   }

   /*  precondition:  target must be in the tree.
                      implies that tree cannot be null.
   */
   public E remove(E target)
   {
      root = remove(root, target);
      size--;
      return target; 
   }
   private TreeNode<E> remove(TreeNode<E> current, E target)
   {
      if(target.compareTo(current.getValue()) < 0)
         current.setLeft(remove(current.getLeft(), target));
      else if(target.compareTo(current.getValue()) > 0)
         current.setRight(remove(current.getRight(), target));
      else
      {
         if(current.getLeft() == null)
            return current.getRight();
         else if(current.getRight() == null)
            return current.getLeft();
         else
         {
            E max = max(current.getLeft());
            current.setValue(max);
            current.setLeft(remove(current.getLeft(), max));
            
         }   
      }
      return current; 
    
   
   }

}

/*******************
  Copy your TreeNode<E> code.  Implement generics.
**********************/
class TreeNode<E>
{
/* TreeNode<E> class for the AP Exams 
 */
   private E value; 
   private TreeNode<E> left, right;
   
   public TreeNode (E initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode (E initValue, TreeNode<E> initLeft, TreeNode<E> initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public E getValue()
   { 
      return value; 
   }
   
   public TreeNode<E> getLeft() 
   { 
      return left; 
   }
   
   public TreeNode<E> getRight() 
   { 
      return right; 
   }
   
   public void setValue(E theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode<E> theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode<E> theNewRight)
   { 
      right = theNewRight;
   }
}

