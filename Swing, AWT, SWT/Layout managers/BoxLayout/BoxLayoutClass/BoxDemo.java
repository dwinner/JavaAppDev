// Использование диспетчера компоновки BoxLayout посредством класса Box.

import java.awt.*;
import javax.swing.*;

public class BoxDemo
{
    public BoxDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("BoxLayout Demo");

        // *** С панелью содержимого связывается
        // диспетчер компоновки FlowLayout ***
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начальных размеров фрейма.
        jfrm.setSize(300, 240);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание меток.
        JLabel jlabOne = new JLabel("Button Group One");
        JLabel jlabTwo = new JLabel("Button Group Two");
        JLabel jlabThree = new JLabel("Check Box Group");

        // Создание кнопок.
        JButton jbtnOne = new JButton("One");
        JButton jbtnTwo = new JButton("Two");
        JButton jbtnThree = new JButton("Three");
        JButton jbtnFour = new JButton("Four");
        Dimension btnDim = new Dimension(100, 25);

        // Установка минимального и максимального размера кнопок.
        jbtnOne.setMinimumSize(btnDim);
        jbtnOne.setMaximumSize(btnDim);
        jbtnTwo.setMinimumSize(btnDim);
        jbtnTwo.setMaximumSize(btnDim);
        jbtnThree.setMinimumSize(btnDim);
        jbtnThree.setMaximumSize(btnDim);
        jbtnFour.setMinimumSize(btnDim);
        jbtnFour.setMaximumSize(btnDim);

        // Создание флажков опций.
        JCheckBox jcbOne = new JCheckBox("Option One");
        JCheckBox jcbTwo = new JCheckBox("Option Two");

        // Создание трёх вертикальных блоков.
        Box box1 = Box.createVerticalBox();
        Box box2 = Box.createVerticalBox();
        Box box3 = Box.createVerticalBox();

        // Создание невидимых рамок для кнопок.
        box1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        box2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        box3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Включение компонентов в блоки box1 и box2.
        box1.add(jlabOne);
        box1.add(Box.createRigidArea(new Dimension(0, 4)));
        box1.add(jbtnOne);
        box1.add(Box.createRigidArea(new Dimension(0, 4)));
        box1.add(jbtnTwo);

        box2.add(jlabTwo);
        box2.add(Box.createRigidArea(new Dimension(0, 4)));
        box2.add(jbtnThree);
        box2.add(Box.createRigidArea(new Dimension(0, 4)));
        box2.add(jbtnFour);

        box3.add(jlabThree);
        box3.add(jcbOne);
        box3.add(jcbTwo);

        // Включение блоков в состав панели содержимого.
        jfrm.getContentPane().add(box1);
        jfrm.getContentPane().add(box2);
        jfrm.getContentPane().add(box3);

        // Отображение фрейма.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // Фрейм создается в потоке обработки событий.
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new BoxDemo();
            }
        });
    }
}