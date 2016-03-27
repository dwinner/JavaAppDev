// Использование интерфейса ListModel

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListModelDemo
{
    private JList<String> jlst;
    private JLabel jlab;
    private JScrollPane jscrlp;
    private JButton jbtnBuy;
    private JButton jbtnAddDel;

    public ListModelDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("JList ModelDemo");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начальных размеров фрейма.
        jfrm.setSize(180, 240);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание и заполнение модели списка.
        //
        // Сначала создается экземпляр DefaultListModel.
        DefaultListModel<String> lm = new DefaultListModel<>();

        // Затем к модели добавляются пункты.
        lm.addElement("Winesap");
        lm.addElement("Cortland");
        lm.addElement("Red Delicious");
        lm.addElement("Golden Delicious");
        lm.addElement("Gala");

        // Создание объекта JList на основании модели списка.
        jlst = new JList<>(lm);

        // Включение списка в панель с прокруткой.
        jscrlp = new JScrollPane(jlst);

        // Установка предпочтительных размеров панели с прокруткой.
        jscrlp.setPreferredSize(new Dimension(120, 90));

        // Создание метки, отображающей результаты выбора.
        jlab = new JLabel("Please choose an apple.");

        // Обработчик событий выбора для списка.
        jlst.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent le)
            {
                String what = "";

                // Получение пунктов списка.
                @SuppressWarnings("deprecation")
                Object values[] = jlst.getSelectedValues();

                // Проверка, был ли выбран хотя бы один пункт.
                if (values.length == 0)
                {
                    jlab.setText("Please choose an apple.");
                    return;
                }

                // Отображение выбранных пунктов.
                for (int i = 0; i < values.length; i++)
                {
                    what += values[i] + "<br />";
                }

                jlab.setText("<html>Current selection:<br /><hr />" + what);
            }
        });

        // Создание кнопки для "покупки" выбранного сорта.
        jbtnBuy = new JButton("Buy Apple");

        // Связывание обработчика событий с кнопкой Buy Apple.
        jbtnBuy.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                String what = "";

                // Получение выбранных пунктов.
                @SuppressWarnings("deprecation")
                Object values[] = jlst.getSelectedValues();

                // Проверка, был ли выбран хотя бы один пункт списка.
                if (values.length == 0)
                {
                    jlab.setText("No apple has been selected.");
                    return;
                }

                // Отображение выбранных пунктов.
                for (int i = 0; i < values.length; i++)
                {
                    what += values[i] + "<br />";
                }

                jlab.setText("<html>Apples purchased:<br />" + what);
            }
        });

        // Создание кнопки, добавляющей пункты.
        jbtnAddDel = new JButton("Add More Varieties");

        // Связывание обработчика событий с кнопками Add More Varieties.
        jbtnAddDel.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                // Получение ссылки на модель.
                @SuppressWarnings("unchecked")
                DefaultListModel<String> lm = (DefaultListModel) jlst.getModel();

                // Проверка, были ли уже добавлены дополнительные пункты.
                if (lm.getSize() > 5)
                {
                    // При положительном результате проверки пункты удаляются.
                    for (int i = 7; i > 4; i--)
                    {
                        lm.remove(i);
                    }
                    jbtnAddDel.setText("Add More Varieties");
                }
                else
                {
                    // Добавление дополнительных пунктов.
                    lm.addElement("Fuji");
                    lm.addElement("Granny Smith");
                    lm.addElement("Jonathan");
                    jbtnAddDel.setText("Remove Extra Varieties");
                }
            }
        });

        // Включение списка и метки в панель содержимого.
        jfrm.getContentPane().add(jscrlp);
        jfrm.getContentPane().add(jbtnBuy);
        jfrm.getContentPane().add(jbtnAddDel);
        jfrm.getContentPane().add(jlab);

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
                new ListModelDemo();
            }
        });
    }
}