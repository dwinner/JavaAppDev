// Управление процессом загрузки при помощи MediaTracker.
import java.applet.Applet;
import java.awt.*;
import java.util.StringTokenizer;

public class TrackedImageLoad extends Applet implements Runnable
{
    private MediaTracker tracker;
    private int tracked;
    private int frame_rate = 5;
    private int current_img = 0;
    private Thread motor;
    private static final int MAXIMAGES = 10;
    private Image img[] = new Image[MAXIMAGES];
    private String name[] = new String[MAXIMAGES];
    private boolean stopFlag;

    @Override
    public void init()
    {
        tracker = new MediaTracker(this);
        StringTokenizer st = new StringTokenizer(getParameter("img"), "+");

        while (st.hasMoreTokens() && tracked <= MAXIMAGES)
        {
            name[tracked] = st.nextToken();
            img[tracked] = getImage(getDocumentBase(), name[tracked] + ".jpg");
            tracker.addImage(img[tracked], tracked);
            tracked++;
        }
    }

    @Override
    public void paint(Graphics g)
    {
        String loaded = "";
        int donecount = 0;

        for (int i = 0; i < tracked; i++)
        {
            if (tracker.checkID(i, true))
            {
                donecount++;
                loaded += name[i] + " ";
            }
        }

        Dimension d = getSize();
        int w = d.width;
        int h = d.height;

        if (donecount == tracked)
        {
            frame_rate = 1;
            Image i = img[current_img++];
            int iw = i.getWidth(null);
            int ih = i.getHeight(null);
            g.drawImage(i, (w - iw) / 2, (h - ih) / 2, null);
            if (current_img >= tracked)
            {
                current_img = 0;
            }
        }
        else
        {
            int x = w * donecount;  // Отслежено
            g.setColor(Color.black);
            g.fillRect(0, h / 3, x, 16);
            g.setColor(Color.white);
            g.fillRect(x, h / 3, w - x, 16);
            g.setColor(Color.black);
            g.drawString(loaded, 10, h / 2);
        }
    }

    @Override
    public void start()
    {
        motor = new Thread(this);
        stopFlag = false;
        motor.start();
    }

    @Override
    public void run()
    {
        motor.setPriority(Thread.MIN_PRIORITY);
        while (true)
        {
            repaint();
            try
            {
                Thread.sleep(1000 / frame_rate);
            }
            catch (InterruptedException e)
            {
            }
            if (stopFlag)
            {
                return;
            }
        }
    }
}