import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.print.PrinterException;

/**
 * Программа, демонстрирующая отображение простой таблицы.
 * @version 1.11 2007-08-01
 * @author Cay Horstmann
 */
public class PlanetTable
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new PlanetTableFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Данный фрейм содержит таблицу с данными о планетах.
 */
class PlanetTableFrame extends JFrame
{
    private String[] columnNames = { "Planet", "Radius", "Moons", "Gaseous", "Color" };
    private Object[][] cells =
        {
            { "Mercury", 2440.0 , 0, false, Color.YELLOW },
            { "Venus", 6052.0, 0, false, Color.YELLOW },
            { "Earth", 6378.0, 1, false, Color.BLUE },
            { "Mars", 3397.0, 2, false, Color.RED },
            { "Jupiter", 71492.0, 16, true, Color.ORANGE },
            { "Saturn", 60268.0, 18, true, Color.ORANGE },
            { "Uranus", 25559.0, 17, true, Color.BLUE },
            { "Neptune", 24766.0, 8, true, Color.BLUE },
            { "Pluto", 1137.0, 1, false, Color.BLACK }
        };
    private final static int DEFAULT_WIDTH = 400;
    private final static int DEFAULT_HEIGHT = 200;

    PlanetTableFrame() throws HeadlessException
    {
        setTitle("PlanetTable");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        final JTable table = new JTable(cells, columnNames);
        table.setAutoCreateRowSorter(true);
        table.setFillsViewportHeight(true);
        add(new JScrollPane(table), BorderLayout.CENTER);
        JButton printButton = new JButton("Print");
        printButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent actionEvent)
            {
                try
                {
                    table.print();
                }
                catch (PrinterException e)
                {
                    e.printStackTrace();
                }
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(printButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}