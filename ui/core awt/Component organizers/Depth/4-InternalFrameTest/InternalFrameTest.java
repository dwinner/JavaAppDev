
/**
 * ������������� ���������� �������.
 * <p/>
 * @version 1.10 2004-08-22
 * @author Cay Horstmann
 */
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import javax.swing.*;

/**
 * ������ ��������� ������������� ������������� ���������� �������.
 */
public class InternalFrameTest
{
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new DesktopFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * ����� �������� ������ ��������������, � ������� ������������ HTML-���������.
 */
class DesktopFrame extends JFrame
{
    private JDesktopPane desktop;
    private int nextFrameX;
    private int nextFrameY;
    private int frameDistance;
    private int counter;
    private static final String[] planets =
    {
        "Mercury",
        "Venus",
        "Earth",
        "Mars",
        "Jupiter",
        "Saturn",
        "Uranus",
        "Neptune",
        "Pluto",
    };
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 400;

    DesktopFrame()
    {
        setTitle("InternalFrameTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        desktop = new JDesktopPane();
        add(desktop, BorderLayout.CENTER);

        counter = 0;

        // �������� ����.

        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu fileMenu = new JMenu("File");
        menuBar.add(fileMenu);
        JMenuItem openItem = new JMenuItem("New");
        openItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                createInternalFrame(new JLabel(new ImageIcon(planets[counter] + ".gif")), planets[counter]);
                counter = (counter + 1) % planets.length;
            }
        });
        fileMenu.add(openItem);
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                System.exit(0);
            }
        });
        fileMenu.add(exitItem);
        JMenu windowMenu = new JMenu("Window");
        menuBar.add(windowMenu);
        JMenuItem nextItem = new JMenuItem("Next");
        nextItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                selectNextWindow();
            }
        });
        windowMenu.add(nextItem);
        JMenuItem cascadeItem = new JMenuItem("Cascade");
        cascadeItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                cascadeWindows();
            }
        });
        windowMenu.add(cascadeItem);
        JMenuItem tileItem = new JMenuItem("Tile");
        tileItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                tileWindows();
            }
        });
        windowMenu.add(tileItem);
        final JCheckBoxMenuItem dragOutlineItem = new JCheckBoxMenuItem("Drag Outline");
        dragOutlineItem.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                desktop.setDragMode(dragOutlineItem.isSelected()
                                    ? JDesktopPane.OUTLINE_DRAG_MODE
                                    : JDesktopPane.LIVE_DRAG_MODE);
            }
        });
        windowMenu.add(dragOutlineItem);
    }

    /**
     * �������� ����������� ������ � ������ �������� �����.
     * <p/>
     * @param c ��������� ��� ����������� �� ���������� ������
     * @param t ��������� ����������� ������.
     */
    public void createInternalFrame(Component c, String t)
    {
        final JInternalFrame iframe = new JInternalFrame(
            t,
            true, // ����������� ��������� ��������.
            true, // ����������� ��������.
            true, // ����������� ������������.
            true // ����������� �����������	
            );

        iframe.add(c, BorderLayout.CENTER);
        desktop.add(iframe);

        iframe.setFrameIcon(new ImageIcon("document.gif"));

        // ���������� ��� ������������� �������� ������.
        iframe.addVetoableChangeListener(new VetoableChangeListener()
        {
            @Override
            public void vetoableChange(PropertyChangeEvent event) throws PropertyVetoException
            {
                String name = event.getPropertyName();
                Object value = event.getNewValue();

                // ��� ���������� ���� ������� �� ��������� ������� �����.
                if (name.equals("closed") && value.equals(true))
                {
                    // ������ ������������� � ������������
                    int result = JOptionPane.showInternalConfirmDialog(
                        iframe,
                        "OK to close?",
                        "Select an Option",
                        JOptionPane.YES_NO_OPTION);

                    // ���� ������������ �� ���������� ���� ���������, ����� �� �����������
                    if (result != JOptionPane.YES_OPTION)
                    {
                        throw new PropertyVetoException("User canceled close", event);
                    }
                }
            }
        });

        // ���������������� ������.
        int width = desktop.getWidth() / 2;
        int height = desktop.getHeight() / 2;
        iframe.reshape(nextFrameX, nextFrameY, width, height);

        iframe.show();

        // ����� ������ - �������� ����� ���� ���������.
        try
        {
            iframe.setSelected(true);
        }
        catch (PropertyVetoException e)
        {
        }

        frameDistance = iframe.getHeight() - iframe.getContentPane().getHeight();

        // ���������� ������� ���������� ������.

        nextFrameX += frameDistance;
        nextFrameY += frameDistance;
        if (nextFrameX + width > desktop.getWidth())
        {
            nextFrameX = 0;
        }
        if (nextFrameY + height > desktop.getHeight())
        {
            nextFrameY = 0;
        }
    }

    /**
     * ��������� ������������ ����������� ���������� �������.
     */
    public void cascadeWindows()
    {
        int x = 0;
        int y = 0;
        int width = desktop.getWidth() / 2;
        int height = desktop.getHeight() / 2;

        for (JInternalFrame frame : desktop.getAllFrames())
        {
            if (!frame.isIcon())
            {
                try
                {
                    // ������� ���������� ���������� ������� ��� ������������������ ������.
                    // �������� ����� �� ��������������.
                    frame.setMaximum(false);
                    frame.reshape(x, y, width, height);

                    x += frameDistance;
                    y += frameDistance;
                    // ���������� �������� ������ ����.
                    if (x + width > desktop.getWidth())
                    {
                        x = 0;
                    }
                    if (y + height > desktop.getHeight())
                    {
                        y = 0;
                    }
                }
                catch (PropertyVetoException e)
                {
                }
            }
        }
    }

    /**
     * ��������� ������������ ����������� ���������� �������.
     */
    public void tileWindows()
    {
        // ������� ����������� �������.
        int frameCount = 0;
        for (JInternalFrame frame : desktop.getAllFrames())
        {
            if (!frame.isIcon())
            {
                frameCount++;
            }
        }
        if (frameCount == 0)
        {
            return;
        }

        int rows = (int) Math.sqrt(frameCount);
        int cols = frameCount / rows;
        int extra = frameCount % rows;
        // ����� �������� � ������������� ������.

        int width = desktop.getWidth() / cols;
        int height = desktop.getHeight() / rows;
        int r = 0;
        int c = 0;
        for (JInternalFrame frame : desktop.getAllFrames())
        {
            if (!frame.isIcon())
            {
                try
                {
                    frame.setMaximum(false);
                    frame.reshape(c * width, r * height, width, height);
                    r++;
                    if (r == rows)
                    {
                        r = 0;
                        c++;
                        if (c == cols - extra)
                        {
                            // ���������� �������������� ������.
                            rows++;
                            height = desktop.getHeight() / rows;
                        }
                    }
                }
                catch (PropertyVetoException e)
                {
                }
            }
        }
    }

    /**
     * ������� ���������� ������������ ������ �� �������� ����.
     */
    public void selectNextWindow()
    {
        JInternalFrame[] frames = desktop.getAllFrames();
        for (int i = 0; i < frames.length; i++)
        {
            if (frames[i].isSelected())
            {
                // ����� ���������� ������������ ������, ������� ����� ���� ������
                int next = (i + 1) % frames.length;
                while (next != i)
                {
                    if (!frames[next].isIcon())
                    {
                        try
                        {
                            // ��������� ������ ���� ��������, ���� �� ����� ��������.
                            frames[next].setSelected(true);
                            frames[next].toFront();
                            frames[i].toBack();
                            return;
                        }
                        catch (PropertyVetoException e)
                        {
                        }
                    }
                    next = (next + 1) % frames.length;
                }
            }
        }
    }
}
