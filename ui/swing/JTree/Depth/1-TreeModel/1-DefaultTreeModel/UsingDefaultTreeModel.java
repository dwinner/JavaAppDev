/*
 * ������������� ����������� ������ ������ � ����� DefaultMutableTreeNode
 */

import java.awt.GridLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

public class UsingDefaultTreeModel extends JFrame
{
    // ��� �������� ������ ����� ������� � ��������
    private String[] drinks =
    {
        "�������",
        "���",
        "����"
    };
    private String[] fruits =
    {
        "������",
        "���������",
        "������"
    };

    public UsingDefaultTreeModel() throws HeadlessException
    {
        super("Default Tree Model");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ������� ���� ����������� ���������
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("������ ������");
        // �������� �����
        DefaultMutableTreeNode drink = new DefaultMutableTreeNode("�������");
        DefaultMutableTreeNode fruit = new DefaultMutableTreeNode("������");
        // ��������� �����
        root.add(drink);
        root.add(fruit);
        // ����������� �����������
        root.add(new DefaultMutableTreeNode("������", true));
        // ��������� ������
        for (int i = 0; i < drinks.length; i++)
        {
            drink.add(new DefaultMutableTreeNode(drinks[i], false));
        }
        for (int i = 0; i < fruits.length; i++)
        {
            fruit.add(new DefaultMutableTreeNode(fruits[i], false));
        }
        // ������� ����������� ������ � ������
        DefaultTreeModel dtm1 = new DefaultTreeModel(root, true);
        JTree tree1 = new JTree(dtm1);
        // ������ ����� �������, ������� � ������ ����
        DefaultTreeModel dtm2 = new DefaultTreeModel(drink);
        JTree tree2 = new JTree(dtm2);
        // ��������� ������ � ���� � ���������� ���
        JPanel contents = new JPanel(new GridLayout(0, 2));
        contents.add(new JScrollPane(tree1));
        contents.add(new JScrollPane(tree2));
        setContentPane(contents);
        setSize(200, 300);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new UsingDefaultTreeModel();
    }
}