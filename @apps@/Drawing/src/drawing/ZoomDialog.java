package drawing;

import javax.swing.*;
import drawing.model.DrawingModel;
import drawing.view.*;

/**
 * ZoomDialog - �������� ������ JDialog, ������� ���������� ����������������
 * ������������� ������ DrawingModel
 * @author dwinner@inbox.ru
 */
public class ZoomDialog extends JDialog {
    
	private static final long serialVersionUID = -4386943200387571164L;
	private ZoomDrawingView drawingView;
    private static final double zoomFactor = 0.5;

    /**
     * ����������� ZoomDialog
     * @param model ������ �����
     * @param title ��������� ���� �������
     */
    public ZoomDialog(DrawingModel model, String title) {
        // ��������� ���� ZoomDialog
        setTitle(title);
        // �������� ���� ZoomDrawingView ��� ������������� �������� zoomFactor �� ���������
        drawingView = new ZoomDrawingView(model, zoomFactor);        
        // ���������� ���� ZoomDrawingView � ������ ContentPane
        getContentPane().add(drawingView);        
        // ��������� �������� ZoomDialog
        pack();        
        // ������� ���� ZoomDialog �������
        setVisible(true);
    }
    
    @Override public void setTitle(String title) {
    	// ������� ��������� ���� JDialog
        super.setTitle(title + " Zoom");
    }

}