// Установка свойств полосы прокрутки

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class CustomSBDemo
{
    private JLabel jlabVert;
    private JLabel jlabHoriz;
    private JLabel jlabVSBInfo;
    private JLabel jlabHSBInfo;
    private JScrollBar jsbVert;
    private JScrollBar jsbHoriz;

    public CustomSBDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Scroll Bars Properties");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начальных размеров фрейма.
        jfrm.setSize(260, 500);

        // Завершение программы при закрытии приложения пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Метки, отображающие текущие значения полос прокрутки.
        jlabVert = new JLabel("Value of vertical scroll bar: 0");
        jlabHoriz = new JLabel("Value of horizontal scroll bar: 250");

        // Установка диапазонов, расширений и начальных значений полос прокрутки.
        jsbVert = new JScrollBar(
                JScrollBar.VERTICAL,
                0,  // Начальное значение.
                5,  // Расширение.
                0,  // Минимальное значение.
                500 // Максимальное значение.
        );
        jsbHoriz = new JScrollBar(
                Adjustable.HORIZONTAL,
                250,    // Начальное значение.
                0,      // Расширение
                0,      // Минимальное значение.
                500     // Максимальное значение.
        );

        // Установка размеров полос прокрутки.

        // Вертикальная полоса прокрутки шире стандартной.
        jsbVert.setPreferredSize(new Dimension(30, 200));
        // Горизонтальная полоса прокрутки уже стандартной
        jsbHoriz.setPreferredSize(new Dimension(200, 10));

        // Установка приращения блока для горизонтальной полосы прокрутки.
        jsbHoriz.setBlockIncrement(25);

        // Обработчики событий регулировки для полос прокрутки.

        // Перед тем как выполнить действие, соответствующее событию, вертикальная
        // полоса прокрутки ожидает окончания действий пользователя.
        jsbVert.addAdjustmentListener(new AdjustmentListener()
        {
            public void adjustmentValueChanged(AdjustmentEvent ae)
            {
                // Если полоса прокрутки претерпевает изменения, никакие действия не выполняются.
                if (jsbVert.getValueIsAdjusting())
                {
                    return;
                }
                // Отображение нового значения.
                jlabVert.setText("Value of vertical scroll bar: " + ae.getValue());
            }
        });

        // Горизонтальная полоса прокрутки реагирует на все события регулировки, независимо
        // от того, выполняет ли пользователь действия, изменяющие состояние компонента.
        jsbHoriz.addAdjustmentListener(new AdjustmentListener()
        {
            public void adjustmentValueChanged(AdjustmentEvent ae)
            {
                // Отображение нового значения.
                jlabHoriz.setText("Value of horizontal scroll bar: " + ae.getValue());
            }
        });

        // Отображение свойств полос прокрутки.
        jlabVSBInfo = new JLabel("<html>Vertical Scroll Bar:<br />"
                + "Minimum value: "
                + jsbVert.getMinimum() + "<br />"
                + "Maximum value: "
                + jsbVert.getMaximum() + "<br />"
                + "Visible amount (extent): "
                + jsbVert.getVisibleAmount() + "<br />"
                + "Block increment: "
                + jsbVert.getBlockIncrement() + "<br />"
                + "Unit increment: "
                + jsbVert.getUnitIncrement());

        jlabHSBInfo = new JLabel("<html>Horizontal Scroll Bar:<br />"
                + "Minimum value: "
                + jsbHoriz.getMinimum() + "<br />"
                + "Maximum value: "
                + jsbHoriz.getMaximum() + "<br />"
                + "Visible amount (extent): "
                + jsbHoriz.getVisibleAmount() + "<br />"
                + "Block increment: "
                + jsbHoriz.getBlockIncrement() + "<br />"
                + "Unit increment: "
                + jsbHoriz.getUnitIncrement());

        // Добавление компонентов к панели содержимого.
        jfrm.getContentPane().add(jsbVert);
        jfrm.getContentPane().add(jsbHoriz);
        jfrm.getContentPane().add(jlabVert);
        jfrm.getContentPane().add(jlabHoriz);
        jfrm.getContentPane().add(jlabVSBInfo);
        jfrm.getContentPane().add(jlabHSBInfo);

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
                new CustomSBDemo();
            }
        });
    }
}