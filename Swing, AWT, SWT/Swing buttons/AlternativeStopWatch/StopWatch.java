// Простой секундомер.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class StopWatch implements ActionListener
{
    private JLabel jlab;
    private long start;	// Содержит время запуска в миллисекундах.
    private JButton jbtnAction;

    public StopWatch()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("A Simple Stopwatch");

        // Установка диспетчера компоновки FlowLayout
        jfrm.setLayout(new FlowLayout());

        // Установка исходного размера фрейма
        jfrm.setSize(230, 90);

        // Завершение программы при закрытии пользователем окна.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание одной кнопки для двух различных действий
        jbtnAction = new JButton("Start");

        // Связывание с кнопкой обработчика событий действия.
        jbtnAction.addActionListener(this);

        // Включение кнопки в состав панели содержимого.
        jfrm.add(jbtnAction);

        // Создание текстовой метки.
        jlab = new JLabel("Press Start to begin timing.");

        // Добавление метки к фрейму.
        jfrm.add(jlab);

        // Отображение фрейма.
        jfrm.setVisible(true);
    }

    // Поддержка событий, связанных с кнопкой.
    public void actionPerformed(ActionEvent ae)
    {
        Calendar cal = Calendar.getInstance();	// Получение текущего системного времени.
        if (ae.getActionCommand().equals("Start"))
        {
            start = cal.getTimeInMillis();
            jbtnAction.setText("Stop");
            jlab.setText("Stopwatch is running...");
        }
        else if (ae.getActionCommand().equals("Stop"))
        {
            jbtnAction.setText("Start");
            jlab.setText("Elapsed time is " + (double) (cal.getTimeInMillis() - start) / 1000);
        }
    }

    public static void main(String args[])
    {
        // Создание фрейма в потоке обработки событий.
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new StopWatch();
            }
        });
    }
}