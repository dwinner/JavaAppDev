// ƒемонстрирует некоторые коды виртуальных клавиш.
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * <applet code="KeyEvents" width="300" height="100"> </applet>
 */
public class KeyEvents extends Applet implements KeyListener
{
    private String msg = "";
    private int X = 10, Y = 20;	// координаты вывода

    @Override
    public void init()
    {
        addKeyListener(this);	// регистраци€ блока прослушивани€
        requestFocus();			// запрос фокуса ввода
    }

    @Override
    public void keyPressed(KeyEvent ke)
    {
        showStatus("Key Down");

        int key = ke.getKeyCode();
        switch (key)
        {
            case KeyEvent.VK_F1:
                msg += "<F1>";
                break;
            case KeyEvent.VK_F2:
                msg += "<F2>";
                break;
            case KeyEvent.VK_F3:
                msg += "<F3>";
                break;
            case KeyEvent.VK_PAGE_DOWN:
                msg += "<PgDn>";
                break;
            case KeyEvent.VK_PAGE_UP:
                msg += "<PgUp>";
                break;
            case KeyEvent.VK_LEFT:
                msg += "<Left Arrow>";
                break;
            case KeyEvent.VK_RIGHT:
                msg += "<Right Arrow>";
                break;
        }

        repaint();
    }

    @Override
    public void keyReleased(KeyEvent ke)
    {
        showStatus("Key Up");
    }

    @Override
    public void keyTyped(KeyEvent ke)
    {
        msg += ke.getKeyChar();
        repaint();
    }

    // ѕоказывает нажатую клавишу в позиции указател€ мыши.
    @Override
    public void paint(Graphics g)
    {
        g.drawString(msg, X, Y);
    }

}
