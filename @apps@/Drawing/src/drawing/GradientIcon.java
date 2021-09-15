package drawing;

import java.awt.Color;
import java.awt.Component;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.Icon;

/**
 * GradientIcon - ���������� ���������� Icon, ������������� ����������� �������
 * �������� 16x16 �������� �� ���������� ����� startColor �� ��������� ����� endColor.
 * @author dwinner@inbox.ru
 */
public class GradientIcon implements Icon {
	
    private static final int DEFAULT_ICON_WIDTH = 16;
    
    // ����� Colors, ������������ ��� ���������
    private Color startColor;
    private Color endColor;

    /**
     * ����������� GradientIcon
     * @param start ��������� ���� ���������
     * @param end �������� ���� ���������
     */
    public GradientIcon(Color start, Color end) {
        setStartColor(start);
        setEndColor(end);
    }
    
    /**
     * ������� ���������� ����� ��� ���������
     * @param start ��������� ���� ���������
     */
    public final void setStartColor(Color start) {
        startColor = start;
    }
    
    /**
     * ������� ��������� ����� ��� ���������
     * @param end �������� ���� ���������
     */ 
    public final void setEndColor(Color end) {
        endColor = end;
    }
    
    /**
     * ����������� ���������� ����� ���������
     * @return ��������� ���� ���������
     */
    public Color getStartColor() {
        return startColor;
    }
    
    /**
     * ��������� ��������� ����� ��� ���������
     * @return �������� ���� ���������
     */
    public Color getEndColor() {
        return endColor;
    }
    
    @Override public int getIconWidth() {
    	// ��������� ������ ������
        return DEFAULT_ICON_WIDTH;
    }
    
    @Override public int getIconHeight() {
    	// ��������� ������ ������
        return DEFAULT_ICON_WIDTH;
    }

    @Override public void paintIcon(Component component, Graphics g, int x, int y) {
    	// ����������� ������ � �������� ����������
        // ��������� ������� Graphics2D
        Graphics2D g2D = (Graphics2D) g;      
        // ������� GradientPaint
        g2D.setPaint(new GradientPaint(x, y, getStartColor(), DEFAULT_ICON_WIDTH, DEFAULT_ICON_WIDTH, getEndColor()));        
        // ������������ �������������� ����������� ��������
        g2D.fillRect(x, y, DEFAULT_ICON_WIDTH, DEFAULT_ICON_WIDTH);
    }
    
}