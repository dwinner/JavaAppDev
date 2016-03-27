// Поддержка кнопками клавиатурных мнемоник
import javax.swing.*;
import java.awt.*;

public class ButtonMnemonics extends JFrame
{    
    public ButtonMnemonics()
    {
        super("Button Mnemonics");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Получаем панель содержимого
        Container c = getContentPane();
        
        // Используем последовательное расположение
        c.setLayout(new FlowLayout());
        
        // Создаем кнопку
        JButton button = new JButton("Нажмите на меня!");
        
        // Мнемоника (русский символ)
        button.setMnemonic('Н');
        c.add(button);
        
        // Ещё одна кнопка, только надпись на английском
        button = new JButton("All Right!");
        button.setMnemonic('L');
        button.setToolTipText("Жмите смело");
        button.setDisplayedMnemonicIndex(2);
        c.add(button);
        
        // Выводим окно на экран
        pack();
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new ButtonMnemonics();
    }

}