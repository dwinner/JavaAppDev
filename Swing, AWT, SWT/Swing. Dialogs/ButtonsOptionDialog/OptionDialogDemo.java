// Использование метода showOptionDialog() для создания диалогового окна.

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class OptionDialogDemo
{
    private JLabel jlab;
    private JButton jbtnShow;
    private JFrame jfrm;

    public OptionDialogDemo()
    {
        // Создание нового контейнера JFrame.
        jfrm = new JFrame("A Simple Option Dialog");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начальных размеров фрейма.
        jfrm.setSize(400, 250);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание метки, отображающей выбор пользователя.
        jlab = new JLabel();

        // Создание кнопки, вызывающей отображение диалогового окна.
        jbtnShow = new JButton("Show Dialog");

        // Связывание с кнопкой обработчика событий действия.
        jbtnShow.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent le)
            {
                // Определение опций для отображения в окне.
                String[] connectOpts =
                {
                    "Modem",
                    "Wireless",
                    "Satelite",
                    "Cable"
                };
                // Создание диалогового окна, позволяющего пользователю
                // выбирать способ подключения к сети.
                // Каждая опция оформляется в виде отдельной кнопки.
                int response = JOptionPane.showOptionDialog(
                   jfrm,
                   "Choose One",
                   "Connection Type",
                   JOptionPane.DEFAULT_OPTION,
                   JOptionPane.QUESTION_MESSAGE,
                   null,
                   connectOpts,
                   "Wireless");
                // Отображение информации о выборе пользователя. В переменной response содержится
                // индекс кнопки, на которой щёлкнул пользователь.
                switch (response)
                {
                    case 0:
                        jlab.setText("Connect via modem.");
                        break;
                    case 1:
                        jlab.setText("Connect via wireless.");
                        break;
                    case 2:
                        jlab.setText("Connect via satelite.");
                        break;
                    case 3:
                        jlab.setText("Connect via cable.");
                        break;
                    case JOptionPane.CLOSED_OPTION:
                        jlab.setText("Dialog cancelled");
                        break;
                }
                /*
                 * Object[] ops = { new JLabel("Name"), new JTextField(10), new JLabel("Phone
                 * Number"), new JTextField(10), "OK", "Cancel" }; * int response =
                 * JOptionPane.showOptionDialog( jfrm, "Enter Info", "Get Name and Telephone",
                 * JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, ops, "Cancel");
                 *
                 */
            }

        });

        jfrm.getContentPane().add(jbtnShow);
        jfrm.getContentPane().add(jlab);

        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override public void run()
            {
                new OptionDialogDemo();
            }

        });
    }

}