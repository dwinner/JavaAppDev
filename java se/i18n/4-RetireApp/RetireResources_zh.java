import java.awt.Color;
import java.util.ListResourceBundle;

/**
 * Ресурсы, не являющиеся строками для китайского интерфейса калькулятора.
 * <p/>
 * @version 1.21 2001-08-27
 * @author Cay Horstmann
 */
public class RetireResources_zh extends ListResourceBundle
{
    static final Object[][] contents =
    {       // BEGIN LOCALIZE
        {
            "colorPre", Color.red
        },
        {
            "colorGain", Color.blue
        },
        {
            "colorLoss", Color.yellow
        }   // END LOCALIZE
    };

    @Override
    protected Object[][] getContents()
    {
        return contents.clone();
    }
}
