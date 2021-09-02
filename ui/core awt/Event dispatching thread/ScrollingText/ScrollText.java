// Апплет на базе Swing, в котором перемещается текст метки.

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.InvocationTargetException;
import javax.swing.JApplet;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

/*
 * <object code="ScrollText" width="240" height="100"> </object>
 */
public class ScrollText extends JApplet
{
    private JLabel jlab;
    private String msg = " Swing makes the GUI move! ";
    private ActionListener scroller;
    private Timer stTimer;  // Таймер, определяющий скорость перемещения.

    @Override
    public void init()
    {    // Инициализация апплета.
        try
        {
            SwingUtilities.invokeAndWait(new Runnable()
            {
                @Override
                public void run()
                {
                    guiInit();
                }

            });
        }
        catch (InterruptedException | InvocationTargetException exc)
        {
            System.out.println("Can't create because of " + exc);
        }
    }

    @Override
    public void start()
    {   // Активизация таймера при запуске апплета.
        stTimer.start();
    }

    @Override
    public void stop()
    {    // Остановка таймера при остановке апплета
        stTimer.stop();
    }

    @Override
    public void destroy()
    {   // Остановка таймера при удалении апплета.
        stTimer.stop();
    }

    private void guiInit()
    {   // Инициализация интерфейса.
        // Создание метки для перемещения сообщения.
        jlab = new JLabel(msg);
        jlab.setHorizontalAlignment(SwingConstants.CENTER);

        // Создание обработчика событий для таймера.
        scroller = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                // Перемещение сообщения влево на один символ.
                char ch = msg.charAt(0);
                msg = msg.substring(1, msg.length());
                msg += ch;
                jlab.setText(msg);
            }

        };

        // Создание таймера.
        stTimer = new Timer(200, scroller);
        // Включение метки в состав панели содержимого апплета.
        getContentPane().add(jlab);
    }

}