// Демонстрирует файловое диалоговое окно.
import java.awt.*;
import java.awt.event.*;

// создать подкласс класса Frame.
class SampleFrame extends Frame
{
    SampleFrame(String title)
    {
        super(title);
        // создать объект для обработки window-событий
        MyWindowAdapter adapter = new MyWindowAdapter(this);
        // регистрировать его для приема этих событий
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

// создать фрейм-окно
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