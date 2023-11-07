package new_synchronizators;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * Тестирование класса Pool
 *
 * @author Denis
 */
public class SemaphoreDemo
{
   public static void main(String[] args) throws InterruptedException
   {
      final Pool<Fat> pool = new Pool<>(Fat.class, SIZE);
      ExecutorService exec = Executors.newCachedThreadPool();
      for (int i = 0; i < SIZE; i++)
      {
         exec.execute(new CheckoutTask<>(pool));
      }
      System.out.println("All CheckoutTasks created");
      List<Fat> list = new ArrayList<>();
      for (int i = 0; i < SIZE; i++)
      {
         Fat f = pool.checkOut();
         System.out.println(i + " main() thread checked out ");
         f.operation();
         list.add(f);
      }
      Future<?> blocked = exec.submit(new Runnable()
      {
         @Override
         public void run()
         {
            try
            {
               /* Семафор предотвращает лишний вызов checkOut,
                поэтому следующий вызов блокируется: */
               pool.checkOut();
            }
            catch (InterruptedException ex)
            {
               System.out.println("checkOut() Interrupted");
            }
         }

      });
      TimeUnit.SECONDS.sleep(2);
      blocked.cancel(true);   // Выход из заблокированного вызова
      System.out.println("Checking in objects in " + list);
      for (Fat f : list)
      {
         pool.checkIn(f);
      }
      for (Fat f : list)
      {
         pool.checkIn(f);  // Второй вызов checkIn игнорируется
      }
      exec.shutdown();
   }

   final static int SIZE = 25;
}

/**
 * Задача для получения ресурса из пула:
 *
 * @author Denis
 * @param <T> Тип объекта в пуле
 */
class CheckoutTask<T> implements Runnable
{
   CheckoutTask(Pool<T> aPool)
   {
      pool = aPool;
   }

   @Override
   public void run()
   {
      try
      {
         T item = pool.checkOut();
         System.out.println(this + " check out " + item);
         TimeUnit.SECONDS.sleep(1);
         System.out.println(this + " checking in " + item);
         pool.checkIn(item);
      }
      catch (InterruptedException ex)
      {
         // TODO: Приемлемый способ завершения
      }
   }

   @Override
   public String toString()
   {
      return "CheckoutTask{" + "id=" + id + ", pool=" + pool + '}';
   }

   private static int counter = 0;
   private final int id = counter++;
   private Pool<T> pool;
}
