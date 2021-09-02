
/**
 * �������� �����.
 * <p/>
 * @version 1.03 2004-08-22
 * @author Cay Horstmann
 */
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * ���������, ��������������� ������������� �������� ������.
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
 * �����, ���������� ������, ��������������� ��� ������� ��������� ������, � ��������� �������, � ������� ������������
 * �������� � ������ ���������.
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

        // ��������� ������� ��� ������ ����������, ��������� � ������� ���������
        textArea = new JTextArea();

        // ������ � �������.
        JPanel panel = new JPanel();
        startButton = new JButton("Start");
        panel.add(startButton);

        add(new JScrollPane(textArea), BorderLayout.CENTER);
        add(panel, BorderLayout.SOUTH);

        // ���������� ������� ��� ������.

        startButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                // ������ ���������.
                activity = new SimulatedActivity(1000);
                activityThread = new Thread(activity);
                activityThread.start();

                // ������ �������� ������.
                progressDialog = new ProgressMonitor(
                    ProgressMonitorFrame.this,
                    "Waiting for Simulated Activity",
                    null,
                    0,
                    activity.getTarget());

                // ������ �������.
                activityMonitor.start();

                startButton.setEnabled(false);
            }
        });

        // ���������� ������� ��� �������.

        activityMonitor = new Timer(500, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                int current = activity.getCurrent();

                // ����� ���������� ���� ��������.
                textArea.append(current + "\n");
                progressDialog.setProgress(current);

                // ��������, ���� �� ������ ��������� ��� ��������.
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
 * ����� ��������� ���������� ������.
 */
class SimulatedActivity implements Runnable
{
    private volatile int current;
    private int target;

    /**
     * �������� ������ ��� ��������� ���������� ������. � ������ ���������� ���������� �������� �������� �� 0 �� �������
     * ����������� ��������.
     * <p/>
     * @param t �������� �������� ��������.
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
