// Фильтрация изображений при помощи иерархии RGBImageFilter.
import java.applet.Applet;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.*;
import java.util.Hashtable;
import java.util.StringTokenizer;

public class ImageFilterDemo extends Applet implements ActionListener
{
    private Image img;
    private PlugInFilter pif;
    private Image fimg;
    private Image curImg;
    private LoadedImage lim;
    private Label lab;
    private Button reset;

    @Override
    public void init()
    {
        setLayout(new BorderLayout());
        Panel p = new Panel();
        add(p, BorderLayout.SOUTH);
        reset = new Button("Reset");
        reset.addActionListener(this);
        p.add(reset);
        StringTokenizer st = new StringTokenizer(getParameter("filters"), "+");
        while (st.hasMoreTokens())
        {
            Button b = new Button(st.nextToken());
            b.addActionListener(this);
            p.add(b);
        }

        lab = new Label("");
        add(lab, BorderLayout.NORTH);

        img = getImage(getDocumentBase(), getParameter("img"));
        lim = new LoadedImage(img);
        add(lim, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        String a = "";

        try
        {
            a = ae.getActionCommand();
            if (a.endsWith("Reset"))
            {
                lim.set(img);
                lab.setText("Normal");
            }
            else
            {
                pif = (PlugInFilter) Class.forName(a).newInstance();
                fimg = pif.filter(this, img);
                lim.set(fimg);
                lab.setText("Filtered: " + a);
            }
            repaint();
        }
        catch (ClassNotFoundException e)
        {
            lab.setText(a + " not found");
            lim.set(img);
            repaint();
        }
        catch (InstantiationException e)
        {
            lab.setText("could't new " + a);
        }
        catch (IllegalAccessException e)
        {
            lab.setText("no access: " + a);
        }
    }
}

interface PlugInFilter
{
    Image filter(Applet a, Image in);
}

class LoadedImage extends Canvas
{
    private Image img;

    LoadedImage(Image i)
    {
        set(i);
    }

    final void set(Image i)
    {
        MediaTracker mt = new MediaTracker(this);
        mt.addImage(i, 0);
        try
        {
            mt.waitForAll();
        }
        catch (InterruptedException e)
        {
        }
        img = i;
        repaint();
    }

    @Override
    public void paint(Graphics g)
    {
        if (img == null)
        {
            g.drawString("no image", 10, 30);
        }
        else
        {
            g.drawImage(img, 0, 0, this);
        }
    }

    public Dimension getPrefferedSize()
    {
        return new Dimension(img.getWidth(this), img.getHeight(this));
    }

    @Override
    public Dimension getMinimumSize()
    {
        return getPrefferedSize();
    }
}

class Grayscale extends RGBImageFilter implements PlugInFilter
{
    @Override
    public Image filter(Applet a, Image in)
    {
        return a.createImage(new FilteredImageSource(in.getSource(), this));
    }

    @Override
    public int filterRGB(int x, int y, int rgb)
    {
        int r = (rgb >> 16) & 0xff;
        int g = (rgb >> 8) & 0xff;
        int b = rgb & 0xff;
        int k = (int) (.56 * g + .33 * r + .11 * b);
        return (0xff000000 | k << 16 | k << 8 | k);
    }
}

class Invert extends RGBImageFilter implements PlugInFilter
{
    @Override
    public Image filter(Applet a, Image in)
    {
        return a.createImage(new FilteredImageSource(in.getSource(), this));
    }

    @Override
    public int filterRGB(int x, int y, int rgb)
    {
        int r = 0xff - (rgb >> 16) & 0xff;
        int g = 0xff - (rgb >> 8) & 0xff;
        int b = 0xff - rgb & 0xff;
        return (0xff000000 | r << 16 | g << 8 | b);
    }
}

class Contrast extends RGBImageFilter implements PlugInFilter
{
    private double gain = 1.2;

    @Override
    public Image filter(Applet a, Image in)
    {
        return a.createImage(new FilteredImageSource(in.getSource(), this));
    }

    private int multclamp(int in, double factor)
    {
        in = (int) (in * factor);
        return in > 255 ? 255 : in;
    }

    private int cont(int in)
    {
        return (in < 128) ? (int) (in / gain) : multclamp(in, gain);
    }

    @Override
    public int filterRGB(int x, int y, int rgb)
    {
        int r = cont((rgb >> 16) & 0xff);
        int g = cont((rgb >> 8) & 0xff);
        int b = cont(rgb & 0xff);
        return (0xff000000 | r << 16 | g << 8 | b);
    }
}

abstract class Convolver implements ImageConsumer, PlugInFilter
{
    private int width, height;

