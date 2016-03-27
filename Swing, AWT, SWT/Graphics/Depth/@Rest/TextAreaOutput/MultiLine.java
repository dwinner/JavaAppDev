// ћногострочный вывод.
import java.applet.Applet;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

/*
 * <applet code="MultiLine" width="300" height="100"> </applet>
 */
public class MultiLine extends Applet
{
    private int curX = 0, curY = 0; // текуща€ позици€

    @Override
    public void init()
    {
        Font f = new Font("SansSerif", Font.PLAIN, 12);
        setFont(f);
    }

    @Override
    public void paint(Graphics g)
    {
        FontMetrics fm = g.getFontMetrics();

        nextLine("This is on line one.", g);
        nextLine("This is on line two.", g);
        sameLine(" This is on same line.", g);
        sameLine(" This, too.", g);
        nextLine("This is on line three.", g);
    }

    // ѕродвинутьс€ к следующей строке.
    void nextLine(String s, Graphics g)
    {
        FontMetrics fm = g.getFontMetrics();

        curY += fm.getHeight(); // продвижение к следующей строке
        curX = 0;
        g.drawString(s, curX, curY);
        curX = fm.stringWidth(s);   // продвижение к концу строки
    }

    // ѕоказать на той же линии.
    void sameLine(String s, Graphics g)
    {
        FontMetrics fm = g.getFontMetrics();
        g.drawString(s, curX, curY);
        curX += fm.stringWidth(s);  // ѕродвижение к концу строки
    }
}