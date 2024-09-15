// Name: Kushaan Vardhan  
// Date: 
import java.util.*;
import java.io.*;
public class PigLatin
{
   public static void main(String[] args) 
   {
     // part_1_using_pig();
     part_2_using_piglatenizeFile();
      
      /*  extension only    */
      String pigLatin = pig("What!?");
      System.out.print(pigLatin + "\t\t" + pigReverse(pigLatin));   //Yahwta!?
      pigLatin = pig("{(Hello!)}");
      System.out.print("\n" + pigLatin + "\t\t" + pigReverse(pigLatin)); //{(Yaholle!)}
      pigLatin = pig("\"McDonald???\"");
      System.out.println("\n" + pigLatin + "  " + pigReverse(pigLatin));//"YaDcmdlano???"
   }

   public static void part_1_using_pig()
   {
      Scanner sc = new Scanner(System.in);
      while(true)
      {
         System.out.print("\nWhat word? ");
         String s = sc.next();
         if(s.equals("-1"))
         {
            System.out.println("Goodbye!"); 
            System.exit(0);
         }
         String p = pig(s);
         System.out.println( p );
      }		
   }

   public static final String punct = ",./;:'\"?<>[]{}|`~!@#$%^&*()";
   public static final String letters = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
   public static final String vowels = "AEIOUaeiou";
   public static final String vowels2 = "AEIOUYaeiouy";
   public static final String us = "Uu";
   public static final String qs = "Qq";
   
   public static String pig(String s)
   {
      String piggedString = "";
      if(s.length() == 0)
         return "";
   
      //remove and store the beginning punctuation 
      String prePunct = "";
      int firstLetterIndex = 0;
      for(int i = 0; i < s.length(); i++)
      {
         if(letters.contains(s.substring(i,i+1)))
         {
            prePunct = s.substring(0,i);
            firstLetterIndex = i;
            break;
         }
         
      }  
           
      //remove and store the ending punctuation
      String postPunct = "";
      int lastLetterIndex = s.length();
      for(int i = s.length()-1; i >= 0; i--)
      {
         if(letters.contains(s.substring(i,i+1)))
         {
            postPunct = s.substring(i+1);
            lastLetterIndex = i+1;
            break;
         }
         
      }
      
      String punctLessS = s.substring(firstLetterIndex,lastLetterIndex);
         
         
      //START HERE with the basic case:
      //     find the index of the first vowel
      //     y is a vowel if it is not the first letter
      //     qu
      if (vowels.contains(punctLessS.substring(0,1)))
      {
         piggedString = prePunct + punctLessS + "way" + postPunct;
      } 
      else 
      {
         for(int i = 1; i < punctLessS.length(); i++)
         {
            int firstVow = vowels2.indexOf(punctLessS.substring(i,i+1));
            if (firstVow >= 0)
            {
               if(us.contains(punctLessS.substring(i,i+1)) && qs.contains(punctLessS.substring(i-1,i)))
               {
                  if (i+2 <= punctLessS.length() && vowels2.contains(punctLessS.substring(i+1,i+2)))
                  {
                     i += 1;
                  }
               }
               //is the first letter capitalized?
               if(Character.isUpperCase(punctLessS.charAt(0)))
               {
                  piggedString = prePunct + punctLessS.substring(i,i+1).toUpperCase() + punctLessS.substring(i+1) + punctLessS.substring(0,1).toLowerCase() + punctLessS.substring(1,i) + "ay" + postPunct;
                  break;
               }
               piggedString = prePunct + punctLessS.substring(i) + punctLessS.substring(0,i) + "ay" + postPunct;
               break;
            }
         } 
      }
      
   
      //if no vowel has been found
      if (piggedString.length() == 0)
         return "**** NO VOWEL ****"; 
      
      
      //return the piglatinized word 
      return piggedString;
      
      
   }


   public static void part_2_using_piglatenizeFile() 
   {
      Scanner sc = new Scanner(System.in);
      System.out.print("input filename including .txt: ");
      String fileNameIn = sc.next();
      System.out.print("output filename including .txt: ");
      String fileNameOut = sc.next();
      piglatenizeFile( fileNameIn, fileNameOut );
      System.out.println("Piglatin done!");
   }

/****************************** 
*  piglatinizes each word in each line of the input file
*    precondition:  both fileNames include .txt
*    postcondition:  output a piglatinized .txt file 
******************************/
   public static void piglatenizeFile(String fileNameIn, String fileNameOut) 
   {
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File(fileNameIn));  
      }
      catch(IOException e)
      {
         System.out.println("oops");
         System.exit(0);   
      }
   
      PrintWriter outfile = null;
      try
      {
         outfile = new PrintWriter(new FileWriter(fileNameOut));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
   	//process each word in each line
      while(infile.hasNext())
      {
         String s = infile.nextLine();
         String [] pigText = s.split(" ");
         for (String str : pigText)
         {
            outfile.print(pig(str) + " ");
         }
         outfile.print("\n");
      }
      
      
   
      outfile.close();
      infile.close();
   }
   
   /** EXTENSION: Output each PigLatin word in reverse, preserving before-and-after 
       punctuation.  
   */
   public static String pigReverse(String s)
   {
      if(s.length() == 0)
         return "";
          
      String prePunct = "";
      int firstLetterIndex = 0;
      for(int i = 0; i < s.length(); i++)
      {
         if(letters.contains(s.substring(i,i+1)))
         {
            prePunct = s.substring(0,i);
            firstLetterIndex = i;
            break;
         }
         
      }  
           
      String postPunct = "";
      int lastLetterIndex = s.length();
      for(int i = s.length()-1; i >= 0; i--)
      {
         if(letters.contains(s.substring(i,i+1)))
         {
            postPunct = s.substring(i+1);
            lastLetterIndex = i+1;
            break;
         }
         
      }
      
      String punctLessS = s.substring(firstLetterIndex,lastLetterIndex);
      
      
      String reversedS = "";
      if (Character.isUpperCase(punctLessS.charAt(0)))
      {
         punctLessS = Character.toLowerCase(punctLessS.charAt(0)) + punctLessS.substring(1,punctLessS.length()-1) + Character.toUpperCase(punctLessS.charAt(punctLessS.length()-1));
            
      }
      for (int i = punctLessS.length()-1; i >= 0; i--)
      { 
         reversedS += punctLessS.substring(i,i+1);
      }
      return prePunct + reversedS + postPunct;    
   }
}
