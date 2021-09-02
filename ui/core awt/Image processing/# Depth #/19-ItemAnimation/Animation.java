// Пример анимации изображений.
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.CropImageFilter;
import java.awt.image.FilteredImageSource;
import java.util.StringTokenizer;

public class Animation extends Applet implements Runnable
{
    private Image cell[];
    private final int MAXSEQ = 64;
    private int sequence[];
    private int nseq;
    private int idx;
    private int framerate;
    private boolean stopFlag;
    private Thread t;

    private int intDef(String s, int def)
    {
        int n = def;
        if (s != null)
        {
            try
            {
                n = Integer.parseInt(s);
            }
            catch (NumberFormatException e)
            {
            }
        }
        return n;
    }

    @Override
    public void init()
    {
        framerate = intDef(getParameter("framerate"), 5);
        int tilex = intDef(getParameter("cols"), 1);
        int tiley = intDef(getParameter("rows"), 1);
        cell = new Image[tilex * tiley];

        StringTokenizer st = new StringTokenizer(getParameter("sequence"), ",");
        sequence = new int[MAXSEQ];
        nseq = 0;
        while (st.hasMoreTokens() && nseq < MAXSEQ)
        {
            sequence[nseq] = intDef(st.nextToken(), 0);
            nseq++;
        }

        try
        {
            Image img = getImage(getDocumentBase(), getParameter("img"));
            @SuppressWarnings("LocalVariableHidesMemberVariable")
            MediaTracker t = new MediaTracker(this);
            t.addImage(img, 0);
            t.waitForID(0);
            int iw = img.getWidth(null);
            int ih = img.getHeight(null);
            int tw = iw / tilex;
            int th = ih / tiley;
            CropImageFilter f;
            FilteredImageSource fis;
            for (int y = 0; y < tiley; y++)
            {
                for (int x = 0; x < tilex; x++)
                {
                    f = new CropImageFilter(tw * x, th * y, tw, th);
                    fis = new FilteredImageSource(img.getSource(), f);
                    int i = y * tilex + x;
                    cell[i] = createImage(fis);
                    t.addImage(cell[i], i);
                }
            }
            t.waitForAll();
        }
        catch (InterruptedException e)
        {
        }
    }

    @Override
    public void update(Graphics g)
    {
    }

    @Override
    public void paint(Graphics g)
    {
        g.drawImage(cell[sequence[idx]], 0, 0, this);
    }

    @Override
    public void start()
    {
        t = new Thread(this);
        stopFlag = false;
        t.start();
    }

    @Override
    public void stop()
    {
        stopFlag = true;
    }

    @Override
    public void run()
    {
        idx = 0;
        while (true)
        {
            paint(getGraphics());
            idx = (idx + 1) % nseq;
            try
            {
                Thread.sleep(1000 / framerate);
            }
            catch (Exception e)
            {
            }
            if (stopFlag)
            {
                return;
            }
        }
    }
}