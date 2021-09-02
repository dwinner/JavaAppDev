// Использование флажков JCheckBox
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Checkboxes extends JFrame
{    
    public Checkboxes()
    {
        super("Checkboxes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Получаем панель содержимого
        Container c = getContentPane();
        
        // Используем последовательное расположение
        c.setLayout(new FlowLayout());
        
        // Отдельный флажок
        JCheckBox ch1 = new JCheckBox("I love JFC", true);
        
        // Группа связанных флажков в своей собственной панели
        JPanel panel = new JPanel(new GridLayout(0, 1, 0, 7));
        panel.setBorder(BorderFactory.createTitledBorder("Мороженое"));
        String[] names =
        {
            "Крем Брюле",
            "Ром с изюмом",
            "Шоколадное"
        };
        JCheckBox check;
        for (int i = 0; i < names.length; i++)
        {
            check = new JCheckBox(names[i]);
            panel.add(check);
        }
        
        // Добавляем все в контейнер
        c.add(ch1);
        c.add(panel);
        
        // Выводим окно на экран
        pack();
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new Checkboxes();
    }

}