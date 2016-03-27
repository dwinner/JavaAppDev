// Настройка некоторых простых свойств панелей прокрутки
import javax.swing.*;
import java.awt.Color;

public class SimpleScrollPanes extends JFrame
{
    public SimpleScrollPanes()
    {
        super("Simple Scroll Panes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Надпись
        JLabel label = new JLabel(new ImageIcon("painting.gif"));
        // Особый конструктор панели прокрутки
        JScrollPane scrollPane = new JScrollPane(
                label/*,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
                JScrollPane.VERTICAL_SCROLLBAR_ALWAYS*/
        );
        // Некоторые свойства
        scrollPane.setViewportBorder(BorderFactory.createLineBorder(Color.BLUE));
        scrollPane.setWheelScrollingEnabled(false);
        // Выводим окно на экран
        getContentPane().add(scrollPane);
        setSize(400, 300);
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new SimpleScrollPanes();
    }

}