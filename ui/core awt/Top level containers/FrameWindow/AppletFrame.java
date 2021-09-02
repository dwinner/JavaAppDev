// ������� �������� �����-���� ������ �������.
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

/*
 * <applet code="AppletFrame" width="300" height="50">
 * </applet>
 */

// ������� �������� Frame.
class SampleFrame extends Frame
{
    SampleFrame(String title)
    {
        super(title);
        // ������� ������ ��� ��������� window-�������
        MyWindowAdapter adapter = new MyWindowAdapter(this);
        // �������������� ��� ��� ������ ���� �������
        addWindowListener(adapter);
    }

    @Override public void paint(Graphics g)
    {
        g.drawString("This is in frame window", 10, 40);
    }
}

class MyWindowAdapter extends WindowAdapter
{
    SampleFrame sampleFrame;

    public MyWindowAdapter(SampleFrame sampleFrame)
    {
        this.sampleFrame = sampleFrame;
    }

    @Override public void windowClosing(WindowEvent we)
    {
        sampleFrame.setVisible(false);
    }
}

// ������� �����-����.
public class AppletFrame extends Applet
{
    Frame f;

    @Override public void init()
    {
        f = new SampleFrame("A Frame Window");
        f.setSize(250, 250);
        f.setVisible(true);
    }

    @Override public void start()
    {
        f.setVisible(true);
    }

    @Override public void stop()
    {
        f.setVisible(false);
    }

    @Override public void paint(Graphics g)
    {
        g.drawString("This is in applet window", 10, 20);
    }
}