// Рамка, составленная из кривых Безье
import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.geom.*;

public class BezierBorder implements Border
{    
    // Свойства рамки
    private Color color, shadow;
    private float thickness;
    
    public BezierBorder(Color color, Color shadow, float thickness)
    {
        this.color = color;
        this.shadow = shadow;
        this.thickness = thickness;
    }
    
    // Место, занимаемое рамкой
    public Insets getBorderInsets(Component c)
    {
        return new Insets(8, 8, 8, 8);
    }
    
    // Наша рамка местами прозрачна
    public boolean isBorderOpaque() { return false; }

    // Прорисовка рамки
    public void paintBorder(Component c, Graphics g, int x, int y, int width, int height)
    {
        // Используем новый объект Graphics
        Graphics2D g2 = (Graphics2D) g.create();
        
        // Настройка пера, координат и цвета
        g2.setStroke(new BasicStroke(thickness));
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        x += 5;
        y += 5;
        width -= 10;
        height -= 10;
        g2.setColor(shadow);
        
        // Прорисовываем тень и рамку
        for (int i = 0; i < 2; i++)
        {
            CubicCurve2D left =
            	new CubicCurve2D.Double(
            	  x,
            	  y,
            	  x - 5,
            	  y + height*i/3,
            	  x + 5,
            	  y + height*i/3,
            	  x,
            	  y + height);
            CubicCurve2D right =
            	new CubicCurve2D.Double(
            	  x + width,
            	  y,
            	  x + width-5,
            	  y + height*i/3,
            	  x + width+5,
            	  y + height*2/3,
            	  x + width,
            	  y + height);
            CubicCurve2D top =
            	new CubicCurve2D.Double(
            	  x,
            	  y,
            	  x + width*i/3,
            	  y + 5,
            	  x + width*2/3,
            	  y - 5,
            	  x + width,
            	  y);
            CubicCurve2D bottom =
            	new CubicCurve2D.Double(
            	  x,
            	  y + height,
            	  x + width*i/3,
            	  y + height+5,
            	  x + width*2/3,
            	  y + height+5,
            	  x + width,
            	  y + height);
            g2.draw(left);
            g2.draw(right);
            g2.draw(top);
            g2.draw(bottom);
            
            // Рисуем саму рамку
            x--;
            y--;
            width--;
            height--;
            g2.setColor(color);
        }
        
        g2.dispose();
    }
    
    public static void main(String[] args)
    {
        JFrame frame = new JFrame("Bezier Border");
        // Создаем панель с нашей рамкой
        JPanel p = new JPanel();
        Border bb = new TitledBorder(new BezierBorder(Color.green, Color.black, 3f), "Bezier");
        p.setBorder(bb);
        // Выводим окно на экран
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.getContentPane().add(p);
        frame.setSize(200, 200);
        frame.setVisible(true);
    }

}