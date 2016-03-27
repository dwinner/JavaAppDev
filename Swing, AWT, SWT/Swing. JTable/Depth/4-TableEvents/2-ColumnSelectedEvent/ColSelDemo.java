/*
 * Обработка событий выбора столбцов таблицы.
 */

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.TableColumnModel;

public class ColSelDemo
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

    public ColSelDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Column Selection Demo");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начальных размеров фрейма.
        jfrm.setSize(500, 160);

        // Завершение программы при закрытии окна пользоваетелем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание метки для отображения информации о выборе пользователя.
        jlab = new JLabel();

        // Создание таблицы для отображения почтовых сообщений.
        jtabEmail = new JTable(data, headings);

        // Включение данных в состав панели с прокруткой.
        JScrollPane jscrlp = new JScrollPane(jtabEmail);

        // Установка размеров прокручиваемой области просмотра.
        jtabEmail.setPreferredScrollableViewportSize(new Dimension(450, 80));

        // Разрешение выбора столбцов и запрет выбора строк.
        jtabEmail.setColumnSelectionAllowed(true);
        jtabEmail.setRowSelectionAllowed(false);

        // Получение модели выбора для модели столбца.
        // С моделью выбора связывается обработчик событий.
        TableColumnModel tcm = jtabEmail.getColumnModel();
        ListSelectionModel lsmCol = tcm.getSelectionModel();

        // Обработка событий выбора.
        lsmCol.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent le)
            {
                String str = "Selected Columns: ";

                // Получение индексов выбранных столбцов.
                int[] cols = jtabEmail.getSelectedColumns();

                // Формирование строки, содержащей индексы.
                for (int i = 0; i < cols.length; i++)
                {
                    str += cols[i] + " ";
                }

                // Отображение индексов выбранных столбцов.
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
        // Фрейм создается в потоке обработки событий
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new ColSelDemo();
            }
        });
    }
}