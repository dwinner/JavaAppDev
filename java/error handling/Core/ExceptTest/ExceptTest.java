import java.awt.EventQueue;
import java.awt.event.*;
import javax.swing.*;
import java.io.*;

/**
 * @version 1.33 2007-06-12
 * @author Cay Horstmann
 */
public class ExceptTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                ExceptTestFrame frame = new ExceptTestFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * �����, ���������� ������, ������� ������������� ��������� ���� ����������
 */
class ExceptTestFrame extends JFrame
{
    public ExceptTestFrame()
    {
        setTitle("ExceptTest");
        ExceptTestPanel panel = new ExceptTestPanel();
        add(panel);
        pack();
    }
}

/**
 * ������ � �����-��������������� ��� ������� ���������� ����,
 * ��������������� ��������� ��������� ��� ��������� ���������� ���� ����������
 */
class ExceptTestPanel extends Box
{
    private ButtonGroup group;
    private JTextField textField;
    private double[] a = new double[10];
   
    public ExceptTestPanel()
    {
        super(BoxLayout.Y_AXIS);
        group = new ButtonGroup();

        // ������� �����-������������� ��� ���������� ����

        addRadioButton("Integer divide by zero", new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                a[1] = 1 / (a.length - a.length);
            }
        });

        addRadioButton("Floating point divide by zero", new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                a[1] = a[2] / (a[3] - a[3]);
            }
        });

        addRadioButton("Array bounds out", new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                a[1] = a[10];
            }
        });

        addRadioButton("Bad cast", new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                a = (double[]) event.getSource();
            }
        });

        addRadioButton("Null pointer", new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                event = null;
                System.out.println(event.getSource());
            }
        });

        addRadioButton("sqrt(-1)", new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                a[1] = Math.sqrt(-1);
            }
        });

        addRadioButton("Overflow", new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                a[1] = 1000 * 1000 * 1000 * 1000;
                int n = (int) a[1];
            }
        });

        addRadioButton("No such file", new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                try
                {
                    InputStream in = new FileInputStream("woozle.txt");
                }
                catch (IOException e)
                {
                    textField.setText(e.toString());
                }
            }
        });

        addRadioButton("Throw unknown", new ActionListener()
        {
            public void actionPerformed(ActionEvent event)
            {
                throw new UnknownError();
            }
        });

        // ������� ��������� ���� ��� ����������� ��������������� ����������
        textField = new JTextField(30);
        add(textField);
    }

    /**
     * ��������� ������������� � �������� ���������� � ������.
     * ������������� ���������� � ������ actionPerformed
     * @param s ����� �����-�������������
     * @param listener ��������� ��� �����-�������������
     */
    private void addRadioButton(String s, ActionListener listener)
    {
        JRadioButton button = new JRadioButton(s, false)
        {
            // ������ �������� ���� ����� ��� ������� ������ ������� ��������
            // ����� �� �������������� ���, ����� ����������� ��������� ����������
            protected void fireActionPerformed(ActionEvent event)
            {
                try
                {
                    textField.setText("No exception");
                    super.fireActionPerformed(event);
                }
                catch (Exception e)
                {
                    textField.setText(e.toString());
                }
            }
        };

        button.addActionListener(listener);
        add(button);
        group.add(button);
    }
}
