import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import static java.awt.GraphicsDevice.WindowTranslucency.*;

/**
 * Проверка возможностей прозрачности трех типов
 * @author dwinner@inbox.ru
 */
public class TSupportedTest
{
    public static void main(String[] args)
    {
        // определить, какие устройства по умолчанию могут поддерживать
        // графику для прозрачности
        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice gd = ge.getDefaultScreenDevice();
        
        boolean isUniformTranslucencySupported =
            gd.isWindowTranslucencySupported(TRANSLUCENT);
        boolean isPerPixelTranslucencySupported =
            gd.isWindowTranslucencySupported(PERPIXEL_TRANSLUCENT);
        boolean isShapedWindowSupported =
            gd.isWindowTranslucencySupported(PERPIXEL_TRANSPARENT);
        
        if (
            isUniformTranslucencySupported
            && isPerPixelTranslucencySupported
            && isShapedWindowSupported)
        {
            System.out.println("Default Graphics Device supported");
        }
    }
}
