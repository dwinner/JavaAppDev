// Использование стандартной модели выделения
// и всех поддерживаемых ею режимов
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;

public class TreeSelectionModes extends JFrame
{
    // Текстовое поле для информации
    private JTextArea log = new JTextArea(5, 20);
    // Листья дерева храним в массивах
    private String[] langs =
    {
        "Java",
        "C++",
        "C#"
    };
    private String[] ides =
    {
        "Visual Studio",
        "Eclipse",
        "NetBeans"
    };

    public TreeSelectionModes() throws HeadlessException
    {
        super("Tree Selection Modes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Создаем модель нашего дерева
        TreeModel model = createTreeModel();
        // Дерево с одиночным режимом выделения
        JTree tree1 = new JTree(model);
        tree1.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        // Дерево с выделением непрерывными интервалами
        JTree tree2 = new JTree(model);
        tree2.getSelectionModel().setSelectionMode(TreeSelectionModel.CONTIGUOUS_TREE_SELECTION);
        // Модель выделения можно хранить и отдельно
        TreeSelectionModel selModel = new DefaultTreeSelectionModel();
        selModel.setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
        JTree tree3 = new JTree(model);
        tree3.setSelectionModel(selModel);
        // Следим за выделением в последнем дереве
        tree3.addTreeSelectionListener(new SelectionL());
        // Размещаем деревья в панели
        JPanel contents = new JPanel(new GridLayout(0, 3));
        contents.add(new JScrollPane(tree1));
        contents.add(new JScrollPane(tree2));
        contents.add(new JScrollPane(tree3));
        getContentPane().add(contents);
        // Добавляем текстовое поле
        getContentPane().add(new JScrollPane(log), BorderLayout.SOUTH);
        // Выводим окно на экран
        setSize(500, 300);
        setVisible(true);
    }

    /**
     * Создание несложной модели дерева
     * <p/>
     * @return Модель дерева
     */
    private TreeModel createTreeModel()
    {
        // Корень нашего дерева
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Создание кода");
        // Основные ветви DefaultMutableTreeNode
        DefaultMutableTreeNode lang = new DefaultMutableTreeNode("Языки");
        DefaultMutableTreeNode ide = new DefaultMutableTreeNode("Среды");
        root.add(lang);
        root.add(ide);
        // Присоединяем листья
        for (int i = 0; i < langs.length; i++)
        {
            lang.add(new DefaultMutableTreeNode(langs[i]));
            ide.add(new DefaultMutableTreeNode(ides[i]));
        }
        // Создаем и возвращаем стандартную модель
        return new DefaultTreeModel(root);
    }

    /**
     * Этот слушатель следит за изменением выделения
     */
    private class SelectionL implements TreeSelectionListener
    {
        @Override
        public void valueChanged(TreeSelectionEvent e)
        {
            // Получаем источник события, т.е. дерево
            JTree tree = (JTree) e.getSource();
            // Добавленные/удаленные пути
            TreePath[] paths = e.getPaths();
            log.append("Изменено путей: " + paths.length + "\n");
            // Выделенные элементы и их номера строк
            TreePath[] selected = tree.getSelectionPaths();
            int[] rows = tree.getSelectionRows();
            // Последние элементы в пути
            for (int i = 0; i < selected.length; i++)
            {
                log.append("Выделен: " + selected[i].getLastPathComponent());
                log.append(" На строке: " + rows[i] + "\n");
            }
            // Полная информация о пути в дереве
            TreePath path = selected[0];
            Object[] nodes = path.getPath();
            for (int i = 0; i < nodes.length; i++)
            {
                // Путь состоит из узлов
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) nodes[i];
                log.append("Отрезок пути " + i + " : " + node.getUserObject() + " ");
            }
            log.append("\n");
        }
    }

    public static void main(String[] args)
    {
        new TreeSelectionModes();
    }
}