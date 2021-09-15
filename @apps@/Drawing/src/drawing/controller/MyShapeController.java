package drawing.controller;

import java.awt.*;
import java.awt.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import drawing.model.DrawingModel;
import drawing.model.shapes.*;

/**
 * ����������� ������� �����, ������� ������������ ���������� 
 * ��� ������������ �����
 * @author dwinner@inbox.ru
 */
public abstract class MyShapeController {
    
    private DrawingModel drawingModel;
    private static final Logger LOG = Logger.getLogger(MyShapeController.class.getName());
    
    // ��������� � ��������� ����� ��� ��������� � ����������
    private Color primaryColor = Color.black;
    private Color secondaryColor = Color.white;
    
    // ������ Class ��� �������� ����� ����������� ���������� ������ MyShape
    private Class<? extends MyShape> shapeClass;
    
    // ������� �������� MyShape
    private boolean fillShape = false;
    private boolean useGradient = false;
    private float strokeSize = 1.0f;
    
    // ���������, ����� �� ������������ ����� ��������������; ���� true,
    // MyShapeController ����� ������������ ������� ����.
    private boolean dragMode = false;
    
    private MouseListener mouseListener;
    private MouseMotionListener mouseMotionListener;
    
    /**
     * ����������� MyShapeController
     * @param model ������ ��������� �����
     * @param myShapeClass ��� ������ ��� ����� MyShape
     */
    public MyShapeController(DrawingModel model, Class<? extends MyShape> myShapeClass) {
        // ������� ������ DrawingModel
        drawingModel = model;        
        // ������� ��������� ������ MyShape
        shapeClass = myShapeClass;        
        // ������������� ������� ����
        mouseListener = new MouseAdapter() {
        	// ��� ������� ������ ���� ������� ����� ������
            @Override public void mousePressed(MouseEvent event) {
                // ���� ��� �� ����� �������������� dragMode, ������
                // ������������ ����� ������
                if (!dragMode) {
                    startShape(event.getX(), event.getY());
                }
            }
            // ��� ���������� ������ ���� ���������� ������������� ���������� ������
            @Override public void mouseReleased(MouseEvent event) {
                // ���� ��� �� ����� �������������� dragMode, ��������� ���������
                // ������� ������
                if (!dragMode) {
                    endShape(event.getX(), event.getY());
                }
            }
        };        
        // ������������� �������, ��������� � ������������ ����
        mouseMotionListener = new MouseMotionAdapter() {
        	// ��� �������������� ���� ������ ���������� ����� Point2 ������� ������
            @Override public void mouseDragged(MouseEvent event) {
                // ���� ��� �� ����� ��������������, �������������� ������� ������
                if (!dragMode) {
                    modifyShape(event.getX(), event.getY());
                }
            }
        };
    }
    
    /**
     * ������� ���������� ����� (���������� ����� ��� ���������)
     * @param color ��������� ���� ���������
     */
    public void setPrimaryColor(Color color) {
        primaryColor = color;
    }
    
    /**
     * ��������� ���������� �����
     * @return ��������� ���� ���������
     */
    public Color getPrimaryColor() {
        return primaryColor;
    }
    
    /**
     * ������� ���������� ����� (��������� ����� ��� ���������)
     * @param color �������� ���� ���������
     */
    public void setSecondaryColor(Color color) {
        secondaryColor = color;
    }
    
    /**
     * ��������� ���������� �����
     * @return �������� ���� ���������
     */
    public Color getSecondaryColor() {
        return secondaryColor;
    }
    
    /**
     * ��������� ������� ��� ������
     * @param fill ���� �������
     */
    public void setShapeFilled(boolean fill) {
        fillShape = fill;
    }
    
    /**
     * ��������� ����� ������� ��� ������
     * @return ���� �������
     */
    public boolean getShapeFilled() {
        return fillShape;
    }
    
    /**
     * ������� ��������� ��� ������������ ������
     * @param gradient ���� ������� ���������
     */
    public void setUseGradient(boolean gradient) {
        useGradient = gradient;
    }
    
    /**
     * ��������� ����� ���������
     * @return ���� ������� ���������
     */
    public boolean getUseGradient() {
        return useGradient;
    }
    
    /**
     * ������� ������ �������������� dragMode
     * @param drag ���� ������ ��������������
     */
    public void setDragMode(boolean drag) {
        dragMode = drag;
    }
    
    /**
     * ������� ������� �����
     * @param stroke ������� �����
     */
    public void setStrokeSize(float stroke) {
        strokeSize = stroke;
    }
    
    /**
     * ��������� ������� �����
     * @return ������� �����
     */
    public float getStrokeSize() {
        return strokeSize;
    }
    
    /**
     * �������� ������ ���������� �������� ��������� ������ MyShape
     * @return ������ �� ������ ������ MyShape
     */
    protected MyShape createNewShape() {
        try {
            MyShape shape = shapeClass.newInstance();           
            // ������� ������� ������� MyShape
            shape.setFilled(fillShape);
            shape.setUseGradient(useGradient);
            shape.setStrokeSize(getStrokeSize());
            shape.setStartColor(getPrimaryColor());
            shape.setEndColor(getSecondaryColor());           
            // ������� ������ �� ����� ��������� ������
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
     * ��������� ��������� MouseListener ��� MyShapeController
     * @return ��������� MouseListener
     */
    public MouseListener getMouseListener() {
        return mouseListener;
    }
    
    /**
     * ��������� ��������� MouseMotionListener ��� MyShapeController
     * @return ��������� MouseMotionListener
     */
    public MouseMotionListener getMouseMotionListener() {
        return mouseMotionListener;
    }
    
    /**
     * ���������� ������ ������ � ������ DrawingModel
     * @param shape ������ �� ������ ������ MyShape
     */
    protected void addShapeToModel(MyShape shape) {
        drawingModel.addShape(shape);
    }
    
    /**
     * �������� ������ ������ �� ������ DrawingModel
     * @param shape ������ �� ������ ������ MyShape
     */
    protected void removeShapeFromModel(MyShape shape) {
        drawingModel.removeShape(shape);
    }
    
    /**
     * ������ ��������� ����� ������
     * @param x ��������� ���������� ��������� �� ��� X
     * @param y ��������� ���������� ��������� �� ��� Y
     */ 
    public abstract void startShape(int x, int y);
    
    /**
     * ����������� ������� ������
     * @param x ������� ���������� ��������� �� ��� X
     * @param y ������� ���������� ��������� �� ��� Y
     */
    public abstract void modifyShape(int x, int y);
    
    /**
     * ��������� ��������� ������
     * @param x �������� ���������� ��������� �� ��� X 
     * @param y �������� ���������� ��������� �� ��� Y
     */
    public abstract void endShape(int x, int y);
    
}