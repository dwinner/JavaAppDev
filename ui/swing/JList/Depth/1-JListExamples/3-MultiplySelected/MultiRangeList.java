// Выбор нескольких пунктов списка.

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class MultiRangeList
{
    private JList<String> jlst;
    private JLabel jlab;
    private JScrollPane jscrlp;
    private JButton jbtnBuy;
    
    // Создание массива с названиями сортов яблок.
    private String apples[] =
    {
        "Winesap", "Cortland", "Red Delicious",
        "Golden Delicious", "Gala", "Fuji",
        "Granny Smith", "Jonathan"
    };

    public MultiRangeList()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Multiple Range");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начальных размеров фрейма.
        jfrm.setSize(180, 240);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание компонента JList. По умолчанию список допускает
        // выбор пунктов в нескольких интервалах.
        jlst = new JList<>(apples);

        // Включение списка в состав панели с прокруткой.
        jscrlp = new JScrollPane(jlst);

        // Установка предпочтительных размеров панели с прокруткой.
        jscrlp.setPreferredSize(new Dimension(120, 90));

        // Создание метки, отображающей выбор пользователя.
        jlab = new JLabel("Please Choose an Apple");

        // Связывание обработчика событий со списком.
        jlst.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent le)
            {
                String what = "";

                // Получение массива с индексами выбранных пунктов.
                int indices[] = jlst.getSelectedIndices();

                // Проверка, был ли выбран хотя бы один пункт. Если длина массива
                // равна нулю, ни один пункт не был выбран.
                if (indices.length == 0)
                {
                    jlab.setText("Please Choose an Apple.");
                    return;
                }

                // Отображение выбранных пунктов.
                for (int i = 0; i < indices.length; i++)
                {
                    what += apples[indices[i]] + "<br />";
                }

                jlab.setText("<html>Current selection:<br />" + what);
            }
        });

        // Создание кнопки Buy Apple.
        jbtnBuy = new JButton("Buy Apple");

        // Связывание с кнопкой Buy Apple обработчика событий.
        jbtnBuy.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                String what = "";

                // Получение индексов выбранных пунктов.
                int indices[] = jlst.getSelectedIndices();

                // Проверка, был ли выбран хотя бы один пункт списка.
                if (indices.length == 0)
                {
                    jlab.setText("No apple has been selected.");
                    return;
                }

                // Отображение выбранных пунктов.
                for (int i = 0; i < indices.length; i++)
                {
                    what += apples[indices[i]] + "<br />";
                }

                jlab.setText("<html>Apples Purchased:<br />" + what);
            }
        });

        // Добавление списка и метки к панели содержимого.
        jfrm.getContentPane().add(jscrlp);
        jfrm.getContentPane().add(jbtnBuy);
        jfrm.getContentPane().add(jlab);

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
                new MultiRangeList();
            }
        });
    }
}