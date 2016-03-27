package exercises;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Согласованность выполнения задач.
 *
 * @author Denis
 */
public class TaskCooperating
{
   public static void main(String[] args)
   {
      SyncType type = new SyncType();
      ExecutorService service = Executors.newFixedThreadPool(2);
      service.execute(new FirstTask(type));
      service.execute(new SecondTask(type));
      service.shutdown();
   }

}

class SyncType
{
   synchronized void mayPrint() throws InterruptedException
   {
      while (waitFlag)
      {
         wait();
      }
   }

   synchronized void mustWait() throws InterruptedException
   {
      if (waitFlag)
      {
         TimeUnit.SECONDS.sleep(5);
         waitFlag = false;
         notify();
      }
   }

   private boolean waitFlag = true;
}

class FirstTask implements Runnable
{
   FirstTask(SyncType syncType)
   {
      this.syncType = syncType;
   }

   @Override
   public void run()
   {
      try
      {
         System.out.println("Must wait for a while");
         syncType.mustWait();
      }
      catch (InterruptedException ex)
      {
         Logger.getLogger(FirstTask.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   private SyncType syncType;
}

class SecondTask implements Runnable
{
   SecondTask(SyncType syncType)
   {
      this.syncType = syncType;
   }

   @Override
   public void run()
   {
      try
      {
         syncType.mayPrint();
         System.out.println("Print message is delivered");
      }
      catch (InterruptedException ex)
      {
         Logger.getLogger(SecondTask.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   private SyncType syncType;
}
