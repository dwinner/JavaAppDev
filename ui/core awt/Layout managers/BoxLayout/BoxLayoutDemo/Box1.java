// Блочное расположение
import javax.swing.*;
import java.awt.*;

public class Box1 extends JFrame
{
    public Box1()
    {
        super("Box1-Y");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Получаем панель содержимого
        Container c = getContentPane();
        
        // Устанавливаем блочное расположение по оси Y (столбиком)
        BoxLayout boxy = new BoxLayout(c, BoxLayout.Y_AXIS);
        c.setLayout(boxy);
        
        // Добавляем компоненты
        c.add(new JButton("Один"));
        c.add(new JButton("Два"));
        c.add(new JButton("Три"));
        
        // Выводим окно на экран
        setVisible(true);
    }

    public static class Box2 extends JFrame
    {
        public Box2()
        {
            super("Box2-X");
            
            // Устанавливаем размер и позицию окна
            setSize(400, 200);
            setLocation(100, 100);
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            
            // Получаем панель содержимого
            Container c = getContentPane();
            
            // Устанавливаем блочное расположение по оси X (полоской)
            BoxLayout boxx = new BoxLayout(c, BoxLayout.X_AXIS);
            c.setLayout(boxx);
            
            // Добавляем компоненты
            c.add(new JButton("One"));
            c.add(new JButton("Two"));
            c.add(new JButton("Three"));
            
            // Выводим окно на экран
            setVisible(true);
        }
    }

    public static void main(String[] args)
    {
        new Box1();
        new Box2();
    }
}