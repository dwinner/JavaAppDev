import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.awt.image.BufferedImage;
import javax.swing.JComponent;

/**
 * Компонент, выполняющий рисование двух фигур и объединяющий их с учетом правил композиции.
 */
public class CompositeComponent extends JComponent
{
    private int rule;
    private Shape shape1;
    private Shape shape2;
    private float alpha;

    public CompositeComponent()
    {
        shape1 = new Ellipse2D.Double(100, 100, 150, 100);
        shape2 = new Rectangle2D.Double(150, 150, 150, 100);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        BufferedImage image =
            new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_ARGB);
        Graphics2D gImage = image.createGraphics();
        gImage.setPaint(Color.red);
        gImage.fill(shape1);
        AlphaComposite composite = AlphaComposite.getInstance(rule, alpha);
        gImage.setComposite(composite);
        gImage.setPaint(Color.blue);
        gImage.fill(shape2);
        g2.drawImage(image, null, 0, 0);
    }

    /**
     * Установка правила композиции.
     * <p/>
     * @param aRule Правило (в виде константы AlphaComposite)
     */
    public void setRule(int aRule)
    {
        rule = aRule;
        repaint();
    }

    /**
     * Установка значения альфа источника.
     * <p/>
     * @param anAlpha Значение альфа в интервале от 0 до 100
     */
    public void setAlpha(int anAlpha)
    {
        alpha = (float) anAlpha / 100.0F;
        repaint();
    }
}
