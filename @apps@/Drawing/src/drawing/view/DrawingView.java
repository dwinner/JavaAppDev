package drawing.view;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import drawing.model.DrawingModel;
import drawing.model.shapes.*;

/**
 * Вид модели DrawingModel, в котором пользователь рисует фигуры
 * с использованием Java2D API.
 * @author dwinner@inbox.ru
 */
public class DrawingView extends JPanel implements Observer {
    
    private static final long serialVersionUID = 1L;
    
    // Константы предпочтительных размеров компонента
    protected static final double PREFERRED_WIDTH = 320.0;
    protected static final double PREFERRED_HEIGHT = 240.0;
    
    // Фоновый цвет панели по умолчанию
    protected static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
    
    // Модель, для которой используется этот вид
    private DrawingModel drawingModel;
    
    /**
     * Создание вида DrawingView для данной модели
     * @param model Модель для фигур
     */
    public DrawingView(DrawingModel model) {
        // Задание модели DrawingModel
        drawingModel = model;
        // Регистрация вида в качестве наблюдателя модели
        drawingModel.addObserver(this);
        // Задание фонового цвета
        setBackground(DEFAULT_BACKGROUND_COLOR);
        // Разрешить двойную буферизацию для уменьшения мерцания экрана
        setDoubleBuffered(true);
    }
    
    /**
     * Задание DrawingModel в качестве модели для заданного вида
     * @param model Модель для фигур
     */
    public void setModel(DrawingModel model) {
        if (drawingModel != null) {
            drawingModel.deleteObserver(this);
        }
        drawingModel = model;
        // Регистрация вида в качестве наблюдателя модели
        if (model != null) {
            model.addObserver(this);
            repaint();
        }
    }
    
    /**
     * Получение модели DrawingModel, ассоциированной с данным видом
     * @return Сылка на объект модели для фигур 
     */
    public DrawingModel getModel() {
        return drawingModel;
    }

    
    @Override public void update(Observable o, Object arg) {
    	// Перерисовка вида при получении обновления от модели
        repaint();
    }
    
    @Override public void paintComponent(Graphics g) {
        // Вызов суперкласса paintComponent
        super.paintComponent(g);
        // Создание объекта Graphics2D для заданного объекта Graphics
        Graphics2D g2D = (Graphics2D) g;      
        // Разрешение сглаживания
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // Разрешение высококачественного отображения объекта Graphics2D
        g2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        // Рисование всех фигур модели
        drawShapes(g2D);
    }

    /**
     * Рисование фигур модели
     * @param g2D Графический контекст Java2D
     */
    public void drawShapes(Graphics2D g2D) {
    	for (MyShape shape : drawingModel.getShapes()) {
    		shape.draw(g2D);
    	}
    }
    
    @Override public Dimension getPreferredSize() {
    	// Получение предпочтительных размеров для данного компонента
        return new Dimension((int) PREFERRED_WIDTH, (int) PREFERRED_HEIGHT);
    }
    
    @Override public Dimension getMinimumSize() {
    	// Запрос минимальных размеров для данного компонента
        return getPreferredSize();
    }
    
    @Override public Dimension getMaximumSize() {
    	// Запрос максимальных размеров для данного компонента
        return getPreferredSize();
    }
    
    @Override public void addNotify() {
    	// Добавление DrawingView в качестве наблюдателя Observer для модели
        // DrawingModel при получении DrawingView ресурсов экрана
        super.addNotify();
        drawingModel.addObserver(this);
    }
    
    @Override public void removeNotify() {
    	// Удаление DrawingView из наблюдателей для модели DrawingModel при освобождении
        // DrawingView ресурсов экрана
        super.removeNotify();
        drawingModel.deleteObserver(this);
    }
    
}