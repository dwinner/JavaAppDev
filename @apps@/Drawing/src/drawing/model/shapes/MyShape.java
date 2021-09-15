/**
 * Пространство имен, для хранения имен
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
 * MyShape - абстрактный класс, который представляет фигуру, рисуемую в
 * приложении Drawing.
 * @author dwinner@inbox.ru
 */
public abstract class MyShape {
    
    // Свойства фигуры MyShape (координаты, цвет и т.д.)
    private int x1, y1, x2, y2;
    private int startX, startY, endX, endY;
    private Color startColor = Color.black;
    private Color endColor = Color.white;
    private boolean filled = false;
    private boolean gradient = false;
    private float strokeSize = 1.0f;
    private Stroke currentStroke;
    
    /**
     * Получение координаты левого угла
     * @return Координату x1 левого угла
     */
    public int getLeftX() {
        return x1;
    }
    
    /**
     * Получение координаты левого угла
     * @return Координату y1 левого угла
     */
    public int getLeftY() {
        return y1;
    }
    
    /**
     * Получение координаты правого угла
     * @return Координуту x2 правого угла
     */
    public int getRightX() {
        return x2;
    }
    
    /**
     * Получение кооддинаты правого угла
     * @return Координуту y2 правого угла
     */
    public int getRightY() {
        return y2;
    }
    
    /**
     * Получение ширины фигуры MyShape
     * @return Ширину фигуры MyShape
     */
    public int getWidth() {
        return Math.abs(getX1() - getX2());
    }
    
    /**
     * Получение высоты фигуры MyShape
     * @return Высоту фигуры MyShape
     */
    public int getHeight() {
        return Math.abs(getY1() - getY2());
    }
    
    /**
     * Задание координат x и y точки Point1
     * @param x Координата x
     * @param y Координата y
     */
    public void setPoint1(int x, int y) {
        x1 = x;
        y1 = y;
    }
    
    /**
     * Задание координат x и y точки Point2
     * @param x Координата x
     * @param y Координата Y
     */
    public void setPoint2(int x, int y) {
        x2 = x;
        y2 = y;
    }
    
    /**
     * Задание начальных координат x и y точки Point
     * @param x Начальная координата X
     * @param y Начальная координата Y
     */
    public void setStartPoint(int x, int y) {
        startX = x;
        startY = y;
    }
    
    /**
     * Задание конечных координат x и y точки Point
     * @param x Конечная координата X
     * @param y Конечная координата Y
     */
    public void setEndPoint(int x, int y) {
        endX = x;
        endY = y;
    }
    
    /**
     * Получение координаты x1
     * @return Координата x1 
     */
    public int getX1() {
        return x1;
    }
    
    /**
     * Получение координаты x2
     * @return Координата x2
     */
    public int getX2() {
        return x2;
    }
    
    /**
     * Получение координаты y1
     * @return Координата y1
     */
    public int getY1() {
        return y1;
    }
    
    /**
     * Получение координаты y2
     * @return Координата y2
     */
    public int getY2() {
        return y2;
    }
    
    /**
     * Получение координаты startX
     * @return Координата startX 
     */
    public int getStartX() {
        return startX;
    }
    
    /**
     * Получение координаты startY
     * @return Координата startY
     */
    public int getStartY() {
        return startY;
    }
    
    /**
     * Получение координаты endX
     * @return Координата endX
     */
    public int getEndX() {
        return endX;
    }
    
    /**
     * Получение координаты endY
     * @return Координата endY
     */
    public int getEndY() {
        return endY;
    }
    
    /**
     * Перемещение фигуры MyShape на заданную величину
     * @param dx Приращение по X
     * @param dy Приращение по Y
     */
    public void moveByOffset(int dx, int dy) {
        setPoint1(getX1() + dx, getY1() + dy);
        setPoint2(getX2() + dx, getY2() + dy);
        setStartPoint(getStartX() + dx, getStartY() + dy);
        setEndPoint(getEndX() + dx, getEndY() + dy);
    }
	
	/**
	 * Задание цвета рисования по умолчанию
	 * @param color Цвет рисования
	 */
	public void setColor(Color color) {
		setStartColor(color);
	}
	
	/**
	 * Получение цвета рисования по умолчанию
	 * @return Цвет рисования по умолчанию
	 */
	public Color getColor() {
		return getStartColor();
	}
	
	/**
	 * Задание первичного цвета рисования
	 * @param color Первичный цвет рисования
	 */
	public void setStartColor(Color color) {
		startColor = color;
	}
	
