package pkg07.colorboxes;

import java.util.Random;
import java.util.concurrent.TimeUnit;
import org.eclipse.swt.SWT;
import org.eclipse.swt.SWTException;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Composite;

public class CBox extends Canvas implements Runnable
{
    class CBoxPaintListener implements PaintListener
    {
        @Override
        public void paintControl(PaintEvent e)
        {
            Color color = new Color(e.display, cColor);
            e.gc.setBackground(color);
            Point size = getSize();
            e.gc.fillRectangle(0, 0, size.x, size.y);
            color.dispose();
        }

    }

    private static Random rand = new Random();

    private static RGB newColor()
    {
        return new RGB(rand.nextInt(255), rand.nextInt(255), rand.nextInt(255));
    }

    private int pause;
    private RGB cColor = newColor();

    public CBox(Composite parent, int pause)
    {
        super(parent, SWT.NONE);
        this.pause = pause;
        addPaintListener(new CBoxPaintListener());
    }

    @Override
    public void run()
    {
        try
        {
            while ( ! Thread.interrupted())
            {
                cColor = newColor();
                getDisplay().asyncExec(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        try
                        {
                            redraw();
                        }
                        catch (SWTException e)
                        {
                        }
                        // SWTException is OK when the parent
                        // is terminated from under us.
                    }

                });
                TimeUnit.MILLISECONDS.sleep(pause);
            }
        }
        catch (InterruptedException | SWTException e)
        {
            // Acceptable way to exit
        }
    }

}
