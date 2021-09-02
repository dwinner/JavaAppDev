package lockcoordinate;

import java.util.Map.Entry;
import java.util.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Координация данных для потоков выполнения. Явная блокировка.
 *
 * @author Denis
 */
public class LockCoordinate
{
   public static void main(String[] args) throws InterruptedException
   {
      LockCoordinate coordinate = new LockCoordinate();
      coordinate.start();
   }

   private Set<Thread> orderingThreads = new HashSet<>();
   private Lock inventoryLock = new ReentrantLock();
   private Map<String, Integer> inventoryMap = new LinkedHashMap<>();
   private List<CustomerOrder> customerOrders = new ArrayList<>();
   private Random random = new Random();

   private void start() throws InterruptedException
   {
      for (int i = 0; i < 100; i++)
      {  // Заполнение ведомости значениями
         inventoryMap.put("Appress Book # " + i, 1000);
      }

      // Потоки заказов
      for (int i = 0; i < 20; i++)
      {
         createOrderingThread();
      }

      Thread.sleep(100);
      checkInventoryLevels();
      Thread.sleep(100);

      for (Thread thread : orderingThreads)
      {
         thread.interrupt();
      }
      Thread.sleep(100);

      checkInventoryLevels();
      displayOrders();
   }

   private void displayOrders()
   {
      try
      {
         inventoryLock.lock();
         for (CustomerOrder order : customerOrders)
         {
            System.out.println(order.getQuantityOrdered() + " " + order.getItemOrdered() + " for " + order.
              getCustomerName());
         }
      }
      finally
      {
         inventoryLock.unlock();
      }
   }

   private void createOrderingThread()
   {
      Thread orderingThread = new Thread(new Runnable()
      {
         @Override
         public void run()
         {
            while (!Thread.interrupted())
            {
               createRandomOrder();
            }
         }

      });
      orderingThread.start();
      orderingThreads.add(orderingThread);
   }

   private void createRandomOrder()
   {
      String itemOrdered = "Apress Book # " + random.nextInt(100);
      int quantityOrdered = random.nextInt(2) + 1;
      String customerName = "Customer: " + UUID.randomUUID().toString();
      fulfillOrder(itemOrdered, quantityOrdered, customerName);
   }

   private boolean fulfillOrder(String itemOrdered, int quantityOrdered, String customerName)
   {
      try
      {
         inventoryLock.lock();
         Integer currentInventory = inventoryMap.get(itemOrdered);
         if (currentInventory == null || currentInventory < quantityOrdered)
         {  // FIXME: Можно ввести объект условия
            System.out.
              println(
              "Could not fulfill order for " + customerName + " not enough " + itemOrdered + " (" + quantityOrdered + ")");
            return false;
         }
         inventoryMap.put(itemOrdered, currentInventory - quantityOrdered);
         CustomerOrder order = new CustomerOrder(itemOrdered, quantityOrdered, customerName);
         customerOrders.add(order);
         System.out.println("Order fulfilled for " + customerName + " of " + itemOrdered + " (" + quantityOrdered + ")");
         return true;
      }
      finally
      {
         inventoryLock.unlock();
      }
   }

   private void checkInventoryLevels()
   {
      try
      {
         inventoryLock.lock();
         System.out.println("------------------------------------");
         for (Entry<String, Integer> inventoryEntry : inventoryMap.entrySet())
         {
            System.out.println("Inventory Level: " + inventoryEntry.getKey() + " " + inventoryEntry.getValue());
         }
         System.out.println("------------------------------------");
      }
      finally
      {
         inventoryLock.unlock();
      }
   }

   private static class CustomerOrder
   {
      CustomerOrder(String anItemOrdered, int aQuantityOrdered, String aCustomerName)
      {
         itemOrdered = anItemOrdered;
         quantityOrdered = aQuantityOrdered;
         customerName = aCustomerName;
      }

      public String getItemOrdered()
      {
         return itemOrdered;
      }

      public int getQuantityOrdered()
      {
         return quantityOrdered;
      }

      public String getCustomerName()
      {
         return customerName;
      }

      private String itemOrdered;
      private int quantityOrdered;
      private String customerName;
   }

}
