// Демонстрация применения шаблона проектирования Command для действий Swing.

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import javax.swing.*;

public class ActionSample extends JFrame
{
    // Действия Swing
    private Action sampleAction;
    private Action exitAction;

    // Конструктор ActionSample
    public ActionSample()
    {
        super("Using Actions");

        // Создание подкласса AbstractAction для sampleAction
        sampleAction = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                // Отображение сообщения, указывающего на вызов sampleAction
                JOptionPane.showMessageDialog(ActionSample.this, "The sampleAction was invoked");
                // Разрешение действия exitAction и активация соответствующих GUI.
                exitAction.setEnabled(true);
            }

        };

        // Задание имени действия
        sampleAction.putValue(Action.NAME, "Sample Action");

        // Установка значка для действия
        sampleAction.putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource("images/help.gif")));

        // Задание текста экранной подсказки
        sampleAction.putValue(Action.SHORT_DESCRIPTION, "A Sample Action");

        // Задание "горячей" клавиши для действия
        sampleAction.putValue(Action.MNEMONIC_KEY, new Integer('S'));

        // Создание подкласса AbstractAction для действия exitAction
        exitAction = new AbstractAction()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                // Отображение сообщения, указывающего на вызов exitAction
                JOptionPane.showMessageDialog(ActionSample.this, "The exitAction was invoked");
                System.exit(0);
            }

        };

        // Установка имени действия
        exitAction.putValue(Action.NAME, "Exit");

        // Задание значка для действия
        exitAction.putValue(Action.SMALL_ICON, new ImageIcon(getClass().getResource("images/exit.gif")));

        // Задание текста экранной подсказки
        exitAction.putValue(Action.SHORT_DESCRIPTION, "Exit Application");

        // Задание "горячей" клавиши для действия
        exitAction.putValue(Action.MNEMONIC_KEY, new Integer('x'));

        // Запрет действия exitAction и дезактивация соответствующих компонентов GUI
        exitAction.setEnabled(false);

        // Создание меню File
        JMenu fileMenu = new JMenu("File");

        // Добавление sampleAction и exitAction в меню File для создания элементов
        // меню JMenuItem для каждого действия
        fileMenu.add(sampleAction);
        fileMenu.add(exitAction);

        fileMenu.setMnemonic('F');

        // Создание объекта JMenuBar и добавление в меню File
        JMenuBar menuBar = new JMenuBar();
        menuBar.add(fileMenu);
        setJMenuBar(menuBar);

        // Создание панели JToolBar
        JToolBar toolBar = new JToolBar();

        // Добавление sampleAction и exitAction в панель JToolBar для создания
        // кнопок JButtons для каждого из действий
        toolBar.add(sampleAction);
        toolBar.add(exitAction);

        // Создание объекта JButton и задание в качестве действия sampleAction
        JButton sampleButton = new JButton();
        sampleButton.setAction(sampleAction);
        // Создание объекта JButton и задание в качестве действия exitAction
        JButton exitButton = new JButton(exitAction);

        // Размещение кнопок JButtons на панели JPanel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(sampleButton);
        buttonPanel.add(exitButton);
        // Добавление панелей toolBar и buttonPanel в панель JFrame
        Container container = getContentPane();
        container.add(toolBar, BorderLayout.NORTH);
        container.add(buttonPanel, BorderLayout.CENTER);
    }

    // Выполнение
    public static void main(String[] args)
    {
        ActionSample sample = new ActionSample();
        sample.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        sample.pack();
        sample.setVisible(true);
    }

}