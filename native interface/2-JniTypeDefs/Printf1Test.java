/**
 * @version 1.10 1997-07-01
 * @author Cay Horstmann
 */

public class Printf1Test
{
   static
   {
      System.loadLibrary("Printf1");
   }
   
   public static void main(String[] args)
   {
      int count = Printf1.print(8, 4, 3.14);
      count += Printf1.print(8, 4, count);
      System.out.println();
      for (int i = 0; i < count; i++)
         System.out.print("-");
      System.out.println();
   }
}