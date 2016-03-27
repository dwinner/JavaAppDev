import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ImageMenu extends Applet {
    
    private static final long serialVersionUID = 1L;
    
    private Dimension d;
    
    private Image img, off;
    private Graphics offg;
    private static final int MAXITEMS = 64;
    private String url[] = new String[MAXITEMS];
    private String target[] = new String[MAXITEMS];
    private String urlPrefix, urlSuffix;
    private int selectedCell = -1;
    private int oldCell = -1;
    private int cellH;
    private int cells;
    
    @Override
    public void init() {
        d = getSize();
        urlPrefix = getParameter("urlPrefix");
        urlSuffix = getParameter("urlSuffix");
        StringTokenizer st;
        st = new StringTokenizer(getParameter("urlList"), "+");
        int i = 0;
        while (st.hasMoreTokens() && i < MAXITEMS) {
            url[i] = st.nextToken();
            i++;
        }
        cells = i;
        cellH = d.height/cells;
        st = new StringTokenizer(getParameter("targetList"), "+");
        i = 0;
        while (st.hasMoreTokens() && i < MAXITEMS) {
            target[i] = st.nextToken();
            i++;
        }
        addMouseListener(new MyMouseAdapter());
        addMouseMotionListener(new MyMouseMotionAdapter());
    }
	
	private void lateInit() {
		off = createImage(d.width, d.height);
		try {
			img = getImage(getDocumentBase(), getParameter("img"));
			MediaTracker t = new MediaTracker(this);
			t.addImage(img, 0);
			t.waitForID(0);
		}
		catch (Exception e) {
			showStatus("error: " + e);
		}
	}
    
    @Override
    public void update(Graphics g) {
        
    }
    
    @Override
    public void paint(Graphics g) {
        if (off == null) {
            lateInit();
        }
        offg = off.getGraphics();
        offg.drawImage(img, 0, 0, this);
        if (selectedCell >= 0) {
            offg.clipRect(0, selectedCell * cellH, d.width, cellH);
            offg.drawImage(img, -d.width, 0, this);
        }
        g.drawImage(off, 0, 0, this);
    }
    
    private class MyMouseMotionAdapter extends MouseMotionAdapter {
        @Override
        public void mouseDragged(MouseEvent me) {
            mouseMoved(me);
        }
        @Override
        public void mouseMoved(MouseEvent me) {
            int y = me.getY();
            selectedCell = (int) (y/(double)d.height*cells);
            if (selectedCell != oldCell) {
                paint(getGraphics());
                showStatus(urlPrefix + url[selectedCell] + urlSuffix);
                oldCell = selectedCell;
            }
        }
    }
    
    private class MyMouseAdapter extends MouseAdapter {
        @Override
        public void mouseExited(MouseEvent me) {
            oldCell = -1;
            selectedCell = oldCell;
            paint(getGraphics());
            showStatus("");
        }
        @Override
        public void mouseReleased(MouseEvent me) {
            URL u = null;
            try {
                u = new URL(urlPrefix + url[selectedCell] + urlSuffix);
            } 
            catch (MalformedURLException ex) {
                showStatus("error: " + ex);
                Logger.getLogger(ImageMenu.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (me.isShiftDown()) {
                getAppletContext().showDocument(u, "_blank");
            }
            else {
                getAppletContext().showDocument(u, target[selectedCell]);
            }
        }
    }
    
}