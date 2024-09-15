//name: Kushaan Vardhan    date: August 26, 2021 

import java.text.DecimalFormat;
public class SmartCard 
{
   public final static DecimalFormat df = new DecimalFormat("$0.00");
   public final static double MIN_FARE = 0.5;
   /* enter the private fields */
   private double balance;
   private Station entryPoint = new Station();
   private boolean isBoarded; 
   
   
   /* the one-arg constructor  */
   public SmartCard(double initBalance)
   {
      balance = initBalance;
      entryPoint = null;
      isBoarded = false;
   }

   //these three getter methods only return your private data
   //they do not make any changes to your data
   public boolean getIsBoarded() 
   { 
     return isBoarded;
   }
   
   public double getBalance()
   {
      return balance;
   }
         
   public Station getBoardedAt()
   {
      return entryPoint;
   }
   public String getFormattedBalance()
   {
      return df.format(balance);
   }
    
   /* write the instance methods  */
   
   public int board(Station s)
   {
     if (entryPoint != null){
      System.out.println ("Error: already boarded?!");
      return 0;
     }
     else if (balance < 0.5){
      System.out.println ("Insufficient funds to board. Please add more money.");
      return 1;    
     }
     else{
      entryPoint = s;
      isBoarded = true;
      return 2;
     }
   }
   
   public double cost(Station s)
   {
      double zoneTravelCost = Math.abs(s.getZone() - entryPoint.getZone());
      return 0.75*(zoneTravelCost) + 0.5; 
   }
   
   public int exit(Station s)
   {
      if (isBoarded == false)
      {
         System.out.println ("Error: Did not board?!");
         return 5;
      }
      else if(cost(s)>balance)
      {
         System.out.println ("Insufficient funds to exit. Please add more money.");
         return 2;
      }
      else
      {
         balance = balance - cost(s);
         isBoarded = false;
         System.out.println ("From " + getBoardedAt().getName() + " to " + s.getName() + " costs " + cost(s) + " . SmartCard has " + getFormattedBalance());
         
         entryPoint = null;
         return 1;
      }
     
   }
  
   public int addMoney (double d)
   {
      balance += d;
      return 0;
      
   }
      
    

}
   
// ***********  start a new class.  The new class does NOT have public or private.  ***/
class Station
{
   private String name;
   private int zone;
   public Station() {
      name = null;
      zone = 0;
   }
   public Station(String n, int z) {
      name = n;
      zone = z;
  }
  public String getName(){
   return name;
  }
  public int getZone(){
   return zone;
  }
}

