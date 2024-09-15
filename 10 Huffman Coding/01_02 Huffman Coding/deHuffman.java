// Name: Kushaan Vardhan              Date: 03/29/2022
import java.util.*;
import java.io.*;
public class deHuffman
{
   public static void main(String[] args) throws IOException
   {
      Scanner keyboard = new Scanner(System.in);
      System.out.print("\nWhat binary message (middle part)? ");
      String middlePart = keyboard.next();
      Scanner sc = new Scanner(new File("message."+middlePart+".txt")); 
      String binaryCode = sc.next();
      Scanner huffLines = new Scanner(new File("scheme."+middlePart+".txt")); 
      	
      TreeNode root = huffmanTree(huffLines);
      String message = dehuff(binaryCode, root);
      System.out.println(message);
      	
      sc.close();
      huffLines.close();
   }
   public static TreeNode huffmanTree(Scanner huffLines)
   {
      TreeNode root = new TreeNode("");
      while(huffLines.hasNext())
      {
         TreeNode p = root; 
         String full = huffLines.nextLine();
         String ints = full.substring(1); 
         String val = full.substring(0,1);  
         for(char c: ints.toCharArray())
         {
            if(c == '0')
            {
               if(p.getLeft() == null)
                  p.setLeft(new TreeNode("")); 
               p = p.getLeft();
           }
           
           if(c == '1')
           {
               if(p.getRight() == null)
                  p.setRight(new TreeNode("")); 
                p = p.getRight();
           }     
         }
         p.setValue(val); 
      }
      
      return root; 
      
   }
   public static String dehuff(String text, TreeNode root)
   {
      TreeNode p = root;
      String toRet = "";  
      for(char c: text.toCharArray())
         {
            if(p.getLeft() == null && p.getRight() == null)
            {
               toRet += p.getValue();
               p = root;
            }
            
            if(c == '0')
               p = p.getLeft();  
            else
               p = p.getRight();
         }
      if(p.getLeft() == null && p.getRight() == null)
        {
               toRet += p.getValue();
               p = root;
         }
     return toRet;
   }
}  

 /* TreeNode class for the AP Exams */
class TreeNode
{
   private Object value; 
   private TreeNode left, right;
   
   public TreeNode(Object initValue)
   { 
      value = initValue; 
      left = null; 
      right = null; 
   }
   
   public TreeNode(Object initValue, TreeNode initLeft, TreeNode initRight)
   { 
      value = initValue; 
      left = initLeft; 
      right = initRight; 
   }
   
   public Object getValue()
   { 
      return value; 
   }
   
   public TreeNode getLeft() 
   { 
      return left; 
   }
   
   public TreeNode getRight() 
   { 
      return right; 
   }
   
   public void setValue(Object theNewValue) 
   { 
      value = theNewValue; 
   }
   
   public void setLeft(TreeNode theNewLeft) 
   { 
      left = theNewLeft;
   }
   
   public void setRight(TreeNode theNewRight)
   { 
      right = theNewRight;
   }
}
