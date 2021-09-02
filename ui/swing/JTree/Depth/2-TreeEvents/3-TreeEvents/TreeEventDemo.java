/*
 * Демонстрация обработки событий дерева.
 * 
 */

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class TreeEventDemo
{
    private JLabel jlab;

    public TreeEventDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Tree Event Demo");

        // Диспетчер компоновки по умолчанию: border layout manager.

        // Установка начальных размеров фрейма.
        jfrm.setSize(200, 200);

        // Завершение программы при закрытии окна пользователя.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание метки, отображающей выбор пользователя.
        jlab = new JLabel();

        // Процесс формирования дерева начинается с определения узлов и связей между ними.

        // Создание корневого узла дерева.
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Food");

        // Создание двух поддеревьев. Одно из них содержит информацию о фруктах,
        // другое - об овощах.

        // Создание корневого узла поддерева Fruit.
        DefaultMutableTreeNode fruit = new DefaultMutableTreeNode("Fruit");
        root.add(fruit); // Добавление узла Fruit к дереву.

        // В состав поддерева Fruit входят два других поддерева: Apples и Pears.

        // Создание поддерева Apples.
        DefaultMutableTreeNode apples = new DefaultMutableTreeNode("Apples");
        fruit.add(apples); // Добавление узла Apples к Fruit.

        // Заполнение поддерева Apples путем добавления к нему узлов, являющихся листьями.
        apples.add(new DefaultMutableTreeNode("Jonathan"));
        apples.add(new DefaultMutableTreeNode("Winesap"));

        // Создание поддерева Pears.
        DefaultMutableTreeNode pears = new DefaultMutableTreeNode("Pears");
        fruit.add(pears); // Добавление узла Pears к Fruit.

        // Заполнение поддерева Pears.
        pears.add(new DefaultMutableTreeNode("Bartlett"));

        // Создание корневого узла поддерева Vegetables.
        DefaultMutableTreeNode veg = new DefaultMutableTreeNode("Vegetables");
        root.add(veg); // Добавление узла Vegetables к дереву.

        // Заполнение поддерева Vegetables.
        veg.add(new DefaultMutableTreeNode("Beans"));
        veg.add(new DefaultMutableTreeNode("Corn"));
        veg.add(new DefaultMutableTreeNode("Potatoes"));
        veg.add(new DefaultMutableTreeNode("Rice"));

        // Создание компонента JTree на основе сформированной ранее структуры.
        JTree jtree = new JTree(root);

        // Разрешение редактирования дерева с тем, чтобы события могли генерироваться.
        jtree.setEditable(true);

        // Установка режима выбора одного узла дерева.
        TreeSelectionModel tsm = jtree.getSelectionModel();
        tsm.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        // Включение дерева в состав панели с прокруткой.
        JScrollPane jscrlp = new JScrollPane(jtree);

        // Обработка событий развёртывания дерева.
        jtree.addTreeExpansionListener(new TreeExpansionListener()
        {
            @Override
            public void treeExpanded(TreeExpansionEvent tse)
            {
                // Получение пути к узлу.
                TreePath tp = tse.getPath();
                // Отображение узла.
                jlab.setText("Expansion: " + tp.getLastPathComponent());
            }

            @Override
            public void treeCollapsed(TreeExpansionEvent tse)
            {
                // Получение пути к узлу.
                TreePath tp = tse.getPath();
                // Отображение узла
                jlab.setText("Collapse: " + tp.getLastPathComponent());
            }
        });

        // Обработка событий выбора.
        jtree.addTreeSelectionListener(new TreeSelectionListener()
        {
            @Override
            public void valueChanged(TreeSelectionEvent tse)
            {
                // Получение пути к узлу.
                TreePath tp = tse.getPath();
                // Отображение выбранного узла
                jlab.setText("Selection event: " + tp.getLastPathComponent());
            }
        });

        // Обработка событий модели дерева. Заметьте, что обработчик связывается с моделью
        jtree.getModel().addTreeModelListener(new TreeModelListener()
        {
            @Override
            public void treeNodesChanged(TreeModelEvent tse)
            {
                // Получение пути к узлу, претерпевшему изменения.
                TreePath tp = tse.getTreePath();
                // Отображение пути.
                jlab.setText("Model change path: " + tp);
            }

            // Реализация остальных методов TreeModelEvent
            // В данном случае методы не выполняют никаких действий.
            @Override
            public void treeNodesInserted(TreeModelEvent tse)
            {
            }

            @Override
            public void treeNodesRemoved(TreeModelEvent tse)
            {
            }

            @Override
            public void treeStructureChanged(TreeModelEvent tse)
            {
            }
        });

        // Включение дерева и метки в панель содержимого.
        jfrm.getContentPane().add(jscrlp, BorderLayout.CENTER);
        jfrm.getContentPane().add(jlab, BorderLayout.SOUTH);

        // Отображение фрейма.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // Фрейм создается в потоке обработки событий.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new TreeEventDemo();
            }
        });
    }
}