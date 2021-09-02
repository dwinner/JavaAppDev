// ������ �� ���� Swing, � ������� ������������ ����� �����.

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/*
 * <object code="ScrollText" width="240" height="100"> </object>
 */
public class ScrollText extends JApplet
{
    private JLabel jlab;
    private String msg = " Swing makes the GUI move! ";
    private ActionListener scroller;
    private Timer stTimer;  // ������, ������������ �������� �����������.

    @Override
    public void init()
    {    // ������������� �������.
        try
        {
            SwingUtilities.invokeAndWait(new Runnable()
            {
                @Override
                public void run()
                {
                    guiInit();
                }

            });
        }
        catch (InterruptedException | InvocationTargetException exc)
        {
            System.out.println("Can't create because of " + exc);
        }
    }

    @Override
    public void start()
    {   // ����������� ������� ��� ������� �������.
        stTimer.start();
    }

    @Override
    public void stop()
    {    // ��������� ������� ��� ��������� �������
        stTimer.stop();
    }

    @Override
    public void destroy()
    {   // ��������� ������� ��� �������� �������.
        stTimer.stop();
    }

    private void guiInit()
    {   // ������������� ����������.
        // �������� ����� ��� ����������� ���������.
        jlab = new JLabel(msg);
        jlab.setHorizontalAlignment(SwingConstants.CENTER);

        // �������� ����������� ������� ��� �������.
        scroller = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                // ����������� ��������� ����� �� ���� ������.
                char ch = msg.charAt(0);
                msg = msg.substring(1, msg.length());
                msg += ch;
                jlab.setText(msg);
            }

        };

        // �������� �������.
        stTimer = new Timer(200, scroller);
        // ��������� ����� � ������ ������ ����������� �������.
        getContentPane().add(jlab);
    }

}