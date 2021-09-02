import java.awt.Color;
import java.util.ListResourceBundle;

/**
 * Ресурсы, не являющиеся строками для английского интерфейса калькулятора.
 * <p/>
 * @version 1.21 2001-08-27
 * @author Cay Horstmann
 */
public class RetireResources extends ListResourceBundle
{
    static final Object[][] contents =
    {       // BEGIN LOCALIZE
        {
            "colorPre", Color.blue
        },
        {
            "colorGain", Color.white
        },
        {
            "colorLoss", Color.red
        }   // END LOCALIZE
    };

    @Override
    protected Object[][] getContents()
    {
        return contents.clone();
    }
}
