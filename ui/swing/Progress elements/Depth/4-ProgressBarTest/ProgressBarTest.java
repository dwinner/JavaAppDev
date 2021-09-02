
/**
 * Индикатор хода процесса.
 * <p/>
 * @version 1.03 2004-08-22
 * @author Cay Horstmann
 */
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Данная программа демонстрирует использование индикатора хода процесса для отображения сведений о ходе выполнения
 * задачи.
 */
public class ProgressBarTest
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new ProgressBarFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Фрейм, содержащий кнопку для запуска имитатора задачи, индикатор хода процесса и текстовую область для вывода данных
 * о выполнении задачи.
 */
class ProgressBarFrame extends JFrame
{
    private Timer activityMonitor;
    private JButton startButton;
    private JProgressBar progressBar;
    private JCheckBox checkBox;
    private JTextArea textArea;
    private SimulatedActivity activity;
    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 200;

    ProgressBarFrame()
    {
        setTitle("ProgressBarTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // В этой текстовой области отображаются данные, предоставляемые
        // имитатором задачи.
        textArea = new JTextArea();

        // Создание панели и помещение в нее кнопки и индикатора хода процесса

        JPanel panel = new JPanel();
        startButton = new JButton("Start");
        progressBar = new JProgressBar();
        progressBar.setStringPainted(true);
        panel.add(startButton);
        panel.add(progressBar);

        checkBox = new JCheckBox("indeterminate");
        checkBox.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                progressBar.setIndeterminate(checkBox.isSelected());
            }
        });
        panel.add(checkBox);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        // Создание обработчика для кнопки.

        startButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                progressBar.setMaximum(1000);
                activity = new SimulatedActivity(1000);
                new Thread(activity).start();
                activityMonitor.start();
                startButton.setEnabled(false);
            }
        });


        // Создание обработчика для таймера.

        activityMonitor = new Timer(500, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                int current = activity.getCurrent();

                // Отображение информации о ходе процесса.
                textArea.append(current + "\n");
                progressBar.setStringPainted(!progressBar.isIndeterminate());
                progressBar.setValue(current);

                // Проверка, завершена ли задача.
                if (current == activity.getTarget())
                {
                    activityMonitor.stop();
                    startButton.setEnabled(true);
                }
            }
        });
    }
}

/**
 * Поток имитатора выполнения задачи.
 */
class SimulatedActivity implements Runnable
{
    private volatile int current;
    private int target;

    /**
     * Создание объекта имитатора. Поток организует счетчик от 0 до заранее заданного значения
     * <p/>
     * @param t Величина, до которой увеличивается значение счетчика.
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
