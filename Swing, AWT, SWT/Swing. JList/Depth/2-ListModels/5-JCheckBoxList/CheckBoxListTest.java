/*
 * Реализация списка для компонентов JCheckBox
 * 
 */

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.*;

/**
 * Данные элемента списка с флажками.
 */
class CheckBoxListElement
{
    private boolean selected;
    private String text;

    CheckBoxListElement(boolean selected, String text)
    {
        this.selected = selected;
        this.text = text;
    }

    CheckBoxListElement(String text)
    {
        this(false, text);
    }

    public boolean isSelected()
    {
        return selected;
    }

    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }
}

/**
 * Список с флажками.
 */
class CheckBoxList extends JList
{
    CheckBoxList(ListModel model)
    {
        super(model);
        initList();
    }

    CheckBoxList(Object[] data)
    {
        super(data);
        initList();
    }

    CheckBoxList(Vector data)
    {
        super(data);
        initList();
    }

    /**
     * Специальная настройка списка.
     */
    private void initList()
    {
        setCellRenderer(new CheckBoxCellRenderer());
        setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent me)
            {
                // Следим за щелчками
                if (me.getClickCount() == 1 && SwingUtilities.isLeftMouseButton(me))
                {
                    // Нужный нам щелчок
                    int pos = locationToIndex(me.getPoint());
                    CheckBoxListElement cblel = (CheckBoxListElement) getModel().getElementAt(pos);
                    cblel.setSelected(!cblel.isSelected());
                    repaint();
                }
            }
        });
    }

    /**
     * Тип для объекта отображения флажков.
     */
    static class CheckBoxCellRenderer extends JCheckBox implements ListCellRenderer
    {
        @Override
        public Component getListCellRendererComponent(JList list,
            Object value,
            int index,
            boolean isSelected,
            boolean cellHasFocus)
        {
            // Проверяем тип на совместимость
            if (value instanceof CheckBoxListElement)
            {
                CheckBoxListElement cblel = (CheckBoxListElement) value;
                // Настраиваем флажок
                if (isSelected)
                {
                    setBackground(list.getSelectionBackground());
                    setForeground(list.getSelectionForeground());
                }
                else
                {
                    setBackground(list.getBackground());
                    setForeground(list.getForeground());
                }
                setSelected(cblel.isSelected());
                setText(cblel.getText());
                return this;
            }
            return null;
        }
    }
}

public class CheckBoxListTest
{
    // Данные списка
    private static Object[] data =
    {
        new CheckBoxListElement(false, "Корпус"),
        new CheckBoxListElement(false, "<html><h3><font color=\"red\">Колонки</font></h3>"),
        new CheckBoxListElement(false, "Набор шлейфов"),
        new CheckBoxListElement(true, "Системная плата")
    };

    public static void main(String[] args)
    {
        // Создаем список и настраиваем его
        JList list = new CheckBoxList(data);
        // Добавляем в окно
        JFrame frame = new JFrame("CheckBoxListTest");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        frame.getContentPane().add(new JScrollPane(list));
        frame.setVisible(true);
    }
}