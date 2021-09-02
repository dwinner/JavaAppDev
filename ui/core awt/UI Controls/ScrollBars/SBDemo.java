// ������������� ������ ���������.
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class SBDemo extends Applet implements AdjustmentListener, MouseMotionListener
{
    String msg = "";
    Scrollbar vertSB, horzSB;

    @Override
    public void init()
    {
        int width = Integer.parseInt(getParameter("width"));
        int height = Integer.parseInt(getParameter("height"));

        vertSB = new Scrollbar(Scrollbar.VERTICAL, 0, 1, 0, height);
        horzSB = new Scrollbar(Scrollbar.HORIZONTAL, 0, 1, 0, width);

        add(vertSB);
        add(horzSB);

        // ������������������ ��� ������ adjustment-�������
        vertSB.addAdjustmentListener(this);
        horzSB.addAdjustmentListener(this);

        addMouseMotionListener(this);
    }

    public void adjustmentValueChanged(AdjustmentEvent ae)
    {
        repaint();
    }

    // �������� ������ ���������, ����� �������� �������������� ����
    public void mouseDragged(MouseEvent me)
    {
        int x = me.getX();
        int y = me.getY();
        vertSB.setValue(y);
        horzSB.setValue(x);
        repaint();
    }

    // ��������� ��� MouseMotionListener
    public void mouseMoved(MouseEvent me) { }

    // ���������� ������� �������� ����� ���������
    @Override
    public void paint(Graphics g)
    {
        msg = "Vertical: " + vertSB.getValue();
        msg += ", Horizontal: " + horzSB.getValue();
        g.drawString(msg, 6, 160);

        // �������� ������� drag-������� ����
        g.drawString("*", horzSB.getValue(), vertSB.getValue());
    }
}