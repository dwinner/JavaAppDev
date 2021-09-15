package drawing.controller;

import drawing.model.shapes.*;
import drawing.model.DrawingModel;

/**
 * MyLineController - �������� ������ MyShapeController ��� �������� MyLine
 * @author dwinner@inbox.ru
 */
public class MyLineController extends MyShapeController {
    
    private MyShape currentShape;
    
    public MyLineController(DrawingModel model, Class<? extends MyShape> shapeClass) {
        super(model, shapeClass);
    }

    @Override public void startShape(int x, int y) {
        // �������� ����� ������
        currentShape = createNewShape();        
        if (currentShape != null) {
            // ������� ��������� ����� ������ �� �������
            currentShape.setPoint1(x, y);
            currentShape.setPoint2(x, y);
            currentShape.setStartPoint(x, y);            
            // ���������� ����� ��������� ������ � DrawingModel
            addShapeToModel(currentShape);
        }
    }

    @Override public void modifyShape(int x, int y) {
        // �������� ������ �� ������ DrawingModel
        removeShapeFromModel(currentShape);
        currentShape.setEndPoint(x, y);        
        int startX = currentShape.getStartX();
        int startY = currentShape.getStartY();        
        // ������� ��������� (x, y) ����� Point1
        currentShape.setPoint1(x, y);        
        // ������� ����� Point2 � �������� ��������� ����� StartPoint
        currentShape.setPoint2(startX, startY);
        // ���������� ������ � ������
        addShapeToModel(currentShape);
    }

    @Override public void endShape(int x, int y) {
        modifyShape(x, y);
    }
    
}