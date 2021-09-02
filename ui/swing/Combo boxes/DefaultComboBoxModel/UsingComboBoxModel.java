// Использование стандартной модели раскрывающихся списков
import javax.swing.*;
import java.awt.event.*;

public class UsingComboBoxModel extends JFrame
{    
    // Наша стандартная модель
    private DefaultComboBoxModel cbm;
    
    public UsingComboBoxModel()
    {
        super("Using Combo Box Model");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Создаем стандартную модель и наполняем ее данными
        cbm = new DefaultComboBoxModel();
        for (int i = 0; i < 20; i++)
        {
            cbm.addElement("Combo box element: " + i);
        }
        // Меняем выбранный элемент
        cbm.setSelectedItem(cbm.getElementAt(4));
        // Список на основе нашей модели
        JComboBox combo = new JComboBox(cbm);
        combo.setMaximumRowCount(5);
        // Стандартная модель позволяет динамически манипулировать данными
        JButton add = new JButton("Добавить");
        add.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                // Случайно выбираем позиции
                int pos = (int) Math.random() * cbm.getSize();
                cbm.insertElementAt("New", pos);
            }
        });
        // Добавляем список и кнопку в панель
        JPanel contents = new JPanel();
        contents.add(combo);
        contents.add(add);
        // Выводим окно на экран
        setContentPane(contents);
        setSize(400, 200);
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new UsingComboBoxModel();
    }

}