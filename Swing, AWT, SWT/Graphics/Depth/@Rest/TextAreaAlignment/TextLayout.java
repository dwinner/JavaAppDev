// Переключение выравнивания текста.
import java.applet.Applet;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.StringTokenizer;


/*
 * <applet code="TextLayout" width="200" height="200"> <param name="text" value="Output to a Java window is actually
 * quite easy. As you have seen, the AWT provides support for fonts, colors, text, and graphics. <P> Of course, you must
 * effectively utilize these items if you are to achieve professional results." /> <param name="fontname" value="Serif"
 * /> <param name="fontSize" value="14" /> </applet>
 */
public class TextLayout extends Applet
{
    final int LEFT = 0;
    final int RIGHT = 1;
    final int CENTER = 2;
    final int LEFTRIGHT = 3;
    private int align;
    private Dimension d;
    private Font f;
    private FontMetrics fm;
    private int fontSize;
    private int fh, bl;
    private int space;
    private String text;

    public int getAlign()
    {
        return align;
    }

    public void setAlign(int align)
    {
        this.align = align;
    }

    @Override
    public void init()
    {
        setBackground(Color.white);
        text = getParameter("text");
        try
        {
            fontSize = Integer.parseInt(getParameter("fontSize"));
        }
        catch (NumberFormatException e)
        {
            fontSize = 14;
        }
        align = LEFT;
        addMouseListener(new MyMouseAdapter(this));
    }

    @Override
    public void paint(Graphics g)
    {
        update(g);
    }

    @Override
    public void update(Graphics g)
    {
        d = getSize();
        g.setColor(getBackground());
        g.fillRect(0, 0, d.width, d.height);
        if (f == null)
        {
            f = new Font(getParameter("fontname"), Font.PLAIN, fontSize);
        }
        g.setFont(f);
        if (fm == null)
        {
            fm = g.getFontMetrics();
            bl = fm.getAscent();
            fh = bl + fm.getDescent();
            space = fm.stringWidth(" ");
        }

        g.setColor(Color.black);
        StringTokenizer st = new StringTokenizer(text);
        int x = 0;
        int nextx;
        int y = 0;
        String word, sp;
        int wordCount = 0;
        String line = "";
        while (st.hasMoreTokens())
        {
            word = st.nextToken();
            if (word.equals("<P>"))
            {
                drawString(g, line, wordCount, fm.stringWidth(line), y + bl);
                line = "";
                wordCount = 0;
                x = 0;
                y += fh * 2;
            }
            else
            {
                int w = fm.stringWidth(word);
                if ((nextx = (x + space + w)) > d.width)
                {
                    drawString(g, line, wordCount, fm.stringWidth(line), y + bl);
                    line = "";
                    wordCount = 0;
                    x = 0;
                    y += fh;
                }
                sp = (x != 0) ? " " : "";
                line += sp + word;
                x += space + w;
                wordCount++;
            }
        }
        drawString(g, line, wordCount, fm.stringWidth(line), y + bl);
    }

    public void drawString(Graphics g, String line, int wc, int lineW, int y)
    {
        switch (align)
        {
            case LEFT:
                g.drawString(line, 0, y);
                break;
            case RIGHT:
                g.drawString(line, d.width - lineW, y);
                break;
            case CENTER:
                g.drawString(line, (d.width - lineW) / 2, y);
                break;
            case LEFTRIGHT:
                if (lineW < (int) (d.width * .75))
                {
                    g.drawString(line, 0, y);
                }
                else
                {
                    int toFill = (int) ((d.width - lineW) / wc);
                    int nudge = d.width - lineW - (toFill * wc);
                    int s = fm.stringWidth(" ");
                    StringTokenizer st = new StringTokenizer(line);
                    int x = 0;
                    while (st.hasMoreTokens())
                    {
                        String word = st.nextToken();
                        g.drawString(word, x, y);
                        if (nudge > 0)
                        {
                            x += fm.stringWidth(word) + space + toFill + 1;
                            nudge--;
                        }
                        else
                        {
                            x += fm.stringWidth(word) + space + toFill;
                        }
                    }
                }
                break;
        }
    }
}

class MyMouseAdapter extends MouseAdapter
{
    private TextLayout tl;

    MyMouseAdapter(TextLayout tl)
    {
        this.tl = tl;
    }

    @Override
    public void mouseClicked(MouseEvent me)
    {
        tl.setAlign((tl.getAlign() + 1) % 4);
        tl.repaint();
    }
}