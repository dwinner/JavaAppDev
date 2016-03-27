import java.util.Random;
import javax.swing.JComboBox;

/**
 * Этот класс, реализующий Runnable, модифицирует случайным образом
 * выпадающий список, добавляя и удаляя числа. Это может вызвать
 * ошибки, потому что методы выпадающего списка не синхронизированы,
 * и к списку одновременно имеют доступ и диспетчер событий и рабочий
 * поток.
 */
public class BadWorkerRunnable implements Runnable
{
    private JComboBox<Integer> combo;
    private Random generator;
    
    public BadWorkerRunnable(JComboBox<Integer> aCombo)
    {
        combo = aCombo;
        generator = new Random();
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {                
                int i = Math.abs(generator.nextInt());
                if (i % 2 == 0)
                {
                    combo.insertItemAt(i, 0);
                }
                else if (combo.getItemCount() > 0)
                {
                    combo.removeItemAt(i % combo.getItemCount());
                }
                Thread.sleep(1);
            }
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }
    
}
