// Класс MemoryImageSource, модель производителя
import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.MemoryImageSource;

public class MemoryImageGenerator extends Applet
{
    private Image img;

    @Override
    public void init()
    {
        Dimension d = getSize();
        int w = d.width;
        int h = d.height;
        int pixels[] = new int[w * h];
        int i = 0;

        for (int y = 0; y < h; y++)
        {
            for (int x = 0; x < w; x++)
            {
                int r = (x ^ y) & 0xff;
                int g = (x * 2 ^ y * 2) & 0xff;
                int b = (x * 4 ^ y * 4) & 0xff;
                pixels[i++] = (255 << 24) | (r << 16) | (g << 8) | b;
            }
        }
        img = createImage(new MemoryImageSource(w, h, pixels, 0, w));
    }

    @Override
    public void paint(Graphics g)
    {
        g.drawImage(img, 0, 0, this);
    }
}