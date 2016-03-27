// Демонстрация работы инкрементного регулятора,
// основанного на модели SpinnerListModel.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SpinnerDemo
{
    private JSpinner jspin;
    private JLabel jlab;
    // Создание массива цветов RGB.
    private String colors[] =
    {
        "Red", "Green", "Blue"
    };

    public SpinnerDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("SpinnerListModel");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начальных размеров фрейма.
        jfrm.setSize(220, 100);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание модели, поддерживающей список строк.
        SpinnerListModel spm = new SpinnerListModel(colors);

        // Создание компонента JSpinner и указание модели.
        jspin = new JSpinner(spm);

        // Установка предпочтительных размеров инкрементного регулятора.
        jspin.setPreferredSize(new Dimension(60, 20));

        // Создание метки, которая отображает выбор пользователя. Вокруг метки
        // выводится цветная рамка. Поскольку Red - первый пункт списка, он выбирается
        // по умолчанию при создании инкрементного регулятора.
        jlab = new JLabel(" Current selection is: Red ");
        jlab.setBorder(BorderFactory.createLineBorder(Color.RED, 4));

        // Связывание обработчика событий изменения состояния с инкрементным регулятором.
        jspin.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent ce)
            {
                // Получение выбранного значения.
                String color = (String) jspin.getValue();
                // Отображение выбранного значения с помощью метки.
                jlab.setText(" Current selection is: " + color + " ");
                // Формирование рамки вокруг метки. Рамка отображается цветом, выбранным пользователем.
                switch (color)
                {
                    case "Red":
                        jlab.setBorder(BorderFactory.createLineBorder(Color.RED, 4));
                        break;
                    case "Green":
                        jlab.setBorder(BorderFactory.createLineBorder(Color.GREEN, 4));
                        break;
                    default:
                        jlab.setBorder(BorderFactory.createLineBorder(Color.BLUE, 4));
                        break;
                }
            }
        });

        // Включение инкрементного регулятора и метки в состав панели содержимого.
        jfrm.getContentPane().add(jspin);
        jfrm.getContentPane().add(jlab);

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
                new SpinnerDemo();
            }
        });
    }
}