	/**
	 * Получение первичного цвета рисования
	 * @return Первичный цвет рисования
	 */
	public Color getStartColor() {
		return startColor;
	}
	
	/**
	 * Задание вторичного цвета рисования (для градиента)
	 * @param color Вторичный цвет рисования
	 */
	public void setEndColor(Color color) {
		endColor = color;
	}
	
	/**
	 * Получение вторичного цвета рисования
	 * @return Вторичный цвет рисования
	 */
	public Color getEndColor() {
		return endColor;
	}
	
	/**
	 * Разрешение/запрещение рисования с использованием градиента
	 * @param useGradient Флаг использования градиента
	 */
	public void setUseGradient(boolean useGradient) {
		gradient = useGradient;
	}
	
	/**
	 * Получение значения свойства разрешения/запрета градиента
	 * @return Флаг использования градиента
	 */
	public boolean useGradient() {
		return gradient;
	}
	
	/**
	 * Задание толщины линии
	 * @param size Толщина линии рисования
	 */
	public void setStrokeSize(float size) {
		strokeSize = size;
		currentStroke = new BasicStroke(strokeSize);
	}
	
	/**
	 * Получение толщины линии
	 * @return Толщина линии рисования
	 */
	public float getStrokeSize() {
		return strokeSize;
	}
	
	/**
	 * Задание заливки
	 * @param fill Флаг задания заливки
	 */
	public void setFilled(boolean fill) {
		filled = fill;
	}
	
	/**
	 * Залита ли фигура?
	 * @return Флаг задания заливки
	 */
	public boolean isFilled() {
		return filled;
	}
	
	/**
	 * Абстрактный метод для реализации подклассами рисования реальных фигур
	 * @param g2D Графический контекст рисования
	 */
	public abstract void draw(Graphics2D g2D);
	
	/**
	 * Возврат true, если точка Point2D лежит внутри фигуры
	 * @param point Точка нахождения
	 * @return Факт нахождения точки внутри фигуры
	 */
	public abstract boolean contains(Point2D point);
	
	/**
	 * Настройка контекста Graphics2D для известных свойств рисунка
	 * @param g2D Графический контекст рисования
	 */
	protected void configureGraphicsContext(Graphics2D g2D) {
		// Задание объекта Stroke для фигуры
		if (currentStroke == null) {
			currentStroke = new BasicStroke(getStrokeSize());
		}
		
		g2D.setStroke(currentStroke);
		// Если выбран градиент, создать новый объект GradientPaint,
		// начиная с x1, y1 с цветом color1 и заканчивая в x2, y2 с цветом color2
		g2D.setPaint(useGradient() 	? new GradientPaint(getStartX(),getStartY(),getStartColor(),getEndX(),getEndY(),getEndColor()) 
									: getColor());
	}
     
    /**
     * Получение представления объекта MyShape в виде XML
     * @param document Объект представления XML-документа
     * @return Элемент представления фигуры в виде XML
     */
    public Element getXML(Document document) {
        Element shapeElement = document.createElement("shape");
        Element temp;
         
        // Создание элементов Element для координат x и y
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
         
        // Создание элемента Element для градиента
        temp = document.createElement("useGradient");
        temp.appendChild(document.createTextNode(String.valueOf(useGradient())));
        shapeElement.appendChild(temp);
        
        // Создание XML-элемента для начального цвета startColor
        Color color = getStartColor();
        temp = document.createElement("startColor");
        temp.setAttribute("red", String.valueOf(color.getRed()));
        temp.setAttribute("green", String.valueOf(color.getGreen()));
        temp.setAttribute("blue", String.valueOf(color.getBlue()));
        shapeElement.appendChild(temp);
        
        // Создание XML-элемента для конечного цвета endColor
        color = getEndColor();
        temp = document.createElement("endColor");
        temp.setAttribute("red", String.valueOf(color.getRed()));
        temp.setAttribute("green", String.valueOf(color.getGreen()));
        temp.setAttribute("blue", String.valueOf(color.getBlue()));
        shapeElement.appendChild(temp);
        
        // Добавление элемента strokeSize
        temp = document.createElement("strokeSize");
        temp.appendChild(document.createTextNode(String.valueOf(getStrokeSize())));
        shapeElement.appendChild(temp);
        
        // Добавление элемента fill
        temp = document.createElement("fill");
        temp.appendChild(document.createTextNode(String.valueOf(isFilled())));
        shapeElement.appendChild(temp);
        
        return shapeElement;
    }

}