package drawing.model.shapes;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Point2D;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * MyOval - �������� ������ MyShape, ������� ������������ ����.
 * @author dwinner@inbox.ru
 */
public class MyOval extends MyShape {
    
    @Override public void draw(Graphics2D g2D) {
        // ��������� Graphics2D (��������, ���� � �.�.)
        configureGraphicsContext(g2D);      
        // �������� ������� Ellipse2D ��� ��������� �����
        Shape shape = new Ellipse2D.Float(getLeftX(), getLeftY(), getWidth(), getHeight());        
        // ������� ������
        if (isFilled()) {
            g2D.fill(shape);
        }
        else {
            g2D.draw(shape);
        }
    }

    @Override public boolean contains(Point2D point) {
        Ellipse2D.Float ellipse = new Ellipse2D.Float(getLeftX(), getLeftY(), getWidth(), getHeight());
        return ellipse.contains(point);
    }
    
    @Override public Element getXML(Document document) {
        Element shapeElement = super.getXML(document);
        shapeElement.setAttribute("type", "MyOval");
        return shapeElement;
    }
    
}