import java.awt.Color;
import java.util.ListResourceBundle;

/**
 * Ресурсы, не являющиеся строками для немецкого интерфейса калькулятора.
 * <p/>
 * @version 1.21 2001-08-27
 * @author Cay Horstmann
 */
public class RetireResources_de extends ListResourceBundle
{
    static final Object[][] contents =
    {       // BEGIN LOCALIZE
        {
            "colorPre", Color.yellow
        },
        {
            "colorGain", Color.black
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
