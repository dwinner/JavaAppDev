// В этой версии для отображения времени, прошедшего с момента запуска секундомера,
// используется отдельный поток.

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class ThreadStopWatch
{
    private JLabel jlab; // Отображение времени, прошедшего с момента запуска секундомера.
    private JButton jbtnStart; // Запуск секундомера.
    private JButton jbtnStop;  // Остановка секундомера.
    private long start; // Время запуска секундомера, выраженное в миллисекундах.
    private boolean running = false; // Значение true, если секундомер запущен.
    // Ссылка на поток, отслеживающий время, прошедшее с момента запуска секундомера.
    private Thread thrd;

    public ThreadStopWatch()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Thread-based Stopwatch");

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

        // Первоначально доступ к кнопке Stop запрещен.
        jbtnStop.setEnabled(false);

        // Создание экземпляра класса Runnable, выполняющегося в отдельном потоке.
        Runnable myThread = new Runnable()
        {
            // Данный метод выполняется в отдельном потоке.
            @Override
            public void run()
            {
                try
                {
                    // Отображение времени десять раз в секунду.
                    for (;;)
                    {
                        // Пауза длительностью в одну десятую секунды.
                        Thread.sleep(100);
                        // Вызов метода updateTime() в потоке обработки событий.
                        SwingUtilities.invokeLater(new Runnable()
                        {
                            @Override
                            public void run()
                            {
                                updateTime();
                            }

                        });
                    }
                }
                catch (InterruptedException exc)
                {
                    System.out.println("Call to sleep was interrupted.");
                    System.exit(1);
                }
            }

        };

        // Создание нового потока.
        thrd = new Thread(myThread);

        // Запуск потока.
        thrd.start();

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
                running = true;
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
                running = false;
            }

        });

        // Включение кнопок и метки в состав панели содержимого.
        jfrm.getContentPane().add(jbtnStart);
        jfrm.getContentPane().add(jbtnStop);
        jfrm.getContentPane().add(jlab);

        // Отображение фрейма.
        jfrm.setVisible(true);
    }

    // Обновление значения времени, прошедшего с момента запуска секундомера.
    // Данный метод выполняется в потоке обработки событий.
    private void updateTime()
    {
        if ( ! running)
        {
            return;
        }
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
                new ThreadStopWatch();
            }

        });
    }

}