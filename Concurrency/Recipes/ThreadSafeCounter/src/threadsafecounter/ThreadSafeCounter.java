package threadsafecounter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.atomic.AtomicLong;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Потокобезопасный генератор чисел.
 *
 * @author Denis
 */
public class ThreadSafeCounter
{
   public static void main(String[] args)
   {
      ThreadSafeCounter testCounter = new ThreadSafeCounter();
      testCounter.start();
   }

   final AtomicLong orderIdGenerator = new AtomicLong(0);
   final List<Order> orders;

   public ThreadSafeCounter()
   {
      this.orders = Collections.synchronizedList(new ArrayList<Order>());
   }

   private void start()
   {
      for (int i = 0; i < 10; i++)
      {
         Thread orderCreationThread = new Thread(new Runnable()
         {
            @Override
            public void run()
            {
               for (int j = 0; j < 10; j++)
               {
                  createOrder(Thread.currentThread().getName());
               }
            }

         });
         orderCreationThread.setName("Order Creation Thread " + i);
         orderCreationThread.start();
      }
      try
      {
         Thread.sleep(1000);
      }
      catch (InterruptedException ex)
      {
         Logger.getLogger(ThreadSafeCounter.class.getName()).log(Level.SEVERE, null, ex);
      }

      // Удостоверимся в уникальности идентификаторов
      Set<Long> orderIds = new HashSet<>();
      for (Order order : orders)
      {
         if (orderIds.contains(order.orderId))
         {
            System.out.println("The horror! An order ID has been reused!");
         }
         orderIds.add(order.orderId);
         System.out.println("Order id: " + order.orderId);
      }
   }

   private void createOrder(String name)
   {
      long orderId = orderIdGenerator.incrementAndGet();
      Order order = new Order(name, orderId);
      orders.add(order);
   }

   class Order
   {
      Order(String orderName, long orderId)
      {
         this.orderName = orderName;
         this.orderId = orderId;
      }

      final String orderName;
      final long orderId;
   }

}
