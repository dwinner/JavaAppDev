// Простые таблицы, создаваемые с помощью удобных конструкторов
import java.awt.HeadlessException;
import java.util.Vector;
import javax.swing.*;

public class SimpleTables extends JFrame
{
    // Данные для таблиц
    private Object[][] colors = new String[][]
    {
        {
            "Красный", "Зеленый", "Синий"
        },
        {
            "Желтый", "Оранжевый", "Белый"
        }
    };
    // Названия заголовков столбцов
    private Object[] colorsHeader = new String[]
    {
        "Цвет", "Ещё цвет", "Тоже цвет"
    };

    public SimpleTables() throws HeadlessException
    {
        super("Simple tables");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Несколько простых таблиц
        JTable table1 = new JTable(colors, colorsHeader);
        JTable table2 = new JTable(5, 5);
        // Таблица на основе вектора, состоящего из векторов
        Vector data = new Vector();
        Vector row1 = new Vector();
        Vector row2 = new Vector();
        // Вектор с заголовками
        Vector columnNames = new Vector();
        // Наполнение данными
        for (int i = 0; i < 3; i++)
        {
            row1.add("Cell 1 : " + i);
            row2.add("Cell 2 : " + i);
            columnNames.add("Column # " + i);
        }
        data.add(row1);
        data.add(row2);
        JTable table3 = new JTable(data, columnNames);
        // Добавляем таблицы в панель с блочным расположением
        Box contents = new Box(BoxLayout.Y_AXIS);
        contents.add(new JScrollPane(table1));
        contents.add(new JScrollPane(table2));
        contents.add(table3);
        // Выводим окно на экран
        setContentPane(contents);
        setSize(350, 400);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new SimpleTables();
    }
}