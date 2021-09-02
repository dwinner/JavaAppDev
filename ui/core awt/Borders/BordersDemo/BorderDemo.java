// Демонстрация использования рамки, состоящей из линий, и рельефного обрамления.

import java.awt.*;
import javax.swing.*;

public class BorderDemo
{
    BorderDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Use Line and Etched Borders");

        // Связывание с контейнером диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начального размера фрейма.
        jfrm.setSize(280, 90);

        // Завершение программы по закрытию пользователем приложения.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание метки и установка для нее рамки, состоящей из линий.
        JLabel jlab = new JLabel(" This uses a line border. ");
        // Связывание обрамления с jlab.
        jlab.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Создание второй метки и установка для нее рельефной рамки.
        JLabel jlab2 = new JLabel(" This uses an etched border. ");
        // Связывание обрамления с jlab2.
        jlab2.setBorder(BorderFactory.createEtchedBorder());

        // Включение меток в состав панели содержимого.
        jfrm.getContentPane().add(jlab);
        jfrm.getContentPane().add(jlab2);

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
                new BorderDemo();
            }
        });
    }
}