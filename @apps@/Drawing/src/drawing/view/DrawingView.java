package drawing.view;

import java.awt.*;
import java.util.*;
import javax.swing.*;
import drawing.model.DrawingModel;
import drawing.model.shapes.*;

/**
 * ��� ������ DrawingModel, � ������� ������������ ������ ������
 * � �������������� Java2D API.
 * @author dwinner@inbox.ru
 */
public class DrawingView extends JPanel implements Observer {
    
    private static final long serialVersionUID = 1L;
    
    // ��������� ���������������� �������� ����������
    protected static final double PREFERRED_WIDTH = 320.0;
    protected static final double PREFERRED_HEIGHT = 240.0;
    
    // ������� ���� ������ �� ���������
    protected static final Color DEFAULT_BACKGROUND_COLOR = Color.WHITE;
    
    // ������, ��� ������� ������������ ���� ���
    private DrawingModel drawingModel;
    
    /**
     * �������� ���� DrawingView ��� ������ ������
     * @param model ������ ��� �����
     */
    public DrawingView(DrawingModel model) {
        // ������� ������ DrawingModel
        drawingModel = model;
        // ����������� ���� � �������� ����������� ������
        drawingModel.addObserver(this);
        // ������� �������� �����
        setBackground(DEFAULT_BACKGROUND_COLOR);
        // ��������� ������� ����������� ��� ���������� �������� ������
        setDoubleBuffered(true);
    }
    
    /**
     * ������� DrawingModel � �������� ������ ��� ��������� ����
     * @param model ������ ��� �����
     */
    public void setModel(DrawingModel model) {
        if (drawingModel != null) {
            drawingModel.deleteObserver(this);
        }
        drawingModel = model;
        // ����������� ���� � �������� ����������� ������
        if (model != null) {
            model.addObserver(this);
            repaint();
        }
    }
    
    /**
     * ��������� ������ DrawingModel, ��������������� � ������ �����
     * @return ����� �� ������ ������ ��� ����� 
     */
    public DrawingModel getModel() {
        return drawingModel;
    }

    
    @Override public void update(Observable o, Object arg) {
    	// ����������� ���� ��� ��������� ���������� �� ������
        repaint();
    }
    
    @Override public void paintComponent(Graphics g) {
        // ����� ����������� paintComponent
        super.paintComponent(g);
        // �������� ������� Graphics2D ��� ��������� ������� Graphics
        Graphics2D g2D = (Graphics2D) g;      
        // ���������� �����������
        g2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        // ���������� ������������������� ����������� ������� Graphics2D
        g2D.setRenderingHint(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
        // ��������� ���� ����� ������
        drawShapes(g2D);
    }

    /**
     * ��������� ����� ������
     * @param g2D ����������� �������� Java2D
     */
    public void drawShapes(Graphics2D g2D) {
    	for (MyShape shape : drawingModel.getShapes()) {
    		shape.draw(g2D);
    	}
    }
    
    @Override public Dimension getPreferredSize() {
    	// ��������� ���������������� �������� ��� ������� ����������
        return new Dimension((int) PREFERRED_WIDTH, (int) PREFERRED_HEIGHT);
    }
    
    @Override public Dimension getMinimumSize() {
    	// ������ ����������� �������� ��� ������� ����������
        return getPreferredSize();
    }
    
    @Override public Dimension getMaximumSize() {
    	// ������ ������������ �������� ��� ������� ����������
        return getPreferredSize();
    }
    
    @Override public void addNotify() {
    	// ���������� DrawingView � �������� ����������� Observer ��� ������
        // DrawingModel ��� ��������� DrawingView �������� ������
        super.addNotify();
        drawingModel.addObserver(this);
    }
    
    @Override public void removeNotify() {
    	// �������� DrawingView �� ������������ ��� ������ DrawingModel ��� ������������
        // DrawingView �������� ������
        super.removeNotify();
        drawingModel.deleteObserver(this);
    }
    
}