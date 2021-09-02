// Простое диалоговое окно для ввода строки текста.

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class InputDialogDemo
{
    private JLabel jlab;
    private JButton jbtnShow;
    private JFrame jfrm;

    public InputDialogDemo()
    {
        // Создание нового контейнера JFrame.
        jfrm = new JFrame("A Simple Input Dialog");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начальных размеров фрейма.
        jfrm.setSize(400, 250);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание метки, с помощью которой отображается ответ пользователя.
        jlab = new JLabel();

        // Создание кнопки, вызывающей отображение диалогового окна.
        jbtnShow = new JButton("Show Dialog");

        // Связывание с кнопкой обработчика событий.
        jbtnShow.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent le)
            {
                // Создание диалогового окна для ввода строки.
                String response = JOptionPane.showInputDialog(
                   jfrm,
                   "Enter Name",
                   "Bob Smith");
                /*
                 * Если метод возвращает значение null, это означает, что пользователь щелкнул на
                 * кнопке Cansel или закрыл окно с помощью кнопки в правом верхнем углу. Метод
                 * возвращает строку нулевой длины в случае, если пользователь не ввёл данные. В
                 * других случаях возвращается строка, введенная пользователем.
                 */
                if (response == null)
                {
                    jlab.setText("Dialog Cancelled or Closed");
                }
                else if (response.length() == 0)
                {
                    jlab.setText("No string entered");
                }
                else
                {
                    jlab.setText("Hi there " + response);
                }
            }

        });

        // Можно также обеспечить выбор из списка:
        jbtnShow.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent le)
            {
                String[] names =
                {
                    "Tom Jones",
                    "Bob Smith",
                    "Mary Doe",
                    "Nancy Oliver"
                };
                // Создание диалогового окна, позволяющего пользователю
                // выбирать имя из списка.
                String response = (String) JOptionPane.showInputDialog(
                   jfrm,
                   "Choose User",
                   "Select User Name",
                   JOptionPane.QUESTION_MESSAGE,
                   null,
                   names,
                   "Bob Smith");
                if (response == null)
                {
                    jlab.setText("Dialog Cancelled or Closed");
                }
                else if (response.length() == 0)
                {
                    jlab.setText("No string entered");
                }
                else
                {
                    jlab.setText("Hi there " + response);
                }
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
                new InputDialogDemo();
            }

        });
    }

}