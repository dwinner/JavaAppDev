// Создание простых раскрывающихся списков
import javax.swing.*;
import java.util.*;

public class SimpleComboBoxes extends JFrame
{    
    // Массив с элементами списка
    public String[] elements = new String[]
    {
        "Обаятельный",
        "Умный",
        "Нежный",
        "Сильный"
    };
    
    public SimpleComboBoxes()
    {
        super("Simple Combo Boxes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Создаем пару раскрывающихся списков
        JComboBox combo1 = new JComboBox(elements);
        // Задаем прототип элемента списка
        combo1.setPrototypeDisplayValue("Длинный элемент");
        // Второй раскрывающийся список
        Vector data = new Vector();
        for (int i = 0; i < 10; i++)
        {
            data.add("Element #: " + i);
        }
        JComboBox combo2 = new JComboBox(data);
        // Делаем его редактируемым
        combo2.setEditable(true);
        combo2.setMaximumRowCount(6);
        // Добавляем списки в панель и выводим окно на экран
        JPanel contents = new JPanel();
        contents.add(combo1);
        contents.add(combo2);
        setContentPane(contents);
        setSize(100, 200);
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new SimpleComboBoxes();
    }

}