package exercises;

import java.util.Random;
import java.util.concurrent.*;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 04.12.12
 * Time: 23:52
 * Модель использования трансферных очередей.
 */
public class TransferQueueTest
{
   public static void main(String[] args) throws InterruptedException
   {
      TransferQueue<Toast>
        dryQueue = new LinkedTransferQueue<>(),
        bufferedQueue = new LinkedTransferQueue<>(),
        finishedQueue = new LinkedTransferQueue<>();
      ExecutorService exec = Executors.newCachedThreadPool();
      exec.execute(new Toaster(dryQueue));
      exec.execute(new Butterer(dryQueue, bufferedQueue));
      exec.execute(new Jammer(bufferedQueue, finishedQueue));
      exec.execute(new Eater(finishedQueue));
      TimeUnit.SECONDS.sleep(5);
      exec.shutdownNow();
   }
}

class Toast
{
   static enum Status
   {
      DRY, BUTTERED, JAMMED
   }

   Toast(int anId)
   {
      this.id = anId;
   }

   void butter()
   {
      status = Status.BUTTERED;
   }

   void jam()
   {
      status = Status.JAMMED;
   }

   Status getStatus()
   {
      return status;
   }

   @Override
   public String toString()
   {
      return "Toast{" + "status=" + status + ", id=" + id + '}';
   }

   final int id;
   private Status status = Status.DRY;
}

class Toaster implements Runnable
{
   @Override
   public void run()
   {
      try
      {
         while (!Thread.interrupted())
         {
            TimeUnit.MILLISECONDS.sleep(100 + random.nextInt(500));
            Toast toast = new Toast(count++);
            System.out.println(toast);
            toastTransferQueue.put(toast);
         }
      }
      catch (InterruptedException e)
      {
         System.out.println(this + " interrupted");
      }
      System.out.println(this + " off");
   }

   @Override
   public String toString()
   {
      return "Toaster{" + "count=" + count + ", random=" + random + '}';
   }

   Toaster(TransferQueue<Toast> toastQueue)
   {
      toastTransferQueue = toastQueue;
   }

   private TransferQueue<Toast> toastTransferQueue;
   private int count = 0;
   private Random random = new Random(47);
}

class Butterer implements Runnable
{
   @Override
   public void run()
   {
      try
      {
         while (!Thread.interrupted())
         {
            Toast toast = dryTransferQueue.take();
            toast.butter();
            System.out.println(toast);
            butteredTransferQueue.put(toast);
         }
      }
      catch (InterruptedException e)
      {
         System.out.println(this + " interrupted");
      }
      System.out.println(this + " off");
   }

   @Override
   public String toString()
   {
      return getClass().getName();
   }

   Butterer(TransferQueue<Toast> dryQueue, TransferQueue<Toast> butteredQueue)
   {
      dryTransferQueue = dryQueue;
      butteredTransferQueue = butteredQueue;
   }

   private TransferQueue<Toast> dryTransferQueue;
   private TransferQueue<Toast> butteredTransferQueue;
}

class Jammer implements Runnable
{
   @Override
   public void run()
   {
      try
      {
         while (!Thread.interrupted())
         {
            Toast toast = butteredTransferQueue.take();
            toast.jam();
            System.out.println(toast);
            finishedTransferQueue.put(toast);
         }
      }
      catch (InterruptedException e)
      {
         System.out.println(this + " interrupted");
      }
      System.out.println(this + " off");
   }

   @Override
   public String toString()
   {
      return getClass().getName();
   }

   Jammer(TransferQueue<Toast> butteredQueue, TransferQueue<Toast> finishedQueue)
   {
      butteredTransferQueue = butteredQueue;
      finishedTransferQueue = finishedQueue;
   }

   private TransferQueue<Toast> butteredTransferQueue;
   private TransferQueue<Toast> finishedTransferQueue;
}

class Eater implements Runnable
{
   @Override
   public void run()
   {
      try
      {
         while (!Thread.interrupted())
         {
            Toast toast = finishedQueue.take();
            if (toast.id != counter++ || toast.getStatus() != Toast.Status.JAMMED)
            {
               System.out.println(">>>> Error: " + toast);
               System.exit(1);
            }
            else
            {
               System.out.println(toast + " chopm!");
            }
         }
      }
      catch (InterruptedException e)
      {
         System.out.println(this + " interrupted");
      }
      System.out.println(this + " off");
   }

   @Override
   public String toString()
   {
      return getClass().getName();
   }

   Eater(TransferQueue<Toast> aFinishedQueue)
   {
      finishedQueue = aFinishedQueue;
   }

   private TransferQueue<Toast> finishedQueue;
   private int counter = 0;
}
