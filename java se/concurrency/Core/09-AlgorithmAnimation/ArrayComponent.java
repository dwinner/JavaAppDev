import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JComponent;

/**
 * Этот компонент рисует массив и помечает два его элемента.
 * @author dwinner@inbox.ru
 */
public class ArrayComponent extends JComponent
{
    private Double marked1;
    private Double marked2;
    private Double[] values;
    
    /**
     * Устанавливает значения для отрисовки. Вызывается потоком сортировки.
     * @param values Массив значений для отображения
     * @param marked1 Первый помеченный элемент
     * @param marked2 Второй помеченный элемент
     */
    public synchronized void setValues(Double[] values, Double marked1, Double marked2)
    {
        this.values = values.clone();
        this.marked1 = marked1;
        this.marked2 = marked2;
        repaint();
    }

    @Override
    public synchronized void paintComponent(Graphics g)
    {   // Вызывается в потоке диспетчера событий.
        if (values == null)
        {
            return;
        }
        Graphics2D g2 = (Graphics2D) g;
        int width = getWidth() / values.length;
        for (int i = 0; i < values.length; i++)
        {
            double height = values[i] * getHeight();
            Rectangle2D bar = new Rectangle2D.Double(width * i, 0, width, height);
            if (values[i] == marked1 || values[i] == marked2)
            {
                g2.fill(bar);
            }
            else
            {
                g2.draw(bar);
            }
        }
    }
    
    
}
