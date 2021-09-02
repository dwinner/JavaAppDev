// ������������� �������� ���������� ����.
import java.awt.*;
import java.awt.event.*;

// ������� �������� ������ Frame.
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

// ������� �����-����
public class FileDialogDemo
{
    public static void main(String args[])
    {
        Frame f = new SampleFrame("File Dialog Demo");
        f.setVisible(true);
        f.setSize(100, 100);

        FileDialog fd = new FileDialog(f, "File Dialog");
        fd.setVisible(true);
    }
}