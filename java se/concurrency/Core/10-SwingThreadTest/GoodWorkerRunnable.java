import java.awt.EventQueue;
import java.util.Random;
import javax.swing.JComboBox;

/**
 * Этот класс, реализующий Runnable, модифицирует случайным образом выпадающий
 * список, добавляя и удаляя числа. Чтобы обеспечить целостность выпадающего
 * списка, операции редактирования отправляются в поток диспетчера событий.
 */
public class GoodWorkerRunnable implements Runnable
{
    private JComboBox<Integer> comboBox;
    private Random generator;
    
    public GoodWorkerRunnable(JComboBox<Integer> aComboBox)
    {
        comboBox = aComboBox;
        generator = new Random();
    }

    @Override
    public void run()
    {
        try
        {
            while (true)
            {                
                EventQueue.invokeLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        int i = Math.abs(generator.nextInt());
                        if (i % 2 == 0)
                        {
                            comboBox.insertItemAt(i, 0);
                        }
                        else if (comboBox.getItemCount() > 0)
                        {
                            comboBox.removeItemAt(i % comboBox.getItemCount());
                        }
                    }
                });
                Thread.sleep(1);
            }
        }
        catch (InterruptedException e)
        {
            throw new RuntimeException(e);
        }
    }
    
}
