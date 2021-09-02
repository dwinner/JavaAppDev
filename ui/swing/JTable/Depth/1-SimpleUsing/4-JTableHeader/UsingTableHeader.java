/*
 * Настройка заголовка таблицы JTableHeader.
 */

import java.awt.Color;
import java.awt.Component;
import java.awt.HeadlessException;
import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class UsingTableHeader extends JFrame
{
    // Данные для таблицы
    private String[][] data =
    {
        {
            "Июнь", "+18 С"
        },
        {
            "Июль", "+22 С"
        },
        {
            "Август", "+19 С"
        }
    };
    // Названия столбцов
    private String[] columnNames =
    {
        "Месяц", "Средняя температура"
    };

    public UsingTableHeader() throws HeadlessException
    {
        super("Using Table Header");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Создаем таблицу
        JTable table = new JTable(data, columnNames);
        
        // Настраиваем заголовок таблицы
        JTableHeader header = table.getTableHeader();
        header.setReorderingAllowed(false);
        header.setResizingAllowed(false);
        
        // Присоединяем отображающий объект
        header.setDefaultRenderer(new HeaderRenderer());
        
        // Добавляем таблицу в панель прокрутки
        getContentPane().add(new JScrollPane(table));
        setSize(400, 300);
        setVisible(true);
    }

    // Специальный отображающий объект для заголовка
    private static class HeaderRenderer extends DefaultTableCellRenderer
    {
        private final static Border border =
            BorderFactory.createBevelBorder(BevelBorder.RAISED, Color.BLUE, Color.GRAY);

        // Метод, возвращающий компонент для прорисовки
        @Override
        public Component getTableCellRendererComponent(
            JTable table,
            Object value,
            boolean isSelected,
            boolean hasFocus,
            int row,
            int column)
        {
            // Получаем настроенную надпись от базового класса
            JLabel label = (JLabel) super.getTableCellRendererComponent(
                table,
                value,
                isSelected,
                hasFocus,
                row,
                column);
            // Настраиваем особую рамку и цвет фона
            label.setBackground(Color.BLACK);
            label.setForeground(Color.WHITE);
            label.setBorder(border);
            return label;
        }
    }

    public static void main(String[] args)
    {
        new UsingTableHeader();
    }
}