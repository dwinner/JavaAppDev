import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Enumeration;

/**
 * Данный фрейм содержит дерево классов, поле редактирования
 * и кнопку для вставки новых классов.
 */
public class ClassTreeFrame extends JFrame
{
    private DefaultMutableTreeNode root;
    private DefaultTreeModel model;
    private JTree tree;
    private JTextField textField;
    private JTextArea textArea;

    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 300;

    public ClassTreeFrame() throws HeadlessException
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

        // Установка режима выбора.
        tree.addTreeSelectionListener(new TreeSelectionListener()
        {
            @Override
            public void valueChanged(TreeSelectionEvent tsEvent)
            {
                // Пользователь выбирает другой режим - обновляем описание.
                TreePath path = tree.getSelectionPath();
                if (path == null)
                    return;
                DefaultMutableTreeNode selectedNode =
                    (DefaultMutableTreeNode) path.getLastPathComponent();
                Class<?> c = (Class<?>) selectedNode.getUserObject();
                String description = getFieldDescription(c);
                textArea.setText(description);
            }
        });
        int mode = TreeSelectionModel.SINGLE_TREE_SELECTION;
        tree.getSelectionModel().setSelectionMode(mode);

        // Эта область текста содержит описание класса.
        textArea = new JTextArea();

        // Добавление дерева и текстовой области.
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(new JScrollPane(tree));
        panel.add(new JScrollPane(textArea));

        add(panel, BorderLayout.CENTER);

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
            public void actionPerformed(ActionEvent actionEvent)
            {
                // Включение класса, имя которого задано в поле редактирования.
                try
                {
                    String text = textField.getText();
                    addClass(Class.forName(text));
                    // Очищение поля редактирования свидетельствует об
                    // удачном выполнении.
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
     * @param obj Искомый объект
     * @return Узел, содержащий объект, или null, если объект отсутствует
     */
    @SuppressWarnings("unchecked")
    public DefaultMutableTreeNode findUserObject(Object obj)
    {
        // Поиск узла, содержащего пользовательский объект.
        Enumeration e = root.breadthFirstEnumeration();
        while (e.hasMoreElements())
        {
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) e.nextElement();
            if (node.getUserObject().equals(obj))
                return node;
        }
        return null;
    }

    /**
     * Добавление нового класса и родительских классов, еще не являющихся частью дерева.
     * @param c Добавляемый класс
     * @return Добавленный узел
     */
    public DefaultMutableTreeNode addClass(Class<?> c)
    {
        // Включение нового класса в состав дерева.

        // Пропуск типов, не являющихся классами.
        if (c.isInterface() || c.isPrimitive())
            return null;

        // Если класс уже присутствует в составе дерева,
        // возвращается соответствующий узел.
        DefaultMutableTreeNode node = findUserObject(c);
        if (node != null)
            return node;

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

    /**
     * Возвращает описание полей класса.
     * @param c Описываемый класс.
     * @return Строка, содержащая все типы полей и имена
     */
    public static String getFieldDescription(Class<?> c)
    {
        // Используем рефлексию для поиска типов и имен полей.
        StringBuilder r = new StringBuilder();
        Field[] fields = c.getDeclaredFields();
        for (int i = 0; i < fields.length; i++)
        {
            Field f = fields[i];
            if ((f.getModifiers() & Modifier.STATIC) != 0)
                r.append("static ");
            r.append(f.getType().getName());
            r.append(" ");
            r.append(f.getName());
            r.append("\n");
        }
        return r.toString();
    }
}
