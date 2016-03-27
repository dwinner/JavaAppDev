/*
 * Печать средствами AWT.
 */

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PrintTest extends Frame
{
    public PrintTest(String s)
    {
        super(s);
        setSize(400, 400);
        setVisible(true);
    }

    public void simplePrint()
    {
        PrintJob pj = getToolkit().getPrintJob(this, "Job Title", null);
        if (pj != null)
        {
            Graphics pg = pj.getGraphics();
            if (pg != null)
            {
                print(pg);
                pg.dispose();
            }
            else
            {
                System.out.println("Graphics context is empty");
            }
            pj.end();
        }
        else
        {
            System.err.println("Job's null");
        }
    }

    @Override
    public void paint(Graphics g)
    {
        g.setFont(new Font("Serif", Font.ITALIC, 30));
        g.setColor(Color.black);
        g.drawArc(100, 100, 200, 200, 0, 360);
        g.drawString("Page 1", 100, 100);
    }

    public static void main(String[] args)
    {
        PrintTest pt = new PrintTest("Simple printing example");
        pt.simplePrint();
        pt.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent ev)
            {
                System.exit(0);
            }
        });
    }
}