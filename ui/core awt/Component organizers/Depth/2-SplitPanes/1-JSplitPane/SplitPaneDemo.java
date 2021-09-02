/*
 * Пример использования JSplitPane.
 */

import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JSplitPane;
import javax.swing.SwingUtilities;

public class SplitPaneDemo
{
    public SplitPaneDemo()
    {
        // Создание нового контейнера JFrame. Для него принимается диспетчер
        // компоновки по умолчанию.
        JFrame jfrm = new JFrame("Split Pane Demo");

        // Установка начальных размеров фрейма.
        jfrm.setSize(380, 150);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание двух меток, предназначенных для отображения в составе разделяемой панели.
        JLabel jlab = new JLabel(" Left side: ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        JLabel jlab2 = new JLabel(" Right side: ABCDEFGHIJKLMNOPQRSTUVWXYZ");

        // Установка минимальных размеров каждой метки. Этот шаг не является
        // абсолютно необходимым, но позволяет ограничить диапазон возможных
        // положений разделителя.
        jlab.setMinimumSize(new Dimension(90, 30));
        jlab2.setMinimumSize(new Dimension(90, 30));

        // Включение разделяемой панели, содержащей метки.
        JSplitPane jsp = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, true, jlab, jlab2);
        jsp.setOneTouchExpandable(true);

        // Включение разделяемой панели в состав панели содержимого.
        jfrm.getContentPane().add(jsp);

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
                new SplitPaneDemo();
            }
        });
    }
}