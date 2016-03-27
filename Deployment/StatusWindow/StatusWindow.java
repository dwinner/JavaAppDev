// Использование окна состояния.
import java.awt.*;
import java.applet.*;
/*
<applet code="StatusWindow" width="300" height="50">
</applet>
 */

public class StatusWindow extends Applet
{
    @Override public void init()
    {
        setBackground(Color.cyan);
    }

    // Отображает msg в окне апплета.
    @Override public void paint(Graphics g)
    {
        g.drawString("This is in the applet window.", 10, 20);
        showStatus("This is shown in the status window.");
    }
}
