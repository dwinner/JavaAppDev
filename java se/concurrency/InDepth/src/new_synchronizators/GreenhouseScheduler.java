package new_synchronizators;

import java.util.*;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created with IntelliJ IDEA.
 * User: Denis
 * Date: 07.12.12
 * Time: 14:54
 * Планирование запуска задач.
 */
public class GreenhouseScheduler
{
   private volatile boolean light = false;
   private volatile boolean water = false;
   private String thermostat = "Day";

   public synchronized String getThermostat()
   {
      return thermostat;
   }

   public synchronized void setThermostat(String value)
   {
      thermostat = value;
   }

   final ScheduledThreadPoolExecutor scheduler = new ScheduledThreadPoolExecutor(10);

   public void schedule(Runnable event, long delay)
   {
      scheduler.schedule(event, delay, TimeUnit.MILLISECONDS);
   }

   public void repeat(Runnable event, long initialDelay, long period)
   {
      scheduler.scheduleAtFixedRate(event, initialDelay, period, TimeUnit.MILLISECONDS);
   }

   private class LightOn implements Runnable
   {
      @Override
      public void run()
      {
         // Сюда помещается аппаратный вызов, выполняющий физическое включение света
         System.out.println("The light is On");
         light = true;
      }
   }

   private class LightOff implements Runnable
   {
      @Override
      public void run()
      {
         // Сюда помещается аппаратный вызов, выполняющий физическое выключение света
         System.out.println("The light is Off");
         light = false;
      }
   }

   private class WaterOn implements Runnable
   {
      @Override
      public void run()
      {
         // Здесь размещается код включения системы полива.
         System.out.println("The water is On");
         water = true;
      }
   }

   private class WaterOff implements Runnable
   {
      @Override
      public void run()
      {
         // Здесь размещается код выключения системы полива
         System.out.println("The water is Off");
         water = false;
      }
   }

   private class ThermostatNight implements Runnable
   {
      @Override
      public void run()
      {
         // Здесь размещается код управления оборудованием
         System.out.println("The night mode is On");
         setThermostat("Night");
      }
   }

   private class ThermostatDay implements Runnable
   {
      @Override
      public void run()
      {
         // Здесь размещается код управления оборудованием
         System.out.println("The day mode is On");
         setThermostat("Day");
      }
   }

   private class Bell implements Runnable
   {
      @Override
      public void run()
      {
         System.out.println("Bum!");
      }
   }

   private class Terminate implements Runnable
   {
      @Override
      public void run()
      {
         System.out.println("Finish");
         scheduler.shutdownNow();
         // Для выполнения этой операции необходимо запустить
         // отдельную задачу, так как планировщик был отключен
         new Thread()
         {
            @Override
            public void run()
            {
               for (DataPoint d : data)
                  System.out.println(d);
            }
         }.start();
      }
   }

   // Коллекция данных
   private static class DataPoint
   {
      final Calendar time;
      final float temperature;
      final float humidity;

      private DataPoint(Calendar d, float temp, float hum)
      {
         time = d;
         temperature = temp;
         humidity = hum;
      }

      @Override
      public String toString()
      {
         return time.getTime() + String.format(" temperature: %1$.1f humidity: %2$.2f",
                                               temperature,
                                               humidity);
      }
   }

   private Calendar lastTime = Calendar.getInstance();
   {
      // Регулировка даты до получаса
      lastTime.set(Calendar.MINUTE, 30);
      lastTime.set(Calendar.SECOND, 00);
   }

   private float lastTemp = 65.0f;
   private int tempDirection = +1;
   private float lastHumidity = 50.0f;
   private int humidityDirection = +1;
   private Random rand = new Random(47);
   final List<DataPoint> data = Collections.synchronizedList(new ArrayList<DataPoint>());

   private class CollectData implements Runnable
   {
      @Override
      public void run()
      {
         System.out.println("Data collect");
         synchronized (GreenhouseScheduler.this)
         {
            lastTime.set(Calendar.MINUTE, lastTime.get(Calendar.MINUTE) + 30);
            // С вероятностью 1/5 происходит смена направления:
            if (rand.nextInt(5) == 4)
               tempDirection = -tempDirection;
            // Сохранить предыдущее значение:
            lastTemp = lastTemp + tempDirection * (1.0f + rand.nextFloat());
            if (rand.nextInt(5) == 4)
               humidityDirection = -humidityDirection;
            lastHumidity = lastHumidity + humidityDirection * rand.nextFloat();
            /* Объект Calendar необходимо клонировать, иначе
            все DataPoint будут содержать ссылки на одно и
            то же lastTime. Для базового объекта - такого как Calendar -
            вызова clone() вполне достаточно. */
            data.add(new DataPoint((Calendar) lastTime.clone(), lastTemp, lastHumidity));
         }
      }
   }

   public static void main(String[] args)
   {
      GreenhouseScheduler gh = new GreenhouseScheduler();
      gh.schedule(gh.new Terminate(), 5000);
      gh.repeat(gh.new Bell(), 0, 1000);
      gh.repeat(gh.new ThermostatNight(), 0, 2000);
      gh.repeat(gh.new LightOn(), 0, 200);
      gh.repeat(gh.new LightOff(), 0, 400);
      gh.repeat(gh.new WaterOn(), 0, 600);
      gh.repeat(gh.new WaterOff(), 0, 800);
      gh.repeat(gh.new ThermostatDay(), 0, 1400);
      gh.repeat(gh.new CollectData(), 500, 500);
   }

}
