package drawing.model.shapes;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * MyRectangle - �������� ������ MyShape, ������� ������������ �������������,
 * ������� ���������� ������ draw ��� ��������� �������������� � ���������
 * Graphics2D.
 * @author dwinner@inbox.ru
 */
public class MyRectangle extends MyShape {
    
    @Override public void draw(Graphics2D g2D) {
        // ��������� Graphics2D (��������, ���� � �.�.)
        configureGraphicsContext(g2D);
        // �������� ������� Rectangle2D ��� ��������� ������ MyRectangle
        Shape shape = new Rectangle2D.Float(getLeftX(), getLeftY(), getWidth(), getHeight());      
        // ���������� ������
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