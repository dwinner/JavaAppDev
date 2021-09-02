// ������� ��������� � AWT
import java.awt.Color;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class AWTPainting extends Frame
{
    public AWTPainting()
    {
        super("AWT Painting");
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent we)
            {
                System.exit(0);
            }

        });
        setSize(200, 200);
        setVisible(true);
    }

    // � ���� ������ ������������ ���������
    @Override
    public void paint(Graphics g)
    {
        // ��������� ��� ������� ������
        g.setColor(Color.red);
        g.fillRect(0, 0, 200, 200);
    }

    public static void main(String[] args)
    {
        new AWTPainting();
    }

}