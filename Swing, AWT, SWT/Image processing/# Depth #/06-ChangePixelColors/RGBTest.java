/*
 * Изменение цвета всех пикселей.
 */

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.FilteredImageSource;
import java.awt.image.RGBImageFilter;

public class RGBTest extends Frame
{
    private Image img, newimg;

    public RGBTest(String s)
    {
        super(s);
        img = getToolkit().getImage("Linux-logo.jpg");
        RGBImageFilter rgb = new ColorFilter();
        newimg = createImage(new FilteredImageSource(img.getSource(), rgb));
        setSize(400, 350);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g)
    {
        g.drawImage(img, 10, 40, this);
        g.drawImage(newimg, 150, 40, this);
    }

    public static void main(String[] args)
    {
        Frame f = new RGBTest("Изменение цвета");
        f.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent ev)
            {
                System.exit(0);
            }
        });
    }
}

class ColorFilter extends RGBImageFilter
{
    ColorFilter()
    {
        canFilterIndexColorModel = true;
    }

    @Override
    public int filterRGB(int x, int y, int rgb)
    {
        return rgb >> 1;
    }
}