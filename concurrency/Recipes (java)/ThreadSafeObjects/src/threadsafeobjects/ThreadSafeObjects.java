package threadsafeobjects;

/**
 * Потокобезопасные объекты.
 *
 * @author Denis
 */
public class ThreadSafeObjects
{
   public static void main(String[] args)
   {
   }

   class CustomerOrder
   {
      private String itemOrdered;
      private int quantityOrdered;
      private String customerName;

      CustomerOrder()
      {
      }

      public double calculateOrderTotal(double price)
      {
         synchronized (this)
         {
            return getQuantityOrdered() * price;
         }
      }

      public synchronized String getItemOrdered()
      {
         return itemOrdered;
      }

      public synchronized int getQuantityOrdered()
      {
         return quantityOrdered;
      }

      public synchronized String getCustomerName()
      {
         return customerName;
      }

      public synchronized void setItemOrdered(String itemOrdered)
      {
         this.itemOrdered = itemOrdered;
      }

      public synchronized void setQuantityOrdered(int quantityOrdered)
      {
         this.quantityOrdered = quantityOrdered;
      }

      public synchronized void setCustomerName(String customerName)
      {
         this.customerName = customerName;
      }

   }

   class ImmutableCustomerOrder
   {
      ImmutableCustomerOrder(String itemOrdered, int quantityOrdered, String customerName)
      {
         this.itemOrdered = itemOrdered;
         this.quantityOrdered = quantityOrdered;
         this.customerName = customerName;
      }

      public synchronized double calculateOrderTotal(double price)
      {
         return quantityOrdered * price;
      }

      final String itemOrdered;
      final int quantityOrdered;
      final String customerName;
   }

}
