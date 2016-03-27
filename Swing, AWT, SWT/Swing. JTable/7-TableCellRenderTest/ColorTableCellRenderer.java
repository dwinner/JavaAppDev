import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;

/**
 * Объект отображения представляет значение
 * посредством панели соответствующего цвета.
 */
public class ColorTableCellRenderer extends JPanel implements TableCellRenderer
{
    @Override
    public Component getTableCellRendererComponent(JTable table,
        Object value,
        boolean isSelected,
        boolean hasFocus,
        int row,
        int column)
    {
        setBackground((Color) value);
        if (hasFocus)
            setBorder(UIManager.getBorder("Table.focusCellHighlightBorder"));
        else
            setBorder(null);
        return this;
    }
}
