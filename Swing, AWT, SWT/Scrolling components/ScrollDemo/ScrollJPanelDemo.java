// Использование компонента JScrollPane для прокрутки содержимого JPanel.

import java.awt.*;
import javax.swing.*;

public class ScrollJPanelDemo
{
    public ScrollJPanelDemo()
    {
        // Создание нового контейнера JFrame.
        // Для него принимается диспетчер компоновки по умолчанию.
        JFrame jfrm = new JFrame("Scroll a JPanel");

        // Установка начальных размеров фрейма.
        jfrm.setSize(280, 130);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание метки.
        JLabel jlabOptions = new JLabel("Select one or more options: ");

        // Создание флажков опций.
        JCheckBox jcbOpt1 = new JCheckBox("Option One");
        JCheckBox jcbOpt2 = new JCheckBox("Option Two");
        JCheckBox jcbOpt3 = new JCheckBox("Option Three");
        JCheckBox jcbOpt4 = new JCheckBox("Option Four");
        JCheckBox jcbOpt5 = new JCheckBox("Option Five");

        // В данном примере обработчики событий не используются.

        // Создание объекта JPanel, в котором будут содержаться флажки опций.
        JPanel jpnl = new JPanel();
        jpnl.setLayout(new GridLayout(6, 1));
        jpnl.setOpaque(true);

        // Включение флажков опций и метки в состав JPanel.
        jpnl.add(jlabOptions);
        jpnl.add(jcbOpt1);
        jpnl.add(jcbOpt2);
        jpnl.add(jcbOpt3);
        jpnl.add(jcbOpt4);
        jpnl.add(jcbOpt5);

        // Создание панели с прокруткой.
        JScrollPane jscrlp = new JScrollPane(jpnl);

        // Добавление панели с прокруткой к фрейму.
        jfrm.getContentPane().add(jscrlp);

        // Отображение фрейма.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // Создание фрейма в потоке обработки событий.
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new ScrollJPanelDemo();
            }
        });
    }
}