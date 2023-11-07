package backgroundtask;

/**
 * Запуск фоновой задачи.
 *
 * @author Denis
 */
public class BackgroundTask
{
   public static void main(String[] args)
   {
      BackgroundTask task = new BackgroundTask();
      task.start();
   }

   private void start()
   {
      Thread backgroundThread = new Thread(new Runnable()
      {
         @Override
         public void run()
         {
            doSomethingInBackground();
         }

      }, "Background Thread");

      System.out.println("Start");
      backgroundThread.start();
      for (int i = 0; i < 10; i++)
      {
         System.out.println(Thread.currentThread().getName() + " : is counting " + i);
      }

      System.out.println("Done");
   }

   private void doSomethingInBackground()
   {
      System.out.println(Thread.currentThread().getName() + " : is running in background");
   }

}
