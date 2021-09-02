// Пример использования компонента JList.

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListDemo
{
    private JList<String> jlst;
    private JLabel jlab;
    private JScrollPane jscrlp;
    private JButton jbtnBuy;
    // Создание массива с именем сортов яблок.
    private String apples[] =
    {
        "Winesap", "Cortland", "Red Delicious",
        "Golden Delicious", "Gala", "Fuji",
        "Granny Smith", "Jonathan"
    };

    public ListDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("JList Demo");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начальных размеров фрейма.
        jfrm.setSize(204, 200);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание компонента JList на основе массива, состоящего из строк текста.
        jlst = new JList<>(apples);

        // Установка режима, позволяющего выбирать только один пункт списка.
        jlst.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Включение списка в состав панели с прокруткой.
        jscrlp = new JScrollPane(jlst);

        // Установка предпочтительных размеров панели с прокруткой.
        jscrlp.setPreferredSize(new Dimension(120, 90));

        // Создание метки, отображающей выбор пользователя.
        jlab = new JLabel("Please Choose an Apple");

        // Связывание со списком обработчика событий.
        jlst.addListSelectionListener(new ListSelectionListener()
        {
            @Override
            public void valueChanged(ListSelectionEvent le)
            {
                // Получение индекса текущего выбранного пункта.
                int idx = jlst.getSelectedIndex();
                // Если пользователь выбрал пункт, отображается информация о выборе.
                if (idx != -1)
                {
                    jlab.setText("Current selection " + apples[idx]);
                }
                else // В противном случае снова выводится приглашение сделать выбор.
                {
                    jlab.setText("Please Choose an Apple");
                }
            }
        });

        // Формирование кнопки Buy Apple.
        jbtnBuy = new JButton("Buy Apple");

        // Связывание обработчика событий с кнопкой Buy Apple.
        jbtnBuy.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                // Получение индекса пункта.
                int idx = jlst.getSelectedIndex();
                // Если пункт списка был выбран, выводится информация о нём.
                if (idx != -1)
                {
                    jlab.setText("You purchased " + apples[idx]);
                }
                else // В противном случае выводится сообщение о неправильных действиях.
                {
                    jlab.setText("No apple has been selected.");
                }
            }
        });

        // Включение списка и метки в состав панели содержимого.
        jfrm.getContentPane().add(jscrlp);
        jfrm.getContentPane().add(jbtnBuy);
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
                new ListDemo();
            }
        });
    }
}