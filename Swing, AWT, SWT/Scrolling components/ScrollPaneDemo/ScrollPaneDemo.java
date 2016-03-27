// Простой пример использования JScrollPane.

import javax.swing.*;

public class ScrollPaneDemo
{
    public ScrollPaneDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Use JScrollPane");

        // Установка начальных размеров фрейма.
        jfrm.setSize(200, 120);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание метки с HTML-содержимым большого объема.
        JLabel jlab =
                new JLabel("<html>JScrollPane simplifies what would<br />"
                + "otherwise be complicated tasks.<br />"
                + "It can be used to scroll any component<br />"
                + "or lightweight container. It is especially<br />"
                + "useful when scrolling tables, lists,<br />"
                + "or images.");

        // Создание панели с прокруткой и передача ей метки.
        JScrollPane jscrlp = new JScrollPane(jlab);

        // Включение панели с прокруткой в состав панели содержимого фрейма.
        jfrm.getContentPane().add(jscrlp);

        // Отображение фрейма.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // Фрейм создается в потоке обработки событий.
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new ScrollPaneDemo();
            }
        });
    }
}