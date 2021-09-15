package drawing.controller;

import drawing.model.shapes.*;
import drawing.model.DrawingModel;

/**
 * �������� ������ MyShapeController ��� �����, ������������ ���������������,
 * ����� ��� MyOval � MyRectangle.
 * @author dwinner@inbox.ru
 */
public class BoundedShapeController extends MyShapeController {
    
    private MyShape currentShape;

    public BoundedShapeController(DrawingModel model, Class<? extends MyShape> shapeClass) {
        super(model, shapeClass);
    }

    @Override public void startShape(int x, int y) {
        // ��������� ����� ������
        currentShape = createNewShape();        
        if (currentShape != null) {
            // ������� ��������� ������ �� �������
            currentShape.setPoint1(x, y);
            currentShape.setPoint2(x, y);
            currentShape.setStartPoint(x, y);
            // ���������� ����� ������ � DrawingModel
            addShapeToModel(currentShape);
        }
    }

    @Override public void modifyShape(int x, int y) {
        // �������� ������ �� ������ DrawingModel
        removeShapeFromModel(currentShape);
        currentShape.setEndPoint(x, y);        
        int startX = currentShape.getStartX();
        int startY = currentShape.getStartY();
        // ������� � ����� Point1 ��������� �������� ������ ���� ������
        currentShape.setPoint1(Math.min(x, startX), Math.min(y, startY));
        // ������� � ����� Point2 ��������� ������� ������� ���� ������
        currentShape.setPoint2(Math.max(x, startX), Math.max(y, startY));        
        // ���������� ������ � ������
        addShapeToModel(currentShape);
    }

    @Override public void endShape(int x, int y) {
        modifyShape(x, y);
    }
    
}