/*
 * Создание объекта JPanel и использование его в качестве панели содержимого.
 */

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Данный класс создает панель, расширяющую JPanel. Она используется в качестве панели содержимого. В этом классе не
 * предусмотрены новые функциональные возможности, но его экземпляр может применяться во всех случаях, в которых
 * допустимо использование JPanel.
 */
class MyContentPanel extends JPanel
{
    private JLabel jlab;
    private JButton jbtnRed;
    private JButton jbtnBlue;

    MyContentPanel()
    {
        // Панель должна быть непрозрачной.
        setOpaque(true);

        // Установка рамки зеленого цвета толщиной в 5 пикселей.
        setBorder(BorderFactory.createLineBorder(Color.GREEN, 5));

        // Создание метки.
        jlab = new JLabel("Select Border Color");

        // Создание двух кнопок.
        jbtnRed = new JButton("Red");
        jbtnBlue = new JButton("Blue");

        // Связывание обработчиков событий с кнопками.
        jbtnRed.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                setBorder(BorderFactory.createLineBorder(Color.RED, 5));
            }
        });

        jbtnBlue.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                setBorder(BorderFactory.createLineBorder(Color.BLUE, 5));
            }
        });

        // Включение кнопок и метки в состав панели.
        add(jbtnRed);
        add(jbtnBlue);
        add(jlab);
    }
}

/**
 * Создание контейнера верхнего уровня и использование панели, созданной с помощью MyContentPanel, в качестве панели
 * содержимого.
 * <p/>
 * @author oracle_pr1
 */
public class CustomCPDemo
{
    public CustomCPDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Set the Content Pane");

        // Установка начальных размеров фрейма.
        jfrm.setSize(240, 150);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание экземпляра пользовательской панели содержимого.
        MyContentPanel mcp = new MyContentPanel();

        // Установка mcp в качестве панели содержимого.
        jfrm.setContentPane(mcp);

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
                new CustomCPDemo();
            }
        });
    }
}