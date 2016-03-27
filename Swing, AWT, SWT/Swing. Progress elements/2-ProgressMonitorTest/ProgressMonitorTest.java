import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Программа, демонстрирующая использование монитора задачи.
 * <p/>
 * @version 1.04 2007-08-01
 * @author Cay Horstmann
 */
public class ProgressMonitorTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
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
 * Фрейм, содержащий кнопку, предназначенную для запуска имитатора задачи, и текстовую область, в
 * которой отображаются сведения о работе имитатора.
 */
class ProgressMonitorFrame extends JFrame
{
    private Timer cancelMonitor;
    private JButton startButton;
    private ProgressMonitor progressDialog;
    private JTextArea textArea;
    private SimulatedActivity activity;
    public static final int DEFAULT_WIDTH = 300;
    public static final int DEFAULT_HEIGHT = 200;

    ProgressMonitorFrame() throws HeadlessException
    {
        setTitle("ProgressMonitorTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // Текстовая область для вывода информации, связанной с работой имитатора.

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
            public void actionPerformed(ActionEvent actionEvent)
            {
                startButton.setEnabled(false);
                final int MAX = 1000;

                // Запуск имитатора.
                activity = new SimulatedActivity(MAX);
                activity.execute();

                // Запуск монитора задачи.
                progressDialog = new ProgressMonitor(ProgressMonitorFrame.this,
                    "Waiting for Simulated Activity",
                    null, 0, MAX);
                cancelMonitor.start();
            }
        });

        // Обработчик событий для таймера.
        cancelMonitor = new Timer(500, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                if (progressDialog.isCanceled())
                {
                    activity.cancel(true);
                    startButton.setEnabled(true);
                }
                else if (activity.isDone())
                {
                    progressDialog.close();
                    startButton.setEnabled(true);
                }
                else
                {
                    progressDialog.setProgress(10 * activity.getProgress());
                }
            }
        });
    }

    private class SimulatedActivity extends SwingWorker<Void, Integer>
    {
        private int current;
        private int target;

        /**
         * Создание потока для имитатора выполнения задачи. В потоке происходит увеличение значения
         * счетчика от 0 до заранее оговоренной величины.
         * <p/>
         * @param aTarget Конечное значение счетчика.
         */
        SimulatedActivity(int aTarget)
        {
            current = 0;
            target = aTarget;
        }

        @Override
        protected Void doInBackground() throws Exception
        {
            try
            {
                while (current < target)
                {
                    Thread.sleep(100);
                    current++;
                    textArea.append(current + "\n");
                    setProgress(current / 10);
                }
            }
            catch (InterruptedException e)
            {
            }
            return null;
        }
    }
}
