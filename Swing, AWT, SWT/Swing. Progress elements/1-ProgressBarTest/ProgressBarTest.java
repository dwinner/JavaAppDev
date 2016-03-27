import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.*;

/**
 * Данная программа демонстрирует использование индикатора хода процесса для отображения сведений о
 * ходе выполнения задачи.
 * <p/>
 * @version 1.04 2007-08-01
 * @author Cay Horstmann
 */
public class ProgressBarTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
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
 * Фрейм, содержащий кнопку для запуска имитатора задачи, индикатор хода процесса и текстовую
 * область для вывода данных о выполнении задачи.
 * <p/>
 */
class ProgressBarFrame extends JFrame
{
    private JButton startButton;
    private JProgressBar progressBar;
    private JCheckBox checkBox;
    private JTextArea textArea;
    private SimulatedActivity activity;
    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 200;

    ProgressBarFrame() throws HeadlessException
    {
        setTitle("ProgressBarTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // В этой текстовой области отображаются данные, предоставляемые имитатором задачи.
        textArea = new JTextArea();

        // Создание панели и помещение в нее кнопки и индикатора хода процесса.

        final int MAX = 1000;
        JPanel panel = new JPanel();
        startButton = new JButton("Start");
        progressBar = new JProgressBar(0, MAX);
        progressBar.setStringPainted(true);
        panel.add(startButton);
        panel.add(progressBar);

        checkBox = new JCheckBox("Indeterminate");
        checkBox.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                progressBar.setIndeterminate(checkBox.isSelected());
                progressBar.setStringPainted(!progressBar.isIndeterminate());
            }
        });
        panel.add(checkBox);
        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        // Создание обработчика для кнопки.

        startButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                startButton.setEnabled(false);
                activity = new SimulatedActivity(MAX);
                activity.execute();
            }
        });
    }

    private class SimulatedActivity extends SwingWorker<Void, Integer>
    {
        private int current;
        private int target;

        /**
         * Создание объекта имитатора. Поток организует счетчик от 0 до заранее заданного значения.
         * <p/>
         * @param aTarget Величина, до которой увеличивается значение счетчика.
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
                    publish(current);
                }
                return null;
            }
            catch (InterruptedException intEx)
            {
                throw new RuntimeException(intEx);
            }
        }

        @Override
        protected void process(List<Integer> chunks)
        {
            for (Integer chunk : chunks)
            {
                textArea.append(chunk + "\n");
                progressBar.setValue(chunk);
            }
        }

        @Override
        protected void done()
        {
            startButton.setEnabled(true);
        }
    }
}