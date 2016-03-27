// Демонстрирует списки.
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

public class ListDemo extends Applet implements ActionListener
{
    List os, browser;
    String msg = "";

    @Override
    public void init()
    {
        os = new List(4, true);
        browser = new List(4, false);

        // Добавить элементы в список OS
        os.add("Windows 98");
        os.add("Windows NT");
        os.add("Solaris");
        os.add("MacOS");

        // Добавить элементы в список браузеров
        browser.add("Netscape 1.1");
        browser.add("Netscape 2.x");
        browser.add("Netscape 3.x");
        browser.add("Netscape 4.x");

        browser.add("Internet Explorer 2.0");
        browser.add("Internet Explorer 3.0");
        browser.add("Internet Explorer 4.0");

        browser.add("Lynx 2.4");

        browser.select(1);

        // Добавить списки в окно
        add(os);
        add(browser);

        // регистрироваться для приема action-событий
        os.addActionListener(this);
        browser.addActionListener(this);
    }

    public void actionPerformed(ActionEvent ae)
    {
        repaint();
    }

    // Отобразить текущие выборы
    @Override
    public void paint(Graphics g)
    {
        int idx[];

        msg = "Current OS: ";
        idx = os.getSelectedIndexes();
        for (int i = 0; i < idx.length; i++) {
            msg += os.getItem(idx[i]) + "  ";
        }
        g.drawString(msg, 6, 120);
        msg = "Current Browser: ";
        msg += browser.getSelectedItem();
        g.drawString(msg, 6, 140);
    }
}