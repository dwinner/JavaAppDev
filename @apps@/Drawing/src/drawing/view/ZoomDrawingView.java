package drawing.view;

import java.awt.*;
import java.awt.geom.*;
import java.awt.event.*;
import drawing.model.DrawingModel;


/**
 * �������� ������ DrawingView, ������� ������������ �������.
 * @author dwinner@inbox.ru
 */
public class ZoomDrawingView extends DrawingView {
    
    private static final long serialVersionUID = 1L;
    
    // ������������ ��������������� ����
    private double scaleFactorX;
    private double scaleFactorY;
    
    // �������������� ��� ��������������� ����
    private AffineTransform scaleTransform;
    
    /**
     * ���������� ���� ZoomDrawingView � �������� �������
     * � ���������� ������������� �� ���������
     * @param model ������ �����
     */
    public ZoomDrawingView(DrawingModel model) {
        this(model, 1.0);
    }
    
    /**
     * ���������� ���� ZoomDrawingView � �������� ������� � ���������� �������������
     * @param model ������ �����
     * @param scale ���������� �����������
     */
    public ZoomDrawingView(DrawingModel model, double scale) {
        this(model, scale, scale);
    }
    
    /**
     * ���������� ���� ZoomDrawingView � �������� ������� � 
     * ���������� ����������� �������������� �� ���� x � y
     * @param model ������ �����
     * @param scaleX ���������� ����������� �� X
     * @param scaleY ���������� ����������� �� Y
     */
    public ZoomDrawingView(DrawingModel model, double scaleX, double scaleY) {
        // ����� ������������ DrawingView
        super(model);        
        // ������� ����������� ������������ ��� ����
        setScaleFactors(scaleX, scaleY);        
        // ������������ ������� ��������� �������� ����������, �����
        // ����������������� �������
        addComponentListener(new ComponentAdapter() {
        	// ��� ��������� �������� ���� �������� ���������� ������������
        	@Override public void componentResized(ComponentEvent event) {
                double width = (double) getSize().width;
                double height = (double) getSize().height;                
                // ���������� ����� ���������� �������������
                double factorX = width / PREFERRED_WIDTH;
                double factorY = height / PREFERRED_HEIGHT;               
                setScaleFactors(factorX, factorY);
            }
        });
    }
    
    // ��������� ����� � �������������� ���������������
    @Override public void drawShapes(Graphics2D g2D) {
        // ������� �������������� ��� ������� Graphics2D
        g2D.setTransform(scaleTransform);        
        // ��������� ����� � ���������������� ������� Graphics2D
        super.drawShapes(g2D);
    }

    /**
     * ������� ���������� ������������� ��� ����
     * @param scaleX ���������� ����������� �� X
     * @param scaleY ���������� ����������� �� Y
     */
    private void setScaleFactors(double scaleX, double scaleY) {
        // ������� ���������� �������������
        scaleFactorX = scaleX;
        scaleFactorY = scaleY;
        // �������� ������� AffineTransform � ��������� ����������� ��������������
        scaleTransform = AffineTransform.getScaleInstance(scaleFactorX, scaleFactorY);
    }
    
    /**
     * ��������� ���������������� �������� ��� ����� ����������
     * @return ����� ������ � ���� Dimension
     */
    public Dimension getPrefferedSize() {
        // ������ �� ���������: PREFERRED_WIDTH x PREFERRED_HEIGHT
        // ��������������� � �������������� ������������� scaleFactors
        return new Dimension(
        		(int) (PREFERRED_WIDTH * scaleFactorX),
        		(int) (PREFERRED_HEIGHT * scaleFactorY)
        );
    }
    
}