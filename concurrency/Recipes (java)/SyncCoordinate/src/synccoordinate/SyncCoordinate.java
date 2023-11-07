package synccoordinate;

import java.util.Map.Entry;
import java.util.*;

/**
 * Координация доступа к объектам. Неявный объект блокировки.
 *
 * @author Denis
 */
public class SyncCoordinate
{
   public static void main(String[] args) throws InterruptedException
   {
      SyncCoordinate coordinate = new SyncCoordinate();
      coordinate.start();
   }

   final Map<String, Integer> inventoryMap = new LinkedHashMap<>();
   final List<CustomerOrder> customerOrders = new ArrayList<>();
   final Random random = new Random();
   final Set<Thread> orderingThreads = new HashSet<>();

   private void start() throws InterruptedException
   {
      for (int i = 0; i < 100; i++)
      {  // Заполнение карты
         inventoryMap.put("Apress Book # " + i, 1000);
      }

      for (int i = 0; i < 20; i++)
      {  // Запуск 20-ти потоков
         createOrderingThread();
      }

      Thread.sleep(100);
      checkInventoryLevels();
      Thread.sleep(100);

      for (Thread thread : orderingThreads)
      {
         thread.interrupt();
      }
      Thread.sleep(1000);

      checkInventoryLevels();
      displayOrders();
   }

   private void displayOrders()
   {
      synchronized (inventoryMap)
      {
         for (CustomerOrder order : customerOrders)
         {
            System.out.println(order.quantityOrdered + " " + order.itemOrdered + " for " + order.customerName);
         }
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
      String customerName = "Customer : " + UUID.randomUUID().toString();
      fulfillOrder(itemOrdered, quantityOrdered, customerName);
   }

   private boolean fulfillOrder(String itemOrdered, int quantityOrdered, String customerName)
   {
      synchronized (inventoryMap)
      {
         int currentInventory = inventoryMap.get(itemOrdered);
         if (currentInventory < quantityOrdered)
         {
            System.out.
              println(
              "Could not fulfill order for " + customerName + " not enough " + itemOrdered + " (" + quantityOrdered + ")");
            return false;  // Продано
         }
         inventoryMap.put(itemOrdered, currentInventory - quantityOrdered);
         CustomerOrder order = new CustomerOrder(itemOrdered, quantityOrdered, customerName);
         customerOrders.add(order);
         System.out.println("Order fulfilled for " + customerName + " of " + itemOrdered + " (" + quantityOrdered + ")");
         return true;
      }
   }

   private void checkInventoryLevels()
   {
      synchronized (inventoryMap)
      {
         System.out.println("----------------------------------");
         for (Entry<String, Integer> inventoryEntry : inventoryMap.entrySet())
         {
            System.out.println("Inventory Level: " + inventoryEntry.getKey() + " " + inventoryEntry.getValue());
         }
         System.out.println("----------------------------------");
      }
   }

   private static class CustomerOrder
   {
      CustomerOrder(String itemOrdered, int quantityOrdered, String customerName)
      {
         this.itemOrdered = itemOrdered;
         this.quantityOrdered = quantityOrdered;
         this.customerName = customerName;
      }

      final String itemOrdered;
      final int quantityOrdered;
      final String customerName;
   }

}
