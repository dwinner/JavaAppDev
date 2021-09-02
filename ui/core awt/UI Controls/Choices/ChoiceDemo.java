// Демонстрирует Choice-списки.
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

/*
<applet code="ChoiceDemo" width="300" height="180">
</applet>
 */
public class ChoiceDemo extends Applet implements ItemListener
{
    Choice os, browser;
    String msg = "";

    public void init()
    {
        os = new Choice();
        browser = new Choice();

        // добавить элементы с список OS
        os.add("Windows 98");
        os.add("Windows NT");
        os.add("Solaris");
        os.add("MacOS");

        // добавить элементы в список браузеров
        browser.add("Netscape 1.1");
        browser.add("Netscape 2.x");
        browser.add("Netscape 3.x");
        browser.add("Netscape 4.x");

        browser.add("Internet Explorer 2.0");
        browser.add("Internet Explorer 3.0");
        browser.add("Internet Explorer 4.0");

        browser.add("Lynx 2.4");

        browser.select("Netscape 4.x");

        // добавить choice-списки в окно
        add(os);
        add(browser);

        // регистрироваться для приема item-событий
        os.addItemListener(this);
        browser.addItemListener(this);
    }

    public void itemStateChanged(ItemEvent ie)
    {
        repaint();
    }

    // Отобразить текущие выборы
    public void paint(Graphics g)
    {
        msg = "Current OS: ";
        msg += os.getSelectedItem();
        g.drawString(msg, 6, 120);
        msg = "Current Browser: ";
        msg += browser.getSelectedItem();
        g.drawString(msg, 6, 140);
    }
}
