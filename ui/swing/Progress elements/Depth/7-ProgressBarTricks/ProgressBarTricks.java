// ��������� �������� ����������� ��������
import java.awt.Color;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;

public class ProgressBarTricks extends JFrame
{
    public ProgressBarTricks()
    {
        super("ProgressBar tricks");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ����������� ��������� ��� UI-��������������
        UIManager.put("ProgressBar.cellSpacing", new Integer(2));
        UIManager.put("ProgressBar.cellLength", new Integer(6));
        // ����������� ������
        final DefaultBoundedRangeModel model = new DefaultBoundedRangeModel(0, 0, 0, 100);
        // ������� ������� ��������� �������� �� ������ ���������� ������
        JProgressBar progress = new JProgressBar(model);
        progress.setBackground(Color.WHITE);
        // ��������� ��� � ����
        JPanel contents = new JPanel();
        contents.add(progress);
        setContentPane(contents);
        // ������� ���� �� �����
        setSize(100, 200);
        setVisible(true);
        // ������� �������������� �������
        Thread process = new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                // ����������� ������� �������� ������
                // �� ���������� ������������� ��������
                while (model.getValue() < model.getMaximum())
                {
                    model.setValue(model.getValue() + 1);
                    try
                    {
                        Thread.currentThread().sleep(200);
                    }
                    catch (InterruptedException ex)
                    {
                        Logger.getLogger(ProgressBarTricks.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                System.exit(0);
            }
        });
        // ��������� �����
        process.start();
    }

    public static void main(String[] args)
    {
        new ProgressBarTricks();
    }
}