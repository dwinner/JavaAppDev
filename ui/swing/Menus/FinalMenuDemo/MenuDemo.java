// Окончательный вариант программы MenuDemo.

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class MenuDemo implements ActionListener
{
    private JLabel jlab;
    private JMenuBar jmb;
    private JToolBar jtb;
    private JPopupMenu jpu;
    private DebugAction setAct;
    private DebugAction clearAct;
    private DebugAction resumeAct;

    public MenuDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Complete Menu Demo");
        // В данном случае используется диспетчер компоновки по умолчанию.
        // Установка начальных размеров фрейма.
        jfrm.setSize(360, 200);
        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Создание метки, с помощью которой отображается информация о выборе пользователя
        jlab = new JLabel();
        // Создание строки меню.
        jmb = new JMenuBar();
        // Создание меню File.
        makeFileMenu();
        // Создание действий для управления меню и панелью инструментов Debug.
        makeActions();
        // Создание панели инструментов.
        makeToolBar();
        // Создание меню Options.
        makeOptionsMenu();
        // Создание меню Help.
        makeHelpMenu();
        // Создание контекстного меню Edit.
        makeEditPUMenu();

        // Создание обработчика событий мыши, в котором будет проверяться признак,
        // разрешающий вывод контекстного меню.
        jfrm.getContentPane().addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent me)
            {
                if (me.isPopupTrigger())
                {
                    jpu.show(me.getComponent(), me.getX(), me.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent me)
            {
                if (me.isPopupTrigger())
                {
                    jpu.show(me.getComponent(), me.getX(), me.getY());
                }
            }

        });

        // Размещение метки в центре панели содержимого.
        jfrm.getContentPane().add(jlab, SwingConstants.CENTER);
        // Размещение панели инструментов в северной области панели содержимого.
        jfrm.getContentPane().add(jtb, BorderLayout.NORTH);
        // Включение строки меню в состав фрейма.
        jfrm.setJMenuBar(jmb);
        // Отображение фрейма.
        jfrm.setVisible(true);
    }

    /**
     * Обработка событий действия, связанных с пунктом меню. Данный обработчик не поддерживает
     * событий, генерируемых меню Debug и одноименной панелью инструментов.
     * <p/>
     * @param ae
     */
    @Override
    public void actionPerformed(ActionEvent ae)
    {
        // Получение команды действия, определяющей выбор пользователя.
        String comStr = ae.getActionCommand();
        // Если пользователь выбирает пункт меню Exit, выполнение программы завершается.
        if (comStr.equals("Exit"))
        {
            System.exit(0);
        }
        // В противном случае на экране отображается информация о выборе пользователя.
        jlab.setText(comStr + " Selected");
    }

    /**
     * Класс действия для меню Debug и одноименной панели инструментов.
     */
    private class DebugAction extends AbstractAction
    {
        DebugAction(String name, Icon image, int mnem, int accel, String tTip)
        {
            super(name, image);
            this.putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(accel, InputEvent.CTRL_MASK));
            this.putValue(MNEMONIC_KEY, new Integer(mnem));
            this.putValue(SHORT_DESCRIPTION, tTip);
        }

        /**
         * Обработка событий, связанных с панелью инструментов и меню Debug.
         * <p/>
         * @param ae
         */
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            String comStr = ae.getActionCommand();
            jlab.setText(comStr + " Selected");
            // Переключение состояния для Set Breakpoint и Clear Breakpoint.
            switch (comStr)
            {
                case "Set Breakpoint":
                    clearAct.setEnabled(true);
                    setAct.setEnabled(false);
                    break;
                case "Clear Breakpoint":
                    clearAct.setEnabled(false);
                    setAct.setEnabled(true);
                    break;
            }
        }

    }

    /**
     * Создание меню File и определение для него мнемонических обозначений и клавиш быстрого
     * доступа.
     */
    private void makeFileMenu()
    {
        JMenu jmFile = new JMenu("File");
        jmFile.setMnemonic(KeyEvent.VK_F);
        JMenuItem jmiOpen = new JMenuItem("Open", KeyEvent.VK_O);
        jmiOpen.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, InputEvent.CTRL_MASK));
        JMenuItem jmiClose = new JMenuItem("Close", KeyEvent.VK_C);
        jmiClose.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, InputEvent.CTRL_MASK));
        JMenuItem jmiSave = new JMenuItem("Save", KeyEvent.VK_S);
        jmiSave.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, InputEvent.CTRL_MASK));
        JMenuItem jmiExit = new JMenuItem("Exit", KeyEvent.VK_E);
        jmiExit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, InputEvent.CTRL_MASK));
        // Добавляем...
        jmFile.add(jmiOpen);
        jmFile.add(jmiClose);
        jmFile.add(jmiSave);
        jmFile.addSeparator();
        jmFile.add(jmiExit);
        jmb.add(jmFile);
        // Связывание обработчиков событий действия с меню File.
        jmiOpen.addActionListener(this);
        jmiClose.addActionListener(this);
        jmiSave.addActionListener(this);
        jmiExit.addActionListener(this);
    }

    /**
     * Создание меню Options.
     */
    private void makeOptionsMenu()
    {
        JMenu jmOptions = new JMenu("Options");
        // Создание подменю Colors.
        JMenu jmColors = new JMenu("Colors");
        // Использование флажков опций для выбора цвета. Данный подход
        // позволяет пользователю выбирать несколько цветов.
        JCheckBoxMenuItem jmiRed = new JCheckBoxMenuItem("Red");
        JCheckBoxMenuItem jmiGreen = new JCheckBoxMenuItem("Green");
        JCheckBoxMenuItem jmiBlue = new JCheckBoxMenuItem("Blue");
        // Включение пунктов в состав меню Colors.
        jmColors.add(jmiRed);
        jmColors.add(jmiGreen);
        jmColors.add(jmiBlue);
        jmOptions.add(jmColors);
        // Создание подменю Priority.
        JMenu jmPriority = new JMenu("Priority");
        // Для выбора свойств применяется переключатель опций. Благодаря использованию
        // пунктов данного типа пользователь видит, какое свойство установлено, и может
        // выбирать в каждый момент времени только одно значение. Заметьте, что кнопка
        // High изначально выбрана.
        JRadioButtonMenuItem jmiHigh = new JRadioButtonMenuItem("High", true);
        JRadioButtonMenuItem jmiLow = new JRadioButtonMenuItem("Low");
        // Включение пунктов в состав меню Priority.
        jmPriority.add(jmiHigh);
        jmPriority.add(jmiLow);
        jmOptions.add(jmPriority);
        // Объединение кнопок переключателя опций в группу
        ButtonGroup bg = new ButtonGroup();
        bg.add(jmiHigh);
        bg.add(jmiLow);
        // Создание подменю Debug, входящего в состав меню Options. 
        // Для создания пунктов меню используются действия.
        JMenu jmDebug = new JMenu("Debug");
        JMenuItem jmiSetBP = new JMenuItem(setAct);
        JMenuItem jmiClearBP = new JMenuItem(clearAct);
        JMenuItem jmiResume = new JMenuItem(resumeAct);
        // Включение пунктов в состав меню Debug.
        jmDebug.add(jmiSetBP);
        jmDebug.add(jmiClearBP);
        jmDebug.add(jmiResume);
        jmOptions.add(jmDebug);
        // Создание пункта меню Reset.
        JMenuItem jmiReset = new JMenuItem("Reset");
        jmOptions.addSeparator();
        jmOptions.add(jmiReset);
        // Связывание меню Options со строкой меню.
        jmb.add(jmOptions);
        // Связывание обработчиков событий действия с меню Options.
        // Эти обработчики не поддерживают событий меню Debug.
        jmiRed.addActionListener(this);
        jmiGreen.addActionListener(this);
        jmiBlue.addActionListener(this);
        jmiHigh.addActionListener(this);
        jmiLow.addActionListener(this);
        jmiReset.addActionListener(this);
    }

    private void makeHelpMenu()
    {
        JMenu jmHelp = new JMenu("Help");
        // Связывание пиктограммы с пунктом меню About.
        ImageIcon icon = new ImageIcon("AboutIcon.gif");
        JMenuItem jmiAbout = new JMenuItem("About", icon);
        jmiAbout.setToolTipText("Info about the MenuDemo program.");
        jmHelp.add(jmiAbout);
        jmb.add(jmHelp);
        // Определение обработчика событий для About.
        jmiAbout.addActionListener(this);
    }

    // Формирование действий для меню Debug и панели инструментов.
    private void makeActions()
    {
        // Загрузка изображений для действий.
        ImageIcon setIcon = new ImageIcon("setBP.gif");
        ImageIcon clearIcon = new ImageIcon("clearBP.gif");
        ImageIcon resumeIcon = new ImageIcon("resume.gif");
        // Создание действий.
        setAct = new DebugAction("Set Breakpoint", setIcon, KeyEvent.VK_S, KeyEvent.VK_B, "Set a breakpoint.");
        clearAct = new DebugAction("Clear Breakpoint", clearIcon, KeyEvent.VK_C, KeyEvent.VK_L, "Clear a breakpoint.");
        resumeAct = new DebugAction("Resume", resumeIcon, KeyEvent.VK_R, KeyEvent.VK_R, "Resume execution after breakpoint.");
        // Первоначально обращение к Clear Breakpoint запрещено.
        clearAct.setEnabled(false);
    }

    // Создание панели инструментов Debug.
    private void makeToolBar()
    {
        // Создание кнопок панели инструментов с использованием действий.
        JButton jbtnSet = new JButton(setAct);
        JButton jbtnClear = new JButton(clearAct);
        JButton jbtnResume = new JButton(resumeAct);
        // Создание панели инструментов Debug.
        jtb = new JToolBar("Breakpoints");
        // Включение кнопок в состав панели инструментов.
        jtb.add(jbtnSet);
        jtb.add(jbtnClear);
        jtb.add(jbtnResume);
    }

    // Создание контекстного меню Edit.
    private void makeEditPUMenu()
    {
        jpu = new JPopupMenu();
        // Создание пунктов контекстного меню
        JMenuItem jmiCut = new JMenuItem("Cut");
        JMenuItem jmiCopy = new JMenuItem("Copy");
        JMenuItem jmiPaste = new JMenuItem("Paste");
        // Включение пунктов в состав контекстного меню.
        jpu.add(jmiCut);
        jpu.add(jmiCopy);
        jpu.add(jmiPaste);
        // Связывание обработчиков событий с пунктами контекстного меню Edit.
        jmiCut.addActionListener(this);
        jmiCopy.addActionListener(this);
        jmiPaste.addActionListener(this);
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