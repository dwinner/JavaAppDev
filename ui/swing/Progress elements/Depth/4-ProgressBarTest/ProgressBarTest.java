
/**
 * ��������� ���� ��������.
 * <p/>
 * @version 1.03 2004-08-22
 * @author Cay Horstmann
 */
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * ������ ��������� ������������� ������������� ���������� ���� �������� ��� ����������� �������� � ���� ����������
 * ������.
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
 * �����, ���������� ������ ��� ������� ��������� ������, ��������� ���� �������� � ��������� ������� ��� ������ ������
 * � ���������� ������.
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

        // � ���� ��������� ������� ������������ ������, ���������������
        // ���������� ������.
        textArea = new JTextArea();

        // �������� ������ � ��������� � ��� ������ � ���������� ���� ��������

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

        // �������� ����������� ��� ������.

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


        // �������� ����������� ��� �������.

        activityMonitor = new Timer(500, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                int current = activity.getCurrent();

                // ����������� ���������� � ���� ��������.
                textArea.append(current + "\n");
                progressBar.setStringPainted(!progressBar.isIndeterminate());
                progressBar.setValue(current);

                // ��������, ��������� �� ������.
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
 * ����� ��������� ���������� ������.
 */
class SimulatedActivity implements Runnable
{
    private volatile int current;
    private int target;

    /**
     * �������� ������� ���������. ����� ���������� ������� �� 0 �� ������� ��������� ��������
     * <p/>
     * @param t ��������, �� ������� ������������� �������� ��������.
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
