package simulation;

// Сложный пример задач, работающих вместе.

import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.*;

class Car
{
   private final int id;
   private boolean engine = false;
   private boolean driveTrain = false;
   private boolean wheels = false;

   Car(int idn)
   {
      id = idn;
   }

   // Пустой объект Car
   Car()
   {
      id = -1;
   }

   synchronized int getId()
   {
      return id;
   }

   synchronized void addEngine()
   {
      engine = true;
   }

   synchronized void addDriveTrain()
   {
      driveTrain = true;
   }

   synchronized void addWheels()
   {
      wheels = true;
   }

   @Override
   public synchronized String toString()
   {
      return "Car " + id + " [" + " engine: " + engine
        + " driveTrain: " + driveTrain
        + " wheels: " + wheels + " ]";
   }

}

class CarQueue extends LinkedBlockingQueue<Car>
{
}

class ChassisBuilder implements Runnable
{
   private CarQueue carQueue;
   private int counter = 0;

   ChassisBuilder(CarQueue cq)
   {
      carQueue = cq;
   }

   @Override
   public void run()
   {
      try
      {
         while (!Thread.interrupted())
         {
            TimeUnit.MILLISECONDS.sleep(500);
            // Сделать шасси:
            Car c = new Car(counter++);
            System.out.println("ChassisBuilder created " + c);
            // Вставить в очередь
            carQueue.put(c);
         }
      }
      catch (InterruptedException e)
      {
         System.out.println("Interrupted: ChassisBuilder");
      }
      System.out.println("ChassisBuilder off");
   }

}

class Assembler implements Runnable
{
   private CarQueue chassisQueue;
   private CarQueue finishingQueue;
   private Car car;
   private CyclicBarrier barrier = new CyclicBarrier(4);
   private RobotPool robotPool;

   Assembler(CarQueue cq, CarQueue fq, RobotPool rp)
   {
      chassisQueue = cq;
      finishingQueue = fq;
      robotPool = rp;
   }

   Car car()
   {
      return car;
   }

   CyclicBarrier barrier()
   {
      return barrier;
   }

   @Override
   public void run()
   {
      try
      {
         while (!Thread.interrupted())
         {
            // Блокировать, пока шасси не станет доступно:
            car = chassisQueue.take();
            // Аренда роботов для выполнения работы:
            robotPool.hire(EngineRobot.class, this);
            robotPool.hire(DriveTrainRobot.class, this);
            robotPool.hire(WheelRobot.class, this);
            barrier.await(); // Пока пока все роботы не завершат работу
            // Поместить объект Car в finishingQueue для дальнейшей работы
            finishingQueue.put(car);
         }
      }
      catch (InterruptedException e)
      {
         System.out.println("Exiting Assembler via interrupt");
      }
      catch (BrokenBarrierException e)
      {
         // This one we want to know about
         throw new RuntimeException(e);
      }
      System.out.println("Assembler off");
   }

}

class Reporter implements Runnable
{
   private CarQueue carQueue;

   Reporter(CarQueue cq)
   {
      carQueue = cq;
   }

   @Override
   public void run()
   {
      try
      {
         while (!Thread.interrupted())
         {
            System.out.println(carQueue.take());
         }
      }
      catch (InterruptedException e)
      {
         System.out.println("Exiting Reporter via interrupt");
      }
      System.out.println("Reporter off");
   }

}

abstract class Robot implements Runnable
{
   private RobotPool pool;

   Robot(RobotPool p)
   {
      pool = p;
   }

   protected Assembler assembler;

   public Robot assignAssembler(Assembler assembler)
   {
      this.assembler = assembler;
      return this;
   }

   private boolean engage = false;

   public synchronized void engage()
   {
      engage = true;
      notifyAll();
   }

   // Часть метода run(), которая различается для каждого робота
   abstract protected void performService();

   @Override
   public void run()
   {
      try
      {
         powerDown(); // Ждать, пока не станет нужен
         while (!Thread.interrupted())
         {
            performService();
            assembler.barrier().await(); // Синхронизация
            // Закончили с этой работой
            powerDown();
         }
      }
      catch (InterruptedException e)
      {
         System.out.println("Exiting " + this + " via interrupt");
      }
      catch (BrokenBarrierException e)
      {
         // Барьер разрушен, нужно сказать об этом
         throw new RuntimeException(e);
      }
      System.out.println(this + " off");
   }

   private synchronized void powerDown() throws InterruptedException
   {
      engage = false;
      assembler = null; // Отсоединяемся от объекта Assembler
      // Вернуть обратно в пул доступных объектов:
      pool.release(this);
      while (engage == false)  // Выключаем
      {
         wait();
      }
   }

   @Override
   public String toString()
   {
      return getClass().getName();
   }

}

class EngineRobot extends Robot
{
   EngineRobot(RobotPool pool)
   {
      super(pool);
   }

   @Override
   protected void performService()
   {
      System.out.println(this + " installing engine");
      assembler.car().addEngine();
   }

}

class DriveTrainRobot extends Robot
{
   DriveTrainRobot(RobotPool pool)
   {
      super(pool);
   }

   @Override
   protected void performService()
   {
      System.out.println(this + " installing DriveTrain");
      assembler.car().addDriveTrain();
   }

}

class WheelRobot extends Robot
{
   WheelRobot(RobotPool pool)
   {
      super(pool);
   }

   @Override
   protected void performService()
   {
      System.out.println(this + " installing Wheels");
      assembler.car().addWheels();
   }

}

class RobotPool
{
   // Предотвращение одинаковых записей:
   private Set<Robot> pool = new HashSet<>();

   synchronized void add(Robot r)
   {
      pool.add(r);
      notifyAll();
   }

   synchronized void hire(Class<? extends Robot> robotType, Assembler d) throws InterruptedException
   {
      for (Robot r : pool)
      {
         if (r.getClass().equals(robotType))
         {
            pool.remove(r);
            r.assignAssembler(d);
            r.engage(); // Увеличиваем можность для выполнения задачи
            return;
         }
      }
      wait(); // Нет доступных данных
      hire(robotType, d); // Пробуем снова
   }

   synchronized void release(Robot r)
   {
      add(r);
   }

}

public class CarBuilder
{
   public static void main(String[] args) throws Exception
   {
      CarQueue chassisQueue = new CarQueue(),
        finishingQueue = new CarQueue();
      ExecutorService exec = Executors.newCachedThreadPool();
      RobotPool robotPool = new RobotPool();
      exec.execute(new EngineRobot(robotPool));
      exec.execute(new DriveTrainRobot(robotPool));
      exec.execute(new WheelRobot(robotPool));
      exec.execute(new Assembler(
        chassisQueue, finishingQueue, robotPool));
      exec.execute(new Reporter(finishingQueue));
      // Запустить все для производства шасси:
      exec.execute(new ChassisBuilder(chassisQueue));
      TimeUnit.SECONDS.sleep(7);
      exec.shutdownNow();
   }

}
