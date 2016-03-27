/*
 * Использование стандартной модели дерева и узлов DefaultMutableTreeNode
 */

import java.awt.GridLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class UsingDefaultTreeModel extends JFrame
{
    // Для удобства листья будем хранить в массивах
    private String[] drinks =
    {
        "Коктейл",
        "Сок",
        "Морс"
    };
    private String[] fruits =
    {
        "Яблоки",
        "Апельсины",
        "Бананы"
    };

    public UsingDefaultTreeModel() throws HeadlessException
    {
        super("Default Tree Model");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создаем нашу древовидную структуру
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Корень дерева");
        // Основные ветви
        DefaultMutableTreeNode drink = new DefaultMutableTreeNode("Напитки");
        DefaultMutableTreeNode fruit = new DefaultMutableTreeNode("Фрукты");
        // Добавляем ветви
        root.add(drink);
        root.add(fruit);
        // Специальный конструктор
        root.add(new DefaultMutableTreeNode("Десерт", true));
        // Добавляем листья
        for (int i = 0; i < drinks.length; i++)
        {
            drink.add(new DefaultMutableTreeNode(drinks[i], false));
        }
        for (int i = 0; i < fruits.length; i++)
        {
            fruit.add(new DefaultMutableTreeNode(fruits[i], false));
        }
        // Создаем стандартную модель и дерево
        DefaultTreeModel dtm1 = new DefaultTreeModel(root, true);
        JTree tree1 = new JTree(dtm1);
        // Модель можно создать, начиная с любого узла
        DefaultTreeModel dtm2 = new DefaultTreeModel(drink);
        JTree tree2 = new JTree(dtm2);
        // Добавляем дерево в окно и показываем его
        JPanel contents = new JPanel(new GridLayout(0, 2));
        contents.add(new JScrollPane(tree1));
        contents.add(new JScrollPane(tree2));
        setContentPane(contents);
        setSize(200, 300);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new UsingDefaultTreeModel();
    }
}