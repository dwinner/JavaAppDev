package drawing.controller;

import drawing.model.shapes.*;
import drawing.model.DrawingModel;

/**
 * Подкласс класса MyShapeController для фигур, ограниченных прямоугольником,
 * таких как MyOval и MyRectangle.
 * @author dwinner@inbox.ru
 */
public class BoundedShapeController extends MyShapeController {
    
    private MyShape currentShape;

    public BoundedShapeController(DrawingModel model, Class<? extends MyShape> shapeClass) {
        super(model, shapeClass);
    }

    @Override public void startShape(int x, int y) {
        // Получение новой фигуры
        currentShape = createNewShape();        
        if (currentShape != null) {
            // Задание положения фигуры на рисунке
            currentShape.setPoint1(x, y);
            currentShape.setPoint2(x, y);
            currentShape.setStartPoint(x, y);
            // Добавление новой фигуры в DrawingModel
            addShapeToModel(currentShape);
        }
    }

    @Override public void modifyShape(int x, int y) {
        // Удаление фигуры из модели DrawingModel
        removeShapeFromModel(currentShape);
        currentShape.setEndPoint(x, y);        
        int startX = currentShape.getStartX();
        int startY = currentShape.getStartY();
        // Задание в точке Point1 координат верхнего левого угла фигуры
        currentShape.setPoint1(Math.min(x, startX), Math.min(y, startY));
        // Задание в точке Point2 координат нижнего правого угла фигуры
        currentShape.setPoint2(Math.max(x, startX), Math.max(y, startY));        
        // Добавление фигуры в модель
        addShapeToModel(currentShape);
    }

    @Override public void endShape(int x, int y) {
        modifyShape(x, y);
    }
    
}