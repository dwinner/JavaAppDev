import java.awt.AWTEvent;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Point;
import java.awt.RadialGradientPaint;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

import javax.swing.JComponent;
import javax.swing.JLayer;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.plaf.LayerUI;

public class SpotlightLayerUI extends LayerUI<JPanel>
{
	private static final long serialVersionUID = 8173902139075050639L;
	
	private boolean mActive;
	private int mX, mY;
	
	@Override public void installUI(JComponent c)
	{
		super.installUI(c);
		@SuppressWarnings("rawtypes")
		JLayer jlayer = (JLayer) c;
		jlayer.setLayerEventMask(
				AWTEvent.MOUSE_EVENT_MASK | AWTEvent.MOUSE_MOTION_EVENT_MASK
		);
	}
	
	@Override public void uninstallUI(JComponent c)
	{
		@SuppressWarnings("rawtypes")
		JLayer jlayer = (JLayer) c;
		jlayer.setLayerEventMask(0);
		super.uninstallUI(c);
	}
	
	@Override public void paint(Graphics g, JComponent c)
	{
		Graphics2D g2 = (Graphics2D) g.create();
		
		// Рисуем представление
		super.paint(g2, c);
		
		if (mActive)
		{
			// Создаем радиальный градиент, с транспэрентом в центре.
			Point2D center = new Point2D.Float(mX, mY);
			float radius = 72;
			float[] dist = { 0.0f, 1.0f };
			Color[] colors = { new Color(0.0f, 0.0f, 0.0f, 0.0f), Color.BLACK };
			RadialGradientPaint p = new RadialGradientPaint(center, radius, dist, colors);
			g2.setPaint(p);
			g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, .6f));
			g2.fillRect(0, 0, c.getWidth(), c.getHeight());
		}
	}
	
	@Override protected void processMouseEvent(
		MouseEvent e,
		JLayer<? extends JPanel> l
	)
	{
		if (e.getID() == MouseEvent.MOUSE_ENTERED)
			mActive = true;
		if (e.getID() == MouseEvent.MOUSE_EXITED)
			mActive = false;
	}
	
	@Override protected void processMouseMotionEvent(
		MouseEvent e,
		JLayer<? extends JPanel> l
	)
	{
		Point p = SwingUtilities.convertPoint(e.getComponent(), e.getPoint(), l);
		mX = p.x;
		mY = p.y;
		l.repaint();
	}
	
}
