
/**
 * Процесс отображения узлов дерева.
 * <p/>
 * @version 1.02 2004-08-21
 * @author Cay Horstmann
 */
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Modifier;
import java.util.Enumeration;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;

/**
 * Программа, иллюстрирующая процесс отображения узлов дерева, представляющих классы и их суперклассы.
 */
public class ClassTree
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new ClassTreeFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Данный фрейм содержит дерево классов, поле редактирования и кнопку для вставки новых классов.
 */
class ClassTreeFrame extends JFrame
{
    private DefaultMutableTreeNode root;
    private DefaultTreeModel model;
    private JTree tree;
    private JTextField textField;
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 300;

    ClassTreeFrame()
    {
        setTitle("ClassTree");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // Корневым узлом дерева классов является узел типа Object.
        root = new DefaultMutableTreeNode(java.lang.Object.class);
        model = new DefaultTreeModel(root);
        tree = new JTree(model);

        // Включение класса для заполнения дерева данными.
        addClass(getClass());

        // Установка пиктограмм узлов.
        ClassNameTreeCellRenderer renderer = new ClassNameTreeCellRenderer();
        renderer.setClosedIcon(new ImageIcon("red-ball.gif"));
        renderer.setOpenIcon(new ImageIcon("yellow-ball.gif"));
        renderer.setLeafIcon(new ImageIcon("blue-ball.gif"));
        tree.setCellRenderer(renderer);

        add(new JScrollPane(tree), BorderLayout.CENTER);

        addTextField();
    }

    /**
     * Добавление поля редактирования и кнопки Add для включения нового класса.
     */
    public void addTextField()
    {
        JPanel panel = new JPanel();

        ActionListener addListener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                // Включение класса, имя которого задано в поле редактирования.
                try
                {
                    String text = textField.getText();
                    addClass(Class.forName(text));
                    // Очищение поля редактирования свидетельствует об удачном выполнении.
                    textField.setText("");
                }
                catch (ClassNotFoundException e)
                {
                    JOptionPane.showMessageDialog(null, "Class not found");
                }
            }
        };

        // В данном поле вводятся имена новых классов.
        textField = new JTextField(20);
        textField.addActionListener(addListener);
        panel.add(textField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(addListener);
        panel.add(addButton);

        add(panel, BorderLayout.SOUTH);
    }

    /**
     * Поиск объекта в дереве.
     * <p/>
     * @param obj Искомый объект
     * <p/>
     * @return Узел, содержащий объект, или null, если объект отсутствует
     */
    public DefaultMutableTreeNode findUserObject(Object obj)
    {
        // Поиск узла, содержащего пользовательский объект.
        Enumeration<?> e = root.breadthFirstEnumeration();
        while (e.hasMoreElements())
        {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
            if (node.getUserObject().equals(obj))
            {
                return node;
            }
        }
        return null;
    }

    /**
     * Добавление нового класса и родительских классов, еще не являющихся частью дерева.
     * <p/>
     * @param c Добавляемый класс
     * <p/>
     * @return Добавленный узел
     */
    public DefaultMutableTreeNode addClass(Class c)
    {
        // Включение нового класса в состав дерева.

        // Пропуск типов, не являющихся классами.
        if (c.isInterface() || c.isPrimitive())
        {
            return null;
        }

        // Если класс уже присутствует в составе дерева, возвращается соответствующий узел.
        DefaultMutableTreeNode node = findUserObject(c);
        if (node != null)
        {
            return node;
        }

        // Класс отсутствует - рекурсивное включение родительских классов.

        Class<?> s = c.getSuperclass();
        DefaultMutableTreeNode parent = (s == null) ? root : addClass(s);

        // Добавление класса в качестве дочернего.
        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(c);
        model.insertNodeInto(newNode, parent, parent.getChildCount());

        // Визуализация узла.
        TreePath path = new TreePath(model.getPathToRoot(newNode));
        tree.makeVisible(path);

        return newNode;
    }
}

/**
 * Данный класс отображает имя класса либо обычным шрифтом, либо курсивом. Курсивом выводятся абстрактные классы.
 */
class ClassNameTreeCellRenderer extends DefaultTreeCellRenderer
{
    private Font plainFont = null;
    private Font italicFont = null;

    @Override
    public Component getTreeCellRendererComponent(JTree tree,
        Object value,
        boolean selected,
        boolean expanded,
        boolean leaf,
        int row,
        boolean hasFocus)
    {
        super.getTreeCellRendererComponent(tree, value, selected, expanded, leaf, row, hasFocus);
        // Получение пользовательского объекта.
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        Class<?> c = (Class) node.getUserObject();

        // Курсив формируется из обычного шрифта.
        if (plainFont == null)
        {
            plainFont = getFont();
            // Объект отображения узла иногда вызывается при отсутствующем шрифте.
            if (plainFont != null)
            {
                italicFont = plainFont.deriveFont(Font.ITALIC);
            }
        }

        // Если класс абстрактный, устанавливается курсив,
        // в противном случае - обычный шрифт.
        if ((c.getModifiers() & Modifier.ABSTRACT) == 0)
        {
            setFont(plainFont);
        }
        else
        {
            setFont(italicFont);
        }
        return this;
    }
}
