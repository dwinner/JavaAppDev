package drawing.controller;

import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import drawing.model.shapes.*;

/**
 * TransferableShape - объект класса Transferable, который содержит объект 
 * MyShape и точку, с которой начинается перетаскивание объекта MyShape.
 * @author dwinner@inbox.ru
 */
public class TransferableShape implements Transferable {
    
    private MyShape shape;
    private Point origin;
    
    // MIME-тип, идентифицирующий перетаскиваемые фигуры MyShape
    public static final String MIME_TYPE = "application/x-deitel-shape";
    // Типы данных DataFlavor, которые объект MyShape поддерживает для операций перетаскивания.
    private static final DataFlavor[] flavors = new DataFlavor[] {new DataFlavor(MIME_TYPE, "Shape")};  

    /**
     * Конструктор
     * @param myShape Объект MyShape, перемещаемый из исходной точки Point
     * @param originPoint Исходная точка Point
     */
    public TransferableShape(MyShape myShape, Point originPoint) {
        shape = myShape;
        origin = originPoint;
    }
    
    /**
     * Получение точки Point, с которой начинается перетаскивание объекта MyShape
     * @return Точка Point
     */
    public Point getOrigin() {
        return origin;
    }
    
    /**
     * Получение объекта MyShape
     * @return Объект MyShape
     */
    public MyShape getShape() {
        return shape;
    }

    @Override public DataFlavor[] getTransferDataFlavors() {
    	// Получение типов данных, поддерживаемых MyShape
        return flavors;
    }

    @Override public boolean isDataFlavorSupported(DataFlavor flavor) {
    	// Определение, поддерживает ли MyShape указанный тип данных
        // Поиск заданного типа данных DataFlavor в массиве flavors
        for (int i = 0; i < flavors.length; i++) {
            if (flavor.equals(flavors[i])) {
                return true;
            }
        }
        return false;
    }

    @Override public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
    	// Получение данных, которые должны передаваться для данного типа DataFlavor
        if (!isDataFlavorSupported(flavor)) {
            throw new UnsupportedFlavorException(flavor);
        }
        // Возврат объекта TransferableShape для передачи
        return this;
    }
    
}