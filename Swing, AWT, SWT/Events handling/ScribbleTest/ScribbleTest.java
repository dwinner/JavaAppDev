
import java.awt.AWTEvent;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 * Программа рисования в Swing.
 * @author dwinner@inbox.ru
 */
public class ScribbleTest extends JFrame
{
    public ScribbleTest(String s)
    {
        super(s);
        Scribble src = new Scribble(this, 500, 500);
        add(new JScrollPane(src));
        JPanel p = new JPanel();
        add(p, BorderLayout.SOUTH);
        JButton b1 = new JButton("Red");
        p.add(b1);
        b1.addActionListener(src);
        JButton b2 = new JButton("Green");
        p.add(b2);
        b2.addActionListener(src);
        JButton b3 = new JButton("Blue");
        p.add(b3);
        b3.addActionListener(src);
        JButton b4 = new JButton("Black");
        p.add(b4);
        b4.addActionListener(src);
        JButton b5 = new JButton("Clear");
        p.add(b5);
        b5.addActionListener(src);
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }

        });
        pack();
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new ScribbleTest("\"Drawing Program\"");
    }

}

class Scribble extends JComponent implements ActionListener, MouseListener, MouseMotionListener
{
    private int lastX, lastY, w, h;
    private Color currColor = Color.black;
    private JFrame f;

    Scribble(JFrame frame, int width, int height)
    {
        f = frame;
        w = width;
        h = height;
        enableEvents(AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK);
        addMouseListener(this);
        addMouseMotionListener(this);
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
        if (s.equalsIgnoreCase("Clear"))
        {
            repaint();
        }
        else if (s.equalsIgnoreCase("Red"))
        {
            currColor = Color.red;
        }
        else if (s.equalsIgnoreCase("Green"))
        {
            currColor = Color.green;
        }
        else if (s.equalsIgnoreCase("Blue"))
        {
            currColor = Color.blue;
        }
        else if (s.equalsIgnoreCase("Black"))
        {
            currColor = Color.black;
        }
    }

    @Override
    public void mousePressed(MouseEvent e)
    {
        if ((e.getModifiers() & MouseEvent.BUTTON1_MASK) == 0)
        {
            return;
        }
        lastX = e.getX();
        lastY = e.getY();
    }

    @Override
    public void mouseDragged(MouseEvent e)
    {
        if ((e.getModifiers() & MouseEvent.BUTTON1_MASK) == 0)
        {
            return;
        }
        Graphics g = getGraphics();
        g.setColor(currColor);
        g.drawLine(lastX, lastY, e.getX(), e.getY());
        lastX = e.getX();
        lastY = e.getY();
    }

    // Пустые реализации методов
    @Override
    public void mouseReleased(MouseEvent e)
    {
    }

    @Override
    public void mouseClicked(MouseEvent e)
    {
    }

    @Override
    public void mouseEntered(MouseEvent e)
    {
    }

    @Override
    public void mouseExited(MouseEvent e)
    {
    }

    @Override
    public void mouseMoved(MouseEvent e)
    {
    }

}