// Демонстрирует флажки.
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

/*
<applet code="CBGroup" width="250" height="200">
</applet>
 */
public class CBGroup extends Applet implements ItemListener
{
    String msg = "";
    Checkbox Win98, winNT, solaris, mac;
    CheckboxGroup cbg;

    public void init()
    {
        cbg = new CheckboxGroup();
        Win98 = new Checkbox("Windows 98", cbg, true);
        winNT = new Checkbox("Windows NT", cbg, false);
        solaris = new Checkbox("Solaris", cbg, false);
        mac = new Checkbox("MacOS", cbg, false);

        add(Win98);
        add(winNT);
        add(solaris);
        add(mac);

        Win98.addItemListener(this);
        winNT.addItemListener(this);
        solaris.addItemListener(this);
        mac.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent ie)
    {
        repaint();
    }

    // Отобразить текущее состояние группы
    public void paint(Graphics g)
    {
        msg = "Current selection: ";
        msg += cbg.getSelectedCheckbox().getLabel();
        g.drawString(msg, 6, 100);
    }
}
