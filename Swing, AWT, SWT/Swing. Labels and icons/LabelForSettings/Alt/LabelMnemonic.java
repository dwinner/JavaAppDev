// Использование надписей для вывода мнемоник
import javax.swing.*;
import java.awt.*;

public class LabelMnemonic extends JFrame
{
    public LabelMnemonic()
    {
        super("Label Mnemonic");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Добавим пару текстовых полей
        JPanel contents = new JPanel(new GridLayout(2, 2));
        JTextField tf = new JTextField(10);
        JLabel label = new JLabel("Ваше имя (Name)");
        // Настройка мнемоники
        label.setLabelFor(tf);
        label.setDisplayedMnemonic('N');
        // Добавляем компоненты в таблицу
        contents.add(new JLabel("Ваша фамилия"));
        contents.add(new JTextField(10));
        contents.add(label);
        contents.add(tf);
        // Выведем окно на экран
        setContentPane(contents);
        pack();
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new LabelMnemonic();
    }

}