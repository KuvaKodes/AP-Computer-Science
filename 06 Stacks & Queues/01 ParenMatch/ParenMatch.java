// Name:Kushaan Vardhan
// Date: 01/10/22

import java.util.*;

public class ParenMatch
{
   public static final String LEFT  = "([{<";
   public static final String RIGHT = ")]}>";
   
   public static void main(String[] args)
   {
      System.out.println("Parentheses Match");
      ArrayList<String> parenExp = new ArrayList<String>();
      /* enter test cases here */
      parenExp.add("5 + 7"); 
   
      for( String s : parenExp )
      {
         boolean good = checkParen(s);
         if(good)
            System.out.println(s + "\t good!");
         else
            System.out.println(s + "\t BAD");
      }
   }
     
   //returns the index of the left parentheses or -1 if it is not there
   public static int isLeftParen(String p)
   {
      return LEFT.indexOf(p);
   }
  
   //returns the index of the right parentheses or -1 if it is not there
   public static int isRightParen(String p)
   {
      return RIGHT.indexOf(p);
   }
   
   public static boolean checkParen(String exp)
   {
     Stack<String> st = new Stack<String>();
     String [] array = exp.split(" ");
     for(int i = 0; i < array.length; i++)
     {
         if(isLeftParen(array[i]) != -1)
            st.push(array[i]);
         else if(isRightParen(array[i]) != -1)
         {
            if (st.isEmpty())
               return false;
            else
            {
               String poped = st.pop();
               if(LEFT.indexOf(poped) != RIGHT.indexOf(array[i]))
                  return false;
            }
         }
     }
     if(!st.isEmpty())
      return false;
        
     return true;  //so it compiles    
   }
}

/*****************************************

Parentheses Match
5 + 7		 good!
( 15 + -7 )		 good!
) 5 + 7 (		 BAD
( ( 5.0 - 7.3 ) * 3.5 )		 good!
< { 5 + 7 } * 3 >		 good!
[ ( 5 + 7 ) * ] 3		 good!
( 5 + 7 ) * 3		 good!
5 + ( 7 * 3 )		 good!
( ( 5 + 7 ) * 3		 BAD
[ ( 5 + 7 ] * 3 )		 BAD
[ ( 5 + 7 ) * 3 ] )		 BAD
( [ ( 5 + 7 ) * 3 ]		 BAD
( ( ( ) $ ) )		 good!
( ) [ ]		 good!
 
 *******************************************/
