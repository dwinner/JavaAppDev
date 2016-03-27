package drawing.model.shapes;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Подкласс класса MyShape, который представляет линию.
 * @author dwinner@inbox.ru
 */
public class MyLine extends MyShape {
    
    private static final double EPS = 10E-1;

    @Override public void draw(Graphics2D g2D) {	// Рисование фигуры MyLine в заданном контексте Graphics2D
        // Настройка Graphics2D (градиент, цвет и т.д.)
        configureGraphicsContext(g2D);
        // Создание нового объекта Line2D.Float
        Shape line = new Line2D.Float(getX1(), getY1(), getX2(), getY2());
        // Рисование фигуры
        g2D.draw(line);
    }

    @Override public boolean contains(Point2D point) {
        // Получение координат точек Point1 и Point2
        float x1 = getX1();
        float x2 = getX2();
        float y1 = getY1();
        float y2 = getY2();       
        // Определение наклона линии
        float slope = (y2 - y1) / (x2 - x1);       
        // Определение наклона по параметру point и точке Point
        float realSlope = (float) ((point.getY() - y1) / (point.getX() - x1));       
        // Возврат true, если величина наклона и значение realSlope близки
        return Math.abs(realSlope - slope) < EPS;
    }
    
    @Override public Element getXML(Document document) {	// Получение XML-представления объекта MyLine
        Element shapeElement = super.getXML(document);
        shapeElement.setAttribute("type", "MyLine");
        
        return shapeElement;
    }
    
}