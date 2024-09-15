// Name: Kushaan Vardhan   
// Date: 2/15/2022

interface BSTinterface
{
   public int size();
   public boolean contains(String obj);
   public void add(String obj);   //does not balance
   public void addBalanced(String obj);
   public void remove(String obj);
   public String min();
   public String max();
   public String display();
   public String toString();
}

public class BST implements BSTinterface
{
      /*  copy your BST code here  */
   private TreeNode root;
   private int size;
   public BST()
   {
      root = null;
      size = 0;
   }
   public int size()
   {
     return size;
   }
   public TreeNode getRoot()   //for Grade-It
   {
      return root; 
   }
   /***************************************
   @param s -- one string to be inserted
   ****************************************/
   public void add(String s) 
   {
      size++; 
      root = add(root, s);
   }
   private TreeNode add(TreeNode t, String s) //recursive helper method
   {      
      if(t==null)
         return new TreeNode(s,null,null);
      if(s.compareTo((String)t.getValue()) <= 0)
         t.setLeft(add(t.getLeft(),s));
      else
         t.setRight(add(t.getRight(),s));
      return t; 
   }
   
   public String display()
   {
      return display(root, 0); 
   }
   private String display(TreeNode t, int level) //recursive helper method
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
   
   public boolean contains( String obj)
   {
      return contains(root, obj); 
   }
   private boolean contains(TreeNode t, String x) //recursive helper method
   {
      if(t == null)
         return false;
      if(x.compareTo((String)t.getValue()) < 0)
         return contains(t.getLeft(),x);
      else if (x.compareTo((String)t.getValue()) > 0)
         return contains(t.getRight(),x);
      return true; 
   }
   
   public String min()
   {
      return min(root);
   }
   private String min(TreeNode t)  //use iteration
   {
      if(t==null)
         return null;
      if(t.getLeft() == null)
         return (String)t.getValue();
      return min(t.getLeft());   
   }
   
   public String max()
   {
      return max(root);   
   }
   private String max(TreeNode t)  //recursive helper method
   {
      if(t==null)
         return null;
      if(t.getRight() == null)
         return (String)t.getValue();
      return max(t.getRight());
   }
   
   public String toString()
   {
      return toString(root);
   }
   private String toString(TreeNode t)  //an in-order traversal.  Use recursion.
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += toString(t.getLeft());
      toReturn += t.getValue() + " ";
      toReturn += toString(t.getRight());  
      return toReturn;
   }

   /*  precondition:  target must be in the tree.
                      implies that tree cannot be null.
   */
   public void remove(String target)
   {
      root = remove(root, target);
      size--;
   }
   private TreeNode remove(TreeNode current, String target)
   {
      if(target.compareTo((String)current.getValue()) < 0)
         current.setLeft(remove(current.getLeft(), target));
      else if(target.compareTo((String)current.getValue()) > 0)
         current.setRight(remove(current.getRight(), target));
      else
      {
         if(current.getLeft() == null)
            return current.getRight();
         else if(current.getRight() == null)
            return current.getLeft();
         else
         {
            String max = max(current.getLeft());
            current.setValue(max);
            current.setLeft(remove(current.getLeft(), max));
            
         }   
      }
      return current; 
   }

   /*  start the addBalanced methods */
   private int calcBalance(TreeNode t) //height to right minus 
   { 
      if(t == null)
         return 0;                                   //height to left
      return(height(t.getRight())-height(t.getLeft()));
   }

   private int height(TreeNode t)   //from TreeLab
   {
      if(t == null)
         return -1;
      return 1 + Math.max(height(t.getLeft()), height(t.getRight()));
   }

   public void addBalanced(String value)  
   {
      add(value);
      balanceTree(root, root);   // for an AVL tree. Put in the arguments you want.
   }
   private void balanceTree(TreeNode p, TreeNode c)  //recursive.  Whatever makes sense.
   {
      if(c == null)
         return;
      balanceTree(c, c.getLeft());
      balanceTree(c, c.getRight());
      if(calcBalance(c) <= -2) // Left 
      {
         if(c == p)
         {
           root = leftLeft(p); 
         }         
         else if(c.getLeft().getLeft() == null) //Right
         {
            if(c == p.getLeft())
               p.setLeft(leftRight(c));
            else
               p.setRight(leftRight(c));
         }
         else
         {
            if(c == p.getLeft())
               p.setLeft(leftLeft(c));
            else
               p.setRight(leftLeft(c));
         }     
      }
      else if(calcBalance(c) >= 2) // Right
      {
         if(c == root)
         {
            root = rightRight(p);
         }
         else if(c.getRight().getRight() == null) //Left
         {
            if(c == p.getRight())
               p.setRight(rightLeft(c));
            else
               p.setLeft(rightLeft(c));
         }
         else
         {
            if(c == p.getRight())
               p.setRight(rightRight(c));
            else
               p.setLeft(rightRight(c)); 
         }
      }
   }
   // 4 rotation methods
   private TreeNode leftLeft(TreeNode t)
   {
   
      TreeNode g = t.getLeft(); 
      TreeNode n = g.getRight();
      g.setRight(t);
      t.setLeft(n);
      return g;
   }
   
   private TreeNode rightRight(TreeNode t)
   {

     TreeNode g = t.getRight(); 
     TreeNode n = g.getLeft();
     g.setLeft(t);
     t.setRight(n);
     return g;
   }
   
   private TreeNode leftRight(TreeNode t)
   {
      TreeNode g = rightRight(t.getLeft());
      t.setLeft(g);
      return leftLeft(t); 
   }
   
   private TreeNode rightLeft(TreeNode t)
   {
      TreeNode g = leftLeft(t.getRight());
      t.setRight(g);
      return rightRight(t); 
   }
   
}