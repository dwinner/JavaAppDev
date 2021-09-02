
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * ��������� ��������� � ���������� ����.
 * @version 1.0.1
 * @author dwinner@inbox.ru 12.05.2004
 */
public class MenuScribble extends Frame
{
    public MenuScribble(String s)
    {
        super(s);
        ScrollPane pane = new ScrollPane();
        pane.setSize(300, 300);
        add(pane, BorderLayout.CENTER);
        Scribble src = new Scribble(this, 500, 500);
        pane.add(src);
        MenuBar mb = new MenuBar();
        setMenuBar(mb);
        Menu f = new Menu("����");
        Menu v = new Menu("���");
        mb.add(f);
        mb.add(v);
        MenuItem open = new MenuItem("�������", new MenuShortcut(KeyEvent.VK_O));
        MenuItem save = new MenuItem("���������", new MenuShortcut(KeyEvent.VK_S));
        MenuItem saveAs = new MenuItem("��������� ���");
        MenuItem exit = new MenuItem("�����", new MenuShortcut(KeyEvent.VK_Q));
        f.add(open);
        f.add(save);
        f.add(saveAs);
        f.addSeparator();
        f.add(exit);
        open.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                FileDialog fd = new FileDialog(new Frame(), "���������", FileDialog.LOAD);
                fd.setVisible(true);
            }

        });
        saveAs.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                FileDialog fd = new FileDialog(new Frame(), "���������", FileDialog.SAVE);
                fd.setVisible(true);
            }

        });
        exit.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                System.exit(0);
            }

        });
        Menu c = new Menu("����");
        MenuItem clear = new MenuItem("��������", new MenuShortcut(KeyEvent.VK_D));
        v.add(c);
        v.add(clear);
        MenuItem red = new MenuItem("�������");
        MenuItem green = new MenuItem("�������");
        MenuItem blue = new MenuItem("�����");
        MenuItem black = new MenuItem("������");
        c.add(red);
        c.add(green);
        c.add(blue);
        c.add(black);
        red.addActionListener(src);
        green.addActionListener(src);
        blue.addActionListener(src);
        black.addActionListener(src);
        clear.addActionListener(src);
        this.addWindowListener(new WinClose());
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
        new MenuScribble("\"���������\" � ����");
    }

}

class Scribble extends Component implements ActionListener, MouseListener, MouseMotionListener
{
    protected int lastX, lastY, w, h;
    protected Color currColor = Color.black;
    protected Frame f;

    Scribble(Frame frame, int width, int height)
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
        if (s.equals("��������"))
        {
            repaint();
        }
        else if (s.equals("�������"))
        {
            currColor = Color.red;
        }
        else if (s.equals("�������"))
        {
            currColor = Color.green;
        }
        else if (s.equals("�����"))
        {
            currColor = Color.blue;
        }
        else if (s.equals("������"))
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