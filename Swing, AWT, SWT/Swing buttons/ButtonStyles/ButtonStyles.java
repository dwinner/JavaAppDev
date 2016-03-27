// Изменение внешнего вида кнопок JButton с помощью значков, цветов, рамок и т.д.
import javax.swing.*;
import java.awt.*;

public class ButtonStyles extends JFrame
{    
    public ButtonStyles()
    {
        super("Button Styles");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Используем последовательное расположение
        Container c = getContentPane();
        c.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 10));
        
        // Самая простая кнопка
        JButton button = new JButton("Обычная кнопка");
        c.add(button);
        
        // Кнопка со значками на все случаи жизни
        button = new JButton();
        button.setIcon(new ImageIcon("images.jpg"));
        button.setRolloverIcon(new ImageIcon("images2.jpg"));
        button.setPressedIcon(new ImageIcon("java.png"));
        button.setDisabledIcon(new ImageIcon("java-icon.png"));
        
        // Для такой кнопки лучше убрать все ненужные рамки и закраску
        button.setBorderPainted(false);
        button.setFocusPainted(false);
        button.setContentAreaFilled(false);
        c.add(button);
        
        // Кнопка с измененным цветом и HTML-текстом
        button = new JButton("<html><h2><font color=\"yellow\">Зеленая кнопка</font></h2>");
        button.setBackground(Color.green);
        c.add(button);
        
        // Изменение выравнивания текста и изображения
        button = new JButton("Изменение выравнивания", new ImageIcon("chrome.png"));
        button.setMargin(new Insets(10, 10, 10, 10));
        button.setVerticalAlignment(SwingConstants.TOP);
        button.setHorizontalAlignment(SwingConstants.RIGHT);
        button.setHorizontalTextPosition(SwingConstants.LEFT);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setIconTextGap(10);
        
        // Сделаем кнопку большой, чтобы увидеть выравнивание
        button.setPreferredSize(new Dimension(300, 100));
        c.add(button);
        
        // Отключенная кнопка
        button = new JButton("Выключено");
        button.setEnabled(false);
        c.add(button);
        
        // Выводим окно на экран
        setSize(400, 350);
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new ButtonStyles();
    }

}