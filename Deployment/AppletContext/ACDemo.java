/* Использование контекста апплета, getCodeBase(),
и showDocument() для просмотра HTML-файла.
 */

import java.awt.*;
import java.applet.*;
import java.net.*;

/*
<applet code="ACDemo" width="300" height="50">
</applet>
 */
public class ACDemo extends Applet
{
    @Override public void start()
    {
        AppletContext ac = getAppletContext();
        URL url = getCodeBase();	// получить url данного апплета

        try
        {
            ac.showDocument(new URL(url + "Test.html"));
        }
        catch (MalformedURLException e)
        {
            showStatus("URL is not found.");
        }
    }
}
