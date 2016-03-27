/*
 * Отслеживание изменений данных таблицы.
 */

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.TableModel;

public class TabModEventDemo
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
    private JLabel jlab2;
    private TableModel tm;

    TabModEventDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Table Model Events Demo");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начальных размеров фрейма.
        jfrm.setSize(500, 200);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание метки для отображения информации о выборе пользователя.
        jlab = new JLabel();
        jlab.setPreferredSize(new Dimension(400, 20));
        jlab.setHorizontalAlignment(SwingConstants.CENTER);

        // Создание метки для отображения информации об изменении данных.
        jlab2 = new JLabel();

        // Создание таблицы для отображения почтовых сообщений.
        jtabEmail = new JTable(data, headings);

        // Включение данных в панель с прокруткой.
        JScrollPane jscrlp = new JScrollPane(jtabEmail);

        // Установка размеров прокручиваемой области просмотра.
        jtabEmail.setPreferredScrollableViewportSize(new Dimension(450, 80));

        // Получение модели выбора.
        ListSelectionModel listSelMod = jtabEmail.getSelectionModel();

        // Обработка событий выбора строк.
        listSelMod.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent le)
            {
                String str = "Selected Row(s): ";

                // Получение индексов выбранных строк.
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

        // Получение модели таблицы.
        tm = jtabEmail.getModel();

        // Обработка событий изменения модели таблицы.
        tm.addTableModelListener(new TableModelListener()
        {
            @Override
            public void tableChanged(TableModelEvent tme)
            {
                if (tme.getType() == TableModelEvent.UPDATE)
                {
                    // Отображение координат ячейки и нового значения.
                    jlab2.setText("Cell " + tme.getFirstRow() + ", " + tme.getColumn() + " changed."
                        + " The new value: " + tm.getValueAt(tme.getFirstRow(), tme.getColumn()));
                }
            }
        });

        // Включение таблицы и метки в состав панели содержимого.
        jfrm.getContentPane().add(jscrlp);
        jfrm.getContentPane().add(jlab);
        jfrm.getContentPane().add(jlab2);

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
                new TabModEventDemo();
            }
        });
    }
}