package drawing.controller;

import java.awt.Point;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import drawing.model.shapes.*;

/**
 * TransferableShape - ������ ������ Transferable, ������� �������� ������ 
 * MyShape � �����, � ������� ���������� �������������� ������� MyShape.
 * @author dwinner@inbox.ru
 */
public class TransferableShape implements Transferable {
    
    private MyShape shape;
    private Point origin;
    
    // MIME-���, ���������������� ��������������� ������ MyShape
    public static final String MIME_TYPE = "application/x-deitel-shape";
    // ���� ������ DataFlavor, ������� ������ MyShape ������������ ��� �������� ��������������.
    private static final DataFlavor[] flavors = new DataFlavor[] {new DataFlavor(MIME_TYPE, "Shape")};  

    /**
     * �����������
     * @param myShape ������ MyShape, ������������ �� �������� ����� Point
     * @param originPoint �������� ����� Point
     */
    public TransferableShape(MyShape myShape, Point originPoint) {
        shape = myShape;
        origin = originPoint;
    }
    
    /**
     * ��������� ����� Point, � ������� ���������� �������������� ������� MyShape
     * @return ����� Point
     */
    public Point getOrigin() {
        return origin;
    }
    
    /**
     * ��������� ������� MyShape
     * @return ������ MyShape
     */
    public MyShape getShape() {
        return shape;
    }

    @Override public DataFlavor[] getTransferDataFlavors() {
    	// ��������� ����� ������, �������������� MyShape
        return flavors;
    }

    @Override public boolean isDataFlavorSupported(DataFlavor flavor) {
    	// �����������, ������������ �� MyShape ��������� ��� ������
        // ����� ��������� ���� ������ DataFlavor � ������� flavors
        for (int i = 0; i < flavors.length; i++) {
            if (flavor.equals(flavors[i])) {
                return true;
            }
        }
        return false;
    }

    @Override public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
    	// ��������� ������, ������� ������ ������������ ��� ������� ���� DataFlavor
        if (!isDataFlavorSupported(flavor)) {
            throw new UnsupportedFlavorException(flavor);
        }
        // ������� ������� TransferableShape ��� ��������
        return this;
    }
    
}