
import java.awt.Component;
import java.awt.HeadlessException;
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableCellRenderer;

/**
 * Класс, хранящий значок и текст ячейки
 * <p/>
 * @author dwinner@inbox.ru
 */
class ImageTableCell
{
    private Icon icon;
    private String text;

    ImageTableCell(Icon anIcon, String aText)
    {
        icon = anIcon;
        text = aText;
    }

    public Icon getIcon()
    {
        return icon;
    }

    public void setIcon(Icon anIcon)
    {
        icon = anIcon;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String aText)
    {
        text = aText;
    }
}

/**
 * Класс для прорисовки значка и текста
 * <p/>
 * @author dwinner@inbox.ru
 */
class ImageTableCellRenderer extends DefaultTableCellRenderer
{
    @Override
    public Component getTableCellRendererComponent(
        JTable table,
        Object value,
        boolean isSelected,
        boolean hasFocus,
        int row,
        int column)
    {
        // Метод возвращает компонент для прорисовки. Получаем объект нужного типа
        if (value instanceof ImageTableCell)
        {
            ImageTableCell imageCell = (ImageTableCell) value;
            // Получаем настроенную надпись от базового класса
            JLabel label = (JLabel) super.getTableCellRendererComponent(
                table,
                imageCell.getText(),
                isSelected,
                hasFocus,
                row,
                column);
            // Устанавливаем значок для JLabel
            label.setIcon(imageCell.getIcon());
            return label;
        }
        else
        {
            return super.getTableCellRendererComponent(
                table,
                value,
                isSelected,
                hasFocus,
                row,
                column);
        }
    }
}

/**
 * Регистрация в таблице собственного отображающего объекта.
 * <p/>
 * @author dwinner@inbox.ru
 */
public class ImageTableCellRendererTest extends JFrame
{
    public ImageTableCellRendererTest() throws HeadlessException
    {
        super("Registering Table Renderer");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Создаем таблицы на основе нашей модели
        JTable table = new JTable(new SpecialModel());
        
        // Регистрируем объект для прорисовки новых данных
        table.setDefaultRenderer(ImageTableCell.class, new ImageTableCellRenderer());
        
        // Выводим окно на экран
        getContentPane().add(new JScrollPane(table));
        pack();
        setVisible(true);
    }

    /**
     * Модель таблицы.
     */
    private class SpecialModel extends AbstractTableModel
    {
        // Значки
        private Icon
            image1 = new ImageIcon("lion.gif"),
            image2 = new ImageIcon("scorpion.gif");
        // Названия столбцов
        private String[] columnNames =
        {
            "Company",
            "Address"
        };
        // Данные таблицы
        private Object[][] data =
        {
            {
                new ImageTableCell(image1, "Mega Works"), "<html><font color=\"red\">Paris</font>"
            },
            {
                new ImageTableCell(image2, "Media Terra"), "<html><b>London</b>"
            }
        };

        // Количество строк
        @Override
        public int getRowCount()
        {
            return data.length;
        }

        // Количество столбцов
        @Override
        public int getColumnCount()
        {
            return columnNames.length;
        }

        // Названия столбцов
        @Override
        public String getColumnName(int column)
        {
            return columnNames[column];
        }

        // Тип данных столбца
        @Override
        public Class<?> getColumnClass(int columnIndex)
        {
            return data[0][columnIndex].getClass();
        }

        // Значение в ячейке
        @Override
        public Object getValueAt(int rowIndex, int columnIndex)
        {
            return data[rowIndex][columnIndex];
        }
    }

    public static void main(String[] args)
    {
        new ImageTableCellRendererTest();
    }
}