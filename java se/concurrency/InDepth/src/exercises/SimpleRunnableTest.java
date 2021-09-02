package exercises;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 22.11.12
 * Time: 1:01
 * Упражнение 1: Реализация интерфейса Runnable.
 */
public class SimpleRunnableTest
{
   public static void main(String[] args)
   {
      for (int i = 0; i < 10; i++)
      {
         new Thread(new RunnableImpl("Running message")).start();
      }
      System.out.println("Just a main holder message");
   }
}

class RunnableImpl implements Runnable
{
   RunnableImpl(String aMessage)
   {
      message = aMessage + " " + runId;
   }

   @Override
   public void run()
   {
      for (int i = 0; i < REPEAT; i++)
      {
         System.out.println(message);
         Thread.yield();
      }
      System.out.println(SHUTDOWN_MESSAGE + " (# " + runId + ")");
   }

   static final String SHUTDOWN_MESSAGE = "Thread is shut down";
   static final int REPEAT = 3;
   private String message;
   private static int counter = 0;
   private final int runId = counter++;
}