    public int getHeight()
    {
        return height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public int[] getImgpixels()
    {
        return imgpixels;
    }

    public void setImgpixels(int[] imgpixels)
    {
        this.imgpixels = imgpixels;
    }

    public int[] getNewimgpixels()
    {
        return newimgpixels;
    }

    public void setNewimgpixels(int[] newimgpixels)
    {
        this.newimgpixels = newimgpixels;
    }

    public int getWidth()
    {
        return width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }
    
    private int imgpixels[], newimgpixels[];

    abstract void convolve();   // здесь идет фильтр...

    @Override
    public Image filter(Applet a, Image in)
    {
        in.getSource().startProduction(this);
        waitForImage();
        newimgpixels = new int[width * height];

        try
        {
            convolve();
        }
        catch (Exception e)
        {
            System.out.println("Convolver failed: " + e);
            e.printStackTrace();
        }

        return a.createImage(new MemoryImageSource(width, height, newimgpixels, 0, width));
    }

    synchronized void waitForImage()
    {
        try
        {
            wait();
        }
        catch (Exception e)
        {
        }
    }

    @Override
    abstract public void setProperties(java.util.Hashtable dummy);

    @Override
    abstract public void setColorModel(ColorModel dummy);

    @Override
    abstract public void setHints(int dummy);

    @Override
    public synchronized void imageComplete(int dummy)
    {
        notifyAll();
    }

    public void setDimensions(int x, int y)
    {
        width = x;
        height = y;
        imgpixels = new int[x * y];
    }

    @Override
    public void setPixels(int x1, int y1, int w, int h, ColorModel model, byte pixels[], int off, int scansize)
    {
        int pix, x, y, x2, y2, sx, sy;

        x2 = x1 + w;
        y2 = y1 + h;
        sy = off;
        for (y = y1; y < y2; y++)
        {
            sx = sy;
            for (x = x1; x < x2; x++)
            {
                pix = model.getRGB(pixels[sx++]);
                if ((pix & 0xff000000) == 0)
                {
                    pix = 0x00ffffff;
                }
                imgpixels[y * width + x] = pix;
            }
            sy += scansize;
        }
    }

    @Override
    public void setPixels(int x1, int y1, int w, int h, ColorModel model, int pixels[], int off, int scansize)
    {
        int pix, x, y, x2, y2, sx, sy;

        x2 = x1 + w;
        y2 = y1 + h;
        sy = off;
        for (y = y1; y < y2; y++)
        {
            sx = sy;
            for (x = x1; x < x2; x++)
            {
                pix = model.getRGB(pixels[sx++]);
                if ((pix & 0xff000000) == 0)
                {
                    pix = 0x00ffffff;
                }
                imgpixels[y * width + x] = pix;
            }
            sy += scansize;
        }
    }
}

class Blur extends Convolver
{
    @Override
    public void convolve()
    {
        for (int y = 1; y < getHeight() - 1; y++)
        {
            for (int x = 1; x < getWidth() - 1; x++)
            {
                int rs = 0;
                int gs = 0;
                int bs = 0;

                for (int k = -1; k <= 1; k++)
                {
                    for (int j = -1; j <= 1; j++)
                    {
                        int rgb = getImgpixels()[(y + k) * getWidth() + x + j];
                        int r = (rgb >> 16) & 0xff;
                        int g = (rgb >> 8) & 0xff;
                        int b = rgb & 0xff;
                        rs += r;
                        gs += g;
                        bs += b;
                    }
                }

                rs /= 9;
                gs /= 9;
                bs /= 9;

                getImgpixels()[y * getWidth() + x] = (0xff000000 | rs << 16 | gs << 8 | bs);
            }
        }
    }

    @Override
    public void setProperties(Hashtable dummy)
    {
        // throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setColorModel(ColorModel dummy)
    {
        // throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setHints(int dummy)
    {
        // throw new UnsupportedOperationException("Not supported yet.");
    }
}

class Sharpen extends Convolver
{
    final int clamp(int c)
    {
        return (c > 255 ? 255 : (c < 0 ? 0 : c));
    }

    @Override
    public void convolve()
    {
        int ro = 0, go = 0, bo = 0;
        for (int y = 1; y < getHeight() - 1; y++)
        {
            for (int x = 1; x < getWidth() - 1; x++)
            {
                int rs = 0;
                int gs = 0;
                int bs = 0;

                for (int k = -1; k <= 1; k++)
                {
                    for (int j = -1; j <= 1; j++)
                    {
                        int rgb = getImgpixels()[(y + k) * getWidth() + x + j];
                        int r = (rgb >> 16) & 0xff;
                        int g = (rgb >> 8) & 0xff;
                        int b = rgb & 0xff;
                        if (j == 0 && k == 0)
                        {
                            ro = r;
                            go = g;
                            bo = b;
                        }
                        else
                        {
                            rs += r;
                            gs += g;
                            bs += b;
                        }
                    }
                }

                rs >>= 3;
                gs >>= 3;
                bs >>= 3;
                getNewimgpixels()[y * getWidth() + x]
                    = (0xff000000 | clamp(ro + ro - rs) << 16 | clamp(go + go - gs) << 8 | clamp(bo + bo - bs));
            }
        }
    }

    @Override
    public void setProperties(Hashtable dummy)
    {
        // throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setColorModel(ColorModel dummy)
    {
        // throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void setHints(int dummy)
    {
        // throw new UnsupportedOperationException("Not supported yet.");
    }
}