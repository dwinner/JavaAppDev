package changingcontainer;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Итерация по изменяющейся коллекции.
 *
 * @author Denis
 */
public class ChangingContainer
{
   public static void main(String[] args)
   {
      ChangingContainer concurContainer = new ChangingContainer();
      concurContainer.start();
   }

   private void start()
   {
      System.out.println("Using CopyOnWrite");
      copyOnWriteSolution();
      System.out.println("Using SynchronizedList");
      synchronizedListSolution();
   }

   /**
    * Читающих и записывающих потоков примерно одинаковое количество.
    */
   private void synchronizedListSolution()
   {
      final List<String> list = Collections.synchronizedList(new ArrayList<String>());
      startUpdatingThread(list);
      synchronized (list)
      {
         for (String element : list)
         {
            System.out.println("Element: " + element);
         }
      }
      stopUpdatingThread();
   }

   /**
    * Записывающих потоков заметно меньше, чем читающих.
    */
   private void copyOnWriteSolution()
   {
      CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
      startUpdatingThread(list);
      for (String element : list)
      {
         System.out.println("Element : " + element);
      }
      stopUpdatingThread();
   }

   private void startUpdatingThread(final List<String> list)
   {
      updatingThread = new Thread(new Runnable()
      {
         @Override
         public void run()
         {
            while (!Thread.interrupted())
            {
               int size = list.size();
               if (random.nextBoolean())
               {
                  if (size > 1)
                  {
                     list.remove(random.nextInt(size - 1));
                  }
               }
               else
               {
                  if (size < 20)
                  {
                     list.add("Random string " + counter);
                  }
               }
               counter++;
            }
         }

         long counter = 0;
      });
      updatingThread.start();
      try
      {
         Thread.sleep(100);
      }
      catch (InterruptedException ex)
      {
         Logger.getLogger(ChangingContainer.class.getName()).log(Level.SEVERE, null, ex);
      }
   }

   private void stopUpdatingThread()
   {
      updatingThread.interrupt();
   }

   private Random random = new Random();
   private Thread updatingThread;
}
