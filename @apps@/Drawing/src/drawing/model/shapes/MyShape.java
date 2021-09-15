/**
 * ������������ ����, ��� �������� ����
 */
package drawing.model.shapes;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.Stroke;
import java.awt.geom.Point2D;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * MyShape - ����������� �����, ������� ������������ ������, �������� �
 * ���������� Drawing.
 * @author dwinner@inbox.ru
 */
public abstract class MyShape {
    
    // �������� ������ MyShape (����������, ���� � �.�.)
    private int x1, y1, x2, y2;
    private int startX, startY, endX, endY;
    private Color startColor = Color.black;
    private Color endColor = Color.white;
    private boolean filled = false;
    private boolean gradient = false;
    private float strokeSize = 1.0f;
    private Stroke currentStroke;
    
    /**
     * ��������� ���������� ������ ����
     * @return ���������� x1 ������ ����
     */
    public int getLeftX() {
        return x1;
    }
    
    /**
     * ��������� ���������� ������ ����
     * @return ���������� y1 ������ ����
     */
    public int getLeftY() {
        return y1;
    }
    
    /**
     * ��������� ���������� ������� ����
     * @return ���������� x2 ������� ����
     */
    public int getRightX() {
        return x2;
    }
    
    /**
     * ��������� ���������� ������� ����
     * @return ���������� y2 ������� ����
     */
    public int getRightY() {
        return y2;
    }
    
    /**
     * ��������� ������ ������ MyShape
     * @return ������ ������ MyShape
     */
    public int getWidth() {
        return Math.abs(getX1() - getX2());
    }
    
    /**
     * ��������� ������ ������ MyShape
     * @return ������ ������ MyShape
     */
    public int getHeight() {
        return Math.abs(getY1() - getY2());
    }
    
    /**
     * ������� ��������� x � y ����� Point1
     * @param x ���������� x
     * @param y ���������� y
     */
    public void setPoint1(int x, int y) {
        x1 = x;
        y1 = y;
    }
    
    /**
     * ������� ��������� x � y ����� Point2
     * @param x ���������� x
     * @param y ���������� Y
     */
    public void setPoint2(int x, int y) {
        x2 = x;
        y2 = y;
    }
    
    /**
     * ������� ��������� ��������� x � y ����� Point
     * @param x ��������� ���������� X
     * @param y ��������� ���������� Y
     */
    public void setStartPoint(int x, int y) {
        startX = x;
        startY = y;
    }
    
    /**
     * ������� �������� ��������� x � y ����� Point
     * @param x �������� ���������� X
     * @param y �������� ���������� Y
     */
    public void setEndPoint(int x, int y) {
        endX = x;
        endY = y;
    }
    
    /**
     * ��������� ���������� x1
     * @return ���������� x1 
     */
    public int getX1() {
        return x1;
    }
    
    /**
     * ��������� ���������� x2
     * @return ���������� x2
     */
    public int getX2() {
        return x2;
    }
    
    /**
     * ��������� ���������� y1
     * @return ���������� y1
     */
    public int getY1() {
        return y1;
    }
    
    /**
     * ��������� ���������� y2
     * @return ���������� y2
     */
    public int getY2() {
        return y2;
    }
    
    /**
     * ��������� ���������� startX
     * @return ���������� startX 
     */
    public int getStartX() {
        return startX;
    }
    
    /**
     * ��������� ���������� startY
     * @return ���������� startY
     */
    public int getStartY() {
        return startY;
    }
    
    /**
     * ��������� ���������� endX
     * @return ���������� endX
     */
    public int getEndX() {
        return endX;
    }
    
    /**
     * ��������� ���������� endY
     * @return ���������� endY
     */
    public int getEndY() {
        return endY;
    }
    
    /**
     * ����������� ������ MyShape �� �������� ��������
     * @param dx ���������� �� X
     * @param dy ���������� �� Y
     */
    public void moveByOffset(int dx, int dy) {
        setPoint1(getX1() + dx, getY1() + dy);
        setPoint2(getX2() + dx, getY2() + dy);
        setStartPoint(getStartX() + dx, getStartY() + dy);
        setEndPoint(getEndX() + dx, getEndY() + dy);
    }
	
	/**
	 * ������� ����� ��������� �� ���������
	 * @param color ���� ���������
	 */
	public void setColor(Color color) {
		setStartColor(color);
	}
	
	/**
	 * ��������� ����� ��������� �� ���������
	 * @return ���� ��������� �� ���������
	 */
	public Color getColor() {
		return getStartColor();
	}
	
	/**
	 * ������� ���������� ����� ���������
	 * @param color ��������� ���� ���������
	 */
	public void setStartColor(Color color) {
		startColor = color;
	}
	
	/**
	 * ��������� ���������� ����� ���������
	 * @return ��������� ���� ���������
	 */
	public Color getStartColor() {
		return startColor;
	}
	
	/**
	 * ������� ���������� ����� ��������� (��� ���������)
	 * @param color ��������� ���� ���������
	 */
	public void setEndColor(Color color) {
		endColor = color;
	}
	
	/**
	 * ��������� ���������� ����� ���������
	 * @return ��������� ���� ���������
	 */
	public Color getEndColor() {
		return endColor;
	}
	
