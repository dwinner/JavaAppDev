// Табличное расположение
import java.awt.*;
import javax.swing.*;

public class GridLayoutTest extends JFrame
{
    public GridLayoutTest()
    {
        super("Grid Layout");
        setSize(200, 200);
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Вспомогательная панель
        JPanel grid = new JPanel();
        // Первые два параметра конструктора GridLayout - количество строк и столбцов в таблице,
        // Вторые два - расстояние между ячейками по X и Y
        GridLayout gl = new GridLayout(2, 0, 5, 12);
        grid.setLayout(gl);
        // Создаем восемь кнопок
        for (int i = 0; i < 8; i++)
        {
            grid.add(new JButton("Button " + i));
        }
        // Помещаем нашу панель в центр окна
        getContentPane().add(grid);
        // Устанавливаем оптимальный размер
        pack();
        // Показываем окно
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new GridLayoutTest();
    }
}