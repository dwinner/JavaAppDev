// Небольшие хитрости индикаторов процесса
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class ProgressBarTricks extends JFrame
{
    public ProgressBarTricks()
    {
        super("ProgressBar tricks");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Настраиваем параметры для UI-представителей
        UIManager.put("ProgressBar.cellSpacing", new Integer(2));
        UIManager.put("ProgressBar.cellLength", new Integer(6));
        // Стандартная модель
        final DefaultBoundedRangeModel model = new DefaultBoundedRangeModel(0, 0, 0, 100);
        // Создаем простой индикатор процесса на основе полученной модели
        JProgressBar progress = new JProgressBar(model);
        progress.setBackground(Color.WHITE);
        // Добавляем его в окно
        JPanel contents = new JPanel();
        contents.add(progress);
        setContentPane(contents);
        // Выводим окно на экран
        setSize(100, 200);
        setVisible(true);
        // Создаем гипотетический процесс
        Thread process = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                // Увеличиваем текущее значение модели
                // до достижения максимального значения
                while (model.getValue() < model.getMaximum())
                {
                    model.setValue(model.getValue() + 1);
                    try
                    {
                        Thread.currentThread().sleep(200);
                    }
                    catch (InterruptedException ex)
                    {
                        Logger.getLogger(ProgressBarTricks.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                System.exit(0);
            }
        });
        // Запускаем поток
        process.start();
    }

    public static void main(String[] args)
    {
        new ProgressBarTricks();
    }
}