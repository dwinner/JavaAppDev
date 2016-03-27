// Создание вложенных меню любой сложности
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class CascadeMenus extends JFrame
{
    public CascadeMenus()
    {
        super("Cascade Menus");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Создаем строку главного меню
        JMenuBar menuBar = new JMenuBar();
        // Создаем выпадающее меню
        JMenu text = new JMenu("Текст");
        // И несколько вложенных меню
        JMenu style = new JMenu("Стиль");
        JMenuItem bold = new JMenuItem("Жирный");
        JMenuItem italic = new JMenuItem("Курсив");
        JMenu font = new JMenu("Шрифт");
        JMenuItem arial = new JMenuItem("Arial");
        JMenuItem times = new JMenuItem("Times");
        font.add(arial);
        font.add(times);
        // Размещаем все в нужном порядке
        style.add(bold);
        style.add(italic);
        style.addSeparator();
        style.add(font);
        text.add(style);
        menuBar.add(text);
        // Помещаем меню в окно
        setJMenuBar(menuBar);
        // Разделитель может быть полезен не только в меню
        getContentPane().add(new JSeparator(SwingConstants.VERTICAL), BorderLayout.WEST);
        // Выводим окно на экран
        setSize(100, 200);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new CascadeMenus();
    }

}