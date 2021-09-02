// ������������� ����������� ��������� �������.
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * <applet code="SimpleKey" width="300" height="100"> </applet>
 */
public class SimpleKey extends Applet implements KeyListener
{
    private String msg = "";
    private int
       X = 10,
       Y = 20;	// ���������� ������

    @Override
    public void init()
    {
        addKeyListener(this);	// ����������� ���� ��� ����� ������������� key-�������
        requestFocus();			// ������ ������ �����
    }

    @Override
    public void keyPressed(KeyEvent ke)
    {
        showStatus("Key Down");
    }

    @Override
    public void keyReleased(KeyEvent ke)
    {
        showStatus("Key Up");
    }

    @Override
    public void keyTyped(KeyEvent ke)
    {
        msg += ke.getKeyChar();
        repaint();
    }

    // ���������� ������� ������� � ������� ��������� ����.
    @Override
    public void paint(Graphics g)
    {
        g.drawString(msg, X, Y);
    }

}
