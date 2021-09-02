// Пример использования компонента JOptionPane.

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class MsgDialogDemo
{
    private JLabel jlab;
    private JButton jbtnShow;
    private JFrame jfrm;

    public MsgDialogDemo()
    {
        // Создание нового контейнера JFrame.
        jfrm = new JFrame("Simple Message Dialog");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начальных размеров фрейма.
        jfrm.setSize(400, 250);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание метки, отображающейся после закрытия диалогового окна.
        jlab = new JLabel();

        // Создание кнопки, вызывающей отображение диалогового окна.
        jbtnShow = new JButton("Show Dialog");

        // Связывание с кнопкой обработчика событий.
        jbtnShow.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent le)
            {
                // Создание диалогового окна, отображающего сообщение.
                JOptionPane.showMessageDialog(jfrm, "Disk Space is Low.");
                // Данное выражение не будет выполнено до тех пор, пока метод
                // showMessageDialog() не завершит работу.
                jlab.setText("Dialog Closed");
            }

        });

        // Включение кнопки и метки в панель содержимого.
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
                new MsgDialogDemo();
            }

        });
    }

}