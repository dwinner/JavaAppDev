package drawing.model.shapes;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * MyRectangle - подкласс класса MyShape, который представляет прямоугольник,
 * включая реализацию метода draw для рисования прямоугольника в контексте
 * Graphics2D.
 * @author dwinner@inbox.ru
 */
public class MyRectangle extends MyShape {
    
    @Override public void draw(Graphics2D g2D) {
        // Настройка Graphics2D (градиент, цвет и т.д.)
        configureGraphicsContext(g2D);
        // Создание объекта Rectangle2D для рисования фигуры MyRectangle
        Shape shape = new Rectangle2D.Float(getLeftX(), getLeftY(), getWidth(), getHeight());      
        // Заполнение фигуры
        if (isFilled()) {
            g2D.fill(shape);
        }
        else {
            g2D.draw(shape);
        }
    }

    @Override public boolean contains(Point2D point) {
        Rectangle2D.Float rectangle = new Rectangle2D.Float(getLeftX(), getLeftY(), getWidth(), getHeight());
        return rectangle.contains(point);
    }
    
    @Override public Element getXML(Document document) {
        Element shapeElement = super.getXML(document);
        shapeElement.setAttribute("type", "MyRectangle");
        
        return shapeElement;
    }
    
}