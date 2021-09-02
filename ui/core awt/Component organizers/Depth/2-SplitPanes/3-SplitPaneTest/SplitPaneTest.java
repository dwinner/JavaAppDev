
/**
 * Панель с границей раздела.
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
 * Использование панели с границей раздела в качестве организатора компонентов.
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
 * Этот фрейм содержит две вложенных панели с границей раздела. В них отображаются рисунки и текстовые данные.
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

        // Создание компонентов для имен, изображений и описаний планет.

        final JList<Planet> planetList = new JList<>(planets);
        final JLabel planetImage = new JLabel();
        final JTextArea planetDescription = new JTextArea();

        planetList.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent event)
            {
                Planet value = planetList.getSelectedValue();

                // Обновление рисунка и описания

                planetImage.setIcon(value.getImage());
                planetDescription.setText(value.getDescription());
            }
        });

        // Установка панелей с границами раздела.

        JSplitPane innerPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, planetList, planetImage);

        innerPane.setContinuousLayout(true);
        innerPane.setOneTouchExpandable(true);

        JSplitPane outerPane = new JSplitPane(JSplitPane.VERTICAL_SPLIT, innerPane, planetDescription);

        add(outerPane, BorderLayout.CENTER);
    }
}

/**
 * Описание планеты.
 */
class Planet
{
    private String name;
    private double radius;
    private int moons;
    private ImageIcon image;

    /**
     * Создание объекта, представляющего планету.
     * <p/>
     * @param n Название планеты
     * @param r Радиус планеты
     * @param m Количество спутников
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
     * Получение описания планеты.
     * <p/>
     * @return Описание
     */
    public String getDescription()
    {
        return "Radius: " + radius + "\nMoons: " + moons + "\n";
    }

    /**
     * Получение изображения планеты.
     * <p/>
     * @return Изображение
     */
    public ImageIcon getImage()
    {
        return image;
    }
}