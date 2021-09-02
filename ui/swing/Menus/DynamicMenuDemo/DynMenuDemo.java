// Динамическое добавление и удаление пунктов меню.

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.SwingUtilities;

public class DynMenuDemo implements ActionListener
{
    private JLabel jlab;
    private JMenuItem jmiYellow;
    private JMenuItem jmiPurple;
    private JMenuItem jmiOrange;
    private JMenu jmColors;

    public DynMenuDemo()
    {
        JFrame jfrm = new JFrame("Dynamic Menu Demo");
        jfrm.getContentPane().setLayout(new FlowLayout());
        jfrm.setSize(220, 200);
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jlab = new JLabel();
        JMenuBar jmb = new JMenuBar();

        JMenu jmFile = new JMenu("File");
        JMenuItem jmiOpen = new JMenuItem("Open");
        JMenuItem jmiClose = new JMenuItem("Close");
        JMenuItem jmiSave = new JMenuItem("Save");
        JMenuItem jmiExit = new JMenuItem("Exit");
        jmFile.add(jmiOpen);
        jmFile.add(jmiClose);
        jmFile.add(jmiSave);
        jmFile.addSeparator();
        jmFile.add(jmiExit);
        jmb.add(jmFile);

        JMenu jmOptions = new JMenu("Options");

        jmColors = new JMenu("Colors");
        JMenuItem jmiRed = new JMenuItem("Red");
        JMenuItem jmiGreen = new JMenuItem("Green");
        JMenuItem jmiBlue = new JMenuItem("Blue");
        jmColors.add(jmiRed);
        jmColors.add(jmiGreen);
        jmColors.add(jmiBlue);

        // Создание пункта меню More Colors / Less Colors.
        JMenuItem jmiMoreLess = new JMenuItem("More Colors");
        jmColors.add(jmiMoreLess);
        jmOptions.add(jmColors);

        // Создание дополнительных цветов. Соответствующие им пункты будут
        // добавляться и удаляться по мере необходимости.
        jmiYellow = new JMenuItem("Yellow");
        jmiPurple = new JMenuItem("Purple");
        jmiOrange = new JMenuItem("Orange");

        JMenu jmPriority = new JMenu("Priority");
        JMenuItem jmiHigh = new JMenuItem("High");
        JMenuItem jmiLow = new JMenuItem("Low");
        jmPriority.add(jmiHigh);
        jmPriority.add(jmiLow);

        jmOptions.add(jmPriority);

        JMenuItem jmiReset = new JMenuItem("Reset");
        jmOptions.addSeparator();
        jmOptions.add(jmiReset);

        jmb.add(jmOptions);

        JMenu jmHelp = new JMenu("Help");
        JMenuItem jmiAbout = new JMenuItem("About");
        jmHelp.add(jmiAbout);
        jmb.add(jmHelp);

        // Добавление обработчиков событий для элементов меню.
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

        // Связывание обработчиков событий с пунктами меню,
        // соответствующих дополнительным цветам.
        jmiMoreLess.addActionListener(this);
        jmiYellow.addActionListener(this);
        jmiPurple.addActionListener(this);
        jmiOrange.addActionListener(this);

        jfrm.getContentPane().add(jlab);

        jfrm.setJMenuBar(jmb);

        jfrm.setVisible(true);
    }

    // Обработка событий действий, соответствующих пукнтам меню.
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        // Получение команды действия для выбранного пункта меню.
        String comStr = ae.getActionCommand();
        // Если пользователь выбрал пункт Exit, выполнение программы должно завершиться.
        switch (comStr)
        {
            case "Exit":
                System.exit(0);
                break;
            case "More Colors":
                {
                    jmColors.add(jmiYellow);
                    jmColors.add(jmiPurple);
                    jmColors.add(jmiOrange);
                    JMenuItem mi = (JMenuItem) ae.getSource();
                    mi.setText("Less Colors");
                    break;
                }
            case "Less Colors":
                {
                    jmColors.remove(jmiYellow);
                    jmColors.remove(jmiPurple);
                    jmColors.remove(jmiOrange);
                    JMenuItem mi = (JMenuItem) ae.getSource();
                    mi.setText("More Colors");
                    break;
                }
        }

        // В противном случае выводится информация о выборе пользователя.
        jlab.setText(comStr + " Selected");
    }

    public static void main(String args[])
    {
        // Создание фрейма в потоке обработки событий.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new DynMenuDemo();
            }

        });
    }

}