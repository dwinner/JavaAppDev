
/**
 * @version 1.31 2004-05-03
 * @author Cay Horstmann
 */
import java.awt.Graphics;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.EventListener;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class EventSourceTest
{
    public static void main(String[] args)
    {
        EventSourceFrame frame = new EventSourceFrame();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}

/**
 * �����, ���������� ������, ������� ��������� ���������.
 */
class EventSourceFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 400;
    public static final int DEFAULT_HEIGHT = 200;

    EventSourceFrame()
    {
        setTitle("EventSourceTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // ���������� ������ � ������.

        final PaintCountPanel panel = new PaintCountPanel();
        add(panel);

        panel.addPropertyChangeListener(new PropertyChangeListener()
        {
            @Override
            public void propertyChange(PropertyChangeEvent event)
            {
                setTitle("EventSourceTest - " + event.getNewValue());
            }
        });
    }
}

/**
 * ������, �������������� ����� �����������.
 */
class PaintCountPanel extends JPanel
{
    private int paintCount;

    @Override
    public void paintComponent(Graphics g)
    {
        int oldPaintCount = paintCount;
        paintCount++;
        firePropertyChangeEvent(new PropertyChangeEvent(this, "paintCount", oldPaintCount,
            paintCount));
        super.paintComponent(g);
    }

    /**
     * ���������� ����������� �������.
     * <p/>
     * @param listener ����������.
     */
    @Override
    public void addPropertyChangeListener(PropertyChangeListener listener)
    {
        listenerList.add(PropertyChangeListener.class, listener);
    }

    /**
     * �������� ����������� �������.
     * <p/>
     * @param listener ����������.
     */
    @Override
    public void removePropertyChangeListener(PropertyChangeListener listener)
    {
        listenerList.remove(PropertyChangeListener.class, listener);
    }

    public void firePropertyChangeEvent(PropertyChangeEvent event)
    {
        EventListener[] listeners = listenerList.getListeners(PropertyChangeListener.class);
        for (EventListener l : listeners)
        {
            ((PropertyChangeListener) l).propertyChange(event);
        }
    }

    public int getPaintCount()
    {
        return paintCount;
    }
}
