/* Простой апплет, который устанавливает цвета
символов и фона и выводит строку. */

import java.awt.*;
import java.applet.*;
/*
<applet code="Sample" width="300" height="50">
</applet>
 */

public class Sample extends Applet
{
    private String msg;

    // Устанавливает цвет символов и фона
    @Override public void init()
    {
        setBackground(Color.cyan);
        setForeground(Color.red);
        msg = "Inside init() --";
    }

    // Инициализирует строку для показа
    @Override public void start()
    {
        msg += " Inside start() --";
    }

    // Показывает msg в окне апплета
    @Override public void paint(Graphics g)
    {
        msg += " Inside paint().";
        g.drawString(msg, 10, 30);
    }
}
