/*
 * ������������ ��������� ������� ������.
 * 
 */

import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;
import javax.swing.tree.TreeSelectionModel;

public class TreeEventDemo
{
    private JLabel jlab;

    public TreeEventDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("Tree Event Demo");

        // ��������� ���������� �� ���������: border layout manager.

        // ��������� ��������� �������� ������.
        jfrm.setSize(200, 200);

        // ���������� ��������� ��� �������� ���� ������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� �����, ������������ ����� ������������.
        jlab = new JLabel();

        // ������� ������������ ������ ���������� � ����������� ����� � ������ ����� ����.

        // �������� ��������� ���� ������.
        DefaultMutableTreeNode root = new DefaultMutableTreeNode("Food");

        // �������� ���� �����������. ���� �� ��� �������� ���������� � �������,
        // ������ - �� ������.

        // �������� ��������� ���� ��������� Fruit.
        DefaultMutableTreeNode fruit = new DefaultMutableTreeNode("Fruit");
        root.add(fruit); // ���������� ���� Fruit � ������.

        // � ������ ��������� Fruit ������ ��� ������ ���������: Apples � Pears.

        // �������� ��������� Apples.
        DefaultMutableTreeNode apples = new DefaultMutableTreeNode("Apples");
        fruit.add(apples); // ���������� ���� Apples � Fruit.

        // ���������� ��������� Apples ����� ���������� � ���� �����, ���������� ��������.
        apples.add(new DefaultMutableTreeNode("Jonathan"));
        apples.add(new DefaultMutableTreeNode("Winesap"));

        // �������� ��������� Pears.
        DefaultMutableTreeNode pears = new DefaultMutableTreeNode("Pears");
        fruit.add(pears); // ���������� ���� Pears � Fruit.

        // ���������� ��������� Pears.
        pears.add(new DefaultMutableTreeNode("Bartlett"));

        // �������� ��������� ���� ��������� Vegetables.
        DefaultMutableTreeNode veg = new DefaultMutableTreeNode("Vegetables");
        root.add(veg); // ���������� ���� Vegetables � ������.

        // ���������� ��������� Vegetables.
        veg.add(new DefaultMutableTreeNode("Beans"));
        veg.add(new DefaultMutableTreeNode("Corn"));
        veg.add(new DefaultMutableTreeNode("Potatoes"));
        veg.add(new DefaultMutableTreeNode("Rice"));

        // �������� ���������� JTree �� ������ �������������� ����� ���������.
        JTree jtree = new JTree(root);

        // ���������� �������������� ������ � ���, ����� ������� ����� ��������������.
        jtree.setEditable(true);

        // ��������� ������ ������ ������ ���� ������.
        TreeSelectionModel tsm = jtree.getSelectionModel();
        tsm.setSelectionMode(TreeSelectionModel.SINGLE_TREE_SELECTION);

        // ��������� ������ � ������ ������ � ����������.
        JScrollPane jscrlp = new JScrollPane(jtree);

        // ��������� ������� ������������ ������.
        jtree.addTreeExpansionListener(new TreeExpansionListener()
        {
            @Override
            public void treeExpanded(TreeExpansionEvent tse)
            {
                // ��������� ���� � ����.
                TreePath tp = tse.getPath();
                // ����������� ����.
                jlab.setText("Expansion: " + tp.getLastPathComponent());
            }

            @Override
            public void treeCollapsed(TreeExpansionEvent tse)
            {
                // ��������� ���� � ����.
                TreePath tp = tse.getPath();
                // ����������� ����
                jlab.setText("Collapse: " + tp.getLastPathComponent());
            }
        });

        // ��������� ������� ������.
        jtree.addTreeSelectionListener(new TreeSelectionListener()
        {
            @Override
            public void valueChanged(TreeSelectionEvent tse)
            {
                // ��������� ���� � ����.
                TreePath tp = tse.getPath();
                // ����������� ���������� ����
                jlab.setText("Selection event: " + tp.getLastPathComponent());
            }
        });

        // ��������� ������� ������ ������. ��������, ��� ���������� ����������� � �������
        jtree.getModel().addTreeModelListener(new TreeModelListener()
        {
            @Override
            public void treeNodesChanged(TreeModelEvent tse)
            {
                // ��������� ���� � ����, ������������� ���������.
                TreePath tp = tse.getTreePath();
                // ����������� ����.
                jlab.setText("Model change path: " + tp);
            }

            // ���������� ��������� ������� TreeModelEvent
            // � ������ ������ ������ �� ��������� ������� ��������.
            @Override
            public void treeNodesInserted(TreeModelEvent tse)
            {
            }

            @Override
            public void treeNodesRemoved(TreeModelEvent tse)
            {
            }

            @Override
            public void treeStructureChanged(TreeModelEvent tse)
            {
            }
        });

        // ��������� ������ � ����� � ������ �����������.
        jfrm.getContentPane().add(jscrlp, BorderLayout.CENTER);
        jfrm.getContentPane().add(jlab, BorderLayout.SOUTH);

        // ����������� ������.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // ����� ��������� � ������ ��������� �������.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new TreeEventDemo();
            }
        });
    }
}