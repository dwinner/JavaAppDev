
/**
 * Обработка текстовых компонентов.
 * <p/>
 * @version 1.32 2004-05-05
 * @author Cay Horstmann
 */
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class TextTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                TextTestFrame frame = new TextTestFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Фрейм с двумя полями редактирования, предназначенными для установки часов.
 */
class TextTestFrame extends JFrame
{
    private JTextField hourField;
    private JTextField minuteField;
    private ClockPanel clock;

    TextTestFrame()
    {
        setTitle("TextTest");

        DocumentListener listener = new ClockFieldListener();

        // Добавление панели с полями редактирования.

        JPanel panel = new JPanel();

        panel.add(new JLabel("Hours:"));
        hourField = new JTextField("12", 3);
        panel.add(hourField);
        hourField.getDocument().addDocumentListener(listener);

        panel.add(new JLabel("Minutes:"));
        minuteField = new JTextField("00", 3);
        panel.add(minuteField);
        minuteField.getDocument().addDocumentListener(listener);

        add(panel, BorderLayout.SOUTH);

        // Добавление циферблата часов.

        clock = new ClockPanel();
        add(clock, BorderLayout.CENTER);
        pack();
    }

    /**
     * Установка часов в соответствии с содержимым полей редактирования.
     */
    public void setClock()
    {
        try
        {
            int hours = Integer.parseInt(hourField.getText().trim());
            int minutes = Integer.parseInt(minuteField.getText().trim());
            clock.setTime(hours, minutes);
        }
        catch (NumberFormatException e)
        {
            // Если разбор входных данных не выполнен, часы не устанавливаются.	
        }
    }

    private class ClockFieldListener implements DocumentListener
    {
        @Override
        public void insertUpdate(DocumentEvent event)
        {
            setClock();
        }

        @Override
        public void removeUpdate(DocumentEvent event)
        {
            setClock();
        }

        @Override
        public void changedUpdate(DocumentEvent event)
        {
        }
    }
}

/**
 * Панель для отображения циферблата часов.
 */
class ClockPanel extends JPanel
{
    private double minutes = 0;
    private int RADIUS = 100;
    private double MINUTE_HAND_LENGTH = 0.8 * RADIUS;
    private double HOUR_HAND_LENGTH = 0.6 * RADIUS;

    ClockPanel()
    {
        setPreferredSize(new Dimension(2 * RADIUS + 1, 2 * RADIUS + 1));
    }

    @Override
    public void paintComponent(Graphics g)
    {
        // Рисование границы в виде круга.

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        Ellipse2D circle = new Ellipse2D.Double(0, 0, 2 * RADIUS, 2 * RADIUS);
        g2.draw(circle);

        // Рисование часовой стрелки.

        double hourAngle = Math.toRadians(90 - 360 * minutes / (12 * 60));
        drawHand(g2, hourAngle, HOUR_HAND_LENGTH);

        // Рисование минутной стрелки.

        double minuteAngle = Math.toRadians(90 - 360 * minutes / 60);
        drawHand(g2, minuteAngle, MINUTE_HAND_LENGTH);
    }

    public void drawHand(Graphics2D g2, double angle, double handLength)
    {
        Point2D end = new Point2D.Double(
            RADIUS + handLength * Math.cos(angle),
            RADIUS - handLength * Math.sin(angle));
        Point2D center = new Point2D.Double(RADIUS, RADIUS);
        g2.draw(new Line2D.Double(center, end));
    }

    /**
     * Установка времени.
     * <p/>
     * @param h Часы
     * @param m Минуты
     */
    public void setTime(int h, int m)
    {
        minutes = h * 60 + m;
        repaint();
    }
}