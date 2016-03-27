// Отображение баз кода и документа
import java.awt.*;
import java.applet.*;
import java.net.*;

/*
<applet code="Bases" width="300" height="50">
</applet>
 */
public class Bases extends Applet
{
    // Отобразить базы кода и документа.

    @Override public void paint(Graphics g)
    {
        String msg;

        URL url = getCodeBase();	// получить базу кода
        msg = "Code base: " + url.toString();
        g.drawString(msg, 10, 20);
        url = getDocumentBase();	// получить базу документа
        msg = "Document base: " + url.toString();
        g.drawString(msg, 10, 40);
    }
}
