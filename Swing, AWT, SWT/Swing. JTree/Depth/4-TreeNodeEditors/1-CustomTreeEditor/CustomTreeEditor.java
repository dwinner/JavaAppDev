/*
 * Создание специализированного редактора узлов
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
    // Список телефонов
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
        // Настраиваем дерево
        JTree tree = new JTree(createTreeModel());
        // Включаем редактирование узлов
        tree.setEditable(true);
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        DefaultTreeCellEditor editor = new DefaultTreeCellEditor(tree, renderer, new MaskTreeEditor(tree));
        tree.setCellEditor(editor);
        // Выводим окно на экран
        getContentPane().add(new JScrollPane(tree));
        setSize(400, 300);
        setVisible(true);
    }

    // Создание модели дерева
    private TreeModel createTreeModel()
    {
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Company");
        DefaultMutableTreeNode node = new DefaultMutableTreeNode("Personnel Department");
        root.add(node);
        // Присоединяем листья
        for (int i = 0; i < phoneDirectory.length; i++)
        {
            node.add(new DefaultMutableTreeNode(phoneDirectory[i]));
        }
        return new DefaultTreeModel(root);
    }

    /**
     * Специальный редактор узлов дерева.
     */
    private static class MaskTreeEditor extends AbstractCellEditor implements TreeCellEditor
    {
        // Дерево
        private JTree tree;
        // Текстовое поле, применяемое для редактирования
        private JFormattedTextField editor;

        // Конструктор редактора
        MaskTreeEditor(JTree tree)
        {
            this.tree = tree;
            try
            {
                // Создаем форматирующий объект
                MaskFormatter phoneMask = new MaskFormatter("###-##-##");
                editor = new JFormattedTextField(phoneMask);
            }
            catch (ParseException ex)
            {
                CustomTreeEditor.LOG.log(Level.SEVERE, null, ex);
            }
            // Присоединяем к полю слушателя
            editor.addActionListener(new ActionListener()
            {
                @Override
                public void actionPerformed(ActionEvent e)
                {
                    stopCellEditing();
                }
            });
        }

        // Возвращает компонент, используемый как редактор
        @Override
        public Component getTreeCellEditorComponent(
            JTree tree,
            Object value,
            boolean isSelected,
            boolean expanded,
            boolean leaf,
            int row)
        {
            // Устанавливаем новое значение
            editor.setText(value.toString());
            // Возвращаем текстовое поле
            return editor;
        }

        // Возвращает текущее значение в редакторе
        @Override
        public Object getCellEditorValue()
        {
            return editor.getText();
        }

        // Определяет, можно ли проводить редактирование
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