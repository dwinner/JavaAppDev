/*
 * ���������� ������ ��� ����������� JCheckBox
 * 
 */

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Vector;
import javax.swing.*;

/**
 * ������ �������� ������ � ��������.
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
 * ������ � ��������.
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
     * ����������� ��������� ������.
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
                // ������ �� ��������
                if (me.getClickCount() == 1 && SwingUtilities.isLeftMouseButton(me))
                {
                    // ������ ��� ������
                    int pos = locationToIndex(me.getPoint());
                    CheckBoxListElement cblel = (CheckBoxListElement) getModel().getElementAt(pos);
                    cblel.setSelected(!cblel.isSelected());
                    repaint();
                }
            }
        });
    }

    /**
     * ��� ��� ������� ����������� �������.
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
            // ��������� ��� �� �������������
            if (value instanceof CheckBoxListElement)
            {
                CheckBoxListElement cblel = (CheckBoxListElement) value;
                // ����������� ������
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
    // ������ ������
    private static Object[] data =
    {
        new CheckBoxListElement(false, "������"),
        new CheckBoxListElement(false, "<html><h3><font color=\"red\">�������</font></h3>"),
        new CheckBoxListElement(false, "����� �������"),
        new CheckBoxListElement(true, "��������� �����")
    };

    public static void main(String[] args)
    {
        // ������� ������ � ����������� ���
        JList list = new CheckBoxList(data);
        // ��������� � ����
        JFrame frame = new JFrame("CheckBoxListTest");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(200, 200);
        frame.getContentPane().add(new JScrollPane(list));
        frame.setVisible(true);
    }
}