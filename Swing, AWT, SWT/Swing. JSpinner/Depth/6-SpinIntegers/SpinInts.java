// Выбор целочисленных значений с помощью инкрементного регулятора.

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SpinInts
{
    private JSpinner jspin;
    private JLabel jlab;

    public SpinInts()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Spin Integers");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начальных размеров фрейма.
        jfrm.setSize(200, 120);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание модели, используемой для поддержки целых чисел.
        SpinnerNumberModel spm = new SpinnerNumberModel(1, 1, 10, 1);

        // Создание компонента JSpinner, использующего подготовленную ранее модель.
        jspin = new JSpinner(spm);

        // Установка предпочтительных размеров инкрементного регулятора.
        jspin.setPreferredSize(new Dimension(40, 20));

        // Создание метки, отображающей выбор пользователя.
        jlab = new JLabel(" Current border size is: 1 ");
        jlab.setBorder(BorderFactory.createLineBorder(Color.BLACK));

        // Связывание обработчика событий изменения состояния с инкрементным регулятором.
        jspin.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent ce)
            {
                // Получение текущего размера.
                Integer bSize = (Integer) jspin.getValue();
                // Отображение сведений о текущем размере.
                jlab.setText(" Current border size is: " + bSize + " ");
                // Установка рамки вокруг метки. Толщина рамки определяется значением, выбранным
                // пользователем с помощью инкрементного регулятора.
                jlab.setBorder(BorderFactory.createLineBorder(Color.BLACK, bSize.intValue()));
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
        // Создание фрейма в потоке обработки событий.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new SpinInts();
            }
        });
    }
}