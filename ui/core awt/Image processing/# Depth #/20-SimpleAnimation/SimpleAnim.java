// Последовательная анимация.
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class SimpleAnim extends Frame
{
    private Image[] img = new Image[10];
    private int count;

    SimpleAnim(String s)
    {
        super(s);
        MediaTracker tr = new MediaTracker(this);
        for (int k = 0; k < 10; k++)
        {
            img[k] = getToolkit().getImage("run" + (k + 1) + ".gif");
            tr.addImage(img[k], 0);
        }
        try
        {   // Ждём загрузки всех изображений
            tr.waitForAll();
        }
        catch (InterruptedException e)
        {
        }
        setSize(400, 300);
        setVisible(true);
    }

    @Override
    public void paint(Graphics g)
    {
        g.drawImage(img[count % 10], 0, 0, this);
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
            repaint();  // Выводим следующий кадр
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
        SimpleAnim f = new SimpleAnim("Простая анимация");
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