// Клавиатурные комбинации и мнемоники для меню Swing
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class GoodMenu extends JFrame
{
    public GoodMenu()
    {
        super("Good Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Создаем строку главного меню
        JMenuBar menuBar = new JMenuBar();
        // Некоторые весьма часто встречающиеся выпадающие меню
        menuBar.add(createFileMenu());
        menuBar.add(createEditMenu());
        // Помещаем меню в наше окно
        setJMenuBar(menuBar);
        // Выводим окно на экран
        setSize(100, 200);
        setVisible(true);
    }

    /**
     * Создание меню "Файл"
     * <p/>
     * @return Ссылка на меню
     */
    private JMenu createFileMenu()
    {
        // Выпадающее меню
        JMenu file = new JMenu("File");
        file.setMnemonic('F');
        // Пункт меню "Открыть"
        JMenuItem open = new JMenuItem("Open");
        open.setMnemonic('O');
        // Устанавливаем клавишу быстрого доступа
        open.setAccelerator(KeyStroke.getKeyStroke('O', KeyEvent.CTRL_MASK));
        // Пункт меню "Сохранить"
        JMenuItem save = new JMenuItem("Save");
        save.setMnemonic('S');
        save.setAccelerator(KeyStroke.getKeyStroke('S', KeyEvent.CTRL_MASK));
        // Добавляем все в меню
        file.add(open);
        file.add(save);
        return file;
    }

    private JMenu createEditMenu()
    {
        // Выпадающее меню
        JMenu edit = new JMenu("Edit");
        edit.setMnemonic('E');
        // Пункт меню "Вырезать"
        JMenuItem cut = new JMenuItem("Cut");
        cut.setMnemonic('C');
        // cut.setAccelerator(KeyStroke.getKeyStroke("Ctrl C"));
        cut.setAccelerator(KeyStroke.getKeyStroke('C', KeyEvent.CTRL_MASK));
        // Пункт меню "Копировать"
        JMenuItem copy = new JMenuItem("Copy");
        copy.setMnemonic('K');
        copy.setAccelerator(KeyStroke.getKeyStroke('C', KeyEvent.CTRL_MASK | KeyEvent.ALT_MASK));
        // Готово
        edit.add(cut);
        edit.add(copy);
        return edit;
    }

    public static void main(String[] args)
    {
        new GoodMenu();
    }

}