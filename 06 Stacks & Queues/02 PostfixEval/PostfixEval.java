// Name: Kushaan Vardhan
// Date: 1/11/2022

import java.util.*;

public class PostfixEval
{
   public static final String operators = "+ - * / % ^ !";
   
   public static void main(String[] args)
   {
      System.out.println("Postfix  -->  Evaluate");
      ArrayList<String> postfixExp = new ArrayList<String>();
      /*  build your list of expressions here  */
   postfixExp.add("3 4 5 * +");//		23
   postfixExp.add("3 4 * 5 +");//		17
   postfixExp.add("10 20 + -6 6 * +");//		-6
   postfixExp.add("3 4 + 5 6 + *");//		77
   postfixExp.add("3 4 5 + * 2 - 5 /");//		5
   postfixExp.add("8 1 2 * + 9 3 / -");//		7
   postfixExp.add("2 3 ^");//		8
   postfixExp.add("20 3 %");//		2
   postfixExp.add("21 3 %");//		0
   postfixExp.add("22 3 %");//		1
   postfixExp.add("23 3 %");//		2
   postfixExp.add("5 !");//		120
   postfixExp.add("1 1 1 1 1 + + + + !");//		120
      
   
      
      for( String pf : postfixExp )
      {
         System.out.println(pf + "\t\t" + eval(pf));
      }
   }
   
   public static double eval(String pf)
   {
      List<String> postfixParts = new ArrayList<String>(Arrays.asList(pf.split(" ")));
      /*  enter your code here  */
      Stack<Double> st = new Stack<Double>();
      for(int i = 0; i < postfixParts.size(); i++)
      {
         String curr = postfixParts.get(i);
         if(!isOperator(curr))
         {
            st.push(Double.parseDouble(curr));
         }
         else
         {
            if(operators.indexOf(curr) == 12)
            {
               double right = st.pop();
               double val = factorial(right);
               st.push(val);
            } 
            else
            {
            double right = st.pop();
            double left = st.pop();
            double val = eval(left, right, curr);
            st.push(val);
            } 
         }
      }
      return st.pop();
   
   }
   
   public static double eval(double a, double b, String ch)
   {
      int g = operators.indexOf(ch);
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
   
   public static boolean isOperator(String op)
   {
     if (operators.indexOf(op) > -1)
      return true;
     return false; 
   }
   
   public static double factorial(double n)
   {
      if (n == 1)
         return 1;
      else
         return n * factorial(n-1); 
   }
}

/**********************************************
Postfix  -->  Evaluate
 3 4 5 * +		23
 3 4 * 5 +		17
 10 20 + -6 6 * +		-6
 3 4 + 5 6 + *		77
 3 4 5 + * 2 - 5 /		5
 8 1 2 * + 9 3 / -		7
 2 3 ^		8
 20 3 %		2
 21 3 %		0
 22 3 %		1
 23 3 %		2
 5 !		120
 1 1 1 1 1 + + + + !		120
 
 
 *************************************/