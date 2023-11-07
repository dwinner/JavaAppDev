package new_synchronizators;

import java.util.List;
import java.util.concurrent.*;
import new_synchronizators.BasicGenerator;
import new_synchronizators.Generator;

/**
 * Синхронизатор-обменник
 *
 * @author Denis
 */
public class ExchangerDemo
{
   public static void main(String[] args) throws InterruptedException
   {
      if (args.length > 0)
      {
         size = Integer.valueOf(args[0]);
      }
      if (args.length > 1)
      {
         delay = Integer.valueOf(args[1]);
      }
      ExecutorService exec = Executors.newCachedThreadPool();
      Exchanger<List<Fat>> xc = new Exchanger<>();
      List<Fat>
        producerList = new CopyOnWriteArrayList<>(),
        consumerList = new CopyOnWriteArrayList<>();
      exec.execute(new ExchangerProducer<>(xc, BasicGenerator.create(Fat.class), producerList));
      exec.execute(new ExchangerConsumer<>(xc, consumerList));
      TimeUnit.SECONDS.sleep(delay);
      exec.shutdownNow();
   }

   protected static int size = 10;
   private static int delay = 5;   // Секунды
}

class ExchangerProducer<T> implements Runnable
{
   ExchangerProducer(Exchanger<List<T>> anExchanger, Generator<T> aGenerator, List<T> holder)
   {
      exchanger = anExchanger;
      generator = aGenerator;
      this.holder = holder;
   }

   @Override
   public void run()
   {
      try
      {
         while (!Thread.interrupted())
         {
            for (int i = 0; i < ExchangerDemo.size; i++)
            {
               holder.add(generator.next());
            }
            // Заполненный контейнер заменяется пустым
            holder = exchanger.exchange(holder);
         }
      }
      catch (InterruptedException e)
      {
         // TODO: Приемлемый способ завершения
      }
   }

   private Generator<T> generator;
   private Exchanger<List<T>> exchanger;
   private List<T> holder;
}

class ExchangerConsumer<T> implements Runnable
{
   ExchangerConsumer(Exchanger<List<T>> anExchanger, List<T> aHolder)
   {
      exchanger = anExchanger;
      holder = aHolder;
   }

   @Override
   public void run()
   {
      try
      {
         while (!Thread.interrupted())
         {
            holder = exchanger.exchange(holder);
            for (T x : holder)
            {
               value = x;  // Выборка значения
               holder.remove(x); // Нормально для CopyOnWriteArrayList
            }
         }
      }
      catch (InterruptedException e)
      {
         // TODO: Приемлемый способ завершения
      }
      System.out.println("Total value: " + value);
   }

   private Exchanger<List<T>> exchanger;
   private List<T> holder;
   private volatile T value;
}
