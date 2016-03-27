// Пример, демонстрирующий использование строк подсказки.

import java.awt.FlowLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class ToolTipDemo
{
    public ToolTipDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Add Tooltips");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начальных размеров фрейма.
        jfrm.setSize(300, 150);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание двух кнопок.
        JButton jbtnAlpha = new JButton("Alpha");
        JButton jbtnBeta = new JButton("Beta");

        // Установка строк подсказки для кнопок.
        jbtnAlpha.setToolTipText("This activates the Alpha option.");
        jbtnBeta.setToolTipText("This activates the Beta option.");

        jfrm.getContentPane().add(jbtnAlpha);
        jfrm.getContentPane().add(jbtnBeta);

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
                new ToolTipDemo();
            }
        });
    }
}