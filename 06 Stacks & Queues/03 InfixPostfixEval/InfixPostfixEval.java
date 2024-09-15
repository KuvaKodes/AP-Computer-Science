// Name: Kushaan Vardhan
// Date: 1/11/2022 
//uses PostfixEval

import java.util.*;
public class InfixPostfixEval
{
   public static final String LEFT  = "([{<";
   public static final String RIGHT = ")]}>";
   public static final String operators = "+ - * / % ^ !";
   
   public static void main(String[] args)
   {
      System.out.println("Infix  \t-->\tPostfix\t\t-->\tEvaluate");
      /*build your list of Infix expressions here  */
      List<String> infixExp = new ArrayList<>();
      infixExp.add("( 2 + 7 ) % 3");
      infixExp.add(" 3 + 4 * 5   ");      

         
      for( String infix : infixExp )
      {
         String pf = infixToPostfix(infix);  //get the conversion to work first
         System.out.println(infix + "\t\t\t" + pf );  
       //  System.out.println(infix + "\t\t\t" + pf + "\t\t\t" + PostfixEval.eval(pf));  //PostfixEval must work!
      }
   }
   
   public static String infixToPostfix(String infix)
   {
      List<String> nums = new ArrayList<String>(Arrays.asList(infix.split(" ")));
            /* enter your code here  */
      String postfix = "";
      Stack<String> st = new Stack<String>(); 
      for (int i = 0; i  < nums.size(); i++)
      {
         String curr = nums.get(i);
         if(LEFT.indexOf(curr) == -1 && RIGHT.indexOf(curr) == -1 && !PostfixEval.isOperator(curr))
         {
            postfix += curr + " ";
         }
         else if(LEFT.indexOf(curr) >= 0)
         {
            st.push(curr);
         }
         else if(RIGHT.indexOf(curr) >= 0)
         {
            String popped = st.pop();
            while (LEFT.indexOf(popped) == -1 && !st.isEmpty())
            {
               postfix += popped + " ";
               popped = st.pop();
            }
         }
         else
         {
            if(st.isEmpty())
               st.push(curr);
            else if(!isHigherOrEqual(st.peek(), curr))
                  st.push(curr);
            else if(LEFT.contains(st.peek()))
                  st.push(curr);
            else
            {
               while(!st.isEmpty() && isHigherOrEqual(st.peek(), curr) && !LEFT.contains(st.peek()))
               {
                  postfix += st.pop() + " ";
               }
               st.push(curr);
            }
         }
       }
         while(!st.isEmpty())
         {
            postfix += st.pop() + " ";
         }
         
         return postfix.strip();
            
       
      }
  
   
   //enter your precedence method below
   public static boolean isHigherOrEqual(String top, String next)
   {
      int ti = operators.indexOf(top);
      int ni = operators.indexOf(next);
      if (ti <= 2 && ni <= 2)
         return true;
      else if(ti <= 8 && ti > 2 && ni <=8 && ni > 2)
         return true;
      else if(ti > ni)
         return true;
      else
         return false;
      
   }
   
}


/********************************************

Infix  	-->	Postfix		-->	Evaluate
 5 - 1 - 1			5 1 - 1 -			3.0
 5 - 1 + 1			5 1 - 1 +			5.0
 12 / 6 / 2			12 6 / 2 /			1.0
 3 + 4 * 5			3 4 5 * +			23.0
 3 * 4 + 5			3 4 * 5 +			17.0
 1.3 + 2.7 + -6 * 6			1.3 2.7 + -6 6 * +			-32.0
 ( 33 + -43 ) * ( -55 + 65 )			33 -43 + -55 65 + *			-100.0
 8 + 1 * 2 - 9 / 3			8 1 2 * + 9 3 / -			7.0
 3 * ( 4 * 5 + 6 )			3 4 5 * 6 + *			78.0
 3 + ( 4 - 5 - 6 * 2 )			3 4 5 - 6 2 * - +			-10.0
 2 + 7 % 3			2 7 3 % +			3.0
 ( 2 + 7 ) % 3			2 7 + 3 %			0.0
      
***********************************************/
