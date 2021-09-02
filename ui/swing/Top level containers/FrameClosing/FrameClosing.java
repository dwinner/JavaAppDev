// Использование окна с рамкой
import javax.swing.*;

public class FrameClosing extends JFrame
{    
    public FrameClosing()
    {
        super("Window title");
        // Операция при закрытии окна
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Значок для окна
        setIconImage(getToolkit().getImage("title.png"));
        // Вывод на экран
        setSize(200, 100);
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new FrameClosing();
    }
}