// Рисование компонента с учетом рамки
import javax.swing.*;
import javax.swing.border.AbstractBorder;
import java.awt.*;

public class PaintingWithBorders extends JFrame
{    
    public PaintingWithBorders()
    {
        super("Painting with borders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Добавим к нашему компоненту рамку
        CustomComponent cc = new CustomComponent();
        cc.setBorder(BorderFactory.createTitledBorder("Рамка!"));
        
        // Добавим компонент в окно
        getContentPane().add(cc);
        setSize(400, 300);
        setVisible(true);
    }
    
    /**
     * Компонент с собственной процедурой прорисовки
     */
    private class CustomComponent extends JComponent
    {
        @Override public void paintComponent(Graphics g)
        {
            // Получаем подходящий прямоугольник
            Rectangle rect = AbstractBorder.getInteriorRectangle(
                this,
                getBorder(),
                0,
                0,
                getWidth(),
                getHeight()
            );
            
            // Рисуем в нем
            g.setColor(Color.white);
            g.fillRect(rect.x, rect.y, rect.width, rect.height);
        }
    }
    
    public static void main(String[] args)
    {
        new PaintingWithBorders();
    }

}