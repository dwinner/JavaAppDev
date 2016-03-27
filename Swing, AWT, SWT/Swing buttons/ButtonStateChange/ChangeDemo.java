// Демонстрация событий изменения состояния и модели кнопки.
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class ChangeDemo
{
    private JButton jbtn;
    private JLabel jlab;

    public ChangeDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Button Change Events");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начального размера фрейма.
        jfrm.setSize(250, 160);

        // Завершение программы при закрытии приложения пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание пустой метки.
        jlab = new JLabel();

        // Создание кнопки.
        jbtn = new JButton("Press for Change Event Test");

        // Добавление обработчика событий изменения состояния.
        jbtn.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent ce)
            {
                ButtonModel mod = jbtn.getModel();
                String what = "";
                if (mod.isEnabled())
                {
                    what += "Enabled<br />";
                }
                if (mod.isRollover())
                {
                    what += "Rollover<br />";
                }
                if (mod.isArmed())
                {
                    what += "Armed<br />";
                }
                if (mod.isPressed())
                {
                    what += "Pressed<br />";
                }
                // Текст на метке задается с помощью HTML-кода.
                jlab.setText("<html>Current state:<br />" + what);
            }
        });

        // Добавление компонентов к панели содержимого.
        jfrm.getContentPane().add(jbtn);
        jfrm.getContentPane().add(jlab);

        // Отображение фрейма.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // Создание фрейма в потоке обработки событий.
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new ChangeDemo();
            }
        });
    }
}