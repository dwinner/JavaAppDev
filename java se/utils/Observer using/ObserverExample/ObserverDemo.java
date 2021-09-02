// Демонстрирует класс Observable и интерфейс Observer.
import java.util.*;

// это наблюдающий класс
class Watcher implements Observer
{
    public void update(Observable obj, Object arg)
    {
        System.out.println("Calling update(), count is " + ((Integer) arg).intValue());
    }
}

// это наблюдаемый класс
class BeingWatched extends Observable
{
    void counter(int period)
    {
        for (; period >= 0; period--) {
            setChanged();
            notifyObservers(new Integer(period));
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
                System.out.println("Sleep is interrupted");
            }
        }
    }
}

public class ObserverDemo
{
    public static void main(String args[])
    {
        BeingWatched observed = new BeingWatched();
        Watcher observing = new Watcher();

        // добавить наблюдающего в список наблюдателей observed object
        observed.addObserver(observing);
        observed.counter(10);
    }
}
