
/**
 * ��������� ������� ������ ����� ������.
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
 * ������ ��������� ������� ������ ����� ������.
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
 * ����� � �������, �������������� ������, ��������� ��������, � ������� ������������ �������� ���������� ������, �
 * ����� ����� ��������������, ��������������� ��� ��������� ����� �������.
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

        // �������� ����� ������ ������� �������� ����� Object.
        root = new DefaultMutableTreeNode(java.lang.Object.class);
        model = new DefaultTreeModel(root);
        tree = new JTree(model);

        // ��������� ������ ��� ���������� ������ �������.
        addClass(getClass());

        // ��������� ������ ������.
        tree.addTreeSelectionListener(new TreeSelectionListener()
        {
            @Override
            public void valueChanged(TreeSelectionEvent event)
            {
                // ���� ������������ ������ ������ ����, ���������� ��������.
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

        // � ��������� ������� ���������� �������� ������.
        textArea = new JTextArea();

        // ��������� � ������ ������ ������ � ��������� �������.
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(1, 2));
        panel.add(new JScrollPane(tree));
        panel.add(new JScrollPane(textArea));

        add(panel, BorderLayout.CENTER);

        addTextField();
    }

    /**
     * ��������� ���� �������������� � ������ Add.
     */
    public void addTextField()
    {
        JPanel panel = new JPanel();

        ActionListener addListener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                // ���������� ������, ��� �������� ������ � ���� ��������������.
                try
                {
                    String text = textField.getText();
                    addClass(Class.forName(text));
                    // �������� ���� �������������� ��������������� �� �������� ����������
                    textField.setText("");
                }
                catch (ClassNotFoundException e)
                {
                    JOptionPane.showMessageDialog(null, "Class not found");
                }
            }
        };

        // � ���� �������������� �������� ����� ����� �������.
        textField = new JTextField(20);
        textField.addActionListener(addListener);
        panel.add(textField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(addListener);
        panel.add(addButton);

        add(panel, BorderLayout.SOUTH);
    }

    /**
     * ����� �������� � ������.
     * <p/>
     * @param obj ������� ������
     * <p/>
     * @return ����, ���������� ������ ��� null, ���� ������ ����������� � ������� ������
     */
    public DefaultMutableTreeNode findUserObject(Object obj)
    {
        // ����� ����, ����������� ���������������� ������.
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
     * ���������� ������ ������ � ������������ �������, ������� ��� �� ������ � ������ ������.
     * <p/>
     * @param c ����������� �����
     * <p/>
     * @return ����� ����
     */
    public DefaultMutableTreeNode addClass(Class c)
    {
        // ���������� � ������ ������ ������.

        // ������� �����, �� ���������� ��������.
        if (c.isInterface() || c.isPrimitive())
        {
            return null;
        }

        // ���� ����� ��� ������������ � ������, ������������ ��������������� ����.
        DefaultMutableTreeNode node = findUserObject(c);
        if (node != null)
        {
            return node;
        }

        // ����� ������������ - ����������� ���������� ������������ �������.

        Class<?> s = c.getSuperclass();
        DefaultMutableTreeNode parent = (s == null) ? root : addClass(s);

        // ���������� ������ ��� ���������.
        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(c);
        model.insertNodeInto(newNode, parent, parent.getChildCount());

        // ������������ ����.
        TreePath path = new TreePath(model.getPathToRoot(newNode));
        tree.makeVisible(path);

        return newNode;
    }

    /**
     * ���������� �������� ����� ������.
     * <p/>
     * @param c �����, ���������� ��������
     * <p/>
     * @return ������, ���������� ���� � ����� �����
     */
    public static String getFieldDescription(Class<?> c)
    {
        // ���������� ��������� ���������.
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
