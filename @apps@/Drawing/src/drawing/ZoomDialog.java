package drawing;

import javax.swing.*;
import drawing.model.DrawingModel;
import drawing.view.*;

/**
 * ZoomDialog - подкласс класса JDialog, который отображает масштабированное
 * представление модели DrawingModel
 * @author dwinner@inbox.ru
 */
public class ZoomDialog extends JDialog {
    
	private static final long serialVersionUID = -4386943200387571164L;
	private ZoomDrawingView drawingView;
    private static final double zoomFactor = 0.5;

    /**
     * Конструктор ZoomDialog
     * @param model Модель фигур
     * @param title Заголовок окна Диалога
     */
    public ZoomDialog(DrawingModel model, String title) {
        // Заголовок окна ZoomDialog
        setTitle(title);
        // Создание вида ZoomDrawingView для использования значения zoomFactor по умолчанию
        drawingView = new ZoomDrawingView(model, zoomFactor);        
        // Добавление вида ZoomDrawingView в панель ContentPane
        getContentPane().add(drawingView);        
        // Изменение размеров ZoomDialog
        pack();        
        // Сделать окно ZoomDialog видимым
        setVisible(true);
    }
    
    @Override public void setTitle(String title) {
    	// Задание заголовка окна JDialog
        super.setTitle(title + " Zoom");
    }

}