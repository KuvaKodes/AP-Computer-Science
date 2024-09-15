// Name: Kushaan Vardhan
// Date: 02/03/2022
/*  Represents a binary expression tree.
 *  The BXT builds itself from postorder expressions. It can
 *  evaluate and print itself.  Also prints inorder and postorder strings. 
 */
 
import java.util.*;

public class BXT
{
   public static final String operators = "+ - * / % ^ !";
   private TreeNode root;   
   
   public BXT()
   {
      root = null;
   }
   public TreeNode getRoot()   //for Codepost
   {
      return root; 
   }
    
   public void buildTree(String str)
   {
     	String[] input = str.split(" ");
      Stack<TreeNode> st = new Stack<>();
      for(String p: input)
      {
         if(!isOperator(p))
         {
            TreeNode t = new TreeNode(p);
            st.push(t); 
         }
         else
         {
            TreeNode g = new TreeNode(p);
            g.setRight(st.pop());
            g.setLeft(st.pop());
            st.push(g); 
         }  
      }
      root = st.pop();
   }
   
   public double evaluateTree()
   {
      return evaluateNode(root);
   }
   
   private double evaluateNode(TreeNode t)  //recursive
   {
      String tval = t.getValue()+"";
      if(t == null)
         return 0; 
      if(!isOperator(tval))
         return Double.parseDouble(tval);
      else
         return computeTerm(tval, evaluateNode(t.getLeft()), evaluateNode(t.getRight())); 
   }
   
   private double computeTerm(String s, double a, double b)
   {
      int g = operators.indexOf(s);
      if(g == 0)
         return a + b;
      else if(g == 2)
         return a-b;
      else if(g == 4)
         return a*b;
      else if(g == 6)
         return a/b;
      else if (g == 8)
         return a % b;
      else 
         return Math.pow(a, b);
   }
   
   private boolean isOperator(String s)
   {
      if(operators.contains(s))
         return true;
      else
         return false; 
   }
   
   public String display()
   {
      return display(root, 0);
   }
   
   private String display(TreeNode t, int level)
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
    
   public String inorderTraverse()
   {
      return inorderTraverse(root);
   }
   
   private  String inorderTraverse(TreeNode t)
   {
      String toReturn = "";
      if(t == null)
         return "";
      toReturn += inorderTraverse(t.getLeft());
      toReturn += t.getValue() + " ";
      toReturn += inorderTraverse(t.getRight());  
      return toReturn;
   }
   
   public String preorderTraverse()
   {
      return preorderTraverse(root);
   }
   
   private String preorderTraverse(TreeNode root)
   {
      String toReturn = "";
      if(root == null)
         return "";
      toReturn += root.getValue() + " ";              //process root
      toReturn += preorderTraverse(root.getLeft());   //recurse left
      toReturn += preorderTraverse(root.getRight());  //recurse right
      return toReturn;
   }
   
  /* extension */
   // public String inorderTraverseWithParentheses()
   // {
      // return inorderTraverseWithParentheses(root);
   // }
//    
   // private String inorderTraverseWithParentheses(TreeNode t)
   // {
      // return "";
   // }
}