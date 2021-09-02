// Пример использования раскрывающегося списка.

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ComboBoxDemo
{
    private JComboBox<String> jcbb;
    private JLabel jlab;

    // Создание массива с названиями сортов яблок.
    private String apples[] =
    {
        "Winesap",
        "Cortland",
        "Red Delicious",
        "Golden Delicious",
        "Gala",
		"Fuji",
        "Granny Smith",
        "Jonathan"
    };

    public ComboBoxDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("JComboBox Demo");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начальных размеров фрейма.
        jfrm.setSize(220, 240);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание компонента JComboBox на основе массива строк.
        jcbb = new JComboBox<String>(apples);

        // Создание метки, отображающей выбор пользователя.
        jlab = new JLabel();

        // Связывание обработчика событий с раскрывающимся списком.
        jcbb.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent le)
            {
                // Получение ссылки на выбранный пункт.
                String item = (String) jcbb.getSelectedItem();
                // Отображение выбранного пункта.
                jlab.setText("Current selection " + item);
            }
        });

        // Первоначально в списке выбирается первый пункт.
        jcbb.setSelectedIndex(0);

        // Добавление раскрывающегося списка и метки к панели содержимого.
        jfrm.getContentPane().add(jcbb);
        jfrm.getContentPane().add(jlab);

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
                new ComboBoxDemo();
            }
        });
    }
}