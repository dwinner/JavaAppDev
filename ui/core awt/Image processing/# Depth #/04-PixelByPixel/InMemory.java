/*
 * Изображение в памяти.
 */

import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.MemoryImageSource;

public class InMemory extends Frame
{
    private int w = 100;
    private int h = 100;
    private int[] pix = new int[w * h];
    private Image img;

    public InMemory(String s)
    {
        super(s);
        int i = 0;
        for (int y = 0; y < h; y++)
        {
            int red = 255 * y / (h - 1);
            for (int x = 0; x < w; x++)
            {
                int green = 255 * x / (w - 1);
                pix[i++] = (255 << 24) | (red << 16) | (green << 8) | 128;
            }
        }
        setSize(250, 250);
        setVisible(true);
    }

    @Override
    public void paint(Graphics gr)
    {
        if (img == null)
        {
            img = createImage(new MemoryImageSource(w, h, pix, 0, w));
        }
        gr.drawImage(img, 50, 50, this);
    }

    public static void main(String[] args)
    {
        Frame f = new InMemory("Изображение в памяти");
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