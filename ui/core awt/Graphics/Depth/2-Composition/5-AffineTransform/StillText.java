/*
 * Аффинное преобразование.
 */

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.font.FontRenderContext;
import java.awt.font.TextLayout;
import java.awt.geom.AffineTransform;

public class StillText extends Frame
{
    public StillText(String s)
    {
        super(s);
        setSize(400, 200);
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
        int w = getSize().width;
        int h = getSize().height;
        FontRenderContext frc = g.getFontRenderContext();
        String s = "Тень";
        Font f = new Font("Serif", Font.BOLD, h / 3);
        TextLayout tl = new TextLayout(s, f, frc);
        AffineTransform at = new AffineTransform();
        at.setToTranslation(w / 2 - tl.getBounds().getWidth(), h / 2);
        Shape sh = tl.getOutline(at);
        g.draw(sh);
        AffineTransform atsh = new AffineTransform(1, 0.0, 1.5, -1, 0.0, 0.0);
        g.transform(at);
        g.transform(atsh);
        Font df = f.deriveFont(atsh);
        TextLayout dtl = new TextLayout(s, df, frc);
        Shape sh2 = dtl.getOutline(atsh);
        g.fill(sh2);
    }

    public static void main(String[] args)
    {
        new StillText("Эффект тени");
    }
}