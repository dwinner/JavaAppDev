import java.util.Arrays;
import java.util.Comparator;
import java.util.concurrent.Semaphore;

/**
 * Этот исполняемый класс реализует алгоритм сортировки.
 * При сравнении двух элементов приостанавливается и обновляет компонент.
 * @author dwinner@inbox.ru
 */
public class Sorter implements Runnable
{
    private Double[] values;
    private ArrayComponent component;
    private Semaphore gate;
    private static final int DELAY = 100;
    private volatile boolean run;
    private static final int VALUES_LENGTH = 30;
    
    /**
     * Конструктор класса Sorter.
     * @param comp Компонент, отображающий ход сортировки.
     */
    public Sorter(ArrayComponent comp)
    {
        values = new Double[VALUES_LENGTH];
        for (int i = 0; i < values.length; i++)
        {
            values[i] = new Double(Math.random());
        }
        this.component = comp;
        this.gate = new Semaphore(1);
        this.run = false;
    }
    
    /**
     * Устанавливает режим "run". Вызывается в потоке диспетчера событий.
     */
    public void setRun()
    {
        run = true;
        gate.release();
    }
    
    /**
     * Устанавливает режим "step". Вызывается в потоке диспетчера событий.
     */
    public void setStep()
    {
        run = false;
        gate.release();
    }

    @Override
    public void run()
    {
        Comparator<Double> comp = new Comparator<Double>()
        {
            @Override
            public int compare(Double i1, Double i2)
            {
                component.setValues(values, i1, i2);
                try
                {
                    if (run)
                    {
                        Thread.sleep(DELAY);
                    }
                    else
                    {
                        gate.acquire();
                    }
                }
                catch (InterruptedException e)
                {
                    Thread.currentThread().interrupt();
                }
                return i1.compareTo(i2);
            }
        };
        Arrays.sort(values, comp);
        component.setValues(values, null, null);
    }
    
}
