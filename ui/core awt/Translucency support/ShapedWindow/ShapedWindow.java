import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.awt.geom.Ellipse2D;
import static java.awt.GraphicsDevice.WindowTranslucency.*;

/**
 * Окно произвольной формы
 * @author dwinner@inbox.ru
 */
public class ShapedWindow extends JFrame
{
    public ShapedWindow() throws HeadlessException
    {
        super("ShapedWindow");
        setLayout(new GridBagLayout());
        
        /* Лучше всего для установки формы окна использовать метод
         componentResize. Тогда, если окно изменяет размер,
         форма будет правильно пересчитываться */
        addComponentListener(new ComponentAdapter()
            {
                @Override public void componentResized(ComponentEvent e)
                {
                    setShape(new Ellipse2D.Double(0, 0, getWidth(), getHeight()));
                }
                
            }
        );
        
        setUndecorated(true);
        setSize(300, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        add(new JButton("I am a Button"));
    }
    
    public static void main(String[] args)
    {
        // Определим, поддерживает ли графическое устройство прозрачность окон
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        final boolean isTranslucencySupported = gd.isWindowTranslucencySupported(TRANSLUCENT);
        
        // Если окна произвольной формы не имеют поддержки, выходим
        if (!gd.isWindowTranslucencySupported(PERPIXEL_TRANSPARENT))
        {
            System.err.println("Shaped windows are not supported");
            System.exit(0);
        }
        
        // Если прозрачные окна не имеют поддержки, создаем непрозрачное окно.
        if (!isTranslucencySupported)
        {
            System.out.println("Translucency is not supported, creating an opaque window");
        }
        
        // Создаем UI в потоке обработки событий.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override public void run()
            {
                ShapedWindow sw = new ShapedWindow();
                
                // Сделаем окно на 70% прозрачным, если имеется такая возможность.
                if (isTranslucencySupported)
                {
                    sw.setOpacity(0.7f);
                }
                
                // Отобразим окно
                sw.setVisible(true);
            }
        });
    }
}
