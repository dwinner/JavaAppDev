/* 
 * Апплет с бегущим заголовком.
 * Этот апплет создает поток, который прокручивает сообщение,
 * содержащееся в msg, через окно апплета. 
 */

import java.awt.*;
import java.applet.*;
/*
<applet code="SimpleBanner" width="300" height="50">
</applet>
 */

public class SimpleBanner extends Applet implements Runnable
{
    private String msg = " A Simple Moving Banner.";
    private Thread t = null;
    private int state;
    private boolean stopFlag;

    // Установка цветов и инициализация потока.
    @Override public void init()
    {
        setBackground(Color.cyan);
        setForeground(Color.red);
    }

    // Запустить поток.
    @Override public void start()
    {
        t = new Thread(this);
        stopFlag = false;
        t.start();
    }

    // Точка входа для потока, который запускает заголовок.
    public void run()
    {
        char ch;

        // Показать заголовок.
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

    // Остановить заголовок.
    @Override public void stop()
    {
        stopFlag = true;
        t = null;
    }

    // Показать заголовок.
    @Override public void paint(Graphics g)
    {
        g.drawString(msg, 50, 30);
    }
}
