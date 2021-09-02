package simulation;

import java.io.IOException;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Пример использования очередей и многопоточного программирования. Симуляция модели кассира.
 *
 * @author Denis
 */
public class BankTellerSimulation
{
   static final int MAX_LINE_SIZE = 50;
   static final int ADJUSTMENT_PERIOD = 1000;

   public static void main(String[] args) throws InterruptedException,
                                                 IOException
   {
      ExecutorService exec = Executors.newCachedThreadPool();
      // Если очередь слишком длинна, клиенты уходят:
      CustomerLine customers = new CustomerLine(MAX_LINE_SIZE);
      exec.execute(new CustomerGenerator(customers));
      // TellerManager добавляет и убирает кассиров по мере необходимости:
      exec.execute(new TellerManager(exec, customers, ADJUSTMENT_PERIOD));
      if (args.length > 0) // Необязательный аргумент
      {
         TimeUnit.SECONDS.sleep(Integer.valueOf(args[0]));
      }
      else
      {
         System.out.println("Press 'Enter' to quit");
         System.in.read();
      }
      exec.shutdownNow();
   }

}


/**
 * Объекты, доступные только для чтения, не требуют синхронизации. Клиенты для кассиров
 *
 * @author Denis
 */
class RestaurantCustomer
{
   private final int serviceTime;

   RestaurantCustomer(int aServiceTime)
   {
      serviceTime = aServiceTime;
   }

   int getServiceTime()
   {
      return serviceTime;
   }

   @Override
   public String toString()
   {
      return "Customer{" + "serviceTime=" + serviceTime + '}';
   }

}

/**
 * Очередь клиентов. Умеет выводить информацию о своем состоянии
 *
 * @author Denis
 */
class CustomerLine extends ArrayBlockingQueue<RestaurantCustomer>
{
   CustomerLine(int maxLineSize)
   {
      super(maxLineSize);
   }

   @Override
   public String toString()
   {
      if (size() == 0)
      {
         return "[Empty]";
      }
      StringBuilder result = new StringBuilder();
      for (RestaurantCustomer customer : this)
      {
         result.append(customer);
      }
      return result.toString();
   }

}

/**
 * Случайное добавление клиентов в очередь.
 *
 * @author Denis
 */
class CustomerGenerator implements Runnable
{
   private CustomerLine customers;
   private static Random rand = new Random(47);

   CustomerGenerator(CustomerLine aCustomerLine)
   {
      customers = aCustomerLine;
   }

   @Override
   public void run()
   {
      try
      {
         while (!Thread.interrupted())
         {
            TimeUnit.MILLISECONDS.sleep(rand.nextInt(300));
            customers.put(new RestaurantCustomer(rand.nextInt(1000)));
         }
      }
      catch (InterruptedException e)
      {
         System.out.println("CustomerGenerator interrupted");
      }
      System.out.println("CustomerGenerator terminating");
   }

}

/**
 * Кассир
 *
 * @author Denis
 */
class Teller implements Runnable, Comparable<Teller>
{
   private static int counter = 0;
   private final int id = counter++;
   private int customerServed = 0;  // Счетчик клиентов, обслуженных за текущую смену
   private CustomerLine customers;
   private boolean servingCustomerLine = true;  // true = Обслуживает очередь

   Teller(CustomerLine aCustomerLine)
   {
      customers = aCustomerLine;
   }

   @Override
   public void run()
   {
      try
      {
         while (!Thread.interrupted())
         {
            RestaurantCustomer customer = customers.take();
            TimeUnit.MILLISECONDS.sleep(customer.getServiceTime());
            synchronized (this)
            {
               customerServed++;
               while (!servingCustomerLine)
               {
                  wait();
               }
            }
         }
      }
      catch (InterruptedException e)
      {
         System.out.println(this + " interrupted");
      }
      System.out.println(this + " finishing");
   }

   synchronized void doSomethingElse()
   {
      customerServed = 0;
      servingCustomerLine = false;
   }

   synchronized void serveCustomerLine()
   {
      assert !servingCustomerLine : " already served: " + this;
      servingCustomerLine = true;
      notifyAll();
   }

   @Override
   public String toString()
   {
      return String.format("Teller %d ", id);
   }

   String shortString()
   {
      return "T-" + id;
   }

   @Override // Используется приоритетной очередью
   public int compareTo(Teller o)
   {
      return customerServed < o.customerServed
        ? -1 : (customerServed == o.customerServed ? 0 : 1);
   }

}

/**
 * Управление кассирами
 *
 * @author Denis
 */
class TellerManager implements Runnable
{
   private ExecutorService exec;
   private CustomerLine customers;
   private PriorityQueue<Teller> workingTellers = new PriorityQueue<>();
   private Queue<Teller> tellersDoingOtherThings = new LinkedList<>();
   private int adjustmentPeriod;
   private static Random rand = new Random(47);

   TellerManager(ExecutorService e, CustomerLine customerLine, int adjustmentPeriod)
   {
      exec = e;
      customers = customerLine;
      this.adjustmentPeriod = adjustmentPeriod;
      // Начинаем с одного кассира
      Teller teller = new Teller(customers);
      exec.execute(teller);
      workingTellers.add(teller);
   }

   /**
    * Фактически это система управления. Регулировка числовых параметров позволяет выявить проблемы
    * стабильности в механизме управления.
    */
   void adjustTellerNumber()
   {
      // Если очередь слишком длинна, добавить другого кассира:
      if (customers.size() / workingTellers.size() > 2)
      {
         // Если кассиры отдыхают или заняты другими делами, вернуть одного из них
         if (tellersDoingOtherThings.size() > 0)
         {
            Teller teller = tellersDoingOtherThings.remove();
            teller.serveCustomerLine();
            workingTellers.offer(teller);
            return;
         }
         // Иначе создаем (нанимаем) нового кассира
         Teller teller = new Teller(customers);
         exec.execute(teller);
         workingTellers.add(teller);
         return;
      }
      // А если очередь достаточно коротка, освободить кассира:
      if (workingTellers.size() > 1 && customers.size() / workingTellers.size() < 2)
      {
         reassignOneTeller();
      }
      // Если очереди нет, достаточно одного кассира:
      if (customers.size() == 0)
      {
         while (workingTellers.size() > 1)
         {
            reassignOneTeller();
         }
      }
   }

   /**
    * Поручаем кассиру другую работу или отправляем его отдыхать.
    */
   private void reassignOneTeller()
   {
      Teller teller = workingTellers.poll();
      teller.doSomethingElse();
      tellersDoingOtherThings.offer(teller);
   }

   @Override
   public void run()
   {
      try
      {
         while (!Thread.interrupted())
         {
            TimeUnit.MILLISECONDS.sleep(adjustmentPeriod);
            adjustTellerNumber();
            System.out.print(customers + " { ");
            for (Teller teller : workingTellers)
            {
               System.out.print(teller.shortString() + " ");
            }
            System.out.println(" } ");
         }
      }
      catch (InterruptedException e)
      {
         System.out.println(this + " interrupted");
      }
      System.out.println(this + " finish");
   }

   @Override
   public String toString()
   {
      return "TellerManager ";
   }

}
