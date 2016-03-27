// Пример использования класса GridBagLayout.

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class GBDemo
{
    public GBDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("GridBagLayout Demo");

        // Создание пустых объектов GridBagLayout и GridBagConstraints.
        GridBagLayout gbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        // Связывание диспетчера компоновки GridBagLayout с панелью содержимого фрейма.
        jfrm.getContentPane().setLayout(gbag);

        // Установка начальных размеров фрейма.
        jfrm.setSize(240, 240);

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
        jbtnOne.setPreferredSize(btnDim);
        jbtnTwo.setPreferredSize(btnDim);
        jbtnThree.setPreferredSize(btnDim);
        jbtnFour.setPreferredSize(btnDim);

        // Создание флажков опций.
        JCheckBox jcbOne = new JCheckBox("Option One");
        JCheckBox jcbTwo = new JCheckBox("Option Two");

        // Определение таблицы для диспетчера компоновки.

        // Значение 1.0 переменной weightx управляет распределением пространства
        // в горизонтальном направлении. Поскольку для переменной weighty принято
        // значение по умолчанию, равное 0.0, в вертикальном направлении компонент
        // выравнивается по центру.
        gbc.weightx = 1.0;

        // Определение расположения каждого компонента в таблице.

        // Метки для кнопок располагаются в ячейках 0,0 и 1,0
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbag.setConstraints(jlabOne, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbag.setConstraints(jlabTwo, gbc);

        // Резервирование пространства между кнопками.
        gbc.insets = new Insets(4, 4, 4, 4);

        // Кнопки располагаются в ячейках 0,1, 1,1, и т.д.
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbag.setConstraints(jbtnOne, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbag.setConstraints(jbtnTwo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbag.setConstraints(jbtnThree, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbag.setConstraints(jbtnFour, gbc);

        // Последняя метка и два флажка опций располагаются в оставшейся
        // области и выравниваются по центру.
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        // Выше метки резервируется свободное пространство в 10 пикселей.
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbag.setConstraints(jlabThree, gbc);

        // Вокруг флажков опций пространство не резервируется.
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbag.setConstraints(jcbOne, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbag.setConstraints(jcbTwo, gbc);

        // Включение компонентов в состав панели содержимого.
        jfrm.getContentPane().add(jlabOne);
        jfrm.getContentPane().add(jlabTwo);
        jfrm.getContentPane().add(jbtnOne);
        jfrm.getContentPane().add(jbtnTwo);
        jfrm.getContentPane().add(jbtnThree);
        jfrm.getContentPane().add(jbtnFour);
        jfrm.getContentPane().add(jlabThree);
        jfrm.getContentPane().add(jcbOne);
        jfrm.getContentPane().add(jcbTwo);

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
                new GBDemo();
            }

        });
    }

}