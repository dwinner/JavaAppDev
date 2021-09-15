import java.awt.Color;
import java.awt.Cursor;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.ImageObserver;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

/**
 * Главный класс апплета, управляющий анимацией переходов.
 * @author dwinner@inbox.ru
 */
public class DynamicBillboard extends java.applet.Applet implements Runnable {
    
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(DynamicBillboard.class.getName());
    
    private BillData[] billboards;
    private int current_billboard;
    private int next_billboard;
    
    private String[] transition_classes;
    private Thread thread = null;
    private Image image = null;
    private long delay = -1;
    private boolean mouse_inside_applet;
    private String link_target_frame;
    private boolean stopFlag;
    
    @Override
    public void init() {
        String s = getParameter("bgcolor");
        if (s != null) {
            Color color = new Color(Integer.parseInt(s.substring(1), 16));
            setBackground(color);
            getParent().setBackground(color);
            getParent().repaint();
        }
        billboards = new BillData[Integer.parseInt(getParameter("billboards"))];
        next_billboard = (int) (Math.random() * billboards.length);
        current_billboard = next_billboard;
        parseBillData();
    }
    
    @SuppressWarnings("CallToThreadDumpStack")
    private void parseBillData() {
        String s = getParameter("bill" + next_billboard);
        int field_end = s.indexOf(',');
        Image new_image = getImage(getDocumentBase(), s.substring(0, field_end));
        URL link;
        try {
            link = new URL(getDocumentBase(), s.substring(field_end + 1));
        } 
        catch (MalformedURLException ex) {
            ex.printStackTrace();
            link = getDocumentBase();
            LOG.log(Level.SEVERE, null, ex);
        }
        billboards[next_billboard] = new BillData(link, new_image);
        if (image == null) {
            image = new_image;
        }
        else {
            prepareImage(new_image, this);
            billboards[next_billboard].initPixels(getSize().width, getSize().height);
        }
    }
    
    private void finishInit() {
        if (delay != -1) {
            return;
        }
        delay = Long.parseLong(getParameter("delay"));
        link_target_frame = getParameter("target");
        if (link_target_frame == null) {
            link_target_frame = "_top";
        }
        
        String s = getParameter("transitions");
        int field_end = s.indexOf(',');
        
        int trans_count = Integer.parseInt(s.substring(0, field_end));
        transition_classes = new String[trans_count];
        for (--trans_count; trans_count > 0; --trans_count) {
            s = s.substring(field_end + 1);
            field_end = s.indexOf(',');
            transition_classes[trans_count] = s.substring(0, field_end);
        }
        transition_classes[0] = s.substring(field_end + 1);
        billboards[next_billboard].initPixels(getSize().width, getSize().height);
        mouse_inside_applet = false;
    }
    
    @Override
    public void paint(Graphics g) {
        g.drawImage(image, 0, 0, this);
    }
    
    @Override
    public void update(Graphics g) {
        paint(g);
    }
    
    @Override
    public void start() {
        next_billboard = current_billboard;
        image = billboards[current_billboard].getImage();
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        if (thread == null) {
            thread = new Thread(this);
            thread.start();
        }
    }
    
    @Override
    public void stop() {
        if (thread != null) {
            stopFlag = true;
        }
    }

    @Override
    @SuppressWarnings({"SleepWhileInLoop", "ValueOfIncrementOrDecrementUsed", "CallToThreadDumpStack"})
    public void run() {
        while ((checkImage(image, this) & ImageObserver.ALLBITS) == 0) {
            try {
                Thread.sleep(600);
            } 
            catch (InterruptedException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
        finishInit();
        addMouseListener(new MyMouseAdapter());
        addMouseMotionListener(new MyMouseMotionAdapter());
        
        int last_transition_type = -1;
        BillTransition transition;
        long next_billboard_time;
        
        while (true) {
            if (stopFlag) {
                return;
            }
            next_billboard_time = System.currentTimeMillis() + delay;
            current_billboard = next_billboard;
            if (++next_billboard >= billboards.length) {
                next_billboard = 0;
            }
            if (billboards[next_billboard] == null) {
                parseBillData();
                try {
                    Thread.sleep(120);
                } 
                catch (InterruptedException ex) {
                    LOG.log(Level.SEVERE, null, ex);
                }
            }
            int transition_type = (int) (Math.random() * (transition_classes.length - 1));
            if (transition_type >= last_transition_type) {
                ++transition_type;
            }
            last_transition_type = transition_type;
            
            try {
                String trans = transition_classes[last_transition_type];
                try {
                    transition = (BillTransition) Class.forName(trans).newInstance();
                } 
                catch (InstantiationException ex) {
                    LOG.log(Level.SEVERE, null, ex);
                    continue;
                } 
                catch (IllegalAccessException ex) {
                    LOG.log(Level.SEVERE, null, ex);
                    continue;
                }
            } 
            catch (ClassNotFoundException ex) {
                LOG.log(Level.SEVERE, null, ex);
                ex.printStackTrace();
                continue;
            }
            
            transition.init(this, billboards[current_billboard].getImage_pixels(), billboards[next_billboard].getImage_pixels());
            
            if (System.currentTimeMillis() < next_billboard_time) {
                try {
                    Thread.sleep(next_billboard_time - System.currentTimeMillis());
                } 
                catch (InterruptedException ex) {
                    LOG.log(Level.SEVERE, null, ex);
                }
            }
            
            Graphics g = getGraphics();
            for (int c = 0; c < transition.getCells().length; ++c) {
                image = transition.getCells()[c];
                g.drawImage(image, 0, 0, null);
                getToolkit().sync();
                try {
                    Thread.sleep(transition.getDelay());
                } 
                catch (InterruptedException ex) {
                    LOG.log(Level.SEVERE, null, ex);
                }
            }
            
            image = billboards[next_billboard].getImage();
            g.drawImage(image, 0, 0, null);
            getToolkit().sync();
            g.dispose();
            if (mouse_inside_applet == true) {
                showStatus(billboards[next_billboard].getLink().toExternalForm());
            }
            transition = null;
            try {
                Thread.sleep(120);
            } 
            catch (InterruptedException ex) {
                LOG.log(Level.SEVERE, null, ex);
            }
        }
    }
    
    private class MyMouseAdapter extends MouseAdapter {
        @Override
        public void mouseExited(MouseEvent me) {
            mouse_inside_applet = false;
            showStatus("");
        }
        @Override
        public void mouseReleased(MouseEvent me) {
            stop();
            setCursor(new Cursor(Cursor.WAIT_CURSOR));
            getAppletContext().showDocument(billboards[current_billboard].getLink(), link_target_frame);
        }
    }

    private class MyMouseMotionAdapter extends MouseMotionAdapter {
        @Override
        public void mouseMoved(MouseEvent me) {
            mouse_inside_applet = true;
            showStatus(billboards[current_billboard].getLink().toExternalForm());
        }
    }
    
}
