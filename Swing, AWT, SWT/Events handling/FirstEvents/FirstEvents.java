
/**
 * FirstEvents.java. События - нажатия клавиш на клавиатуре.
 */
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JFrame;

public class FirstEvents extends JFrame
{
    public FirstEvents()
    {
        super("FirstEvents");
        // При закрытии окна - выход
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Регистрируем нашего слушателя
        addKeyListener(new KeyL());
        // Выводим окно на экран
        setSize(200, 200);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new FirstEvents();
    }

}

/**
 * Этот класс будет получать извещения о событиях
 */
class KeyL implements KeyListener
{
    // Печать символа
    @Override
    public void keyTyped(KeyEvent e)
    {
        System.out.println(e);
    }

    // Нажатие клавиши
    @Override
    public void keyPressed(KeyEvent e)
    {
        System.out.println(e);
    }

    // Отпускание нажатой клавиши
    @Override
    public void keyReleased(KeyEvent e)
    {
        System.out.println(e);
    }

}