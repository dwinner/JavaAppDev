
/**
 * ������� ����������� ����� ������.
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
 * ���������, �������������� ������� ����������� ����� ������, �������������� ������ � �� �����������.
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
 * ������ ����� �������� ������ �������, ���� �������������� � ������ ��� ������� ����� �������.
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

        // �������� ����� ������ ������� �������� ���� ���� Object.
        root = new DefaultMutableTreeNode(java.lang.Object.class);
        model = new DefaultTreeModel(root);
        tree = new JTree(model);

        // ��������� ������ ��� ���������� ������ �������.
        addClass(getClass());

        // ��������� ���������� �����.
        ClassNameTreeCellRenderer renderer = new ClassNameTreeCellRenderer();
        renderer.setClosedIcon(new ImageIcon("red-ball.gif"));
        renderer.setOpenIcon(new ImageIcon("yellow-ball.gif"));
        renderer.setLeafIcon(new ImageIcon("blue-ball.gif"));
        tree.setCellRenderer(renderer);

        add(new JScrollPane(tree), BorderLayout.CENTER);

        addTextField();
    }

    /**
     * ���������� ���� �������������� � ������ Add ��� ��������� ������ ������.
     */
    public void addTextField()
    {
        JPanel panel = new JPanel();

        ActionListener addListener = new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent event)
            {
                // ��������� ������, ��� �������� ������ � ���� ��������������.
                try
                {
                    String text = textField.getText();
                    addClass(Class.forName(text));
                    // �������� ���� �������������� ��������������� �� ������� ����������.
                    textField.setText("");
                }
                catch (ClassNotFoundException e)
                {
                    JOptionPane.showMessageDialog(null, "Class not found");
                }
            }
        };

        // � ������ ���� �������� ����� ����� �������.
        textField = new JTextField(20);
        textField.addActionListener(addListener);
        panel.add(textField);

        JButton addButton = new JButton("Add");
        addButton.addActionListener(addListener);
        panel.add(addButton);

        add(panel, BorderLayout.SOUTH);
    }

    /**
     * ����� ������� � ������.
     * <p/>
     * @param obj ������� ������
     * <p/>
     * @return ����, ���������� ������, ��� null, ���� ������ �����������
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
     * ���������� ������ ������ � ������������ �������, ��� �� ���������� ������ ������.
     * <p/>
     * @param c ����������� �����
     * <p/>
     * @return ����������� ����
     */
    public DefaultMutableTreeNode addClass(Class c)
    {
        // ��������� ������ ������ � ������ ������.

        // ������� �����, �� ���������� ��������.
        if (c.isInterface() || c.isPrimitive())
        {
            return null;
        }

        // ���� ����� ��� ������������ � ������� ������, ������������ ��������������� ����.
        DefaultMutableTreeNode node = findUserObject(c);
        if (node != null)
        {
            return node;
        }

        // ����� ����������� - ����������� ��������� ������������ �������.

        Class<?> s = c.getSuperclass();
        DefaultMutableTreeNode parent = (s == null) ? root : addClass(s);

        // ���������� ������ � �������� ���������.
        DefaultMutableTreeNode newNode = new DefaultMutableTreeNode(c);
        model.insertNodeInto(newNode, parent, parent.getChildCount());

        // ������������ ����.
        TreePath path = new TreePath(model.getPathToRoot(newNode));
        tree.makeVisible(path);

        return newNode;
    }
}

/**
 * ������ ����� ���������� ��� ������ ���� ������� �������, ���� ��������. �������� ��������� ����������� ������.
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
        // ��������� ����������������� �������.
        DefaultMutableTreeNode node = (DefaultMutableTreeNode) value;
        Class<?> c = (Class) node.getUserObject();

        // ������ ����������� �� �������� ������.
        if (plainFont == null)
        {
            plainFont = getFont();
            // ������ ����������� ���� ������ ���������� ��� ������������� ������.
            if (plainFont != null)
            {
                italicFont = plainFont.deriveFont(Font.ITALIC);
            }
        }

        // ���� ����� �����������, ��������������� ������,
        // � ��������� ������ - ������� �����.
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
