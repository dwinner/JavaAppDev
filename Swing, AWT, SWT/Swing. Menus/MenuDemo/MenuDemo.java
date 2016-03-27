// Создание простой строки меню.

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import javax.swing.SwingUtilities;

public class MenuDemo implements ActionListener
{
    private JLabel jlab;

    public MenuDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Menu Demo");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начальных размеров фрейма.
        jfrm.setSize(220, 200);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание метки, с помощью которой отображается информация о выборе пользователя.
        jlab = new JLabel();

        // Создание строки меню.
        JMenuBar jmb = new JMenuBar();

        // Создание меню File.
        JMenu jmFile = new JMenu("File");

        // Связывание мнемонических обозначений и клавиш быстрого доступа с меню File.
        // Доступ к меню File можно получить с помощью клавиши <F>
        jmFile.setMnemonic(KeyEvent.VK_F);

        // Создание пунктов, которые будут находиться в составе меню File.
        // В каждом случае в качестве мнемонического обозначения используется первая
        // буква имени пункта. Для быстрого доступа используется та же клавиша, нажатая
        // в комбинации с клавишей <Ctrl>
        JMenuItem jmiOpen = new JMenuItem("Open", KeyEvent.VK_O);
        jmiOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        JMenuItem jmiClose = new JMenuItem("Close", KeyEvent.VK_C);
        jmiClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        JMenuItem jmiSave = new JMenuItem("Save", KeyEvent.VK_S);
        jmiSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        JMenuItem jmiExit = new JMenuItem("Exit", KeyEvent.VK_E);
        jmiExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));

        // Включение пунктов в состав меню File.
        jmFile.add(jmiOpen);
        jmFile.add(jmiClose);
        jmFile.add(jmiSave);
        jmFile.addSeparator();
        jmFile.add(jmiExit);
        jmb.add(jmFile);

        // Создание меню Options.
        JMenu jmOptions = new JMenu("Options");

        // Создание подменю Colors.
        JMenu jmColors = new JMenu("Colors");
        JMenuItem jmiRed = new JMenuItem("Red");
        JMenuItem jmiGreen = new JMenuItem("Green");
        JMenuItem jmiBlue = new JMenuItem("Blue");
        jmColors.add(jmiRed);
        jmColors.add(jmiGreen);
        jmColors.add(jmiBlue);
        jmOptions.add(jmColors);

        // Создание подменю Priority.
        JMenu jmPriority = new JMenu("Priority");
        JMenuItem jmiHigh = new JMenuItem("High");
        JMenuItem jmiLow = new JMenuItem("Low");
        jmPriority.add(jmiHigh);
        jmPriority.add(jmiLow);

        // Включение меню Priority в состав меню Options.
        jmOptions.add(jmPriority);

        // Создание пункта Reset и включение его в меню Options.
        JMenuItem jmiReset = new JMenuItem("Reset");
        jmOptions.addSeparator();
        jmOptions.add(jmiReset);

        // Связывание меню Options со строкой меню.
        jmb.add(jmOptions);

        // Создание меню Help и связывание его со строкой меню.
        JMenu jmHelp = new JMenu("Help");
        JMenuItem jmiAbout = new JMenuItem("About");
        jmHelp.add(jmiAbout);
        jmb.add(jmHelp);

        // Связывание обработчика событий с пунктами меню.
        jmiOpen.addActionListener(this);
        jmiClose.addActionListener(this);
        jmiSave.addActionListener(this);
        jmiExit.addActionListener(this);
        jmiRed.addActionListener(this);
        jmiGreen.addActionListener(this);
        jmiBlue.addActionListener(this);
        jmiHigh.addActionListener(this);
        jmiLow.addActionListener(this);
        jmiReset.addActionListener(this);
        jmiAbout.addActionListener(this);

        // Включение метки на панель содержимого.
        jfrm.getContentPane().add(jlab);

        // Включение меню в состав фрейма.
        jfrm.setJMenuBar(jmb);

        // Отображение фрейма.
        jfrm.setVisible(true);
    }

    // Обработка событий действия для пунктов меню.
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        // Получение команды действия, соответствующей выбранному пункту.
        String comStr = ae.getActionCommand();
        // Если пользователь выбрал пункт Exit меню File выполнение программы завершается.
        if (comStr.equals("Exit"))
        {
            System.exit(0);
        }
        // В противном случае отображается информация о выборе пользователя.
        jlab.setText(comStr + " Selected");
    }

    public static void main(String args[])
    {
        // Фрейм создается в потоке обработки событий.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new MenuDemo();
            }

        });
    }

}