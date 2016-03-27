// Использование диалогового окна, предназначенного для получения подтверждения от пользователя.

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ConfirmDialogDemo
{
    private JLabel jlab;
    private JButton jbtnShow;
    private JFrame jfrm;

    public ConfirmDialogDemo()
    {
        // Создание нового контейнера JFrame.
        jfrm = new JFrame("A Confirmation Dialog");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начальных размеров фрейма.
        jfrm.setSize(400, 250);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание метки, отображающей выбор пользователя.
        jlab = new JLabel();

        // Создание кнопки, вызывающей отображения диалогового окна.
        jbtnShow = new JButton("Show Dialog");

        // Связывание с кнопкой обработчика событий.
        jbtnShow.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent le)
            {
                // Создание диалогового окна, отображающего сообщения.
                int response = JOptionPane.showConfirmDialog(
                   jfrm,
                   "Remove unused files?", // Сообщение
                   "Disk Space is Low", // Заголовок
                   JOptionPane.YES_NO_OPTION // Отображаются только кнопки Yes и No
                   );

                switch (response)
                {
                    case JOptionPane.YES_OPTION:
                        jlab.setText("You answered Yes.");
                        break;
                    case JOptionPane.NO_OPTION:
                        jlab.setText("You answered No.");
                        break;
                    case JOptionPane.CLOSED_OPTION:
                        jlab.setText("Dialog closed without response.");
                        break;
                }
            }

        });


        // Включение кнопки и метки в состав панели содержимого.
        jfrm.getContentPane().add(jbtnShow);
        jfrm.getContentPane().add(jlab);

        // Отображение фрейма.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // Фрейм создается в потоке обработки событий.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override public void run()
            {
                new ConfirmDialogDemo();
            }

        });
    }

}