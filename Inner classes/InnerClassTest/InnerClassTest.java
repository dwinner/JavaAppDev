import java.awt.*;
import java.awt.event.*;
import java.util.*;
import javax.swing.*;
import javax.swing.Timer;

/**
 * Эта программа демонстрирует использование вложенных классов.
 * @version 1.10 2004-02-27
 * @author Cay Horstmann
 */
public class InnerClassTest
{
    public static void main(String[] args)
    {
        TalkingClock clock = new TalkingClock(1000, true);
        clock.start();

        // Программа выполняется до тех пор, пока пользователь
        // не щелкнет на кнопке ОК.
        JOptionPane.showMessageDialog(null, "Quit program?");
        System.exit(0);
    }
}

/**
 * Часы, выводящие информацию о времени через заданный интервал.
 */
class TalkingClock
{
    private int interval;
    private boolean beep;
   
    /**
     * Конструирует "говорящие часы"
     * @param interval Интервал в миллисекундах между сообщениями
     * @param beep Значение true включает звуковой сигнал
     */
    TalkingClock(int interval, boolean beep)
    {
        this.interval = interval;
        this.beep = beep;
    }

    /**
     * Запускает часы.
     */
    public void start()
    {
        ActionListener listener = new TimePrinter();
        Timer t = new Timer(interval, listener);
        t.start();
    }

    class TimePrinter implements ActionListener
    {
        public void actionPerformed(ActionEvent event)
        {
            Date now = new Date();
            System.out.println("At the tone, the time is " + now);
            if (beep)
                Toolkit.getDefaultToolkit().beep();
        }
    }
}
