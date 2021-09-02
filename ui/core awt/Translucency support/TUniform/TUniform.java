import java.awt.*;
import javax.swing.*;
import static java.awt.GraphicsDevice.WindowTranslucency.*;

/**
 * Равномерная степень прозрачности окна
 * @author dwinner@inbox.ru
 */
public class TUniform extends JFrame
{
    public TUniform()
    {
        super("TranslucentWindow");
        setLayout(new GridBagLayout());
        
        setSize(300, 200);
        setLocationRelativeTo(null);
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Добавим кнопку для демонстрации
        add(new JButton("I am a Button"));
    }
    
    public static void main(String[] args)
    {
        // определим, поддерживает ли устройство полупрозрачность графики
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        
        // если полупрозрачные окна не поддерживаются, выходим
        if (!gd.isWindowTranslucencySupported(TRANSLUCENT))
        {
            System.err.println("Translucency is not supported");
            System.exit(0);
        }
        
        // Создаем UI в потоке обработки событий
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override public void run()
            {
                TUniform tw = new TUniform();
                
                // Установим окно на 55% непрозрачным (т.е. на 45% прозрачным)
                tw.setOpacity(0.55f);
                
                // Отобразим окно
                tw.setVisible(true);
            }
        });
    }
}
