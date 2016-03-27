// Апплеты на базе Swing.

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

/*
 * This HTML can be used to launch the applet: <object code="MyApplet" width="240" height="100">
 * </object>
 */
public class MyApplet extends JApplet
{
    private JButton jbtnOne;
    private JButton jbtnTwo;
    private JLabel jlab;

    // Данный метод вызывается в первую очередь.
    @Override
    public void init()
    {
        try
        {
            SwingUtilities.invokeAndWait(new Runnable()
            {
                @Override
                public void run()
                {
                    guiInit(); // Инициализация графического пользовательского интерфейса.
                }

            });
        }
        catch (InterruptedException | InvocationTargetException exc)
        {
            System.out.println("Can't create because of " + exc);
        }
    }

    // Данный метод вызывается после init(). Также обращение к нему производится
    // при повторном запуске апплета.
    @Override
    public void start()
    {
        // В данном апплете этот метод не используется.
    }

    // Данный метод вызывается при остановке апплета.
    @Override
    public void stop()
    {
        // В данном апплете этот метод не используется.
    }

    // Данный метод вызывается при завершении работы апплета.
    // Это последний выполняемый метод.
    @Override
    public void destroy()
    {
        // В данном апплете этот метод не используется.
    }

    // Формирование и инициализация пользовательского интерфейса.
    private void guiInit()
    {
        // Установка диспетчера компоновки FlowLayout.
        setLayout(new FlowLayout());

        // Создание двух кнопок и метки.
        jbtnOne = new JButton("One");
        jbtnTwo = new JButton("Two");

        jlab = new JLabel("Press a button.");

        // Связывание с кнопками обработчиков событий.
        jbtnOne.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent le)
            {
                jlab.setText("Button One pressed.");
            }

        });

        jbtnTwo.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent le)
            {
                jlab.setText("Button Two pressed.");
            }

        });

        // Включение компонентов в состав панели содержимого апплета.
        getContentPane().add(jbtnOne);
        getContentPane().add(jbtnTwo);
        getContentPane().add(jlab);
    }

}