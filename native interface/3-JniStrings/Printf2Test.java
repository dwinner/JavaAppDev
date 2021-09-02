public class Printf2Test
{
   static
   {
      System.loadLibrary("Printf2");
   }
   
   public static void main(String[] args)
   {
      double price = 44.95;
      double tax = 7.75;
      double amountDue = price * (1 + tax / 100);
      
      String s = Printf2.sprint("Amount due = %8.2f", amountDue);
      System.out.println(s);
   }
}