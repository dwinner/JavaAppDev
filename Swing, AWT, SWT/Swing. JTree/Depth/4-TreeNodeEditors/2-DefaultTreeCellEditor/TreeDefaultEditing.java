// Стандартные редакторы для деревьев
import javax.swing.*;
import javax.swing.tree.*;

public class TreeDefaultEditing extends JFrame
{
    // Листья дерева храним в массивах
    private String[] basics =
    {
        "Red",
        "Green",
        "Blue"
    };
    private String[] extendeds =
    {
        "Yellow",
        "Deepsky",
        "Pink"
    };

    public TreeDefaultEditing()
    {
        super("Tree default editing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Создаем дерево на основе простой модели
        JTree tree = new JTree(createTreeModel());
        // Включаем редактирование
        tree.setEditable(true);
        // "Настоящий" редактор
        JComboBox<String> combo = new JComboBox<>(new String[]
            {
                "Red", "Green", "Blue"
            });
        DefaultCellEditor editor = new DefaultCellEditor(combo);
        // Специальный редактор для дерева
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        DefaultTreeCellEditor treeEditor = new DefaultTreeCellEditor(tree, renderer, editor);
        // Присоединяем редактор к дереву
        tree.setCellEditor(treeEditor);
        // Выводим окно на экран
        getContentPane().add(new JScrollPane(tree));
        setSize(400, 300);
        setVisible(true);
    }

    // Создание несложной модели дерева
    private TreeModel createTreeModel()
    {
        // Корень нашего дерева
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Colors");
        // Основные ветви
        DefaultMutableTreeNode basic = new DefaultMutableTreeNode("Main");
        DefaultMutableTreeNode extended = new DefaultMutableTreeNode("Extra");
        root.add(basic);
        root.add(extended);
        // Присоединяем листья
        for (int i = 0; i < basics.length; i++)
        {
            basic.add(new DefaultMutableTreeNode(basics[i]));
            extended.add(new DefaultMutableTreeNode(extendeds[i]));
        }
        // Создаем и возвращаем стандартную модель
        return new DefaultTreeModel(root);
    }

    public static void main(String[] args)
    {
        new TreeDefaultEditing();
    }
}