// Демонстрирует обработчики событий мыши.
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/*
 * <applet code="MouseEvents" width="300" height="100"> </applet>
 */
public class MouseEvents extends Applet implements MouseListener, MouseMotionListener
{
    private String msg = "";
    private int
       mouseX = 0,
       mouseY = 0;	// координаты мыши

    @Override
    public void init()
    {
        addMouseListener(this);			// регистрация (себя) как блока прослушивания mouse-событий
        addMouseMotionListener(this);	// регистрация (себя) как блока прослушивания MouseMotion-событий
    }

    // Обработка щелчка мыши
    @Override
    public void mouseClicked(MouseEvent me)
    {
        mouseX = 0;
        mouseY = 10;
        msg = "Mouse clicked.";
        repaint();
    }

    // Обработка ввода мыши в область окна.
    @Override
    public void mouseEntered(MouseEvent me)
    {
        // сохранить координаты
        mouseX = 0;
        mouseY = 10;
        msg = "Mouse entered.";
        repaint();
    }

    // Обработка вывода мыши из области окна.
    @Override
    public void mouseExited(MouseEvent me)
    {
        // сохранить координаты
        mouseX = 0;
        mouseY = 10;
        msg = "Mouse exited.";
        repaint();
    }

    // Обработка нажатия кнопки.
    @Override
    public void mousePressed(MouseEvent me)
    {
        // Сохранить координаты
        mouseX = me.getX();
        mouseY = me.getY();
        msg = "Down";
        repaint();
    }

    // Обработка освобождения кнопки.
    @Override
    public void mouseReleased(MouseEvent me)
    {
        // Сохранить координаты
        mouseX = me.getX();
        mouseY = me.getY();
        msg = "Up";
        repaint();
    }

    // Обработка перетаскивания мыши.
    @Override
    public void mouseDragged(MouseEvent me)
    {
        // Сохранить координаты
        mouseX = me.getX();
        mouseY = me.getY();
        msg = "*";
        showStatus("Dragging mouse at " + mouseX + ", " + mouseY);
        repaint();
    }

    // Обработка перемещения мыши.
    @Override
    public void mouseMoved(MouseEvent me)
    {
        // Отобразить состояние
        showStatus("Moving mouse at " + me.getX() + ", " + me.getY());
    }

    // Вывести msg в окне апплета в положении (x, y)-координат.
    @Override
    public void paint(Graphics g)
    {
        g.drawString(msg, mouseX, mouseY);
    }

}
