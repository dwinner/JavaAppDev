package drawing.model.shapes;

import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * �������� ������ MyShape, ������� ������������ �����.
 * @author dwinner@inbox.ru
 */
public class MyLine extends MyShape {
    
    private static final double EPS = 10E-1;

    @Override public void draw(Graphics2D g2D) {	// ��������� ������ MyLine � �������� ��������� Graphics2D
        // ��������� Graphics2D (��������, ���� � �.�.)
        configureGraphicsContext(g2D);
        // �������� ������ ������� Line2D.Float
        Shape line = new Line2D.Float(getX1(), getY1(), getX2(), getY2());
        // ��������� ������
        g2D.draw(line);
    }

    @Override public boolean contains(Point2D point) {
        // ��������� ��������� ����� Point1 � Point2
        float x1 = getX1();
        float x2 = getX2();
        float y1 = getY1();
        float y2 = getY2();       
        // ����������� ������� �����
        float slope = (y2 - y1) / (x2 - x1);       
        // ����������� ������� �� ��������� point � ����� Point
        float realSlope = (float) ((point.getY() - y1) / (point.getX() - x1));       
        // ������� true, ���� �������� ������� � �������� realSlope ������
        return Math.abs(realSlope - slope) < EPS;
    }
    
    @Override public Element getXML(Document document) {	// ��������� XML-������������� ������� MyLine
        Element shapeElement = super.getXML(document);
        shapeElement.setAttribute("type", "MyLine");
        
        return shapeElement;
    }
    
}