// Пример, демонстрирующий работу с объектом JPasswordField.

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;

public class JPasswordFieldDemo
{
    private JLabel jlabPW;
    private JPasswordField jpswd;

    public JPasswordFieldDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Use JPasswordField");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начальных размеров фрейма.
        jfrm.setSize(240, 100);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание метки.
        jlabPW = new JLabel("Enter Password");

        // Создание поля ввода пароля.
        jpswd = new JPasswordField(15);

        // Связывание с полем ввода пароля обработчика событий действия. Каждый раз, когда
        // пользователь нажимает клавишу <Enter>, содержимое компонента
        // проверяется на соответствие паролю.
        jpswd.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent le)
            {
                char pw[] =
                {
                    't', 'e', 's', 't'
                };
                char[] userSeq = jpswd.getPassword();
                // Проверка строки, введенной пользователем.
                if (Arrays.equals(userSeq, pw))
                {
                    jlabPW.setText("Password Valid");
                }
                else
                {
                    jlabPW.setText("Password Invalid -- Try Again");
                }
                // После завершения работы очищаются два массива.
                Arrays.fill(pw, (char) 0);
                Arrays.fill(userSeq, (char) 0);
            }
        });

        // Включение компонентов в состав панели содержимого.
        jfrm.getContentPane().add(jpswd);
        jfrm.getContentPane().add(jlabPW);

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
                new JPasswordFieldDemo();
            }
        });
    }
}