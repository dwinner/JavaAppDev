// Анимация изображений в памяти.
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.MemoryImageSource;

public class InMemory extends Frame
{
    private int w = 100, h = 100, count;
    private int[] pix = new int[w * h];
    private Image img;
    private MemoryImageSource mis;

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
        mis = new MemoryImageSource(w, h, pix, 0, w);
        // Задаем возможность анимации
        mis.setAnimated(true);
        img = createImage(mis);
        setSize(350, 300);
        setVisible(true);
    }

    @Override
    public void paint(Graphics gr)
    {
        gr.drawImage(img, 10, 30, this);
    }

    @Override
    public void update(Graphics g)
    {
        paint(g);
    }

    public void go()
    {
        while (count < 100)
        {
            int i = 0;
            // Изменяем массив пикселов по некоторому закону
            for (int y = 0; y < h; y++)
            {
                for (int x = 0; x < w; x++)
                {
                    pix[i++] =
                        (255 << 24) | (255 + 8 * count << 16) | (8 * count << 8) | 255 + 8 * count;
                }
            }
            // Уведомляеи потребителя об изменении
            mis.newPixels();
            try
            {
                Thread.sleep(100);
            }
            catch (InterruptedException e)
            {
            }
            count++;
        }
    }

    public static void main(String[] args)
    {
        InMemory f = new InMemory("Изображение в памяти");
        f.go();
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