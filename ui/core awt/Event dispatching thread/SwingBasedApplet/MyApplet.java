// ������� �� ���� Swing.

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/*
 * This HTML can be used to launch the applet: <object code="MyApplet" width="240" height="100">
 * </object>
 */
public class MyApplet extends JApplet
{
    private JButton jbtnOne;
    private JButton jbtnTwo;
    private JLabel jlab;

    // ������ ����� ���������� � ������ �������.
    @Override
    public void init()
    {
        try
        {
            SwingUtilities.invokeAndWait(new Runnable()
            {
                @Override
                public void run()
                {
                    guiInit(); // ������������� ������������ ����������������� ����������.
                }

            });
        }
        catch (InterruptedException | InvocationTargetException exc)
        {
            System.out.println("Can't create because of " + exc);
        }
    }

    // ������ ����� ���������� ����� init(). ����� ��������� � ���� ������������
    // ��� ��������� ������� �������.
    @Override
    public void start()
    {
        // � ������ ������� ���� ����� �� ������������.
    }

    // ������ ����� ���������� ��� ��������� �������.
    @Override
    public void stop()
    {
        // � ������ ������� ���� ����� �� ������������.
    }

    // ������ ����� ���������� ��� ���������� ������ �������.
    // ��� ��������� ����������� �����.
    @Override
    public void destroy()
    {
        // � ������ ������� ���� ����� �� ������������.
    }

    // ������������ � ������������� ����������������� ����������.
    private void guiInit()
    {
        // ��������� ���������� ���������� FlowLayout.
        setLayout(new FlowLayout());

        // �������� ���� ������ � �����.
        jbtnOne = new JButton("One");
        jbtnTwo = new JButton("Two");

        jlab = new JLabel("Press a button.");

        // ���������� � �������� ������������ �������.
        jbtnOne.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent le)
            {
                jlab.setText("Button One pressed.");
            }

        });

        jbtnTwo.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent le)
            {
                jlab.setText("Button Two pressed.");
            }

        });

        // ��������� ����������� � ������ ������ ����������� �������.
        getContentPane().add(jbtnOne);
        getContentPane().add(jbtnTwo);
        getContentPane().add(jlab);
    }

}