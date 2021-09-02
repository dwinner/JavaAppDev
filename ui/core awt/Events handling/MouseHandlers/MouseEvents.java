// ������������� ����������� ������� ����.
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/*
 * <applet code="MouseEvents" width="300" height="100"> </applet>
 */
public class MouseEvents extends Applet implements MouseListener, MouseMotionListener
{
    private String msg = "";
    private int
       mouseX = 0,
       mouseY = 0;	// ���������� ����

    @Override
    public void init()
    {
        addMouseListener(this);			// ����������� (����) ��� ����� ������������� mouse-�������
        addMouseMotionListener(this);	// ����������� (����) ��� ����� ������������� MouseMotion-�������
    }

    // ��������� ������ ����
    @Override
    public void mouseClicked(MouseEvent me)
    {
        mouseX = 0;
        mouseY = 10;
        msg = "Mouse clicked.";
        repaint();
    }

    // ��������� ����� ���� � ������� ����.
    @Override
    public void mouseEntered(MouseEvent me)
    {
        // ��������� ����������
        mouseX = 0;
        mouseY = 10;
        msg = "Mouse entered.";
        repaint();
    }

    // ��������� ������ ���� �� ������� ����.
    @Override
    public void mouseExited(MouseEvent me)
    {
        // ��������� ����������
        mouseX = 0;
        mouseY = 10;
        msg = "Mouse exited.";
        repaint();
    }

    // ��������� ������� ������.
    @Override
    public void mousePressed(MouseEvent me)
    {
        // ��������� ����������
        mouseX = me.getX();
        mouseY = me.getY();
        msg = "Down";
        repaint();
    }

    // ��������� ������������ ������.
    @Override
    public void mouseReleased(MouseEvent me)
    {
        // ��������� ����������
        mouseX = me.getX();
        mouseY = me.getY();
        msg = "Up";
        repaint();
    }

    // ��������� �������������� ����.
    @Override
    public void mouseDragged(MouseEvent me)
    {
        // ��������� ����������
        mouseX = me.getX();
        mouseY = me.getY();
        msg = "*";
        showStatus("Dragging mouse at " + mouseX + ", " + mouseY);
        repaint();
    }

    // ��������� ����������� ����.
    @Override
    public void mouseMoved(MouseEvent me)
    {
        // ���������� ���������
        showStatus("Moving mouse at " + me.getX() + ", " + me.getY());
    }

    // ������� msg � ���� ������� � ��������� (x, y)-���������.
    @Override
    public void paint(Graphics g)
    {
        g.drawString(msg, mouseX, mouseY);
    }

}
