
/**
 * ������ � �������� �������.
 * <p/>
 * @version 1.02 2004-08-22
 * @author Cay Horstmann
 */
import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

/**
 * ������������� ������ � �������� ������� � �������� ������������ �����������.
 */
public class SplitPaneTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new SplitPaneFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * ���� ����� �������� ��� ��������� ������ � �������� �������. � ��� ������������ ������� � ��������� ������.
 */
class SplitPaneFrame extends JFrame
{
    private Planet[] planets =
    {
        new Planet("Mercury", 2440, 0),
        new Planet("Venus", 6052, 0),
        new Planet("Earth", 6378, 1),
        new Planet("Mars", 3397, 2),
        new Planet("Jupiter", 71492, 16),
        new Planet("Saturn", 60268, 18),
        new Planet("Uranus", 25559, 17),
        new Planet("Neptune", 24766, 8),
        new Planet("Pluto", 1137, 1),
    };
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 300;

    SplitPaneFrame()
    {
        setTitle("SplitPaneTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // �������� ����������� ��� ����, ����������� � �������� ������.

        final JList<Planet> planetList = new JList<>(planets);
        final JLabel planetImage = new JLabel();
        final JTextArea planetDescription = new JTextArea();

        planetList.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent event)
            {
                Planet value = planetList.getSelectedValue();

                // ���������� ������� � ��������

                planetImage.setIcon(value.getImage());
                planetDescription.setText(value.getDescription());
            }
        });

        // ��������� ������� � ��������� �������.

        JSplitPane innerPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, planetList, planetImage);

        innerPane.setContinuousLayout(true);
        innerPane.setOneTouchExpandable(true);

        JSplitPane outerPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, innerPane, planetDescription);

        add(outerPane, BorderLayout.CENTER);
    }
}

/**
 * �������� �������.
 */
class Planet
{
    private String name;
    private double radius;
    private int moons;
    private ImageIcon image;

    /**
     * �������� �������, ��������������� �������.
     * <p/>
     * @param n �������� �������
     * @param r ������ �������
     * @param m ���������� ���������
     */
    Planet(String n, double r, int m)
    {
        name = n;
        radius = r;
        moons = m;
        image = new ImageIcon(name + ".gif");
    }

    @Override
    public String toString()
    {
        return name;
    }

    /**
     * ��������� �������� �������.
     * <p/>
     * @return ��������
     */
    public String getDescription()
    {
        return "Radius: " + radius + "\nMoons: " + moons + "\n";
    }

    /**
     * ��������� ����������� �������.
     * <p/>
     * @return �����������
     */
    public ImageIcon getImage()
    {
        return image;
    }
}