import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 * Панель с диаграммой пенсионного плана.
 */
public class RetireCanvas extends JPanel
{
    private RetireInfo info = null;
    private Color colorPre;
    private Color colorGain;
    private Color colorLoss;
    private final static int PANEL_WIDTH = 400;
    private final static int PANEL_HEIGHT = 200;

    public RetireCanvas()
    {
        setSize(PANEL_WIDTH, PANEL_HEIGHT);
    }

    /**
     * Установка информации, отображаемой на графике.
     * <p/>
     * @param newInfo Информация для отображения.
     */
    public void setInfo(RetireInfo newInfo)
    {
        info = newInfo;
        repaint();
    }

    @Override
    public void paintComponent(Graphics g)
    {
        Graphics2D g2 = (Graphics2D) g;
        if (info == null)
        {
            return;
        }

        double minValue = 0;
        double maxValue = 0;
        int i;
        for (i = info.getCurrentAge(); i <= info.getDeathAge(); i++)
        {
            double v = info.getBalance(i);
            if (minValue > v)
            {
                minValue = v;
            }
            if (maxValue < v)
            {
                maxValue = v;
            }
        }
        if (maxValue == minValue)
        {
            return;
        }

        int barWidth = getWidth() / (info.getDeathAge() - info.getCurrentAge() + 1);
        double scale = getHeight() / (maxValue - minValue);

        for (i = info.getCurrentAge(); i <= info.getDeathAge(); i++)
        {
            int x1 = (i - info.getCurrentAge()) * barWidth + 1;
            int y1;
            double v = info.getBalance(i);
            int height;
            int yOrigin = (int) (maxValue * scale);

            if (v >= 0)
            {
                y1 = (int) ((maxValue - v) * scale);
                height = yOrigin - y1;
            }
            else
            {
                y1 = yOrigin;
                height = (int) (-v * scale);
            }
            if (i < info.getRetireAge())
            {
                g2.setPaint(colorPre);
            }
            else if (v >= 0)
            {
                g2.setPaint(colorGain);
            }
            else
            {
                g2.setPaint(colorLoss);
            }
            Rectangle2D bar = new Rectangle2D.Double(x1, y1, barWidth - 2, height);
            g2.fill(bar);
            g2.setPaint(Color.black);
            g2.draw(bar);
        }
    }

    /**
     * Задает цвет диаграммы после даты выхода на пенсию
     * при условии положительного баланса.
     * @param colorGain Цвет
     */
    public void setColorGain(Color colorGain)
    {
        this.colorGain = colorGain;
        repaint();
    }

    /**
     * Задает цвет диаграммы после даты выхода на пенсию
     * при условии положительного баланса.
     * @param colorLoss Цвет
     */
    public void setColorLoss(Color colorLoss)
    {
        this.colorLoss = colorLoss;
        repaint();
    }

    /**
     * Установка цвета диаграммы до даты выхода на пенсию.
     * @param colorPre Цвет
     */
    public void setColorPre(Color colorPre)
    {
        this.colorPre = colorPre;
        repaint();
    }
}
