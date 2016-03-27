
/**
 * Использование возможностей стандартного отображающего объекта DefaultTreeCellRenderer.
 */
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

public class TreeDefaultRendering extends JFrame
{
    // Листья дерева храним в массивах
    private String[] langs =
    {
        "<html><b>Java</b>",
        "<html><pre>C++</pre>",
        "C#"
    };
    private String[] ides =
    {
        "Visual Studio",
        "<html><i>Eclipse</i>",
        "NetBeans"
    };

    public TreeDefaultRendering()
    {
        super("Tree Default Rendering");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Создаем дерево на основе незатейливой модели
        JTree tree = new JTree(createTreeModel());
        // Создаем и настраиваем отображающий объект
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        renderer.setLeafIcon(new ImageIcon("server_issue.gif"));
        renderer.setClosedIcon(new ImageIcon("ServiceList16x.gif"));
        renderer.setOpenIcon(new ImageIcon("server_ok.gif"));
        renderer.setFont(new Font("Consolas", Font.ITALIC, 12));
        // Передаем его дереву
        tree.setCellRenderer(renderer);
        // Добавляем дерево и выводим окно на экран
        getContentPane().add(new JScrollPane(tree));
        setSize(400, 300);
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
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("<html><font color=\"blue\">Создание кода</font>");
        // Основные ветви
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

    public static void main(String[] args)
    {
        new TreeDefaultRendering();
    }
}