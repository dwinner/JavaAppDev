/*
 * Обработка событий выбора строк таблицы.
 */

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class RowSelDemo
{
    private String[] headings =
    {
        "From", "Address", "Subject", "Size"
    };
    private Object[][] data =
    {
        {
            "Wendy", "Wendy@HerbSchildt.com", "Hello Herb", new Integer(287)
        },
        {
            "Alex", "Alex@HerbSchildt.com", "Check this out!", new Integer(308)
        },
        {
            "Hale", "Hale@HerbSchildt.com", "Found a bug", new Integer(887)
        },
        {
            "Todd", "Todd@HerbSchildt.com", "Did you see this?", new Integer(223)
        },
        {
            "Steve", "Steve@HerbSchildt.com", "I'm back", new Integer(357)
        },
        {
            "Ken", "Ken@HerbSchildt.com", "Arrival time change", new Integer(512)
        }
    };
    private JTable jtabEmail;
    private JLabel jlab;

    public RowSelDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Row Selection Demo");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начальных размеров фрейма.
        jfrm.setSize(500, 160);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание метки для отображения информации о выбранных строках таблицы.
        jlab = new JLabel();

        // Создание таблицы для отображения почтовых сообщений.
        jtabEmail = new JTable(data, headings);

        // Включение данных в состав панели с прокруткой.
        JScrollPane jscrlp = new JScrollPane(jtabEmail);

        // Установка размера прокручиваемой области просмотра.
        jtabEmail.setPreferredScrollableViewportSize(new Dimension(450, 80));

        // Получение модели выбора. С ней связывается обработчик событий выбора.
        ListSelectionModel lsmRow = jtabEmail.getSelectionModel();

        // Связывание обработчика событий с моделью выбора.
        // Обработчик реагирует на события выбора строк.
        lsmRow.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent le)
            {
                String str = "Selected Rows: ";

                // Создание строки для представления индексов.
                int[] rows = jtabEmail.getSelectedRows();

                // Создание строки для представления индексов.
                for (int i = 0; i < rows.length; i++)
                {
                    str += rows[i] + " ";
                }

                // Отображение индексов выбранных строк.
                jlab.setText(str);
            }
        });

        // Включение таблицы и метки в состав панели содержимого.
        jfrm.getContentPane().add(jscrlp);
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
                new RowSelDemo();
            }
        });
    }
}