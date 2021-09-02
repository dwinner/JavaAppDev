import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.Color;

/**
 * Модель таблицы определяет значения,
 * свойства визуализации и редактирования.
 */
public class PlanetTableModel extends AbstractTableModel
{
    public static final int PLANET_COLUMN = 0;
    public static final int MOONS_COLUMN = 2;
    public static final int GASEOUS_COLUMN = 3;
    public static final int COLOR_COLUMN = 4;

    private Object[][] cells =
        {
            {
                "Mercury", 2440.0, 0, false, Color.YELLOW, new ImageIcon("Mercury.gif")
            },
            {
                "Venus", 6052.0, 0, false, Color.YELLOW, new ImageIcon("Venus.gif")
            },
            {
                "Earth", 6378.0, 1, false, Color.BLUE, new ImageIcon("Earth.gif")
            },
            {
                "Mars", 3397.0, 2, false, Color.RED, new ImageIcon("Mars.gif")
            },
            {
                "Jupiter", 71492.0, 16, true, Color.ORANGE, new ImageIcon("Jupiter.gif")
            },
            {
                "Saturn", 60268.0, 18, true, Color.ORANGE, new ImageIcon("Saturn.gif")
            },
            {
                "Uranus", 25559.0, 17, true, Color.BLUE, new ImageIcon("Uranus.gif")
            },
            {
                "Neptune", 24766.0, 8, true, Color.BLUE, new ImageIcon("Neptune.gif")
            },
            {
                "Pluto", 1137.0, 1, false, Color.BLACK, new ImageIcon("Pluto.gif")
            }
        };
    private String[] columnNames =
        {
            "Planet", "Radius", "Moons", "Gaseous", "Color", "Image"
        };

    @Override
    public String getColumnName(int column)
    {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex)
    {
        return cells[0][columnIndex].getClass();
    }

    @Override
    public int getColumnCount()
    {
        return cells[0].length;
    }

    @Override
    public int getRowCount()
    {
        return cells.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        return cells[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex)
    {
        cells[rowIndex][columnIndex] = aValue;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex)
    {
        return columnIndex == PLANET_COLUMN || columnIndex == MOONS_COLUMN
            || columnIndex == GASEOUS_COLUMN || columnIndex == COLOR_COLUMN;
    }
}
