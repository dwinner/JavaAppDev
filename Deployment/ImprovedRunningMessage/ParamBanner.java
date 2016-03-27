// Параметризованный заголовок.
import java.awt.*;
import java.applet.*;

/*
<applet code="ParamBanner" width="300" height="50">
<param name="message" value="Java makes the Web move!">
</applet>
 */
public class ParamBanner extends Applet implements Runnable
{
    private String msg;
    private Thread t = null;
    private int state;
    private boolean stopFlag;

    // Устанавливает цвета и инициализирует поток.
    @Override public void init()
    {
        setBackground(Color.cyan);
        setForeground(Color.red);
    }

    // Запустить поток.
    @Override public void start()
    {
        msg = getParameter("message");
        msg = (msg == null) ? "Message not found." : " " + msg;
        t = new Thread(this);
        stopFlag = false;
        t.start();
    }

    // Точка входа для потока, который выполняет заголовок.
    public void run()
    {
        char ch;

        // Показать заголовок на экране.
        for (;;)
        {
            try
            {
                repaint();
                Thread.sleep(250);
                ch = msg.charAt(0);
                msg = msg.substring(1, msg.length());
                msg += ch;
                if (stopFlag)
                {
                    break;
                }
            }
            catch (InterruptedException e)
            {
            }
        }
    }

    // Приостановить заголовок.
    @Override public void stop()
    {
        stopFlag = true;
        t = null;
    }

    // Показать заголовок на экране.
    @Override public void paint(Graphics g)
    {
        g.drawString(msg, 50, 30);
    }
}
