/*
 * Эффект устранения мерцаний.
 */

import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

public class DoubleBuffer extends Applet
{
    private int gap = 3;
    private int mx, my;
    private boolean flicker = true;
    private Image buffer = null;
    private int w, h;

    @Override
    public void init()
    {
        Dimension d = getSize();
        w = d.width;
        h = d.height;
        buffer = createImage(w, h);
        addMouseMotionListener(new MouseMotionAdapter()
        {
            @Override
            public void mouseDragged(MouseEvent me)
            {
                mx = me.getX();
                my = me.getY();
                flicker = false;
                repaint();
            }

            @Override
            public void mouseMoved(MouseEvent me)
            {
                mx = me.getX();
                my = me.getY();
                flicker = true;
                repaint();
            }
        });
    }

    @Override
    public void paint(Graphics g)
    {
        Graphics screengc = null;

        if (!flicker)
        {
            screengc = g;
            g = buffer.getGraphics();
        }

        g.setColor(Color.blue);
        g.fillRect(0, 0, w, h);

        g.setColor(Color.red);
        for (int i = 0; i < w; i += gap)
        {
            g.drawLine(i, 0, w - i, h);
        }
        for (int i = 0; i < h; i += gap)
        {
            g.drawLine(0, i, w, h - i);
        }

        g.setColor(Color.black);
        g.drawString("Press mouse button to double buffer", 10, h / 2);

        g.setColor(Color.yellow);
        g.fillOval(mx - gap, my - gap, gap * 2 + 1, gap * 2 + 1);

        if (!flicker)
        {
            screengc.drawImage(buffer, 0, 0, null);
        }
    }

    @Override
    public void update(Graphics g)
    {
        paint(g);
    }
}
