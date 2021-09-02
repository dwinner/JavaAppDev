// Обобщенная система управления

import java.util.*;

// Общие для всякого управляющего события методы.
abstract class Event
{
    private long eventTime;
    protected final long delayTime;

    public Event(long delayTime)
    {
        this.delayTime = delayTime;
        start();
    }

    public void start()
    {	// Позволяет перезапуск
        eventTime = System.nanoTime() + delayTime;
    }

    public boolean ready()
    {
        return System.nanoTime() >= eventTime;
    }

    public abstract void action();
}

// Обобщенная система управления
class Controller
{
    private List<Event> eventList = new ArrayList<Event>();

    public void addEvent(Event c)
    {
        eventList.add(c);
    }

    public void run()
    {
        while (eventList.size() > 0)
        {
            for (Event e : new ArrayList<Event>(eventList))
            {
                if (e.ready())
                {
                    System.out.println(e);
                    e.action();
                    eventList.remove(e);
                }
            }
        }
    }
}

/* Пример конкретного приложения на основе системы управления, все находится в
одном классе. Внутренние классы дают возможность инкапсулировать различную
функциональность для каждого отдельного события. */
class GreenhouseControls extends Controller
{
    private boolean light = false;

    public class LightOn extends Event
    {
        public LightOn(long delayTime)
        {
            super(delayTime);
        }

        public void action()
        {
            // Сюда помещается аппаратный вызов, выполняющий физическое включение света.
            light = true;
        }

        @Override public String toString()
        {
            return "The light is on!";
        }
    }

    public class LightOff extends Event
    {
        public LightOff(long delayTime)
        {
            super(delayTime);
        }

        public void action()
        {
            // Сюда помещается аппаратный вызов, выполняющий физическое выключение света
            light = false;
        }

        @Override public String toString()
        {
            return "The light is off!";
        }
    }

    private boolean water = false;

    public class WaterOn extends Event
    {
        public WaterOn(long delayTime)
        {
            super(delayTime);
        }

        public void action()
        {
            // Здесь размещается код включения системы полива
            water = true;
        }

        @Override public String toString()
        {
            return "Water is on";
        }
    }

    public class WaterOff extends Event
    {
        public WaterOff(long delayTime)
        {
            super(delayTime);
        }

        public void action()
        {
            // Здесь размещается код выключения системы полива
            water = false;
        }

        @Override public String toString()
        {
            return "Water is off";
        }
    }
    
    private String thermostat = "Day";

    public class ThermostatNight extends Event
    {
        public ThermostatNight(long delayTime)
        {
            super(delayTime);
        }

        public void action()
        {
            // Здесь размещается код управления оборудованием
            thermostat = "Night";
        }

        @Override public String toString()
        {
            return "Thermostat uses the night stat";
        }
    }

    public class ThermostatDay extends Event
    {
        public ThermostatDay(long delayTime)
        {
            super(delayTime);
        }

        public void action()
        {
            // Здесь размещается код управления оборудованием
            thermostat = "Day";
        }

        @Override public String toString()
        {
            return "Thermostat uses the day stat";
        }
    }

    // Пример метода action(), вставляющего самого себя в список событий.
    public class Bell extends Event
    {
        public Bell(long delayTime)
        {
            super(delayTime);
        }

        public void action()
        {
            addEvent(new Bell(delayTime));
        }

        @Override public String toString()
        {
            return "Boom!";
        }
    }

    public class Restart extends Event
    {
        private Event[] eventList;

        public Restart(long delayTime, Event[] eventList)
        {
            super(delayTime);
            this.eventList = eventList;
            for (Event e : eventList)
            {
                addEvent(e);
            }
        }

        public void action()
        {
            for (Event e : eventList)
            {
                e.start();	// Перезапуск каждый раз
                addEvent(e);
            }
            start();	// Возвращаем это событие Event
            addEvent(this);
        }

        @Override public String toString()
        {
            return "Restart of the system";
        }
    }

    public static class Terminate extends Event
    {
        public Terminate(long delayTime)
        {
            super(delayTime);
        }

        public void action()
        {
            System.exit(0);
        }

        public String toString()
        {
            return "Terminate";
        }
    }
}

// Настраивает и запускает систему управления (Args: 5000)
public class GreenhouseController
{
    public static void main(String[] args)
    {
        GreenhouseControls gc = new GreenhouseControls();
        /* Вместо жесткого кодирования фиксированных данных можно было
        бы считать информацию для настройки из текстового файла */
        gc.addEvent(gc.new Bell(900));
        Event[] eventList =
        {
            gc.new ThermostatNight(0),
            gc.new LightOn(200),
            gc.new LightOff(400),
            gc.new WaterOn(600),
            gc.new WaterOff(800),
            gc.new ThermostatDay(1400)
        };
        gc.addEvent(gc.new Restart(2000, eventList));
        if (args.length == 1)
        {
            gc.addEvent(new GreenhouseControls.Terminate(new Integer(args[0])));
        }
        gc.run();
    }
}
