
/**
 * @version 1.21 2003-09-09
 * @author Cay Horstmann
 */
import java.awt.Container;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.TimeZone;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/**
 * В этом классе показана панель с несколькими часами, которые обновляются потоком таймера.
 */
public class TimerTest
{
    @SuppressWarnings("deprecation")
    public static void main(String[] args)
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                TimerTestFrame frame = new TimerTestFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });

    }
}

/**
 * Фрейм с часами.
 */
class TimerTestFrame extends JFrame
{
    public static final int DEFAULT_WIDTH = 450;
    public static final int DEFAULT_HEIGHT = 300;

    TimerTestFrame()
    {
        setTitle("TimerTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        Container c = getContentPane();
        c.setLayout(new GridLayout(2, 3));
        c.add(new ClockCanvas("America/Los_Angeles"));
        c.add(new ClockCanvas("America/New_York"));
        c.add(new ClockCanvas("America/Caracas"));
        c.add(new ClockCanvas("Europe/Rome"));
        c.add(new ClockCanvas("Africa/Cairo"));
        c.add(new ClockCanvas("Asia/Taipei"));
    }
}

/**
 * Холст для отображения часов, который обновляется по таймеру.
 */
class ClockCanvas extends JPanel
{
    private String zone;
    private GregorianCalendar calendar;
    public static final int DEFAULT_WIDTH = 125;
    public static final int DEFAULT_HEIGHT = 125;

    /**
     * Конструктор "часовой" панели.
     * <p/>
     * @param tz Временная метка
     */
    ClockCanvas(String tz)
    {
        zone = tz;
        calendar = new GregorianCalendar(TimeZone.getTimeZone(tz));
        Timer t = new Timer(1000, new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                calendar.setTime(new Date());
                repaint();
            }
        });
        t.start();
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        g.drawOval(0, 0, 100, 100);

        int seconds = calendar.get(Calendar.HOUR) * 60 * 60
            + calendar.get(Calendar.MINUTE) * 60
            + calendar.get(Calendar.SECOND);
        double hourAngle = 2 * Math.PI * (seconds - 3 * 60 * 60) / (12 * 60 * 60);
        double minuteAngle = 2 * Math.PI * (seconds - 15 * 60) / (60 * 60);
        double secondAngle = 2 * Math.PI * (seconds - 15) / 60;
        g.drawLine(50, 50, 50 + (int) (30 * Math.cos(hourAngle)),
            50 + (int) (30 * Math.sin(hourAngle)));
        g.drawLine(50, 50, 50 + (int) (40 * Math.cos(minuteAngle)),
            50 + (int) (40 * Math.sin(minuteAngle)));
        g.drawLine(50, 50, 50 + (int) (45 * Math.cos(secondAngle)),
            50 + (int) (45 * Math.sin(secondAngle)));
        g.drawString(zone, 0, 115);
    }
}