// Возможности строки меню JMenuBar
import javax.swing.*;

public class TrickyMenuBar extends JFrame
{    
    public TrickyMenuBar()
    {
        super("Tricky menu bar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Создаем строку главного меню
        JMenuBar menuBar = new JMenuBar();
        // Добавляем в нее выпадающее меню
        menuBar.add(new JMenu("Main"));
        menuBar.add(new JMenu("Правка"));
        // Мы знаем, что используется блочное расположение,
        // так что заполнитель вполне уместен
        menuBar.add(Box.createHorizontalGlue());
        // Теперь помещаем в строку меню не выпадающее меню,
        // а надпись со значком
        JLabel icon = new JLabel(new ImageIcon("load.gif"));
        icon.setBorder(BorderFactory.createLoweredBevelBorder());
        menuBar.add(icon);
        // Помещаем меню в наше окно
        setJMenuBar(menuBar);
        // Выведем наше окно на экран
        setSize(100, 200);
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new TrickyMenuBar();
    }

}