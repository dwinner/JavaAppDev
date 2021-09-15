package drawing.controller;

import static java.lang.Class.forName;
import java.util.logging.Level;
import java.util.logging.Logger;
import drawing.model.DrawingModel;
import drawing.model.shapes.*;

/**
 * MyShapeControllerFactory ���������� Factory-����� ��� �������� ����������������
 * ���������� ����������� MyShapeController ��� ��������� ��������� ������ MyShape.
 * @author dwinner@inbox.ru
 */
public class MyShapeControllerFactory {
    
    private static final Logger LOG = Logger.getLogger(MyShapeControllerFactory.class.getName());    
    private static final String FACTORY_PROPERTY_KEY = "MyShapeControllerFactory";
    
    private static final String[] supportedShapes = {
        "MyLine",
        "MyRectangle",
        "MyOval",
        "MyText"
    };
    
    /**
     * ������ �� Singleton-������ MyShapeControllerFactory
     */
    private static MyShapeControllerFactory factory;
    
    /**
     * ����������� MyShapeControllerFactory
     */
    protected MyShapeControllerFactory() {
        
    }
    
    /**
     * ������� ���������� Singleton ������ MyShapeControllerFactory
     * @return ������ �� Singleton-������ MyShapeControllerFactory
     */
    public static final MyShapeControllerFactory getInstance() {
        // ���� factory ����� null, ������� ����� ������ MyShapeControllerFactory
        if (factory == null) {
            // ��������� ���������� �������� System, ����������� ��� ������ �������
            String factoryClassName = System.getProperty(FACTORY_PROPERTY_KEY);
            // ���� �������� System �� ������, ������� ����� ��������� ������
            // MyShapeControllerFactory �� ���������
            if (factoryClassName == null) {
                factory = new MyShapeControllerFactory();
            }
            else {
                try {
                    
                    // �������� ������ ������� MyShapeControllerFactory � ��������������
                    // ����� ������, ���������������� ��������� System
                    // �������� ��������� ������ MyShapeControllerFactory
                    factory = (MyShapeControllerFactory) forName(factoryClassName).newInstance();
                }
                catch (ClassNotFoundException ex) { // ���� ����� �� ������
                    LOG.log(Level.SEVERE, null, ex);
                }
                catch (InstantiationException ex) { // ������ �������� ���������� ������ �������
                    LOG.log(Level.SEVERE, null, ex);
                } 
                catch (IllegalAccessException ex) { // ���������� ������� � ������
                    LOG.log(Level.SEVERE, null, ex);
                }
            }
        }
        return factory;
    }
    
    /**
     * �������� ������ ���������� ��������� MyShapeController ��� 
     * ���������� ��������� ������ MyShape.
     * @param model ������ ��� DrawingModel
     * @param shapeClassName ��� ������ ������ MyShape
     * @return ������ MyShapeController
     */
    public MyShapeController newMyShapeController(DrawingModel model, String shapeClassName) {
        // �������� ���������� ������ Class ��� ��������� ����� ������ �
        // ���������� ���������������� ����������� MyShapeController
        try {
            // ��������� ������� ������ Class ��� ���������� ��������� ������ MyShape
            @SuppressWarnings("unchecked")
            Class<? extends MyShape> shapeClass = (Class<? extends MyShape>) forName(MyShape.class.getPackage().getName() + "." + shapeClassName);
            // ������� ���������������� ����������� ��� ��������� ������ MyShape
            if (shapeClassName.equals("MyLine")) {
                return new MyLineController(model, shapeClass);
            }
            else if (shapeClassName.equals("MyText")) {
                return new MyTextController(model, shapeClass);
            }
            else {
                return new BoundedShapeController(model, shapeClass);
            }
        }
        catch (ClassNotFoundException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    /**
     * ���������� ������ ��������� ������� �����
     * @return ������ �� ����� ������� ��������� ������� �����
     */
    public String[] getSupportedShapes() {
    	String[] supShapesCopy = new String[supportedShapes.length];
    	System.arraycopy(supportedShapes, 0, supShapesCopy, 0, supportedShapes.length);
        return supShapesCopy;
    }
    
}