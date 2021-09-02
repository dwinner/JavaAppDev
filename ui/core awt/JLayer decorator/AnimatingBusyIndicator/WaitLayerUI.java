
import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Composite;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JComponent;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.Timer;
import javax.swing.plaf.LayerUI;

public class WaitLayerUI extends LayerUI<JPanel> implements ActionListener
{
    private boolean mIsRunning;
    private boolean mIsFadingOut;
    private Timer mTimer;
    private int mAngle;
    private int mFadeCount;
    private int mFadeLimit = 15;

    @Override public void paint(Graphics g, JComponent c)
    {
        int w = c.getWidth();
        int h = c.getHeight();

        // Рисуем представление.
        super.paint(g, c);

        if (!mIsRunning)
        {
            return;
        }

        Graphics2D g2 = (Graphics2D) g.create();

        float fade = (float) mFadeCount / (float) mFadeLimit;
        // Затемняем.
        Composite urComposite = g2.getComposite();
        g2.setComposite(
            AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .5f * fade)
        );
        g2.fillRect(0, 0, w, h);
        g2.setComposite(urComposite);

        // Рисуем индикатор ожидания.
        int s = Math.min(w, h) / 5;
        int cx = w / 2;
        int cy = h / 2;
        g2.setRenderingHint(
            RenderingHints.KEY_ANTIALIASING,
            RenderingHints.VALUE_ANTIALIAS_ON
        );
        g2.setStroke(
            new BasicStroke(
            s / 4,
            BasicStroke.CAP_ROUND,
            BasicStroke.JOIN_ROUND)
        );
        g2.setPaint(Color.white);
        g2.rotate(Math.PI * mAngle / 180, cx, cy);
        for (int i = 0; i < 12; i++)
        {
            float scale = (11.0f - (float) i) / 11.0f;
            g2.drawLine(cx + s, cy, cx + s * 2, cy);
            g2.rotate(-Math.PI / 6, cx, cy);
            g2.setComposite(
                AlphaComposite.getInstance(AlphaComposite.SRC_OVER, scale * fade)
            );
        }

        g2.dispose();
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
        if (mIsRunning)
        {
            // Уведомляем об изменении свойства явным образом,
            // т.о. происходит вызов метода applyPropertyChange
            firePropertyChange("tick", 0, 1);
            mAngle += 3;
            if (mAngle >= 360)
            {
                mAngle = 0;
            }
            if (mIsFadingOut)
            {
                if (--mFadeCount == 0)
                {
                    mIsRunning = false;
                    mTimer.stop();
                }
            }
            else if (mFadeCount < mFadeLimit)
            {
                mFadeCount++;
            }
        }
    }

    public void start()
    {
        if (mIsRunning)
        {
            return;
        }

        // Запускаем таймер для анимации.
        mIsRunning = true;
        mIsFadingOut = false;
        mFadeCount = 0;
        int fps = 24;
        int tick = 1000 / fps;
        mTimer = new Timer(tick, this);
        mTimer.start();
    }

    public void stop()
    {
        mIsFadingOut = true;
    }

    @Override public void applyPropertyChange(PropertyChangeEvent pce, JLayer l)
    {
        if ("tick".equals(pce.getPropertyName()))
        {
            l.repaint();
        }
    }
}