
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Программа рисования с всплывающим меню.
 * @author dwinner@inbox.ru
 */
public class PopupMenuScribble extends Frame
{
    public PopupMenuScribble(String s)
    {
        super(s);
        ScrollPane pane = new ScrollPane();
        pane.setSize(300, 300);
        add(pane, BorderLayout.CENTER);
        Scribble scr = new Scribble(this, 500, 500);
        pane.add(scr);
        addWindowListener(new WinClose());
        pack();
        setVisible(true);
    }

    private class WinClose extends WindowAdapter
    {
        @Override
        public void windowClosing(WindowEvent e)
        {
            System.exit(0);
        }

    }

    public static void main(String[] args)
    {
        new PopupMenuScribble("\"Рисовалка\" с всплывающим меню");
    }

}

class Scribble extends Component implements ActionListener
{
    protected int lastX, lastY, w, h;
    protected Color currColor = Color.black;
    protected Frame f;
    protected PopupMenu c;

    Scribble(Frame frame, int width, int height)
    {
        f = frame;
        w = width;
        h = height;
        enableEvents(AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK);
        c = new PopupMenu("Цвет");
        add(c);
        MenuItem clear = new MenuItem("Очистить", new MenuShortcut(KeyEvent.VK_D));
        MenuItem red = new MenuItem("Красный");
        MenuItem green = new MenuItem("Зеленый");
        MenuItem blue = new MenuItem("Синий");
        MenuItem black = new MenuItem("Черный");
        c.add(red);
        c.add(green);
        c.add(blue);
        c.add(black);
        c.addSeparator();
        c.add(clear);
        red.addActionListener(this);
        green.addActionListener(this);
        blue.addActionListener(this);
        black.addActionListener(this);
        clear.addActionListener(this);
    }

    @Override
    public Dimension getPreferredSize()
    {
        return new Dimension(w, h);
    }

    @Override
    public void actionPerformed(ActionEvent event)
    {
        String s = event.getActionCommand();
        switch (s)
        {
            case "Очистить":
                repaint();
                break;
            case "Зеленый":
                currColor = Color.green;
                break;
            case "Красный":
                currColor = Color.red;
                break;
            case "Синий":
                currColor = Color.blue;
                break;
            case "Черный":
                currColor = Color.black;
                break;
        }
    }

    @Override
    public void processMouseEvent(MouseEvent e)
    {
        if (e.isPopupTrigger())
        {
            c.show(e.getComponent(), e.getX(), e.getY());
        }
        else if (e.getID() == MouseEvent.MOUSE_PRESSED)
        {
            lastX = e.getX();
            lastY = e.getY();
        }
        else
        {
            super.processMouseEvent(e);
        }
    }

    @Override
    public void processMouseMotionEvent(MouseEvent e)
    {
        if (e.getID() == MouseEvent.MOUSE_DRAGGED)
        {
            Graphics g = getGraphics();
            g.setColor(currColor);
            g.drawLine(lastX, lastY, e.getX(), e.getY());
            lastX = e.getX();
            lastY = e.getY();
        }
        else
        {
            super.processMouseMotionEvent(e);
        }
    }

}