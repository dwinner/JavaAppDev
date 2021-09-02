// ����������� ��������� ��� ��������
import javax.swing.*;
import javax.swing.tree.*;

public class TreeDefaultEditing extends JFrame
{
    // ������ ������ ������ � ��������
    private String[] basics =
    {
        "Red",
        "Green",
        "Blue"
    };
    private String[] extendeds =
    {
        "Yellow",
        "Deepsky",
        "Pink"
    };

    public TreeDefaultEditing()
    {
        super("Tree default editing");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ������� ������ �� ������ ������� ������
        JTree tree = new JTree(createTreeModel());
        // �������� ��������������
        tree.setEditable(true);
        // "���������" ��������
        JComboBox<String> combo = new JComboBox<>(new String[]
            {
                "Red", "Green", "Blue"
            });
        DefaultCellEditor editor = new DefaultCellEditor(combo);
        // ����������� �������� ��� ������
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        DefaultTreeCellEditor treeEditor = new DefaultTreeCellEditor(tree, renderer, editor);
        // ������������ �������� � ������
        tree.setCellEditor(treeEditor);
        // ������� ���� �� �����
        getContentPane().add(new JScrollPane(tree));
        setSize(400, 300);
        setVisible(true);
    }

    // �������� ��������� ������ ������
    private TreeModel createTreeModel()
    {
        // ������ ������ ������
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Colors");
        // �������� �����
        DefaultMutableTreeNode basic = new DefaultMutableTreeNode("Main");
        DefaultMutableTreeNode extended = new DefaultMutableTreeNode("Extra");
        root.add(basic);
        root.add(extended);
        // ������������ ������
        for (int i = 0; i < basics.length; i++)
        {
            basic.add(new DefaultMutableTreeNode(basics[i]));
            extended.add(new DefaultMutableTreeNode(extendeds[i]));
        }
        // ������� � ���������� ����������� ������
        return new DefaultTreeModel(root);
    }

    public static void main(String[] args)
    {
        new TreeDefaultEditing();
    }
}