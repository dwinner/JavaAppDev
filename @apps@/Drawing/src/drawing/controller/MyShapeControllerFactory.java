package drawing.controller;

import static java.lang.Class.forName;
import java.util.logging.Level;
import java.util.logging.Logger;
import drawing.model.DrawingModel;
import drawing.model.shapes.*;

/**
 * MyShapeControllerFactory исрользует Factory-метод для создания соответствующего
 * экземпляра контроллера MyShapeController для заданного подкласса класса MyShape.
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
     * ссылка на Singleton-объект MyShapeControllerFactory
     */
    private static MyShapeControllerFactory factory;
    
    /**
     * Конструктор MyShapeControllerFactory
     */
    protected MyShapeControllerFactory() {
        
    }
    
    /**
     * Возврат экземпляра Singleton класса MyShapeControllerFactory
     * @return ссылка на Singleton-объект MyShapeControllerFactory
     */
    public static final MyShapeControllerFactory getInstance() {
        // Если factory равен null, создать новый объект MyShapeControllerFactory
        if (factory == null) {
            // Получение системного свойства System, содержащего имя класса фабрики
            String factoryClassName = System.getProperty(FACTORY_PROPERTY_KEY);
            // Если свойство System не задано, создать новый экземпляр класса
            // MyShapeControllerFactory по умолчанию
            if (factoryClassName == null) {
                factory = new MyShapeControllerFactory();
            }
            else {
                try {
                    
                    // Создание нового объекта MyShapeControllerFactory с использованием
                    // имени класса, предоставленного свойством System
                    // Создание подкласса класса MyShapeControllerFactory
                    factory = (MyShapeControllerFactory) forName(factoryClassName).newInstance();
                }
                catch (ClassNotFoundException ex) { // Если класс не найден
                    LOG.log(Level.SEVERE, null, ex);
                }
                catch (InstantiationException ex) { // Ошибка создания экземпляра класса фабрики
                    LOG.log(Level.SEVERE, null, ex);
                } 
                catch (IllegalAccessException ex) { // Отсутствие доступа к классу
                    LOG.log(Level.SEVERE, null, ex);
                }
            }
        }
        return factory;
    }
    
    /**
     * Создание нового экземпляра подкласса MyShapeController для 
     * указанного подкласса класса MyShape.
     * @param model Модель для DrawingModel
     * @param shapeClassName Имя класса фигуры MyShape
     * @return объект MyShapeController
     */
    public MyShapeController newMyShapeController(DrawingModel model, String shapeClassName) {
        // Создание экземпляра класса Class для заданного имени класса и
        // построение соответствующего контроллера MyShapeController
        try {
            // Получение объекта класса Class для выбранного подкласса класса MyShape
            @SuppressWarnings("unchecked")
            Class<? extends MyShape> shapeClass = (Class<? extends MyShape>) forName(MyShape.class.getPackage().getName() + "." + shapeClassName);
            // Возврат соответствующего контроллера для подкласса класса MyShape
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
     * Возвращает Массив доступных классов фигур
     * @return Ссылка на копию массива доступных классов фигур
     */
    public String[] getSupportedShapes() {
    	String[] supShapesCopy = new String[supportedShapes.length];
    	System.arraycopy(supportedShapes, 0, supShapesCopy, 0, supportedShapes.length);
        return supShapesCopy;
    }
    
}