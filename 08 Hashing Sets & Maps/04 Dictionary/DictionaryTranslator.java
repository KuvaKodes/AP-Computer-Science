import java.io.*;
import java.util.*;

public class DictionaryTranslator
{
   public static void main(String[] args)
   {
   
      Scanner infile = null;
      try
      {
         infile = new Scanner(new File("spanglish.txt"));
      }
      catch(Exception e)
      {
      }
      
      Map<String, Set<String>> eng2spn = Dictionary.makeDictionary(infile);
      Map<String, Set<String>> spn2eng = Dictionary.reverse(eng2spn);    
      
      Scanner sc = new Scanner(System.in);
      System.out.println("Enter E for an English to Spanish Dictionary or S for Spanish to English: ");
      String eOrS = sc.nextLine(); 
      System.out.println("What word would you like to translate? ");
      String word = sc.nextLine();
      
      if(eOrS.equals("E"))
      {
         if(!eng2spn.containsKey(word))
            System.out.println("Sorry we do not have " + word + "'s translations :(");
         else
         {
            String toReturn = ""; 
            String val = eng2spn.get(word).toString();
            toReturn += val.substring(1,val.length()-1);
            System.out.println(toReturn);
            String yOrN = ""; 
            while(!yOrN.equals("N"))
            {
               System.out.println("Do you have any translations you would like to add (Y or N)?");
               yOrN = sc.nextLine(); 
               if(yOrN.equals("N"))
                  break; 
               System.out.println("What would you like to add?");
               String add = sc.nextLine(); 
               toReturn += ", " + add; 
               System.out.println(toReturn);  
            }
                    
         }
      } 
      
     else
      {
         if(!spn2eng.containsKey(word))
            System.out.println("Sorry we do not have " + word + "'s translations :(");
         else
         {
            String toReturn = ""; 
            String val = spn2eng.get(word).toString();
            toReturn += val.substring(1,val.length()-1);
            System.out.println(toReturn);
            String yOrN = ""; 
            while(!yOrN.equals("N"))
            {
               System.out.println("Do you have any translations you would like to add (Y or N)?");
               yOrN = sc.nextLine(); 
               if(yOrN.equals("N"))
                  break; 
               System.out.println("What would you like to add?");
               String add = sc.nextLine(); 
               toReturn += ", " + add; 
               System.out.println(toReturn);  
            }
                    
         }
      } 
   
   
   
   
   }





















}
