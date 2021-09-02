
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.TreePath;

public class JTreeEvents extends JFrame
{
    private JTree tree;
    private JTextField jtf;

    public JTreeEvents()
    {
        // �������� ������ �����������
        Container contentPane = getContentPane();

        // ���������� �������� ����������
        contentPane.setLayout(new BorderLayout());

        // ������� �������� ���� ������
        DefaultMutableTreeNode top = new DefaultMutableTreeNode("Options");

        // ������� ��������� "A"
        DefaultMutableTreeNode a = new DefaultMutableTreeNode("A");
        top.add(a);
        DefaultMutableTreeNode a1 = new DefaultMutableTreeNode("A1");
        a.add(a1);
        DefaultMutableTreeNode a2 = new DefaultMutableTreeNode("A2");
        a.add(a2);

        // ������� ��������� "B"
        DefaultMutableTreeNode b = new DefaultMutableTreeNode("B");
        top.add(b);
        DefaultMutableTreeNode b1 = new DefaultMutableTreeNode("B1");
        b.add(b1);
        DefaultMutableTreeNode b2 = new DefaultMutableTreeNode("B2");
        b.add(b2);
        DefaultMutableTreeNode b3 = new DefaultMutableTreeNode("B3");
        b.add(b3);

        // ������� ������
        tree = new JTree(top);

        // �������� ������ � ������ ���������
        int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
        int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
        JScrollPane jsp = new JScrollPane(tree, v, h);

        // �������� ������ ��������� � ������ ����������
        contentPane.add(jsp, BorderLayout.CENTER);

        // �������� ��������� ���� � �������
        jtf = new JTextField("", 20);
        contentPane.add(jtf, BorderLayout.SOUTH);

        // ��������� ���������� ����� ��� ��������� ������� ����
        tree.addMouseListener(new MouseAdapter()
        {
            @Override
            public void mouseClicked(MouseEvent me)
            {
                doMouseClicked(me);
            }
        });
    }

    void doMouseClicked(MouseEvent me)
    {
        TreePath tp = tree.getPathForLocation(me.getX(), me.getY());
        if (tp != null)
        {
            jtf.setText(tp.toString());
        }
        else
        {
            jtf.setText("");
        }
    }

    public static void main(String... args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                JFrame theFrame = new JTreeEvents();
                theFrame.setSize(640, 480);
                theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                theFrame.setVisible(true);
            }
        });
    }
}