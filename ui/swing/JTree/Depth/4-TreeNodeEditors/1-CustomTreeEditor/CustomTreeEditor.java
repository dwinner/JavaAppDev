/*
 * �������� ������������������� ��������� �����
 */

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.util.EventObject;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.text.MaskFormatter;
import javax.swing.tree.*;

public class CustomTreeEditor extends JFrame
{
    private static final Logger LOG = Logger.getLogger(CustomTreeEditor.class.getName());
    // ������ ���������
    private String[] phoneDirectory =
    {
        "123-13-13",
        "444-55-67",
        "111-23-45"
    };

    public CustomTreeEditor()
    {
        super("Custom Tree Editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ����������� ������
        JTree tree = new JTree(createTreeModel());
        // �������� �������������� �����
        tree.setEditable(true);
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        DefaultTreeCellEditor editor = new DefaultTreeCellEditor(tree, renderer, new MaskTreeEditor(tree));
        tree.setCellEditor(editor);
        // ������� ���� �� �����
        getContentPane().add(new JScrollPane(tree));
        setSize(400, 300);
        setVisible(true);
    }

    // �������� ������ ������
    private TreeModel createTreeModel()
    {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Company");
        DefaultMutableTreeNode node = new DefaultMutableTreeNode("Personnel Department");
        root.add(node);
        // ������������ ������
        for (int i = 0; i < phoneDirectory.length; i++)
        {
            node.add(new DefaultMutableTreeNode(phoneDirectory[i]));
        }
        return new DefaultTreeModel(root);
    }

    /**
     * ����������� �������� ����� ������.
     */
    private static class MaskTreeEditor extends AbstractCellEditor implements TreeCellEditor
    {
        // ������
        private JTree tree;
        // ��������� ����, ����������� ��� ��������������
        private JFormattedTextField editor;

        // ����������� ���������
        MaskTreeEditor(JTree tree)
        {
            this.tree = tree;
            try
            {
                // ������� ������������� ������
                MaskFormatter phoneMask = new MaskFormatter("###-##-##");
                editor = new JFormattedTextField(phoneMask);
            }
            catch (ParseException ex)
            {
                CustomTreeEditor.LOG.log(Level.SEVERE, null, ex);
            }
            // ������������ � ���� ���������
            editor.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    stopCellEditing();
                }
            });
        }

        // ���������� ���������, ������������ ��� ��������
        @Override
        public Component getTreeCellEditorComponent(
            JTree tree,
            Object value,
            boolean isSelected,
            boolean expanded,
            boolean leaf,
            int row)
        {
            // ������������� ����� ��������
            editor.setText(value.toString());
            // ���������� ��������� ����
            return editor;
        }

        // ���������� ������� �������� � ���������
        @Override
        public Object getCellEditorValue()
        {
            return editor.getText();
        }

        // ����������, ����� �� ��������� ��������������
        @Override
        public boolean isCellEditable(EventObject event)
        {
            MutableTreeNode node = (MutableTreeNode) tree.getLastSelectedPathComponent();
            return ((node == null) || !node.isLeaf()) ? false : true;
        }
    }

    public static void main(String[] args)
    {
        new CustomTreeEditor();
    }
}