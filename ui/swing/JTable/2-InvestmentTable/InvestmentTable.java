import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;
import java.awt.*;

/**
 * Пример создания таблицы на основе модели.
 * @version 1.02 2007-08-01
 * @author Cay Horstmann
 */
public class InvestmentTable
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new InvestmentTableFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Фрейм, содержащий таблицу с данными об инвестициях.
 */
class InvestmentTableFrame extends JFrame
{
    private static final int DEFAULT_WIDTH = 600;
    private static final int DEFAULT_HEIGHT = 300;

    InvestmentTableFrame() throws HeadlessException
    {
        setTitle("InvestmentTable");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        TableModel model = new InvestmentTableModel(30, 5, 10);
        JTable table = new JTable(model);
        add(new JScrollPane(table));
    }
}

/**
 * Модель таблицы вычисляет данные для отображения в ячейках
 * для каждого запроса. В таблице показан рост инвестиций за
 * указанное количество лет для разных учетных ставок.
 */
class InvestmentTableModel extends AbstractTableModel
{
    private int years;
    private int minRate;
    private int maxRate;

    private static double INITIAL_BALANCE = 100000.0;

    InvestmentTableModel(int years, int minRate, int maxRate)
    {
        this.years = years;
        this.minRate = minRate;
        this.maxRate = maxRate;
    }

    @Override
    public int getRowCount()
    {
        return years;
    }

    @Override
    public int getColumnCount()
    {
        return maxRate - minRate + 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        double rate = (columnIndex + minRate) / 100.0;
        int nperiods = rowIndex;
        double futureBalance = INITIAL_BALANCE * Math.pow(1 + rate, nperiods);
        return String.format("%.2f", futureBalance);
    }

    @Override
    public String getColumnName(int column)
    {
        return (column + minRate) + "%";
    }
}