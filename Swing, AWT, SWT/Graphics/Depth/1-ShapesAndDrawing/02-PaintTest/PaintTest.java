/*
 * Способы заливки фигур.
 */

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;

public class PaintTest extends Frame
{
    public PaintTest(String s)
    {
        super(s);
        setSize(300, 300);
        setVisible(true);
        addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent ev)
            {
                System.exit(0);
            }
        });
    }

    @Override
    public void paint(Graphics gr)
    {
        Graphics2D g = (Graphics2D) gr;
        BufferedImage bi = new BufferedImage(20, 20, BufferedImage.TYPE_INT_RGB);
        Graphics2D big = bi.createGraphics();
        big.draw(new Line2D.Double(0.0, 0.0, 10.0, 10.0));
        big.draw(new Line2D.Double(0.0, 10.0, 10.0, 0.0));
        TexturePaint tp = new TexturePaint(bi, new Rectangle2D.Double(0.0, 0.0, 10.0, 10.0));
        g.setPaint(tp);
        g.fill(new Rectangle2D.Double(50, 50, 200, 200));
        GradientPaint gp = new GradientPaint(100, 100, Color.white, 150, 150, Color.black, true);
        g.setPaint(gp);
        g.fill(new Ellipse2D.Double(100, 100, 200, 200));
    }

    public static void main(String[] args)
    {
        new PaintTest("Способы заливки");
    }
}