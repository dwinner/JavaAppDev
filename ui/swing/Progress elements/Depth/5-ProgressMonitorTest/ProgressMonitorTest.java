
/**
 * Мониторы задач.
 * <p/>
 * @version 1.03 2004-08-22
 * @author Cay Horstmann
 */
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Программа, демонстрирующая использование монитора задачи.
 */
public class ProgressMonitorTest
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new ProgressMonitorFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Фрейм, содержащий кнопку, предназначенную для запуска имитатора задачи, и текстовую область, в которой отображаются
 * сведения о работе имитатора.
 */
class ProgressMonitorFrame extends JFrame
{
    private Timer activityMonitor;
    private JButton startButton;
    private ProgressMonitor progressDialog;
    private JTextArea textArea;
    private Thread activityThread;
    private SimulatedActivity activity;
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;

    ProgressMonitorFrame()
    {
        setTitle("ProgressMonitorTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // Текстовая область для вывода информации, связанной с работой имитатора
        textArea = new JTextArea();

        // Панель с кнопкой.
        JPanel panel = new JPanel();
        startButton = new JButton("Start");
        panel.add(startButton);

        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        // Обработчик событий для кнопки.

        startButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                // Запуск имитатора.
                activity = new SimulatedActivity(1000);
                activityThread = new Thread(activity);
                activityThread.start();

                // Запуск монитора задачи.
                progressDialog = new ProgressMonitor(
                    ProgressMonitorFrame.this,
                    "Waiting for Simulated Activity",
                    null,
                    0,
                    activity.getTarget());

                // Запуск таймера.
                activityMonitor.start();

                startButton.setEnabled(false);
            }
        });

        // Обработчик событий для таймера.

        activityMonitor = new Timer(500, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                int current = activity.getCurrent();

                // Вывод индикатора хода процесса.
                textArea.append(current + "\n");
                progressDialog.setProgress(current);

                // Проверка, была ли задача завершена или прервана.
                if (current == activity.getTarget() || progressDialog.isCanceled())
                {
                    activityMonitor.stop();
                    progressDialog.close();
                    activityThread.interrupt();
                    startButton.setEnabled(true);
                }
            }
        });
    }
}

/**
 * Класс имитатора выполнения задачи.
 */
class SimulatedActivity implements Runnable
{
    private volatile int current;
    private int target;

    /**
     * Создание потока для имитатора выполнения задачи. В потоке происходит увеличение значения счетчика от 0 до заранее
     * оговоренной величины.
     * <p/>
     * @param t Конечное значение счетчика.
     */
    SimulatedActivity(int t)
    {
        current = 0;
        target = t;
    }

    public int getTarget()
    {
        return target;
    }

    public int getCurrent()
    {
        return current;
    }

    @Override
    public void run()
    {
        try
        {
            while (current < target)
            {
                Thread.sleep(100);
                current++;
            }
        }
        catch (InterruptedException e)
        {
        }
    }
}
