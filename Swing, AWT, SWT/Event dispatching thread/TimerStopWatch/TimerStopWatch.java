// Версия программы-секундомера, использующая класс Timer.

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

public class TimerStopWatch
{
    private JLabel jlab; // Отображение времени, прошедшего с момента запуска секундомера.
    private JButton jbtnStart; // Запуск секундомера.
    private JButton jbtnStop;  // Остановка секундомера.
    private long start; // Время запуска секундомера, выраженное в миллисекундах.
    private Timer swTimer; // Для обновления значения времени используется класс Timer.

    public TimerStopWatch()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Timer-based Stopwatch");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начальных размеров фрейма.
        jfrm.setSize(230, 90);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание метки, отображающей время, прошедшее с момента запуска секундомера.
        jlab = new JLabel("Press Start to begin timing.");

        // Создание меток Start и Stop.
        jbtnStart = new JButton("Start");
        jbtnStop = new JButton("Stop");
        jbtnStop.setEnabled(false);

        // Обработчик, получающий уведомление о событии, сгенерированном таймером.
        ActionListener timerAL = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                updateTime();
            }

        };

        // Создание таймера, генерирующего события десять раз в секунду. Обработка событий
        // осуществляется обработчиком, указанным с помощью параметра timerAL.
        swTimer = new Timer(100, timerAL);

        // Связывание обработчиков событий с кнопками Start и Stop.
        jbtnStart.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                // Запись времени запуска таймера.
                start = Calendar.getInstance().getTimeInMillis();
                // Изменение состояния кнопок на обратное.
                jbtnStop.setEnabled(true);
                jbtnStart.setEnabled(false);
                // Запуск секундомера.
                swTimer.start();
            }

        });

        jbtnStop.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                long stop = Calendar.getInstance().getTimeInMillis();
                // Вычисление времени, прошедшего с момента запуска.
                jlab.setText("Elapsed time is " + (double) (stop - start) / 1000);
                // Изменение состояния кнопок на обратное.
                jbtnStart.setEnabled(true);
                jbtnStop.setEnabled(false);
                // Остановка секундомера.
                swTimer.stop();
            }

        });

        // Включение кнопок и метки в состав панели содержимого.
        jfrm.getContentPane().add(jbtnStart);
        jfrm.getContentPane().add(jbtnStop);
        jfrm.getContentPane().add(jlab);

        // Отображение фрейма.
        jfrm.setVisible(true);
    }

    // Обновление отображаемого значения времени. Обратите внимание на то,
    // что переменная running больше не нужна.
    private void updateTime()
    {
        long temp = Calendar.getInstance().getTimeInMillis();
        jlab.setText("Elapsed time is " + (double) (temp - start) / 1000);
    }

    public static void main(String args[])
    {
        // Фрейм создается в потоке обработки событий.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new TimerStopWatch();
            }

        });
    }

}