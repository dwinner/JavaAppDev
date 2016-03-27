// Обработка событий мыши как в дочернем окне, так и окне апплета.
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

/*
 * <applet code="WindowEvents" width="300" height="50">
 * </applet>
 */

// Создать подкласс Frame.
class SampleFrame extends Frame implements MouseListener, MouseMotionListener
{
    String msg = "";
    int mouseX = 10, mouseY = 40;
    int movX = 0, movY = 0;

    SampleFrame(String title)
    {
        super(title);
        // регистрировать этот объект, чтобы получать свои собственные события мыши
        addMouseListener(this);
        addMouseMotionListener(this);
        // Создать объект для обработки событий окна
        MyWindowAdapter adapter = new MyWindowAdapter(this);
        // регистрировать его для получения таких событий
        addWindowListener(adapter);
    }

    // Обработать событие "Щелчок мыши".
    public void mouseClicked(MouseEvent me) { }

    // Обработать событие "Мышь введена".
    public void mouseEntered(MouseEvent evtObj)
    {
        // сохранить координаты
        mouseX = 10;
        mouseY = 54;
        msg = "Mouse just entered child";
        repaint();
    }

    // Обработать событие "Мышь выведена".
    public void mouseExited(MouseEvent evtObj)
    {
        // сохранить координаты
        mouseX = 10;
        mouseY = 54;
        msg = "Mouse just left child window.";
        repaint();
    }

    // Обработать событие "Кнопка мыши нажата".
    public void mousePressed(MouseEvent me)
    {
        // сохранить координаты
        mouseX = me.getX();
        mouseY = me.getY();
        msg = "Down";
        repaint();
    }

    // Обработать событие "Кнопка мыши отпущена".
    public void mouseReleased(MouseEvent me)
    {
        // Сохранить координаты
        mouseX = me.getX();
        mouseY = me.getY();
        msg = "Up";
        repaint();
    }

    // Обработать событие "Мышь перетащена".
    public void mouseDragged(MouseEvent me)
    {
        // сохранить координаты
        mouseX = me.getX();
        mouseY = me.getY();
        movX = me.getX();
        movY = me.getY();
        msg = "*";
        repaint();
    }

    // Обработать событие "Мышь передвинута".
    public void mouseMoved(MouseEvent me)
    {
        // сохранить координаты
        movX = me.getX();
        movY = me.getY();
        repaint(0, 0, 100, 60);
    }

    @Override public void paint(Graphics g)
    {
        g.drawString(msg, mouseX, mouseY);
        g.drawString("Mouse at " + movX + ", " + movY, 10, 40);
    }

}

class MyWindowAdapter extends WindowAdapter
{
    SampleFrame sampleFrame;

    public MyWindowAdapter(SampleFrame sampleFrame)
    {
        this.sampleFrame = sampleFrame;
    }

    public void windowClosing(WindowEvent we)
    {
        sampleFrame.setVisible(false);
    }

}

// Окно апплета.
public class WindowEvents extends Applet implements MouseListener, MouseMotionListener
{
    SampleFrame f;
    String msg = "";
    int mouseX = 0, mouseY = 10;
    int movX = 0, movY = 0;

    // Создать фрейм-окно
    @Override public void init()
    {
        f = new SampleFrame("Handle Mouse Events");
        f.setSize(300, 200);
        f.setVisible(true);

        // регистрировать этот объект, чтобы получить его собственные события мыши.
        addMouseListener(this);
        addMouseMotionListener(this);
    }

    // Удалить фрейм-окно при останове апплета.
    @Override public void stop()
    {
        f.setVisible(false);
    }

    // Показать фрейм-окно при старте апплета.
    @Override public void start()
    {
        f.setVisible(true);
    }

    // Обработать событие "Кнопка мыши нажата".
    public void mouseClicked(MouseEvent me) { }

    // Обработать событие "Мышь введена".
    public void mouseEntered(MouseEvent me)
    {
        // сохранить координаты
        mouseX = 0;
        mouseY = 24;
        msg = "Mouse just entered applet window.";
        repaint();
    }

    // Обработать событие "Мышь выведена".
    public void mouseExited(MouseEvent me)
    {
        // сохранить координаты
        mouseX = 0;
        mouseY = 24;
        msg = "Mouse just left applet window.";
        repaint();
    }

    // Обработать событие "Кнопка мыши нажата".
    public void mousePressed(MouseEvent me)
    {
        // сохранить координаты
        mouseX = me.getX();
        mouseY = me.getY();
        msg = "Down";
        repaint();
    }

    // Обработать событие "Кнопка мыши отпущена".
    public void mouseReleased(MouseEvent me)
    {
        // сохранить координаты
        mouseX = me.getX();
        mouseY = me.getY();
        msg = "Up";
        repaint();
    }

    // Обработать событие "Мышь перетащена".
    public void mouseDragged(MouseEvent me)
    {
        // сохранить координаты
        mouseX = me.getX();
        mouseY = me.getY();
        movX = me.getX();
        movY = me.getY();
        msg = "*";
        repaint();
    }

    // Обработать событие "Мышь передвинута".
    public void mouseMoved(MouseEvent me)
    {
        // сохранить координаты
        movX = me.getX();
        movY = me.getY();
        repaint(0, 0, 100, 20);
    }

    // Вывести msg в окне апплета.
    @Override public void paint(Graphics g)
    {
        g.drawString(msg, mouseX, mouseY);
        g.drawString("Mouse at " + movX + ", " + movY, 0, 10);
    }

}