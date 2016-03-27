// Использование переключателей
import javax.swing.*;
import java.awt.*;

public class RadioButtons extends JFrame
{    
    public RadioButtons()
    {
        super("Radio Buttons");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Получаем панель содержимого
        Container c = getContentPane();
        
        // Используем последовательное расположение
        c.setLayout(new FlowLayout());
        
        // Отдельный переключатель
        JRadioButton r1 = new JRadioButton("Сам по себе");
        
        // Группа связанных переключателей в своей собственной панели
        JPanel panel = new JPanel(new GridLayout(0, 1, 0, 5));
        panel.setBorder(BorderFactory.createTitledBorder("Внешний вид"));
        ButtonGroup bg = new ButtonGroup();
        String[] names =
        {
            "Внешний вид Java",
            "MS Windows",
            "CDE/Motif"
        };
        JRadioButton radio;
        for (int i = 0; i < names.length; i++)
        {
            radio = new JRadioButton(names[i]);
            panel.add(radio);
            bg.add(radio);
        }
        
        // Добавляем все в контейнер
        c.add(r1);
        c.add(panel);
        
        // Выводим окно на экран
        pack();
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new RadioButtons();
    }

}