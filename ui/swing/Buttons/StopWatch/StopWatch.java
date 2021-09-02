// Простой секундомер.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;

public class StopWatch implements ActionListener
{
    private JLabel jlab;
    private long start;	// Содержит время запуска в миллисекундах.

    public StopWatch()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("A Simple Stopwatch");

        // Установка диспетчера компоновки FlowLayout
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка исходного размера фрейма
        jfrm.setSize(230, 90);

        // Завершение программы при закрытии пользователем окна.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание двух кнопок
        JButton jbtnStart = new JButton("Start");
        JButton jbtnStop = new JButton("Stop");

        // Связывание с кнопками обработчиков событий.
        jbtnStart.addActionListener(this);
        jbtnStop.addActionListener(this);

        // Включение кнопок в состав панели содержимого.
        jfrm.getContentPane().add(jbtnStart);
        jfrm.getContentPane().add(jbtnStop);

        // Создание текстовой метки.
        jlab = new JLabel("Press Start to begin timing.");

        // Добавление метки к фрейму.
        jfrm.getContentPane().add(jlab);

        // Отображение фрейма.
        jfrm.setVisible(true);
    }

    // Поддержка событий, связанных с кнопкой.
    public void actionPerformed(ActionEvent ae)
    {
        Calendar cal = Calendar.getInstance();	// Получение текущего системного времени.
        if (ae.getActionCommand().equals("Start"))
        {
            // Сохранение времени запуска.
            start = cal.getTimeInMillis();
            jlab.setText("Stopwatch is running...");
        }
        else
        {
            // Вычисление времени, прошедшего от запуска до остановки.
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