package threadcoordinating3;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Координация потоков. Ожидание завершения.
 *
 * @author Denis
 */
public class ThreadCoordinating3
{
   public static void main(String[] args)
   {
      ThreadCoordinating3 test = new ThreadCoordinating3();
      test.start();
   }

   private Random random = new Random();
   private List<String> itemList = new ArrayList<>();
   private Map<String, Integer> inventoryMap = new HashMap<>();
   private Collection<Order> orderList = new ArrayList<>();

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
         }

      });

      inventoryThread.start();
      try
      {
         inventoryThread.join();
      }
      catch (InterruptedException ex)
      {
         Logger.getLogger(ThreadCoordinating3.class.getName()).log(Level.SEVERE, null, ex);
      }

      Thread ordersThread = new Thread(new Runnable()
      {
         @Override
         public void run()
         {
            System.out.println("Loading Orders from XML Web Service...");
            loadOrders();
         }

      });

      ordersThread.start();
      try
      {
         ordersThread.join();
      }
      catch (InterruptedException ex)
      {
         Logger.getLogger(ThreadCoordinating3.class.getName()).log(Level.SEVERE, null, ex);
      }

      processOrders();
   }

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
            System.out.println("Order # " + String.valueOf(order.getOrderId()) + " CANNOT be fulfilled");
         }
      }
   }

   private void doFulfill(Order order)
   {
      for (String item : order.getOrderedItems().keySet())
      {
         int quantity = order.getOrderedItems().get(item);
         int currentInventory = inventoryMap.get(item);
         inventoryMap.put(item, currentInventory - quantity);
      }
   }

   private boolean canFulfill(Order order)
   {
      for (String item : order.getOrderedItems().keySet())
      {
         int quantity = order.getOrderedItems().get(item);
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
         itemList.add("Item #" + i);
      }
   }

   private void loadInventory()
   {
      for (String item : itemList)
      {
         inventoryMap.put(item, random.nextInt(500));
      }
   }

   private class Order
   {
      Order(long orderId)
      {
         this.orderId = orderId;
      }

      public void addItem(String itemName, int quantity)
      {
         Integer currentQuantity = orderedItems.get(itemName);
         if (currentQuantity == null)
         {
            currentQuantity = 0;
         }
         orderedItems.put(itemName, currentQuantity + quantity);
      }

      public Map<String, Integer> getOrderedItems()
      {
         return Collections.unmodifiableMap(orderedItems);
      }

      public long getOrderId()
      {
         return orderId;
      }

      private long orderId;
      private Map<String, Integer> orderedItems = new HashMap<>();
   }

}
