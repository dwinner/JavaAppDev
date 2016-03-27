// ƒемонстрирует обработчики клавишных событий.
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/*
 * <applet code="SimpleKey" width="300" height="100"> </applet>
 */
public class SimpleKey extends Applet implements KeyListener
{
    private String msg = "";
    private int
       X = 10,
       Y = 20;	// координаты вывода

    @Override
    public void init()
    {
        addKeyListener(this);	// регистраци€ себ€ как блока прослушивани€ key-событий
        requestFocus();			// запрос фокуса ввода
    }

    @Override
    public void keyPressed(KeyEvent ke)
    {
        showStatus("Key Down");
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
