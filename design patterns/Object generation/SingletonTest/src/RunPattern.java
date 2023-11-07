import goff_singleton.SingletonGui;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 04.08.12
 * Time: 10:36
 * To change this template use File | Settings | File Templates.
 */
public class RunPattern
{
   public static void main(String[] args)
   {
      System.out.println("Example for Singleton pattern");
      System.out.println();
      System.out.println("This example will demonstrate the use of ");
      System.out.println(" the Singleton pattern by creating two GUI");
      System.out.println(" editors, both of which will reference the");
      System.out.println(" same underlying history list.");

      System.out.println("Creating the first editor");
      System.out.println();
      SingletonGui editor2 = new SingletonGui();
      editor2.createGui();
   }
}
