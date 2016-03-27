// Использование объектов JScrollBar
  
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public class SBDemo
{
    private JLabel jlabVert;
    private JLabel jlabHoriz;
    private JLabel jlabSBInfo;

    private JScrollBar jsbVert;
    private JScrollBar jsbHoriz;

    public SBDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Demonstrate Scroll Bars");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Определение исходного размера фрейма.
        jfrm.setSize(260, 260);

        // Завершение программы при закрытии пользователем окна.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Метки, отображающие текущие значения полос прокрутки.
        jlabVert = new JLabel("Value of vertical scroll bar: 0");
        jlabHoriz = new JLabel("Value of horizontal scroll bar: 0");

        // Создание вертикальной и горизонтальной полос прокрутки
        jsbVert = new JScrollBar(); // Вертикальная ориентация по умолчанию.
        jsbHoriz = new JScrollBar(Adjustable.HORIZONTAL);

        // Обработчики событий регулировки для полос прокрутки.
        // Перед тем как выполнить действие, соответствующее событию,
        // вертикальная полоса прокрутки ожидает окончание действий пользователя.
        jsbVert.addAdjustmentListener(new AdjustmentListener()
        {
            public void adjustmentValueChanged(AdjustmentEvent ae)
            {
                // Если полоса прокрутки претерпевает изменения, никакие действия не выполняются.
                if (jsbVert.getValueIsAdjusting()) 
                    return;
                // Отображение нового значения.
                jlabVert.setText("Value of vertical scroll bar: " + ae.getValue());
            }
        });

        // Горизонтальная полоса прокрутки реагирует на все события регулировки,
        // независимо от того, выполняет ли пользователь действия,
        // изменяющие состояние компонента.
        jsbHoriz.addAdjustmentListener(new AdjustmentListener()
        {
            public void adjustmentValueChanged(AdjustmentEvent ae)
            {
                // Отображение нового значения.
                jlabHoriz.setText("Value of horizontal scroll bar: " + ae.getValue());
            }
        });

        // Отображение свойств полос прокрутки, установленных по умолчанию.
        jlabSBInfo = new JLabel("<html>Scroll Bar Defaults<br />" +
                       "Minimum value: " +
                       jsbVert.getMinimum() + "<br />" +
                       "Maximum value: " +
                       jsbVert.getMaximum() + "<br />" +
                       "Visible amount (extent): " +
                       jsbVert.getVisibleAmount() + "<br />" +
                       "Block increment: " +
                       jsbVert.getBlockIncrement() + "<br />" +
                       "Unit increment: " +
                       jsbVert.getUnitIncrement());
        // Добавление компонентов к панели содержимого.
        jfrm.getContentPane().add(jsbVert);
        jfrm.getContentPane().add(jsbHoriz);
        jfrm.getContentPane().add(jlabVert);
        jfrm.getContentPane().add(jlabHoriz);
        jfrm.getContentPane().add(jlabSBInfo);

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
                new SBDemo();
            }
        });
    }
}