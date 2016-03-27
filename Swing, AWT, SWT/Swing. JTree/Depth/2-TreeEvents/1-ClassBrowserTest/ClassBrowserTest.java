
/**
 * Обработка событий выбора узлов дерева.
 * <p/>
 * @version 1.02 2004-08-21
 * @author Cay Horstmann
 */
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.Enumeration;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

/**
 * Пример обработки событий выбора узлов дерева.
 */
public class ClassBrowserTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame frame = new ClassBrowserTestFrame();
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}

/**
 * Фрейм с деревом, представляющим классы, текстовой областью, в которой отображаются свойства выбранного класса, а
 * также полем редактирования, предназначенным для включения новых классов.
 */
class ClassBrowserTestFrame extends JFrame
{
    private DefaultMutableTreeNode root;
    private DefaultTreeModel model;
    private JTree tree;
    private JTextField textField;
    private JTextArea textArea;
    private static final int DEFAULT_WIDTH = 400;
    private static final int DEFAULT_HEIGHT = 300;

    ClassBrowserTestFrame()
    {
        setTitle("ClassBrowserTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // Корневым узлом дерева классов является класс Object.
        root = new DefaultMutableTreeNode(java.lang.Object.class);
        model = new DefaultTreeModel(root);
        tree = new JTree(model);

        // Включение класса для заполнения дерева данными.
        addClass(getClass());

        // Установка режима выбора.
        tree.addTreeSelectionListener(new TreeSelectionListener()
        {
            @Override
            public void valueChanged(TreeSelectionEvent event)
            {
                // Если пользователь выбрал другой узел, обновление описания.
                TreePath path = tree.getSelectionPath();
                if (path == null)
                {
                    return;
                }
                DefaultMutableTreeNode selectedNode = (DefaultMutableTreeNode) path.getLastPathComponent();
                Class c = (Class) selectedNode.getUserObject();
                String description = getFieldDescription(c);
                textArea.setText(description);
            }
        });
        int mode = TreeSelectionModel.SINGLE_TREE_SELECTION;
        tree.getSelectionModel().setSelectionMode(mode);

        // В текстовой области содержится описание класса.
        textArea = new JTextArea();

        // Включение в состав панели дерева и текстовой области.
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(new JScrollPane(tree));
        panel.add(new JScrollPane(textArea));

        add(panel, BorderLayout.CENTER);

        addTextField();
    }

    /**
     * Включение поля редактирования и кнопки Add.
     */
    public void addTextField()
    {
        JPanel panel = new JPanel();

        ActionListener addListener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                // Добавление класса, имя которого задано в поле редактирования.
                try
                {
                    String text = textField.getText();
                    addClass(Class.forName(text));
                    // Очищение поля редактирования свидетельствует об успешном выполнении
                    textField.setText("");
                }
                catch (ClassNotFoundException e)
                {
                    JOptionPane.showMessageDialog(null, "Class not found");
                }
            }
        };

        // В поле редактирования вводятся новые имена классов.
        textField = new JTextField(20);
        textField.addActionListener(addListener);
        panel.add(textField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(addListener);
        panel.add(addButton);

        add(panel, BorderLayout.SOUTH);
    }

    /**
     * Поиск объектов в дереве.
     * <p/>
     * @param obj Искомый объект
     * <p/>
     * @return Узел, содержащий объект или null, если объект отсутствует в составе дерева
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
     * Добавление нового класса и родительских классов, которые ещё не входят в состав дерева.
     * <p/>
     * @param c Добавляемый класс
     * <p/>
     * @return Новый узел
     */
    public DefaultMutableTreeNode addClass(Class c)
    {
        // Добавление к дереву нового класса.

        // Пропуск типов, не являющихся классами.
        if (c.isInterface() || c.isPrimitive())
        {
            return null;
        }

        // Если класс уже присутствует в дереве, возвращается соответствующий узел.
        DefaultMutableTreeNode node = findUserObject(c);
        if (node != null)
        {
            return node;
        }

        // Класс отстутствует - рекурсивное добавление родительских классов.

        Class<?> s = c.getSuperclass();
        DefaultMutableTreeNode parent = (s == null) ? root : addClass(s);

        // Добавление класса как дочернего.
        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(c);
        model.insertNodeInto(newNode, parent, parent.getChildCount());

        // Визуализация узла.
        TreePath path = new TreePath(model.getPathToRoot(newNode));
        tree.makeVisible(path);

        return newNode;
    }

    /**
     * Возвращает описание полей класса.
     * <p/>
     * @param c Класс, подлежащий описанию
     * <p/>
     * @return Строка, содержащая типы и имена полей
     */
    public static String getFieldDescription(Class<?> c)
    {
        // Используем механизмы отражения.
        StringBuilder r = new StringBuilder();
        Field[] fields = c.getDeclaredFields();
        for (int i = 0; i < fields.length; i++)
        {
            Field f = fields[i];
            if ((f.getModifiers() & Modifier.STATIC) != 0)
            {
                r.append("static ");
            }
            r.append(f.getType().getName());
            r.append(" ");
            r.append(f.getName());
            r.append("\n");
        }
        return r.toString();
    }
}
