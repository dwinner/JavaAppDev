// Создание системы меню в Swing
import javax.swing.*;
import java.awt.event.ActionEvent;

public class MenuSystem extends JFrame
{    
    public MenuSystem()
    {
        super("Menu System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Создаем строку главного меню JMenuBar
        JMenuBar menuBar = new JMenuBar();
        // Добавляем в нее выпадающие меню
        menuBar.add(createFileMenu());
        menuBar.add(createWhoMenu());
        // И устанавливаем ее в качестве меню нашего окна
        setJMenuBar(menuBar);
        // Выводим окно на экран
        setSize(300, 200);
        setVisible(true);
    }
    
    /**
     * Создает меню "Файл"
     * @return Ссылка на созданное меню
     */
    private JMenu createFileMenu()
    {
        // Создаем выпадающее меню, которое будет содержать обычные элементы меню
        JMenu file = new JMenu("File");
        // Элемент меню со значком
        JMenuItem open = new JMenuItem("Открыть", new ImageIcon("open.gif"));
        // Элемент меню - команда
        JMenuItem exit = new JMenuItem(new ExitAction());
        // Добавляем все в меню
        file.add(open);
        file.addSeparator();    // Разделитель
        file.add(exit);
        return file;
    }
    
    /**
     * Создаем забавное меню
     * @return Ссылка на созданное меню
     */
    private JMenu createWhoMenu()
    {
        // Создаем выпадающее меню
        JMenu who = new JMenu("Кто вы?");
        // Элементы меню - флажки
        JCheckBoxMenuItem clever = new JCheckBoxMenuItem("Умный");
        JCheckBoxMenuItem smart = new JCheckBoxMenuItem("Красивый");
        JCheckBoxMenuItem tender = new JCheckBoxMenuItem("Нежный");
        // Элементы меню - переключатели
        JRadioButtonMenuItem male = new JRadioButtonMenuItem("Мужчина");
        JRadioButtonMenuItem female = new JRadioButtonMenuItem("Женщина");
        // Организуем переключатели в логическую группу
        ButtonGroup bg = new ButtonGroup();
        bg.add(male);
        bg.add(female);
        // Добавляем все в меню
        who.add(clever);
        who.add(smart);
        who.add(tender);
        // Разделитель можно создать и явно
        who.add(new JSeparator());
        who.add(male);
        who.add(female);
        return who;
    }
    
    /**
     * Команда выхода из приложения
     */
    private class ExitAction extends AbstractAction
    {
        ExitAction()
        {
            putValue(Action.NAME, "Выход");
        }
        public void actionPerformed(ActionEvent ae) {
            System.exit(0);
        }
    }
    
    public static void main(String[] args)
    {
        new MenuSystem();
    }

}