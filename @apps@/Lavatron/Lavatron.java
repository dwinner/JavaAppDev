import java.applet.Applet;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.MediaTracker;
import java.awt.image.MemoryImageSource;
import java.awt.image.PixelGrabber;
import java.util.logging.Logger;

public class Lavatron extends Applet implements Runnable {
    
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(Lavatron.class.getName());
    
    private int scrollX;
    private int bulbsW, bulbsH;
    private int bulbS = 8;
    private Dimension d;
    private Image offscreen, bulb, img;
    private Graphics offgraphics;
    private int pixels[];
    private int pixscan;
    private IntHash clut = new IntHash();
    private boolean stopFlag;
    private Thread t;
    
    @Override
    public void init() {
        d = getSize();
        int offw = (int) Math.ceil(d.width/bulbS) * bulbS;
        int offh = (int) Math.ceil(d.height/bulbS) * bulbS;
        offscreen = createImage(offw, offh);
        offgraphics = offscreen.getGraphics();
        bulbsW = offw/bulbS;
        bulbsH = offh/bulbS;
        
        bulb = createBulbs(bulbS, bulbsH*bulbS);
        try {
            img = getImage(getDocumentBase(), getParameter("img"));
            MediaTracker tr = new MediaTracker(this);
            tr.addImage(img, 0);
            tr.waitForID(0);
            pixscan = img.getWidth(null);
            int h = img.getHeight(null);
            pixels = new int[pixscan * h];
            PixelGrabber pg = new PixelGrabber(img, 0, 0, pixscan, h, pixels, 0, pixscan);
            pg.grabPixels();
        }
        catch (InterruptedException e) {
            
        }
        scrollX = 0;
        // Рисование черных лампочек во внеэкранном изображении
        offgraphics.setColor(Color.black);
        offgraphics.fillRect(0, 0, d.width, d.height);
        for (int x = 0; x < bulbsW; x++) {
            offgraphics.drawImage(bulb, x*bulbS, 0, null);
        }
    }
    
    private Image createBulbs(int w, int h) {
        int lpixels[] = new int[w*h];
        int bulbBits[] = {
            0,0,1,1,1,1,0,0,
            0,1,2,1,1,1,1,0,
            1,2,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,
            1,1,1,1,1,1,1,1,
            0,1,1,1,1,1,1,0,
            0,0,1,1,1,1,0,0
        };
        int bulbCLUT[] = {
            0xff000000,
            0x00c0c0c0,
            0xffffffff
        };
        for (int i = 0; i < w*h; i++) {
            pixels[i] = bulbCLUT[bulbBits[i%bulbBits.length]];
        }
        return createImage(new MemoryImageSource(w, h, pixels, 0, w));
    }
    
    @SuppressWarnings({"FinalMethod", "NestedAssignment"})
    public final Color color(int x, int y) {
        int p = pixels[y*pixscan + x];
        Color c;
        if ((c = (Color) clut.get(p)) == null) {
            clut.put(p, c = new Color(p));
        }
        return c;
    }
    
    public void update() {
        
    }
    
    @Override
    public void paint(Graphics g) {
        offgraphics.copyArea(bulbS, 0, bulbsW*bulbS-bulbS, d.height, -bulbS, 0);
        for (int y = 0; y < bulbsH; y++) {
            offgraphics.setColor(color(scrollX, y));
            offgraphics.fillRect(d.width-bulbS, y*bulbS, bulbS, bulbS);
        }
        offgraphics.drawImage(bulb, d.width-bulbS, 0, null);
        g.drawImage(offscreen, 0, 0, null);
        scrollX = (scrollX + 1) % pixscan;
    }

    @SuppressWarnings("CallToThreadYield")
    public void run() {
        while (true) {
            paint(getGraphics());
            try {
                Thread.yield();
            }
            catch (Exception e) {
                
            }
            if (stopFlag) {
                break;
            }
        }
    }
    
    @Override
    public void start() {
        t = new Thread(this);
        t.setPriority(Thread.MIN_PRIORITY);
        stopFlag = false;
        t.start();
    }
    
    @Override
    public void stop() {
        stopFlag = true;
    }
    
}

@SuppressWarnings("MultipleTopLevelClassesInFile")
class IntHash {
    
    private static final Logger LOG = Logger.getLogger(IntHash.class.getName());
    
    private int capacity;
    private int size;
    private float load = 0.7F;
    private int keys[];
    private Object vals[];
    
    IntHash(int n) {
        capacity = n;
        size = 0;
        keys = new int[n];
        vals = new Object[n];
    }
    
    IntHash() {
        this(101);
    }
    
    private void rehash() {
        int newcapacity = capacity * 2 + 1;
        Object newvals[] = new Object[newcapacity];
        int newkeys[] = new int[newcapacity];
        for (int i = 0; i < capacity; i++) {
            Object o = vals[i];
            if (o != null) {
                int k = keys[i];
                int newi = (k & 0x7fffffff) % newcapacity;
                while (newvals[newi] != null) {
                    newi = (newi + 1) % newcapacity;
                }
                newkeys[newi] = k;
                newvals[newi] = o;
            }
        }
        capacity = newcapacity;
        keys = newkeys;
        vals = newvals;
    }
    
    public void put(int k, Object o) {
        int i = (k & 0x7fffffff) % capacity;
        while (vals[i] != null && k != keys[i]) {   // хэш-коллизия
            i = (i + 1) % capacity;
        }
        if (vals[i] == null) {
            size++;
        }
        keys[i] = k;
        vals[i] = o;
        if (size > (int) (capacity * load)) {
            rehash();
        }
    }
    
    @SuppressWarnings("FinalMethod")
    public final Object get(int k) {
        int i = (k & 0x7fffffff) % capacity;
        while (vals[i] != null && k != keys[i]) {   // хэш потерян
            i = (i + 1) % capacity;
        }
        return vals[i];
    }
    
    @SuppressWarnings("FinalMethod")
    public final boolean contains(int k) {
        return get(k) != null;
    }
    
    public int size() {
        return size;
    }
    
    public int capacity() {
        return capacity;
    }
    
}