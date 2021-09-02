/*
 * Пример использования компонента JTabbedPane.
 */

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;
import javax.swing.SwingUtilities;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class TabbedPaneDemo
{
    public TabbedPaneDemo()
    {
        // Создание нового контейнера JFrame. Для него принимается
        // диспетчер компоновки по умолчанию, т.е. BorderLayout.
        final JFrame jfrm = new JFrame("Tabbed Pane Demo");

        // Установка начальных размеров фрейма.
        jfrm.setSize(380, 150);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание панели с вкладками.
        JTabbedPane jtp = new JTabbedPane();

        // Добавление вкладок к панели.
        jtp.addTab("File Manager", new JLabel(" This is the File Manager tab."));
        jtp.addTab("Performance", new JLabel(" This is the Performance tab."));
        jtp.addTab("Reports", new JLabel(" This is the Reports tab."));
        jtp.addTab("Customize", new JLabel(" This is the Customize tab."));

        /*
         * В качестве бонуса добавим поддержку обработки событий изменения состояния для панели вкладок: Изменяем
         * заголовок JFrame в соответствии с выбранной вкладкой
         */
        jtp.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent ce)
            {
                JTabbedPane jtpFrom = (JTabbedPane) ce.getSource();
                String selTitle = jtpFrom.getTitleAt(jtpFrom.getSelectedIndex());
                jfrm.setTitle(selTitle);
            }
        });

        // Включение панели с вкладками в состав панели содержимого.
        jfrm.getContentPane().add(jtp);

        // Отображение фрейма.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // Создание фрейма в потоке обработки событий.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new TabbedPaneDemo();
            }
        });
    }
}