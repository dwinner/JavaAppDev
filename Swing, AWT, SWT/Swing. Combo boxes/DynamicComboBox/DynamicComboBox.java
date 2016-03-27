// Динамическое добавление и удаление пунктов раскрывающегося списка.

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class DynamicComboBox
{
    private JComboBox jcbb;
    private JLabel jlab;
    private JButton jbtnRemove;

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

    public DynamicComboBox()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Dynamic JComboBox");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начальных размеров фрейма.
        jfrm.setSize(220, 240);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание компонента JComboBox
        jcbb = new JComboBox(apples);

        // Включение средств редактирования в состав раскрывающегося списка.
        jcbb.setEditable(true);

        // Создание метки, отображающей выбор пользователя.
        jlab = new JLabel();

        // Связывание обработчика событий с раскрывающимся списком. Новый пункт,
        // введенный пользователем, добавляется к списку.
        jcbb.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent le)
            {
                // Получение ссылки на выбранный пункт.
                String item = (String) jcbb.getSelectedItem();

                // Если ни один пункт не выбран, никакие действия не выполняются.
                if (item == null)
                    return;

                // Отображение выбранного пункта.
                jlab.setText("Current selection " + item);

                // Если данный пункт отсутствует в списке, он включается в его состав.
                int i;

                // Проверка наличия пункта в списке.
                for (i = 0; i < jcbb.getItemCount(); i++)
                {
                    if (item.equals(jcbb.getItemAt(i)))
                    { // Пункт есть в списке.
                        break;
                    }
                }

                // Если пункта нет в списке, он добавляется.
                if (i == jcbb.getItemCount()) 
                    jcbb.addItem(item);
            }
        });

        // Первоначально в списке выбирается первый пункт.
        jcbb.setSelectedIndex(0);

        // Создание кнопки Remove Selection.
        jbtnRemove = new JButton("Remove Selection");

        // Связывание обработчика событий с кнопкой.
        jbtnRemove.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent le)
            {
                // Получение ссылки на выбранный пункт.
                String item = (String) jcbb.getSelectedItem();

                // Если ни один пункт не выбран, никакие действия не выполняются.
                if (item == null)
                    return;

                // Удаление выбранного пункта из списка.
                jcbb.removeItem(item);

                // Отображение выбранного пункта.
                jlab.setText("Removed " + item);
            }
        });

        // Включение раскрывающегося списка, метки и кнопки в состав панели содержимого.
        jfrm.getContentPane().add(jcbb);
        jfrm.getContentPane().add(jlab);
        jfrm.getContentPane().add(jbtnRemove);

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
                new DynamicComboBox();
            }
        });
    }
}