package threadcoordinating1;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Координация потоков. Традиционный подход: применение вспомогательного объекта.
 *
 * @author Denis
 */
public class ThreadCoordinating1
{
   public static void main(String[] args)
   {
      ThreadCoordinating1 test = new ThreadCoordinating1();
      test.start();
   }

   private void start()
   {
      loadItems();

      Thread inventoryThread = new Thread(new Runnable()
      {
         @Override
         public void run()
         {
            System.out.println("Loading Inventory from Database...");
            loadInventory();
            synchronized (objectToSync)
            {
               objectToSync.notify();
            }
         }

      });

      synchronized (objectToSync)
      {
         inventoryThread.start();
         try
         {
            objectToSync.wait();
         }
         catch (InterruptedException ex)
         {
            Logger.getLogger(ThreadCoordinating1.class.getName()).log(Level.SEVERE, null, ex);
         }
      }

      Thread ordersThread = new Thread(new Runnable()
      {
         @Override
         public void run()
         {
            System.out.println("Loading Orders from XML Web Service...");
            loadOrders();
            synchronized (objectToSync)
            {
               objectToSync.notify();
            }
         }

      });

      synchronized (objectToSync)
      {
         ordersThread.start();
         try
         {
            objectToSync.wait();
         }
         catch (InterruptedException ex)
         {
            Logger.getLogger(ThreadCoordinating1.class.getName()).log(Level.SEVERE, null, ex);
         }
      }

      processOrders();
   }

   private Random random = new Random();
   private List<String> itemList = new ArrayList<>();
   private Map<String, Integer> inventoryMap = new HashMap<>();
   private Collection<Order> orderList = new ArrayList<>();
   private final Object objectToSync = new Object();
   private int result;

   private void processOrders()
   {
      for (Order order : orderList)
      {
         boolean fulfillable = canFulfill(order);
         if (fulfillable)
         {
            doFulfill(order);
            System.out.println("Order # " + String.valueOf(order.getOrderId()) + " has been fulfilled");
         }
         else
         {
            System.out.println("Order # " + String.valueOf(order.getOrderId()) + " cannot be fulfilled");
         }
      }
   }

   private void doFulfill(Order anOrder)
   {
      for (String item : anOrder.getOrderedItems().keySet())
      {
         int quantity = anOrder.getOrderedItems().get(item);
         int currentInventory = inventoryMap.get(item);
         inventoryMap.put(item, currentInventory - quantity);
      }
   }

   private boolean canFulfill(Order anOrder)
   {
      for (String item : anOrder.getOrderedItems().keySet())
      {
         int quantity = anOrder.getOrderedItems().get(item);
         int currentInventory = inventoryMap.get(item);
         if (quantity > currentInventory)
         {
            return false;
         }
      }
      return true;
   }

   private void loadOrders()
   {
      for (int i = 0; i < 1000; i++)
      {
         Order order = new Order(i);
         for (int j = 0; j < 10; j++)
         {
            String randomItem = itemList.get(random.nextInt(itemList.size()));
            order.addItem(randomItem, random.nextInt(2) + 1);
         }
         orderList.add(order);
      }
   }

   private void loadItems()
   {
      for (int i = 0; i < 100; i++)
      {
         itemList.add("Item # " + i);
      }
   }

   private void loadInventory()
   {
      for (String item : itemList)
      {
         inventoryMap.put(item, random.nextInt(500));
      }
   }

   private static class Order
   {
      Order(long anOrderId)
      {
         orderId = anOrderId;
      }

      void addItem(String itemName, int quantity)
      {
         Integer currentQuantity = orderedItems.get(itemName);
         if (currentQuantity == null)
         {
            currentQuantity = 0;
         }
         orderedItems.put(itemName, currentQuantity + quantity);
      }

      long getOrderId()
      {
         return orderId;
      }

      Map<String, Integer> getOrderedItems()
      {
         return Collections.unmodifiableMap(orderedItems);
      }

      private long orderId;
      private Map<String, Integer> orderedItems = new HashMap<>();
   }

}
