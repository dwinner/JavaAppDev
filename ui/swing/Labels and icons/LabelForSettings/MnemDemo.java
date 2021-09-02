// Использование мнемонических обозначений

import java.awt.FlowLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class MnemDemo
{
    public MnemDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Demonstrate Mnemomics");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начального размера фрейма.
        jfrm.setSize(260, 140);

        // Завершение программы при закрытии приложения пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание двух меток.
        JLabel jlab1 = new JLabel("E-mail Address");
        JLabel jlab2 = new JLabel("Name");

        // Назначение мнемонических обозначений меткам.
        jlab1.setDisplayedMnemonic('e');
        jlab2.setDisplayedMnemonic('n');

        // Создание двух полей редактирования.
        JTextField jtf1 = new JTextField(20);
        JTextField jtf2 = new JTextField(20);

        // Связывание меток с компонентами.
        jlab1.setLabelFor(jtf1);
        jlab2.setLabelFor(jtf2);

        // Назначение команд действий полям редактирования.
        jtf1.setActionCommand("jtf1");
        jtf2.setActionCommand("jtf2");

        // Включение полей редактирования и меток в состав панели содержимого.
        jfrm.getContentPane().add(jlab1);
        jfrm.getContentPane().add(jtf1);
        jfrm.getContentPane().add(jlab2);
        jfrm.getContentPane().add(jtf2);

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
                new MnemDemo();
            }

        });
    }

}