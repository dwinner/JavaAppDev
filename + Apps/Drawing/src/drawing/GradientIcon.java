package drawing;

import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.Icon;

/**
 * GradientIcon - реализация интерфейса Icon, отображающего градиентную заливку
 * размером 16x16 пикселов от начального цвета startColor до конечного цвета endColor.
 * @author dwinner@inbox.ru
 */
public class GradientIcon implements Icon {
	
    private static final int DEFAULT_ICON_WIDTH = 16;
    
    // Цвета Colors, используемые для градиента
    private Color startColor;
    private Color endColor;

    /**
     * Конструктор GradientIcon
     * @param start Начальный цвет градиента
     * @param end Конечный цвет градиента
     */
    public GradientIcon(Color start, Color end) {
        setStartColor(start);
        setEndColor(end);
    }
    
    /**
     * Задание начального цвета для градиента
     * @param start Начальный цвет градиента
     */
    public final void setStartColor(Color start) {
        startColor = start;
    }
    
    /**
     * Задание конечного цвета для градиента
     * @param end Конечный цвет градиента
     */ 
    public final void setEndColor(Color end) {
        endColor = end;
    }
    
    /**
     * Возвращение начального цвета градиента
     * @return Начальный цвет градиента
     */
    public Color getStartColor() {
        return startColor;
    }
    
    /**
     * Получение конечного цвета для градиента
     * @return Конечный цвет градиента
     */
    public Color getEndColor() {
        return endColor;
    }
    
    @Override public int getIconWidth() {
    	// Получение ширины значка
        return DEFAULT_ICON_WIDTH;
    }
    
    @Override public int getIconHeight() {
    	// Получение высоты значка
        return DEFAULT_ICON_WIDTH;
    }

    @Override public void paintIcon(Component component, Graphics g, int x, int y) {
    	// Отображение значка в заданном компоненте
        // Получение объекта Graphics2D
        Graphics2D g2D = (Graphics2D) g;      
        // Задание GradientPaint
        g2D.setPaint(new GradientPaint(x, y, getStartColor(), DEFAULT_ICON_WIDTH, DEFAULT_ICON_WIDTH, getEndColor()));        
        // Закрашивание прямоугольника градиентной заливкой
        g2D.fillRect(x, y, DEFAULT_ICON_WIDTH, DEFAULT_ICON_WIDTH);
    }
    
}