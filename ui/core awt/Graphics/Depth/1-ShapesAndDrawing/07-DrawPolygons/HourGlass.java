// ������ �������������.
import java.applet.Applet;
import java.awt.Graphics;

/*
 * <applet code="HourGlass" width="230" height="210"> </applet>
 */
public class HourGlass extends Applet
{
    @Override
    public void paint(Graphics g)
    {
        int xpoints[] =
        {
            30, 200, 30, 200, 30
        };
        int ypoints[] =
        {
            30, 30, 200, 200, 30
        };
        int num = 5;

        g.drawPolygon(xpoints, ypoints, num);
    }
}
