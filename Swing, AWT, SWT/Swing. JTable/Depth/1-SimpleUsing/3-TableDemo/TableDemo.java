/*
 * Пример формирования простой таблицы.
 */

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.SwingUtilities;

public class TableDemo
{
    // Массив, содержащий заголовки таблицы.
    private String[] headings =
    {
        "From", "Address", "Subject", "Size"
    };
    // Массив, содержащий данные таблицы.
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

    public TableDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Simple Table Demo");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начальных размеров фрейма.
        jfrm.setSize(500, 160);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание таблицы для отображения почтовых сообщений.
        // Заголовки и данные задаются посредством массивов.
        jtabEmail = new JTable(data, headings);

        /*
         * Выбор столбцов. Запрет на выбор строк. jtabEmail.setColumnSelectionAllowed(true);
         * jtabEmail.setRowSelectionAllowed(false);
         */

        // Включение таблицы в состав панели с прокруткой.
        JScrollPane jscrlp = new JScrollPane(jtabEmail);

        // Установка размеров прокручиваемой области просмотра.
        jtabEmail.setPreferredScrollableViewportSize(new Dimension(450, 80));

        // Включение таблицы в состав панели содержимого.
        jfrm.getContentPane().add(jscrlp);

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
                new TableDemo();
            }
        });
    }
}