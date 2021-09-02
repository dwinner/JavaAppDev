// ������������� ����������� ������ ���������
// � ���� �������������� �� �������
import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.HeadlessException;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.*;

public class TreeSelectionModes extends JFrame
{
    // ��������� ���� ��� ����������
    private JTextArea log = new JTextArea(5, 20);
    // ������ ������ ������ � ��������
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
        // ������� ������ ������ ������
        TreeModel model = createTreeModel();
        // ������ � ��������� ������� ���������
        JTree tree1 = new JTree(model);
        tree1.getSelectionModel().setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);
        // ������ � ���������� ������������ �����������
        JTree tree2 = new JTree(model);
        tree2.getSelectionModel().setSelectionMode(TreeSelectionModel.CONTIGUOUS_TREE_SELECTION);
        // ������ ��������� ����� ������� � ��������
        TreeSelectionModel selModel = new DefaultTreeSelectionModel();
        selModel.setSelectionMode(TreeSelectionModel.DISCONTIGUOUS_TREE_SELECTION);
        JTree tree3 = new JTree(model);
        tree3.setSelectionModel(selModel);
        // ������ �� ���������� � ��������� ������
        tree3.addTreeSelectionListener(new SelectionL());
        // ��������� ������� � ������
        JPanel contents = new JPanel(new GridLayout(0, 3));
        contents.add(new JScrollPane(tree1));
        contents.add(new JScrollPane(tree2));
        contents.add(new JScrollPane(tree3));
        getContentPane().add(contents);
        // ��������� ��������� ����
        getContentPane().add(new JScrollPane(log), BorderLayout.SOUTH);
        // ������� ���� �� �����
        setSize(500, 300);
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
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("�������� ����");
        // �������� ����� DefaultMutableTreeNode
        DefaultMutableTreeNode lang = new DefaultMutableTreeNode("�����");
        DefaultMutableTreeNode ide = new DefaultMutableTreeNode("�����");
        root.add(lang);
        root.add(ide);
        // ������������ ������
        for (int i = 0; i < langs.length; i++)
        {
            lang.add(new DefaultMutableTreeNode(langs[i]));
            ide.add(new DefaultMutableTreeNode(ides[i]));
        }
        // ������� � ���������� ����������� ������
        return new DefaultTreeModel(root);
    }

    /**
     * ���� ��������� ������ �� ���������� ���������
     */
    private class SelectionL implements TreeSelectionListener
    {
        @Override
        public void valueChanged(TreeSelectionEvent e)
        {
            // �������� �������� �������, �.�. ������
            JTree tree = (JTree) e.getSource();
            // �����������/��������� ����
            TreePath[] paths = e.getPaths();
            log.append("�������� �����: " + paths.length + "\n");
            // ���������� �������� � �� ������ �����
            TreePath[] selected = tree.getSelectionPaths();
            int[] rows = tree.getSelectionRows();
            // ��������� �������� � ����
            for (int i = 0; i < selected.length; i++)
            {
                log.append("�������: " + selected[i].getLastPathComponent());
                log.append(" �� ������: " + rows[i] + "\n");
            }
            // ������ ���������� � ���� � ������
            TreePath path = selected[0];
            Object[] nodes = path.getPath();
            for (int i = 0; i < nodes.length; i++)
            {
                // ���� ������� �� �����
                DefaultMutableTreeNode node = (DefaultMutableTreeNode) nodes[i];
                log.append("������� ���� " + i + " : " + node.getUserObject() + " ");
            }
            log.append("\n");
        }
    }

    public static void main(String[] args)
    {
        new TreeSelectionModes();
    }
}