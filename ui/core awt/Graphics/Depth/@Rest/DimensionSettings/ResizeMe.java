// Изменяет размеры вывода для установки текущих размеров окна.
import java.applet.Applet;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/*
 * <applet code="ResizeMe" width="200" height="200"> </applet>
 */
public class ResizeMe extends Applet
{
    final int inc = 25;
    private int max = 500;
    private int min = 200;
    private Dimension d;

    public ResizeMe()
    {
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseReleased(MouseEvent me)
            {
                int w = (d.width + inc) > max ? min : (d.width + inc);
                int h = (d.height + inc) > max ? min : (d.height + inc);
                setSize(new Dimension(w, h));
            }
        });
    }

    @Override
    public void paint(Graphics g)
    {
        d = getSize();

        g.drawLine(0, 0, d.width - 1, d.height - 1);
        g.drawLine(0, d.height - 1, d.width - 1, 0);
        g.drawLine(0, 0, d.width - 1, d.height - 1);
    }
}
