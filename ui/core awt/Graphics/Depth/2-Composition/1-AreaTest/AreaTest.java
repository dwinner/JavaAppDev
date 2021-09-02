
/**
 * Операции объединения областей.
 * <p/>
 * @version 1.02 2004-08-24
 * @author Cay Horstmann
 */
import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Area;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.EventQueue;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

/**
 * Пример использования операций для объединения областей.
 */
public class AreaTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new AreaTestFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Фрейм с набором переключателей для определения операций над областями и панели для отображения результатов выполнения
 * операций.
 */
class AreaTestFrame extends JFrame
{
    private JPanel panel;
    private Area area;
    private Area area1;
    private Area area2;
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 400;

    AreaTestFrame()
    {
        setTitle("AreaTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        area1 = new Area(new Ellipse2D.Double(100, 100, 150, 100));
        area2 = new Area(new Rectangle2D.Double(150, 150, 150, 100));

        panel = new JPanel()
        {
            @Override
            public void paintComponent(Graphics g)
            {
                super.paintComponent(g);
                Graphics2D g2 = (Graphics2D) g;
                g2.draw(area1);
                g2.draw(area2);
                if (area != null)
                {
                    g2.fill(area);
                }
            }
        };

        add(panel, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        ButtonGroup group = new ButtonGroup();

        JRadioButton addButton = new JRadioButton("Add", false);
        buttonPanel.add(addButton);
        group.add(addButton);
        addButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                area = new Area();
                area.add(area1);
                area.add(area2);
                panel.repaint();
            }
        });

        JRadioButton subtractButton = new JRadioButton("Subtract", false);
        buttonPanel.add(subtractButton);
        group.add(subtractButton);
        subtractButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                area = new Area();
                area.add(area1);
                area.subtract(area2);
                panel.repaint();
            }
        });

        JRadioButton intersectButton = new JRadioButton("Intersect", false);
        buttonPanel.add(intersectButton);
        group.add(intersectButton);
        intersectButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                area = new Area();
                area.add(area1);
                area.intersect(area2);
                panel.repaint();
            }
        });

        JRadioButton exclusiveOrButton = new JRadioButton("Exclusive Or", false);
        buttonPanel.add(exclusiveOrButton);
        group.add(exclusiveOrButton);
        exclusiveOrButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                area = new Area();
                area.add(area1);
                area.exclusiveOr(area2);
                panel.repaint();
            }
        });

        add(buttonPanel, BorderLayout.NORTH);
    }
}
