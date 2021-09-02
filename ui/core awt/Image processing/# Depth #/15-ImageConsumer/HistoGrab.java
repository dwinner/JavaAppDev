/*
 * ����� PixelGrabber, ������ �����������.
 */

import java.applet.Applet;
import java.awt.*;
import java.awt.image.PixelGrabber;

public class HistoGrab extends Applet
{
    private Dimension d;
    private Image img;
    private int iw, ih;
    private int pixels[];
    private int w, h;
    private int hist[] = new int[256];
    private int max_hist = 0;

    @Override
    public void init()
    {
        d = getSize();
        w = d.width;
        h = d.height;

        try
        {
            img = getImage(getDocumentBase(), getParameter("img"));
            MediaTracker t = new MediaTracker(this);
            t.addImage(img, 0);
            t.waitForID(0);
            iw = img.getWidth(null);
            ih = img.getHeight(null);
            pixels = new int[iw * ih];
            PixelGrabber pg = new PixelGrabber(img, 0, 0, iw, ih, pixels, 0, iw);
            pg.grabPixels();
        }
        catch (InterruptedException e)
        {
        }

        for (int i = 0; i < iw * ih; i++)
        {
            int p = pixels[i];
            int r = 0xff & (p >> 16);
            int g = 0xff & (p >> 8);
            int b = 0xff & (p);
            int y = (int) (.33 * r + .56 * g + .11 * b);
            hist[y]++;
        }

        for (int i = 0; i < 256; i++)
        {
            if (hist[i] > max_hist)
            {
                max_hist = hist[i];
            }
        }
    }

    @Override
    public void update(Graphics g)
    {
        super.update(g);
    }

    @Override
    public void paint(Graphics g)
    {
        g.drawImage(img, 0, 0, null);
        int x = (w - 256) / 2;
        int lasty = h - h * hist[0] / max_hist;
        for (int i = 0; i < 256; i++, x++)
        {
            int y = h - h * hist[i] / max_hist;
            g.setColor(new Color(i, i, i));
            g.fillRect(x, y, 1, h);
            g.setColor(Color.red);
            g.drawLine(x - 1, lasty, x, y);
            lasty = y;
        }
    }
}