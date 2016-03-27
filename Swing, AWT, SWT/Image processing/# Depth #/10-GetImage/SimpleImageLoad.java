// Загрузка изображений в апплете.
import java.applet.Applet;
import java.awt.Graphics;
import java.awt.Image;

public class SimpleImageLoad extends Applet
{
    private Image img;

    @Override
    public void init()
    {
        img = getImage(getDocumentBase(), getParameter("img"));
    }

    @Override
    public void paint(Graphics g)
    {
        g.drawImage(img, 0, 0, this);
    }
}
