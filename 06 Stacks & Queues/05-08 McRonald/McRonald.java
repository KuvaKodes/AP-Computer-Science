//Updated on 12.14.2020 v2

//Name: Kushaan Vardhan Date: 1/18/2022
import java.util.*;
import java.io.*;
public class McRonald
{
   public static final int TIME = 1080;     //18 hrs * 60 min
   public static double CHANCE_OF_CUSTOMER = .2;
   public static int customers = 0;
   public static int totalMinutes = 0;
   public static int longestWaitTime = 0;
   public static int longestQueue = 0;
   public static int serviceWindow = 0;      // to serve the front of the queue
   //public static final int numberOfServiceWindows = 3;  //for McRonald 3
   public static int thisCustomersTime;
   public static PrintWriter outfile = null; // file to display the queue information
      
   public static int timeToOrderAndBeServed()
   {
      return (int)(Math.random() * 6 + 2);
   }
  
   public static void displayTimeAndQueue(Queue<Customer> q, int min)
   { 
      //Billington's
      outfile.println(min + ": " + q);	
      //Jurj's
      //outfile.println("Customer#" + intServiceAreas[i] + 
      //                            " leaves and his total wait time is " + (intMinute - intServiceAreas[i]));                     	
      
   }
   
   public static int getCustomers()
   {
      return customers;
   }
   public static double calculateAverage()
   {
      return (int)(1.0 * totalMinutes/customers * 10)/10.0;
   }
   public static int getLongestWaitTime()
   {
      return longestWaitTime;
   }
   public static int getLongestQueue()
   {
      return longestQueue;
   }
            
   public static void main(String[] args)
   {     
    //set up file      
      try
      {
         outfile = new PrintWriter(new FileWriter("McRonald 1 Queue 1 ServiceArea.txt"));
      }
      catch(IOException e)
      {
         System.out.println("File not created");
         System.exit(0);
      }
      
      mcRonald(TIME, outfile);   //run the simulation
      
      outfile.close();	
   }
   
   public static void mcRonald(int TIME, PrintWriter of)
   {
      /***************************************
           Write your code for the simulation   
      **********************************/
      Queue<Customer> queue = new LinkedList<Customer>(); 
      for(int min = 0; min < 1080; min++)
      {
         if(Math.random() < 0.2)
         {
            queue.add(new Customer(min, timeToOrderAndBeServed()));
            customers++;
         }
         if(!queue.isEmpty())
         {
            totalMinutes+= queue.size();
            Customer curr = queue.peek();
            curr.setOrderAndBeServed(-1);
            if(curr.getOrderAndBeServed() == 0)
            {
               int wt = min - curr.getArrivedAt();
               if(wt > longestWaitTime)
               {
                  longestWaitTime = wt;
               }
               queue.remove();
               curr = queue.peek();    
            }
            if(queue.size() > longestQueue)
               longestQueue = queue.size(); 
         }
         
         displayTimeAndQueue(queue, min);

      }
      int minnies = 1080; 
      while(!queue.isEmpty())
      {
            totalMinutes++;
            Customer curr = queue.peek();
            curr.setOrderAndBeServed(-1);
            if(curr.getOrderAndBeServed() == 0)
            {
               int wt = minnies - curr.getArrivedAt();
               if(wt > longestWaitTime)
                  longestWaitTime = wt;
               queue.remove();
               curr = queue.peek(); 
            }
            if(queue.size() > longestQueue)
               longestQueue = queue.size();    
            displayTimeAndQueue(queue, minnies);
            minnies++;
      }
        
        
              
      /*   report the data to the screen    */  
      System.out.println("1 queue, 1 service window, probability of arrival = "+ CHANCE_OF_CUSTOMER);
      System.out.println("Total customers served = " + getCustomers());
      System.out.println("Average wait time = " + calculateAverage());
      System.out.println("Longest wait time = " + longestWaitTime);
      System.out.println("Longest queue = " + longestQueue);
   }
   
   static class Customer      
   {
      private int arrivedAt;
      private int orderAndBeServed;
      
    /**********************************
       Complete the Customer class with  
       constructor, accessor methods, toString.
    ***********************************/
   public Customer(int n, int i)
   {
      arrivedAt = n;
      orderAndBeServed = i;     
   }
   
   public int getArrivedAt()
   {
      return arrivedAt;
   }
   
   public int getOrderAndBeServed()
   {
      return orderAndBeServed;
   }
   
   public void setOrderAndBeServed(int n)
   {
      orderAndBeServed = orderAndBeServed + n; 
   }
   
   public String toString()
   {
      return "" + arrivedAt; 
   }
    
   }
}