public class Recursion_Contest
{
   public static void main(String[] args)
   {
      System.out.println(factorial(4));
      System.out.println(powerOfNumber(2,5));     
      System.out.println(sumOfDigits(1111));
      System.out.println(decimalToBinary(14));     
      System.out.println(fiboSeries(5));     


   }
   
   /****************************************
    * #1. Find factorial of a number 
    *     using recursion
    *    Ex) factorial(4) returns 24 (4*3*2*1)
    *  precondition: n > 0
    ****************************************/
   public static int factorial(int n)
   {
      if (n==1)
         return 1;
      else
         return n * factorial(n-1);
   }
   
  /****************************************  
   * #2. Find the sum of digits of a number
   *     using recursion
   *    Ex) 1302 returns 6 (1 + 3 + 0 + 2).
   * precondition: n > 0
   ****************************************/
   public static int sumOfDigits(int num)
   {
      if (num < 10)
         return num;
      else
         return num % 10 + sumOfDigits(num/10);
   }
    
  /***************************************
   * #3. Convert a number from Decimal 
   *     to Binary using recursion
   *    Ex) 14 is converted to "1110"  
   * precondition: n >= 0
   ***************************************/
   public static String decimalToBinary(int dec)
   {
      if (dec <= 1)
         return "" + dec;
      else
         return decimalToBinary(dec/2) + dec % 2;
   }     
    
  /*******************************************
   * #4. Calculate Power of a number
   *     using recursion
   *    Ex) powerOfNumber(3, 2) returns 9 (3^2)
   * precondition: base > 0
   ********************************************/
   public static int powerOfNumber(int base, int exp)
   {
      if (exp == 0)
         return 1;
      else
         return base * powerOfNumber(base,exp-1);
   }
     
  /********************************************
   * #5. Find GCD(Greatest Common Divisor) of
   *     two numbers usirng recursion
   *   Ex) gcd(4, 6) returns 2
   * precondition: a > 0 and b > 0
   *******************************************/
   public static int gcd(int a, int b)
   {
      return 9999;
   }
      
   /*******************************************
    * #6. Find all permutations of characters
    *     in a String using recursion
    *    Ex) permutation("abc") prints
    *        abc
    *        acb
    *        bac
    *        bca
    *        cab
    *        cba
    * precondition: str has no same character
    *               str.length() > 0
    *******************************************/
   public static void permutation(String str)
   {
            //You may make a private helper method
   }
       
   /*******************************************
    * #7. Find the Fibonacci series on nth term 
    *     using recursion
    *   Ex) fiboSeries(5) returns 12 (1+1+2+3+5)
        Ex) fiboseries(4) returns 7 (1+1+2+3)
        Ex) fiboSeries(3) returns 4 (1+1+2)
        Ex) fiboSeries(2) returns 2 (1 + 1)
        Ex) fiboSerios(1) returns 1 (1)
    * precondition: n > 0
    ********************************************/
   public static int fiboSeries(int n)
   {
      if(n <= 2)
         return n;
      else 
         return 1 + fiboSeries(n-1) + fiboSeries(n-2);
   } 
        
   /********************************************
    * #8. Print the reversed sentence
    *     using recursion
    *   Ex) "How are you?" -> "you? are How"     
    * precondition: sentence.length() > 0
    ********************************************/
   public static void reverseSentence(String sentence)
   {
            
   }
        
   /********************************************
    * #9. Print the reversed sentence and return
    *     the number of words using recursion
    *    Ex) "How are you?" -> "you? are How"
    *        and then, the method returns 3
    * precondition: sentence.length() > 0
    *********************************************/
   public static int reverseAndCount(String setence)
   {
      return 9999;
   }
   
   /************************************************
    * #10. Find 'T' (Treasure) in the given char 
    *      array and print the position of 'T'
    *      using recursion
    *    Ex) char[][] map = {{'.', '.', '.', 'T'},
    *                        {'.', '.', '.', '.'},
    *                        {'.', '.', '.', '.'}}
    *    findT(map, 1, 3) will print "(0, 3)"
    * precondition: map != null
    ************************************************/
   public static void findT(char[][] map, int startRow, int startCol)
   {
   } 
}