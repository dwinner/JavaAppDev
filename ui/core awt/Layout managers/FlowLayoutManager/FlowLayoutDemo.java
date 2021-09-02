// Использует поточное размещение с левым выравниванием.
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class FlowLayoutDemo extends Applet implements ItemListener
{
    private String msg = "";
    private Checkbox Win98, winNT, solaris, mac;

    @Override public void init()
    {
        // установить поточное размещение с левым выраниванием
        setLayout(new FlowLayout(FlowLayout.LEFT));

        Win98 = new Checkbox("Windows 98", null, true);
        winNT = new Checkbox("Windows NT");
        solaris = new Checkbox("Solaris");
        mac = new Checkbox("MacOS");

        add(Win98);
        add(winNT);
        add(solaris);
        add(mac);

        // зарегистрироваться для приема item-событий
        Win98.addItemListener(this);
        winNT.addItemListener(this);
        solaris.addItemListener(this);
        mac.addItemListener(this);
    }

    // перерисовать, когда изменяется состояние флажка
    public void itemStateChanged(ItemEvent ie)
    {
        repaint();
    }

    // показать текущее состояние флажков
    @Override public void paint(Graphics g)
    {
        msg = "Current state: ";
        g.drawString(msg, 6, 80);
        msg = " Windows 98: " + Win98.getState();
        g.drawString(msg, 6, 100);
        msg = " Windows NT: " + winNT.getState();
        g.drawString(msg, 6, 120);
        msg = " Solaris: " + solaris.getState();
        g.drawString(msg, 6, 140);
        msg = " Mac: " + mac.getState();
        g.drawString(msg, 6, 160);
    }
}