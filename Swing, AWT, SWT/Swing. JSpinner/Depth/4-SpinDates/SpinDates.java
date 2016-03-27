// Выбор даты с помощью инкрементного регулятора.

import java.awt.FlowLayout;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

public class SpinDates
{
    private JSpinner jspin;
    private JLabel jlab;

    public SpinDates()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Spin Dates");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начальных размеров фрейма.
        jfrm.setSize(300, 120);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание объекта Calendar, который представляет текущую дату. Впоследствии
        // этот объект будет использован для формирования границ диапазона.
        GregorianCalendar g = new GregorianCalendar();

        // Получение текущей даты.
        Date curDate = new Date();

        // Установка нижней границы диапазона, равной текущей дате минус один месяц.
        g.add(Calendar.MONTH, -1);
        Date begin = g.getTime();
        g.add(Calendar.MONTH, 2);
        Date end = g.getTime();

        // Создание модели инкрементного регулятора, в который используются
        // подготовленные ранее значения даты.
        SpinnerDateModel spm = new SpinnerDateModel(curDate, begin, end, Calendar.HOUR);

        // Создание инкрементного регулятора, использующего подготовленную модель.
        jspin = new JSpinner(spm);

        // Создание метки для отображения выбранной даты.
        jlab = new JLabel(" Selected date is: " + curDate);

        // Связывание обработчика событий изменения состояния с инкрементным регулятором.
        jspin.addChangeListener(new ChangeListener()
        {
            @Override
            public void stateChanged(ChangeEvent ce)
            {
                // Получение выбранной даты.
                Date date = (Date) jspin.getValue();
                // Отображение выбранной даты.
                jlab.setText(" Selected date is: " + date + " ");
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
        // Create the frame on the event dispatching thread.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new SpinDates();
            }
        });
    }
}