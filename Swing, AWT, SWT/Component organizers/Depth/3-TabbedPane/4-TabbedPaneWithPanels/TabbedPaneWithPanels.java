/*
 * Использование JTabbedPane для отображения JPanel.
 */

import java.awt.GridLayout;
import javax.swing.*;

public class TabbedPaneWithPanels
{
    private JCheckBox jcbDVD;
    private JCheckBox jcbScanner;
    private JCheckBox jcbNtwrkRdy;
    private JCheckBox jcbWordProc;
    private JCheckBox jcbCompiler;
    private JCheckBox jcbDatabase;
    private JRadioButton jrbTower;
    private JRadioButton jrbNotebook;
    private JRadioButton jrbHandheld;

    public TabbedPaneWithPanels()
    {
        // Создание нового контейнера JFrame. В качестве диспетчера компоновки
        // принимается BorderLayout, связанный по умолчанию с панелью
        // содержимого данного контейнера.
        JFrame jfrm = new JFrame("Use JPanels with JTabbedPane");

        // Установка начальных размеров фрейма.
        jfrm.setSize(280, 140);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание кнопок переключателя опций для вкладки Style.
        jrbTower = new JRadioButton("Tower");
        jrbNotebook = new JRadioButton("Notebook");
        jrbHandheld = new JRadioButton("Handheld");
        ButtonGroup bg = new ButtonGroup();
        bg.add(jrbTower);
        bg.add(jrbNotebook);
        bg.add(jrbHandheld);

        // Создание объекта JPanel для размещения кнопок переключателя опций.
        JPanel jpnl = new JPanel();
        jpnl.setLayout(new GridLayout(3, 1));
        jpnl.setOpaque(true);

        // Включение кнопок переключателя опций в состав панели.
        jpnl.add(jrbTower);
        jpnl.add(jrbNotebook);
        jpnl.add(jrbHandheld);

        // Создание флажков опций для вкладки Options.
        jcbDVD = new JCheckBox("DVD Burner");
        jcbScanner = new JCheckBox("Scanner");
        jcbNtwrkRdy = new JCheckBox("Network Ready");

        // Создание объекта JPanel для хранения флажков опций.
        JPanel jpnl2 = new JPanel();
        jpnl2.setLayout(new GridLayout(3, 1));
        jpnl2.setOpaque(true);

        // Включение флажков опций в контейнер JPanel.
        jpnl2.add(jcbDVD);
        jpnl2.add(jcbScanner);
        jpnl2.add(jcbNtwrkRdy);

        // Создание флажков опций для вкладки Software.
        jcbWordProc = new JCheckBox("Word Processing");
        jcbCompiler = new JCheckBox("Program Development");
        jcbDatabase = new JCheckBox("Database");

        // Создание объекта JPanel для хранения флажков опций.
        JPanel jpnl3 = new JPanel();
        jpnl3.setLayout(new GridLayout(3, 1));
        jpnl3.setOpaque(true);

        // Включение флажков опций в контейнер JPanel.
        jpnl3.add(jcbWordProc);
        jpnl3.add(jcbCompiler);
        jpnl3.add(jcbDatabase);

        // Обработчики событий в программе не используются.

        // Создание панели с вкладками. Если ярлыки вкладок
        // не помещаются в одной строке, организуется их прокрутка.
        JTabbedPane jtp = new JTabbedPane(JTabbedPane.TOP, JTabbedPane.SCROLL_TAB_LAYOUT);

        // Включение контейнеров JPanel в состав панели с вкладками.
        jtp.addTab("Style", jpnl);
        jtp.addTab("Options", jpnl2);
        jtp.addTab("Software", jpnl3);

        // Добавление панели с вкладками к панели содержимого.
        jfrm.getContentPane().add(jtp);

        // Отображение фрейма.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // Фрейм создается в потоке обработки событий.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new TabbedPaneWithPanels();
            }
        });
    }
}