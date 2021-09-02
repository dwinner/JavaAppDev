// �������� �� ��������� ��������: ImageObserver
import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;

public class ObservedImageLoad extends Applet
{
    private Image img;
    private boolean error = false;
    private String imgname;

    @Override
    public void init()
    {
        setBackground(Color.blue);
        imgname = getParameter("img");
        img = getImage(getDocumentBase(), imgname);
    }

    @Override
    public void paint(Graphics g)
    {
        if (error)
        {
            Dimension d = getSize();
            g.setColor(Color.red);
            g.fillRect(0, 0, d.width, d.height);
            g.setColor(Color.black);
            g.drawString("Image not found: " + imgname, 10, d.height / 2);
        }
        else
        {
            g.drawImage(img, 0, 0, this);
        }
    }

    @Override
    public void update(Graphics g)
    {
        paint(g);
    }

    @Override
    public boolean imageUpdate(Image img, int flags, int x, int y, int w, int h)
    {
        if ((flags & SOMEBITS) != 0)
        {	// ����� ��������� ������
            repaint(x, y, w, h);	// ������ ����� �������
        }
        else if ((flags & ABORT) != 0)
        {
            error = true;	// ���� �� ������
            repaint();	// �������� ���� ������
        }
        return (flags & (ALLBITS | ABORT)) == 0;
    }
    // ����... ���� ����������� �� ����� ��������� ��������� ����� 
    // ������������ �������� ��� � ����� ������������ ������������
	/*
     * public boolean imageUpdate(Image img, int flags, int x, int y, int w, int h) { if ((flags & ALLBITS) != 0) {
     * repaint(x, y, w, h); } else if ((flags & (ABORT|ERROR)) != 0) { error = true;	// ���� �� ������ repaint(); }
     * return (flags & (ALLBITS|ABORT|ERROR)) == 0; }
     */
}