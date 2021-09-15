package downloadmanager;

import java.awt.*;
import javax.swing.*;
import javax.swing.table.*;

/**
 * Этот класс формирует изображение JProgressBar в отдельной ячейке
 * таблицы. Класс ProgressRenderer наследует класс JProgressBar и
 * реализует интерфейс TableCellRenderer.
 * @author dwinner@inbox.ru
 */
class ProgressRenderer extends JProgressBar implements TableCellRenderer {

    private static final long serialVersionUID = 1L;

    // Конструктор для ProgressRenderer.
    public ProgressRenderer(int min, int max) {
        super(min, max);
    }

    // Возвращает JProgressBar как исходный для данной ячейки.
    @Override
    public Component getTableCellRendererComponent( JTable table,
                                                    Object value,
                                                    boolean isSelected,
                                                    boolean hasFocus,
                                                    int row,
                                                    int column)
    {
        // Установить процент завершенности для JProgressBar
        setValue((int) ((Float) value).floatValue());
        return this;
    }
}