	/**
	 * ����������/���������� ��������� � �������������� ���������
	 * @param useGradient ���� ������������� ���������
	 */
	public void setUseGradient(boolean useGradient) {
		gradient = useGradient;
	}
	
	/**
	 * ��������� �������� �������� ����������/������� ���������
	 * @return ���� ������������� ���������
	 */
	public boolean useGradient() {
		return gradient;
	}
	
	/**
	 * ������� ������� �����
	 * @param size ������� ����� ���������
	 */
	public void setStrokeSize(float size) {
		strokeSize = size;
		currentStroke = new BasicStroke(strokeSize);
	}
	
	/**
	 * ��������� ������� �����
	 * @return ������� ����� ���������
	 */
	public float getStrokeSize() {
		return strokeSize;
	}
	
	/**
	 * ������� �������
	 * @param fill ���� ������� �������
	 */
	public void setFilled(boolean fill) {
		filled = fill;
	}
	
	/**
	 * ������ �� ������?
	 * @return ���� ������� �������
	 */
	public boolean isFilled() {
		return filled;
	}
	
	/**
	 * ����������� ����� ��� ���������� ����������� ��������� �������� �����
	 * @param g2D ����������� �������� ���������
	 */
	public abstract void draw(Graphics2D g2D);
	
	/**
	 * ������� true, ���� ����� Point2D ����� ������ ������
	 * @param point ����� ����������
	 * @return ���� ���������� ����� ������ ������
	 */
	public abstract boolean contains(Point2D point);
	
	/**
	 * ��������� ��������� Graphics2D ��� ��������� ������� �������
	 * @param g2D ����������� �������� ���������
	 */
	protected void configureGraphicsContext(Graphics2D g2D) {
		// ������� ������� Stroke ��� ������
		if (currentStroke == null) {
			currentStroke = new BasicStroke(getStrokeSize());
		}
		
		g2D.setStroke(currentStroke);
		// ���� ������ ��������, ������� ����� ������ GradientPaint,
		// ������� � x1, y1 � ������ color1 � ���������� � x2, y2 � ������ color2
		g2D.setPaint(useGradient() 	? new GradientPaint(getStartX(),getStartY(),getStartColor(),getEndX(),getEndY(),getEndColor()) 
									: getColor());
	}
     
    /**
     * ��������� ������������� ������� MyShape � ���� XML
     * @param document ������ ������������� XML-���������
     * @return ������� ������������� ������ � ���� XML
     */
    public Element getXML(Document document) {
        Element shapeElement = document.createElement("shape");
        Element temp;
         
        // �������� ��������� Element ��� ��������� x � y
        temp = document.createElement("x1");
        temp.appendChild(document.createTextNode(String.valueOf(getX1())));
        shapeElement.appendChild(temp);
        
        temp = document.createElement("y1");
        temp.appendChild(document.createTextNode(String.valueOf(getY1())));
        shapeElement.appendChild(temp);
         
        temp = document.createElement("x2");
        temp.appendChild(document.createTextNode(String.valueOf(getX2())));
        shapeElement.appendChild(temp);
         
        temp = document.createElement("y2");
        temp.appendChild(document.createTextNode(String.valueOf(getY2())));
        shapeElement.appendChild(temp);
         
        temp = document.createElement("startX");
        temp.appendChild(document.createTextNode(String.valueOf(getStartX())));
        shapeElement.appendChild(temp);
         
        temp = document.createElement("startY");
        temp.appendChild(document.createTextNode(String.valueOf(getStartY())));
        shapeElement.appendChild(temp);
         
        temp = document.createElement("endX");
        temp.appendChild(document.createTextNode(String.valueOf(getEndX())));
        shapeElement.appendChild(temp);
         
        temp = document.createElement("endY");
        temp.appendChild(document.createTextNode(String.valueOf(getEndY())));
        shapeElement.appendChild(temp);
         
        // �������� �������� Element ��� ���������
        temp = document.createElement("useGradient");
        temp.appendChild(document.createTextNode(String.valueOf(useGradient())));
        shapeElement.appendChild(temp);
        
        // �������� XML-�������� ��� ���������� ����� startColor
        Color color = getStartColor();
        temp = document.createElement("startColor");
        temp.setAttribute("red", String.valueOf(color.getRed()));
        temp.setAttribute("green", String.valueOf(color.getGreen()));
        temp.setAttribute("blue", String.valueOf(color.getBlue()));
        shapeElement.appendChild(temp);
        
        // �������� XML-�������� ��� ��������� ����� endColor
        color = getEndColor();
        temp = document.createElement("endColor");
        temp.setAttribute("red", String.valueOf(color.getRed()));
        temp.setAttribute("green", String.valueOf(color.getGreen()));
        temp.setAttribute("blue", String.valueOf(color.getBlue()));
        shapeElement.appendChild(temp);
        
        // ���������� �������� strokeSize
        temp = document.createElement("strokeSize");
        temp.appendChild(document.createTextNode(String.valueOf(getStrokeSize())));
        shapeElement.appendChild(temp);
        
        // ���������� �������� fill
        temp = document.createElement("fill");
        temp.appendChild(document.createTextNode(String.valueOf(isFilled())));
        shapeElement.appendChild(temp);
        
        return shapeElement;
    }

}