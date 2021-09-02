package simulation;

/*
 * Ресторан с очередями {Args: 5}
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.*;

/**
 * Заказ, отправляемый официанту, который далее отдает его шеф-повару. Объект для передачи данных
 *
 * @author Denis
 */
class Order
{
   private static int counter = 0;
   private final int id = counter++;
   private final Customer customer;
   private final WaitPerson waitPerson;
   private final Food food;

   Order(Customer cust, WaitPerson wp, Food f)
   {
      customer = cust;
      waitPerson = wp;
      food = f;
   }

   Food item()
   {
      return food;
   }

   Customer getCustomer()
   {
      return customer;
   }

   WaitPerson getWaitPerson()
   {
      return waitPerson;
   }

   @Override
   public String toString()
   {
      return "Order: " + id + " item: " + food
        + " for: " + customer
        + " served by: " + waitPerson;
   }

}

/**
 * То, что возвращается от шеф-повара.
 *
 * @author Denis
 */
class Plate
{
   private final Order order;
   private final Food food;

   Plate(Order ord, Food f)
   {
      order = ord;
      food = f;
   }

   Order getOrder()
   {
      return order;
   }

   Food getFood()
   {
      return food;
   }

   @Override
   public String toString()
   {
      return food.toString();
   }

}

/**
 * Клиент
 *
 * @author Denis
 */
class Customer implements Runnable
{
   private static int counter = 0;
   private final int id = counter++;
   private final WaitPerson waitPerson;
   // Только один заказ в единицу времени может быть получен:
   private SynchronousQueue<Plate> placeSetting = new SynchronousQueue<>();

   Customer(WaitPerson w)
   {
      waitPerson = w;
   }

   void deliver(Plate p) throws InterruptedException
   {
      // Блокировать, если клиент все еще не закончил с предыдущим заказом:
      placeSetting.put(p);
   }

   @Override
   public void run()
   {
      for (Course course : Course.values())
      {
         Food food = course.randomSelection();
         try
         {
            waitPerson.placeOrder(this, food);
            // Блокировать, пока заказ не будет доставлеен:
            System.out.println(this + "eating " + placeSetting.take());
         }
         catch (InterruptedException e)
         {
            System.out.println(this + "waiting for " + course + " interrupted");
            break;
         }
      }
      System.out.println(this + "finished meal, leaving");
   }

   @Override
   public String toString()
   {
      return "Customer " + id + " ";
   }

}

/**
 * Официант
 *
 * @author Denis
 */
class WaitPerson implements Runnable
{
   private static int counter = 0;
   private final int id = counter++;
   private final Restaurant restaurant;
   private BlockingQueue<Plate> filledOrders = new LinkedBlockingQueue<>();

   WaitPerson(Restaurant rest)
   {
      restaurant = rest;
   }

   public void placeOrder(Customer cust, Food food)
   {
      try
      {
         /* Фактически не должно блокироваться, потому что LinkedBlockingQueue
          не имеет фиксированного размера */
         restaurant.getOrders().put(new Order(cust, this, food));
      }
      catch (InterruptedException e)
      {
         System.out.println(this + " placeOrder interrupted");
      }
   }

   @Override
   public void run()
   {
      try
      {
         while (!Thread.interrupted())
         {
            // Блокировать, пока заказ не будет готов
            Plate plate = getFilledOrders().take();
            System.out.println(this + "received " + plate + " delivering to "
              + plate.getOrder().getCustomer());
            plate.getOrder().getCustomer().deliver(plate);
         }
      }
      catch (InterruptedException e)
      {
         System.out.println(this + " interrupted");
      }
      System.out.println(this + " off duty");
   }

   @Override
   public String toString()
   {
      return "WaitPerson " + id + " ";
   }

   /**
    * @return the filledOrders
    */
   public BlockingQueue<Plate> getFilledOrders()
   {
      return filledOrders;
   }

   /**
    * @param filledOrders the filledOrders to set
    */
   public void setFilledOrders(BlockingQueue<Plate> filledOrders)
   {
      this.filledOrders = filledOrders;
   }

}

/**
 * Шеф-повар
 *
 * @author Denis
 */
class Chef implements Runnable
{
   private static int counter = 0;
   private final int id = counter++;
   private final Restaurant restaurant;
   private static Random rand = new Random(47);

   Chef(Restaurant rest)
   {
      restaurant = rest;
   }

   @Override
   public void run()
   {
      try
      {
         while (!Thread.interrupted())
         {
            // Блокировать, пока не появится закак:
            Order order = restaurant.getOrders().take();
            Food requestedItem = order.item();
            // Время на подготовку заказа:
            TimeUnit.MILLISECONDS.sleep(rand.nextInt(500));
            Plate plate = new Plate(order, requestedItem);
            order.getWaitPerson().getFilledOrders().put(plate);
         }
      }
      catch (InterruptedException e)
      {
         System.out.println(this + " interrupted");
      }
      System.out.println(this + " off duty");
   }

   @Override
   public String toString()
   {
      return "Chef " + id + " ";
   }

}

/**
 * Ресторан.
 *
 * @author Denis
 */
class Restaurant implements Runnable
{
   private List<WaitPerson> waitPersons = new ArrayList<>();
   private List<Chef> chefs = new ArrayList<>();
   private ExecutorService exec;
   private static Random rand = new Random(47);
   private BlockingQueue<Order> orders = new LinkedBlockingQueue<>();

   Restaurant(ExecutorService e, int nWaitPersons, int nChefs)
   {
      exec = e;
      for (int i = 0; i < nWaitPersons; i++)
      {
         WaitPerson waitPerson = new WaitPerson(this);
         waitPersons.add(waitPerson);
         exec.execute(waitPerson);
      }
      for (int i = 0; i < nChefs; i++)
      {
         Chef chef = new Chef(this);
         chefs.add(chef);
         exec.execute(chef);
      }
   }

   /**
    * @return the orders
    */
   BlockingQueue<Order> getOrders()
   {
      return orders;
   }

   /**
    * @param orders the orders to set
    */
   void setOrders(BlockingQueue<Order> orders)
   {
      this.orders = orders;
   }

   @Override
   public void run()
   {
      try
      {
         while (!Thread.interrupted())
         {
            // Появление нового клиента. Зовем официанта:
            WaitPerson wp = waitPersons.get(rand.nextInt(waitPersons.size()));
            Customer c = new Customer(wp);
            exec.execute(c);
            TimeUnit.MILLISECONDS.sleep(100);
         }
      }
      catch (InterruptedException e)
      {
         System.out.println("Restaurant interrupted");
      }
      System.out.println("Restaurant closing");
   }

}

public class RestaurantWithQueues
{
   public static void main(String[] args) throws Exception
   {
      ExecutorService exec = Executors.newCachedThreadPool();
      Restaurant restaurant = new Restaurant(exec, 5, 2);
      exec.execute(restaurant);
      if (args.length > 0)
      {  // Необязательный аргумент
         TimeUnit.SECONDS.sleep(new Integer(args[0]));
      }
      else
      {
         System.out.println("Press 'Enter' to quit");
         System.in.read();
      }
      exec.shutdownNow();
   }

}
