import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import java.awt.*;

/**
 * Отображение простой древовидной структуры.
 * @version 1.02 2007-08-01
 * @author Cay Horstmann
 */
public class SimpleTree
{
    public static void main(String... args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new SimpleTreeFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Этот фрейм содержит простое дерево, которое отображает
 * созданную вручную модель.
 */
class SimpleTreeFrame extends JFrame
{
    private static final int DEFAULT_WIDTH = 300;
    private static final int DEFAULT_HEIGHT = 200;

    SimpleTreeFrame()
    {
        setTitle("SimpleTree");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // Установка данных для модели дерева.

        DefaultMutableTreeNode root = new DefaultMutableTreeNode("World");
        DefaultMutableTreeNode country = new DefaultMutableTreeNode("USA");
        root.add(country);
        DefaultMutableTreeNode state = new DefaultMutableTreeNode("California");
        country.add(state);
        DefaultMutableTreeNode city = new DefaultMutableTreeNode("San Jose");
        state.add(city);
        state = new DefaultMutableTreeNode("Michigan");
        country.add(state);
        city = new DefaultMutableTreeNode("Ann Arbor");
        state.add(city);
        country = new DefaultMutableTreeNode("Germany");
        root.add(country);
        state = new DefaultMutableTreeNode("Schleswig-Holsten");
        country.add(state);
        city = new DefaultMutableTreeNode("Kiel");
        state.add(city);

        // Формирование дерева и включение его
        // в состав панели с возможностью прокрутки.
        JTree tree = new JTree(root);
        add(new JScrollPane(tree));
    }
}