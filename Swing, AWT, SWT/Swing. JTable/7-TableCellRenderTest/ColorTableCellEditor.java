import javax.swing.*;
import javax.swing.table.TableCellEditor;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.EventObject;

/**
 * Редактор отображает диалоговое окно выбора цвета.
 */
public class ColorTableCellEditor extends AbstractCellEditor implements TableCellEditor
{
    private Color color;
    private JColorChooser colorChooser;
    private JDialog colorDialog;
    private JPanel panel;

    public ColorTableCellEditor()
    {
        panel = new JPanel();

        // Подготовка диалогового окна выбора цвета.

        colorChooser = new JColorChooser();
        colorDialog = JColorChooser.createDialog(null,
            "Planet Color",
            false,
            colorChooser,
            new ActionListener()
            {   // Слушатель кнопки OK
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    stopCellEditing();
                }
            },
            new ActionListener()
            {   // Слушатель кнопки Cancel
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    cancelCellEditing();
                }
            }
        );
        colorDialog.addWindowListener(new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent e)
            {
                cancelCellEditing();
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table,
        Object value,
        boolean isSelected,
        int row,
        int column)
    {
        /*
        Получение текущего значения Color. Если пользователь начинает редактирование,
        это значение устанавливается в диалоговом окне.
         */
        colorChooser.setColor((Color) value);
        return panel;
    }

    @Override
    public boolean shouldSelectCell(EventObject anEvent)
    {
        // Начало редактирования.
        colorDialog.setVisible(true);

        // Вызывающий метод получает уведомление
        // о допустимости выбора данной ячейки.
        return true;
    }

    @Override
    public void cancelCellEditing()
    {
        // Редактирование отменено - скрыть диалоговое окно.
        colorDialog.setVisible(false);
        super.cancelCellEditing();
    }

    @Override
    public boolean stopCellEditing()
    {
        // Редактирование завершено - скрыть диалоговое окно.
        colorDialog.setVisible(false);
        super.stopCellEditing();

        // Вызывающий метод уведомляется о том,
        // что можно использовать значение цвета.
        return true;
    }

    @Override
    public Object getCellEditorValue()
    {
        return colorChooser.getColor();
    }
}
