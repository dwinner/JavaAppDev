// Использование распорок в блочном расположении
import javax.swing.*;
import java.awt.BorderLayout;

public class BoxStruts extends JFrame
{    
    public BoxStruts()
    {
        super("Box Struts");
        setSize(250, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Панель с вертикальным блочным расположением
        Box p = Box.createVerticalBox();
        p.add(new JButton("First"));
        
        // Создание вертикальной распорки
        p.add(Box.createVerticalStrut(5));
        
        // Новый компонент и распорка другого размера
        p.add(new JButton("Second"));
        p.add(Box.createVerticalStrut(5));
        p.add(new JButton("Third"));
        
        // Панель с горизонтальным блочным расположением
        Box p2 = Box.createHorizontalBox();
        
        // Распорки можно ставить и перед компонентами
        p2.add(Box.createHorizontalStrut(10));
        p2.add(new JButton("One"));
        
        // Создание горизонтальной распорки
        p2.add(Box.createHorizontalStrut(25));
        p2.add(new JButton("Two"));
        
        // Добавляем панели на север и юг окна
        getContentPane().add(p, BorderLayout.NORTH);
        getContentPane().add(p2, BorderLayout.SOUTH);
        
        // Выводим окно на экран
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new BoxStruts();
    }

}