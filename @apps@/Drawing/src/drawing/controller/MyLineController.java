package drawing.controller;

import drawing.model.shapes.*;
import drawing.model.DrawingModel;

/**
 * MyLineController - подкласс класса MyShapeController для объектов MyLine
 * @author dwinner@inbox.ru
 */
public class MyLineController extends MyShapeController {
    
    private MyShape currentShape;
    
    public MyLineController(DrawingModel model, Class<? extends MyShape> shapeClass) {
        super(model, shapeClass);
    }

    @Override public void startShape(int x, int y) {
        // Создание новой фигуры
        currentShape = createNewShape();        
        if (currentShape != null) {
            // Задание положения новой фигуры на рисунке
            currentShape.setPoint1(x, y);
            currentShape.setPoint2(x, y);
            currentShape.setStartPoint(x, y);            
            // Добавление вновь созданной фигуры в DrawingModel
            addShapeToModel(currentShape);
        }
    }

    @Override public void modifyShape(int x, int y) {
        // Удаление фигуры из модели DrawingModel
        removeShapeFromModel(currentShape);
        currentShape.setEndPoint(x, y);        
        int startX = currentShape.getStartX();
        int startY = currentShape.getStartY();        
        // Задание координат (x, y) точки Point1
        currentShape.setPoint1(x, y);        
        // Задание точки Point2 в качестве начальной точки StartPoint
        currentShape.setPoint2(startX, startY);
        // Добавление фигуры в модель
        addShapeToModel(currentShape);
    }

    @Override public void endShape(int x, int y) {
        modifyShape(x, y);
    }
    
}