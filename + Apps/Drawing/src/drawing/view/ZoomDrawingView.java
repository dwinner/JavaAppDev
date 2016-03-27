package drawing.view;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import drawing.model.DrawingModel;


/**
 * Подкласс класса DrawingView, который масштабирует рисунок.
 * @author dwinner@inbox.ru
 */
public class ZoomDrawingView extends DrawingView {
    
    private static final long serialVersionUID = 1L;
    
    // Коэффициенты масштабирования вида
    private double scaleFactorX;
    private double scaleFactorY;
    
    // Преобразование для масштабирования вида
    private AffineTransform scaleTransform;
    
    /**
     * Построение вида ZoomDrawingView с заданной моделью
     * и масштабным коэффициентом по умолчанию
     * @param model Модель фигур
     */
    public ZoomDrawingView(DrawingModel model) {
        this(model, 1.0);
    }
    
    /**
     * Построение вида ZoomDrawingView с заданной моделью и масштабным коэффициентом
     * @param model Модель фигур
     * @param scale Масштабный коэффициент
     */
    public ZoomDrawingView(DrawingModel model, double scale) {
        this(model, scale, scale);
    }
    
    /**
     * Построение вида ZoomDrawingView с заданной моделью и 
     * отдельными масштабными коэффициентами по осям x и y
     * @param model Модель фигур
     * @param scaleX Масштабный коэффициент по X
     * @param scaleY Масштабный коэффициент по Y
     */
    public ZoomDrawingView(DrawingModel model, double scaleX, double scaleY) {
        // Вызов конструктора DrawingView
        super(model);        
        // Задание масштабного коэффициента для вида
        setScaleFactors(scaleX, scaleY);        
        // Отслеживание событий изменения размеров компонента, чтобы
        // подкорректировать масштаб
        addComponentListener(new ComponentAdapter() {
        	// При изменении размеров вида обновить масштабные коэффициенты
        	@Override public void componentResized(ComponentEvent event) {
                double width = (double) getSize().width;
                double height = (double) getSize().height;                
                // Вычисление новых масштабных коэффициентов
                double factorX = width / PREFERRED_WIDTH;
                double factorY = height / PREFERRED_HEIGHT;               
                setScaleFactors(factorX, factorY);
            }
        });
    }
    
    // Рисование фигур с использованием масштабирования
    @Override public void drawShapes(Graphics2D g2D) {
        // Задание преобразования для объекта Graphics2D
        g2D.setTransform(scaleTransform);        
        // Рисование фигур в масштабированном объекте Graphics2D
        super.drawShapes(g2D);
    }

    /**
     * Задание масштабных коэффициентов для вида
     * @param scaleX Масштабный коэффициент по X
     * @param scaleY Масштабный коэффициент по Y
     */
    private void setScaleFactors(double scaleX, double scaleY) {
        // Задание масштабных коэффициентов
        scaleFactorX = scaleX;
        scaleFactorY = scaleY;
        // Создание объекта AffineTransform с заданными масштабными коэффициентами
        scaleTransform = AffineTransform.getScaleInstance(scaleFactorX, scaleFactorY);
    }
    
    /**
     * Получение предпочтительных размеров для этого компонента
     * @return Новый размер в виде Dimension
     */
    public Dimension getPrefferedSize() {
        // Размер по умолчанию: PREFERRED_WIDTH x PREFERRED_HEIGHT
        // Масштабирование с использованием коэффициентов scaleFactors
        return new Dimension(
        		(int) (PREFERRED_WIDTH * scaleFactorX),
        		(int) (PREFERRED_HEIGHT * scaleFactorY)
        );
    }
    
}