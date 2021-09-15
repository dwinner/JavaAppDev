package drawing.model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Observable;
import drawing.model.shapes.*;

/**
 * ������ ��� ��������� � Drawing
 * ��� ������������� ������ ��� ���������� � �������� ����� �������.
 * @author dwinner@inbox.ru
 */
public class DrawingModel extends Observable {
    
    private static final int INITIAL_CAPACITY = 64;    
    // ������, ������������ � ������
    private Collection<MyShape> shapes;
    
    /**
     * ����������� ��� ����������
     */
    public DrawingModel() {
        shapes = new ArrayList<MyShape>(INITIAL_CAPACITY);
    }
    
    /**
     * ���������� ������ � ������
     * @param shape ������, ��������������� ��� ���������� 
     */
    public void addShape(MyShape shape) {
        // ���������� ����� ������ � ������ �����
        shapes.add(shape);       
        // �������� ����������� �� ��������� ������
        fireModelChanged();
    }
    
    /**
     * �������� ������ �� ������
     * @param shape ������, ��������������� ��� ��������
     */
    public void removeShape(MyShape shape) {
        // �������� ������ �� ������
        shapes.remove(shape);        
        // �������� ����������� �� ��������� ������
        fireModelChanged();
    }
    
    /**
     * ��������� ��������� ����� ������
     * @return ��������� ����������������� ������������� ��������� �����
     */
    public Collection<MyShape> getShapes() {
        return Collections.unmodifiableCollection(shapes);
    }
    
    /**
     * ������� ��������� ����� ������
     * @param newShapes ����� ������ �� ��������� �����
     */
    public void setShapes(Collection<? extends MyShape> newShapes) {
        // ����������� ��������� Collection � ����� ArrayList
    	shapes.clear();
    	shapes.addAll(newShapes);
        // �������� ����������� �� ���������� ������
        fireModelChanged();
    }
    
    /**
     * ������� �������� ������ ����� ArrayList
     */
    public void clear() {
        shapes.clear();
        // �������� ����������� �� ���������� ������
        fireModelChanged();
    }

    /**
     * �������� ����������� �� ���������� ������
     */
    private void fireModelChanged() {
        // ��������� ����� ��������� ������
        setChanged();      
        // ����������� ������������ Observer �� ���������� ������
        notifyObservers();
    }
}