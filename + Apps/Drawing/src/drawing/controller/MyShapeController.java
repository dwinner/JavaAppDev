package drawing.controller;

import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import drawing.model.DrawingModel;
import drawing.model.shapes.*;

/**
 * Абстрактный базовый класс, который представляет контроллер 
 * для закрашивания фигур
 * @author dwinner@inbox.ru
 */
public abstract class MyShapeController {
    
    private DrawingModel drawingModel;
    private static final Logger LOG = Logger.getLogger(MyShapeController.class.getName());
    
    // Первичный и вторичный цвета для рисования и градиентов
    private Color primaryColor = Color.black;
    private Color secondaryColor = Color.white;
    
    // Объект Class для создания новых экземпляров подклассов класса MyShape
    private Class<? extends MyShape> shapeClass;
    
    // Типовые свойства MyShape
    private boolean fillShape = false;
    private boolean useGradient = false;
    private float strokeSize = 1.0f;
    
    // Указывает, задал ли пользователь режим перетаскивания; если true,
    // MyShapeController будет игнорировать события мыши.
    private boolean dragMode = false;
    
    private MouseListener mouseListener;
    private MouseMotionListener mouseMotionListener;
    
    /**
     * Конструктор MyShapeController
     * @param model Модель рисования фигур
     * @param myShapeClass Тип класса для фигур MyShape
     */
    public MyShapeController(DrawingModel model, Class<? extends MyShape> myShapeClass) {
        // Задание модели DrawingModel
        drawingModel = model;        
        // Задание подкласса класса MyShape
        shapeClass = myShapeClass;        
        // Прослушивание событий мыши
        mouseListener = new MouseAdapter() {
        	// При нажатии кнопки мыши создать новую фигуру
            @Override public void mousePressed(MouseEvent event) {
                // Если это не режим перетаскивания dragMode, начать
                // формирование новой фигуры
                if (!dragMode) {
                    startShape(event.getX(), event.getY());
                }
            }
            // При отпускании кнопки мыши установить окончательные координаты фигуры
            @Override public void mouseReleased(MouseEvent event) {
                // Если это не режим перетаскивания dragMode, закончить рисование
                // текущей фигуры
                if (!dragMode) {
                    endShape(event.getX(), event.getY());
                }
            }
        };        
        // Прослушивание событий, связанных с перемещением мыши
        mouseMotionListener = new MouseMotionAdapter() {
        	// При перетаскивании мыши задать координаты точки Point2 текущей фигуры
            @Override public void mouseDragged(MouseEvent event) {
                // Если это не режим перетаскивания, модифицировать текущую фигуру
                if (!dragMode) {
                    modifyShape(event.getX(), event.getY());
                }
            }
        };
    }
    
    /**
     * Задание первичного цвета (начального цвета для градиента)
     * @param color Начальный цвет градиента
     */
    public void setPrimaryColor(Color color) {
        primaryColor = color;
    }
    
    /**
     * Получение первичного цвета
     * @return Начальный цвет градиента
     */
    public Color getPrimaryColor() {
        return primaryColor;
    }
    
    /**
     * Задание вторичного цвета (конечного цвета для градиента)
     * @param color Конечный цвет градиента
     */
    public void setSecondaryColor(Color color) {
        secondaryColor = color;
    }
    
    /**
     * Получение вторичного цвета
     * @return Конечный цвет градиента
     */
    public Color getSecondaryColor() {
        return secondaryColor;
    }
    
    /**
     * Установка заливки для фигуры
     * @param fill Флаг заливки
     */
    public void setShapeFilled(boolean fill) {
        fillShape = fill;
    }
    
    /**
     * Получение флага заливки для фигуры
     * @return Флаг заливки
     */
    public boolean getShapeFilled() {
        return fillShape;
    }
    
    /**
     * Задание градиента при закрашивании фигуры
     * @param gradient Флаг задания градиента
     */
    public void setUseGradient(boolean gradient) {
        useGradient = gradient;
    }
    
    /**
     * Получение флага градиента
     * @return Флаг задания градиента
     */
    public boolean getUseGradient() {
        return useGradient;
    }
    
    /**
     * Задание режима перетаскивания dragMode
     * @param drag Флаг режима перетаскивания
     */
    public void setDragMode(boolean drag) {
        dragMode = drag;
    }
    
    /**
     * Задание толщины линии
     * @param stroke Толщина линии
     */
    public void setStrokeSize(float stroke) {
        strokeSize = stroke;
    }
    
    /**
     * Получение толщины линии
     * @return Толщина линии
     */
    public float getStrokeSize() {
        return strokeSize;
    }
    
    /**
     * Создание нового экземпляра текущего подкласса класса MyShape
     * @return Ссылка на объект класса MyShape
     */
    protected MyShape createNewShape() {
        try {
            MyShape shape = shapeClass.newInstance();           
            // Задание свойств объекта MyShape
            shape.setFilled(fillShape);
            shape.setUseGradient(useGradient);
            shape.setStrokeSize(getStrokeSize());
            shape.setStartColor(getPrimaryColor());
            shape.setEndColor(getSecondaryColor());           
            // Возврат ссылки на вновь созданную фигуру
            return shape;
        }
        catch (InstantiationException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } 
        catch (IllegalAccessException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * Получение слушателя MouseListener для MyShapeController
     * @return Слушатель MouseListener
     */
    public MouseListener getMouseListener() {
        return mouseListener;
    }
    
    /**
     * Получение слушателя MouseMotionListener для MyShapeController
     * @return Слушатель MouseMotionListener
     */
    public MouseMotionListener getMouseMotionListener() {
        return mouseMotionListener;
    }
    
    /**
     * Добавление данной фигуры в модель DrawingModel
     * @param shape Ссылка на объект класса MyShape
     */
    protected void addShapeToModel(MyShape shape) {
        drawingModel.addShape(shape);
    }
    
    /**
     * Удаление данной фигуры из модели DrawingModel
     * @param shape Ссылка на объект класса MyShape
     */
    protected void removeShapeFromModel(MyShape shape) {
        drawingModel.removeShape(shape);
    }
    
    /**
     * Начало рисования новой фигуры
     * @param x Начальная координата рисования по оси X
     * @param y Начальная координата рисования по оси Y
     */ 
    public abstract void startShape(int x, int y);
    
    /**
     * Модификация текущей фигуры
     * @param x Текущая координата рисования по оси X
     * @param y Текущая координата рисования по оси Y
     */
    public abstract void modifyShape(int x, int y);
    
    /**
     * Окончание рисования фигуры
     * @param x Конечная координата рисования по оси X 
     * @param y Конечная координата рисования по оси Y
     */
    public abstract void endShape(int x, int y);
    
}