// ������������� �������� ������������ ���������� JProgressBar
import javax.swing.*;

public class UsingProgressBars extends JFrame
{
    // ����� ������������ ����� ������
    private BoundedRangeModel model;

    public UsingProgressBars()
    {
        super("UsingProgressBars");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ������� ����������� ������
        model = new DefaultBoundedRangeModel(5, 0, 0, 100);
        
        // �������������� ���������
        JProgressBar progress1 = new JProgressBar(model);
        progress1.setStringPainted(true);
        
        // ������������ ���������
        JProgressBar progress2 = new JProgressBar(JProgressBar.VERTICAL);
        progress2.setModel(model);
        progress2.setStringPainted(true);
        progress2.setString("������� ��������");
        
        // ��������� ���������� � ����
        JPanel contents = new JPanel();
        contents.add(progress1);
        contents.add(progress2);
        
        // ������� ���� �� �����
        setContentPane(contents);
        setSize(400, 300);
        setVisible(true);
        
        // ��������� �������
        new LongProcess().start();
    }

    private class LongProcess extends Thread
    {
        @Override
        public void run()
        {
            while (model.getValue() < model.getMaximum())
            {
                try
                {
                    // ����������� ������� ��������
                    model.setValue(model.getValue() + 1);
                    // ��������� ��������
                    Thread.sleep((int) (Math.random() * 1000));
                }
                catch (InterruptedException ie)
                {
                    ie.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args)
    {
        new UsingProgressBars();
    }
}