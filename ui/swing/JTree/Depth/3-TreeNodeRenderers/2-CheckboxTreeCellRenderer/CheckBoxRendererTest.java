/*
 * ������, ��������� ���������� � �������� ����� ������.
 */

import java.awt.Component;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.*;

class CheckBoxTree extends JTree
{
    // ����������� ������ ��� ����������� �����
    private DefaultTreeCellRenderer defRenderer = new DefaultTreeCellRenderer();

    // ����������� �� ������ ������
    CheckBoxTree(TreeModel model)
    {
        super(model);
        // ������ ����������� ������������ ������
        setCellRenderer(new CheckBoxRenderer());
        // ������ �� �������� ����
        addMouseListener(new MouseL());
    }

    private class CheckBoxRenderer extends JCheckBox implements TreeCellRenderer
    {
        CheckBoxRenderer()
        {
            // ������ ������ ����������
            setOpaque(false);
        }

        /**
         * ������ ����� ������ ������� ��������� ��� ����
         * <p/>
         * @param tree     ������ ��������� ������� �����������
         * @param value    ��������� ��� �����������
         * @param selected ���� ������
         * @param expanded ���� ���������
         * @param leaf     ���� �����
         * @param row      ������� ����������� ����
         * @param hasFocus ���� ������
         * <p/>
         * @return ��������� ������������� ������� ����
         */
        @Override
        public Component getTreeCellRendererComponent(
            JTree tree,
            Object value,
            boolean selected,
            boolean expanded,
            boolean leaf,
            int row,
            boolean hasFocus)
        {
            // ���������, ��� ������������ ����������� ������
            if (!(value instanceof DefaultMutableTreeNode))
            {
                // ���� ���, �� ���������� ����������� ������ �����������
                return defRenderer.getTreeCellRendererComponent(
                    tree,
                    value,
                    leaf,
                    expanded,
                    leaf,
                    row,
                    hasFocus);
            }
            Object data = ((DefaultMutableTreeNode) value).getUserObject();
            // ���������, �������� �� ��� ��� ������ ����
            if (data instanceof CheckBoxListElement)
            {
                CheckBoxListElement element = (CheckBoxListElement) data;
                // ����������� ������
                setSelected(element.isSelected());
                setText(element.getText());
                return this;
            }
            else
            {  // ����� ����������� ����������� ������
                return defRenderer.getTreeCellRendererComponent(
                    tree,
                    value,
                    leaf,
                    expanded,
                    leaf,
                    row,
                    hasFocus);
            }
        }
    }

    private class MouseL extends MouseAdapter
    {
        @Override
        public void mousePressed(MouseEvent e)
        {
            // �������� ���� � ����
            TreePath path = CheckBoxTree.this.getClosestPathForLocation(e.getX(), e.getY());
            if (path == null)
            {
                return;
            }
            // ���������, �������� �� ��� ������ ����
            DefaultMutableTreeNode node = (DefaultMutableTreeNode) path.getLastPathComponent();
            Object data = node.getUserObject();
            if (data instanceof CheckBoxListElement)
            {
                // ������ ��������� ������
                CheckBoxListElement element = (CheckBoxListElement) data;
                element.setSelected(!element.isSelected());
                repaint();
            }
        }
    }
}

class CheckBoxListElement
{
    private String text;
    private boolean selected;

    CheckBoxListElement(String text, boolean selected)
    {
        this.text = text;
        this.selected = selected;
    }

    CheckBoxListElement(String text)
    {
        this(text, false);
    }

    CheckBoxListElement()
    {
        this(null, false);
    }

    public boolean isSelected()
    {
        return selected;
    }

    public void setSelected(boolean selected)
    {
        this.selected = selected;
    }

    public String getText()
    {
        return text;
    }

    public void setText(String text)
    {
        this.text = text;
    }
}

public class CheckBoxRendererTest extends JFrame
{
    // ������ ������ ������ � ��������
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

    public CheckBoxRendererTest()
    {
        super("Test CheckBox-Tree");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ������� ������ � ������
        TreeModel model = createTreeModel();
        CheckBoxTree tree = new CheckBoxTree(model);
        // ��������� ������ � ����
        getContentPane().add(new JScrollPane(tree));
        // ������� ���� �� �����
        setSize(400, 300);
        setVisible(true);
    }

    /**
     * �������� ��������� ������ ������
     * <p/>
     * @return ������ ������
     */
    private TreeModel createTreeModel()
    {
        // ������ ������ ������
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("<html><font color=\"blue\">�������� ����</font>");
        // �������� �����
        DefaultMutableTreeNode lang = new DefaultMutableTreeNode("�����");
        DefaultMutableTreeNode ide = new DefaultMutableTreeNode("�����");
        root.add(lang);
        root.add(ide);
        // ������������ ������ � ������� ��� �������
        for (int i = 0; i < langs.length; i++)
        {
            lang.add(new DefaultMutableTreeNode(new CheckBoxListElement(langs[i])));
            ide.add(new DefaultMutableTreeNode(new CheckBoxListElement(ides[i])));
        }
        // ������� � ���������� ����������� ������
        return new DefaultTreeModel(root);
    }

    public static void main(String[] args)
    {
        new CheckBoxRendererTest();
    }
}