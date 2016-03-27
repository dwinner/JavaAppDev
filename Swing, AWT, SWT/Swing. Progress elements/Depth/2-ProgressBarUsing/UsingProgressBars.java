// Использование основных возможностей компонента JProgressBar
import javax.swing.*;

public class UsingProgressBars extends JFrame
{
    // Будем использовать общую модель
    private BoundedRangeModel model;

    public UsingProgressBars()
    {
        super("UsingProgressBars");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Создаем стандартную модель
        model = new DefaultBoundedRangeModel(5, 0, 0, 100);
        
        // Горизонтальный индикатор
        JProgressBar progress1 = new JProgressBar(model);
        progress1.setStringPainted(true);
        
        // Вертикальный индикатор
        JProgressBar progress2 = new JProgressBar(JProgressBar.VERTICAL);
        progress2.setModel(model);
        progress2.setStringPainted(true);
        progress2.setString("Немного терпения");
        
        // Добавляем индикаторы в окно
        JPanel contents = new JPanel();
        contents.add(progress1);
        contents.add(progress2);
        
        // Выводим окно на экран
        setContentPane(contents);
        setSize(400, 300);
        setVisible(true);
        
        // Запускаем процесс
        new LongProcess().start();
    }

    private class LongProcess extends Thread
    {
        @Override
        public void run()
        {
            while (model.getValue() < model.getMaximum())
            {
                try
                {
                    // Увеличиваем текущее значение
                    model.setValue(model.getValue() + 1);
                    // Случайная задержка
                    Thread.sleep((int) (Math.random() * 1000));
                }
                catch (InterruptedException ie)
                {
                    ie.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args)
    {
        new UsingProgressBars();
    }
}