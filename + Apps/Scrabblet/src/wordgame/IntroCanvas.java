package wordgame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.logging.Logger;

/**
 * Полотно с некоторой краткой информацией.
 * @author dwinner@inbox.ru
 */
class IntroCanvas extends Canvas {
    
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(IntroCanvas.class.getName());
    
    private Color pink = new Color(255, 200, 200);
    private Color blue = new Color(150, 200, 255);
    private Color yellow = new Color(250, 220, 100);
    
    private int w, h;
    private int edge = 16;
    private static final String title = "Scrabblet";
    private static final String name = "Copyright 2011 - Vinevcev D.I.";
    private static final String book = "A simple client-server word game on Java";
    private Font namefont, titlefont, bookfont;
    
    IntroCanvas() {
        setBackground(yellow);
        titlefont = new Font("SansSerif", Font.BOLD, 58);
        namefont = new Font("SansSerif", Font.BOLD, 18);
        bookfont = new Font("SansSerif", Font.PLAIN, 12);
        addMouseListener(new MyMouseAdapter());
    }
    
    private void d(Graphics g, String s, Color c, Font f, int y, int off) { // Convinience method
        g.setFont(f);
        FontMetrics fm = g.getFontMetrics();
        g.setColor(c);
        g.drawString(s, (w - fm.stringWidth(s)) / 2 + off, y + off);
    }
    
    @Override
    public void paint(Graphics g) {
        Dimension d = getSize();
        w = d.width;
        h = d.height;
        g.setColor(blue);
        g.fill3DRect(edge, edge, w - 2 * edge, h - 2 * edge, true);
        d(g, title, Color.black, titlefont, h / 2, 1);
        d(g, title, Color.white, titlefont, h / 2, -1);
        d(g, title, pink, titlefont, h / 2, 0);
        d(g, name, Color.black, namefont, h * 3 / 4, 0);
        d(g, book, Color.black, bookfont, h * 7 / 8, 0);
    }
    
    private class MyMouseAdapter extends MouseAdapter {
        @Override
        public void mousePressed(MouseEvent me) {
            ((Frame) getParent()).setVisible(false);
        }
    }
    
}
