// Пример использования класса JColorChooser.

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class ColorChooserDemo
{
    private JLabel jlab;
    private JButton jbtnShow;

    public ColorChooserDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Color Chooser Demo");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начальных размеров фрейма.
        jfrm.setSize(400, 200);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание метки, отображающей выбор пользователя.
        jlab = new JLabel();

        // Создание кнопки, вызывающей отображение диалогового окна.
        jbtnShow = new JButton("Show Color Chooser");

        // Отображение окна выбора цвета при активизации кнопки Show Color Chooser.
        jbtnShow.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent le)
            {
                // Создание окна выбора цвета. В качестве первого параметра методу передается значение null.
                // В результате окно выводится по центру экрана. При отображении окна первоначально
                // выбирается красный цвет.
                Color color = JColorChooser.showDialog(null, "Choose Color", Color.RED);
                jlab.setText(color != null
                   ? "Selected color is " + color.toString()
                   : "Color selection was cancelled.");
            }

        });

        // Включение кнопки Show Color Chooser и метки в состав панели содержимого.
        jfrm.getContentPane().add(jbtnShow);
        jfrm.getContentPane().add(jlab);

        // Отображение фрейма.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // Фрейм создается в потоке обработки событий.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override public void run()
            {
                new ColorChooserDemo();
            }
        });
    }

}