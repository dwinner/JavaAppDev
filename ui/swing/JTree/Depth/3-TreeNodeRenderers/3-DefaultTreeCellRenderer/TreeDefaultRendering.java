
/**
 * ������������� ������������ ������������ ������������� ������� DefaultTreeCellRenderer.
 */
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;

public class TreeDefaultRendering extends JFrame
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

    public TreeDefaultRendering()
    {
        super("Tree Default Rendering");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ������� ������ �� ������ ������������ ������
        JTree tree = new JTree(createTreeModel());
        // ������� � ����������� ������������ ������
        DefaultTreeCellRenderer renderer = new DefaultTreeCellRenderer();
        renderer.setLeafIcon(new ImageIcon("server_issue.gif"));
        renderer.setClosedIcon(new ImageIcon("ServiceList16x.gif"));
        renderer.setOpenIcon(new ImageIcon("server_ok.gif"));
        renderer.setFont(new Font("Consolas", Font.ITALIC, 12));
        // �������� ��� ������
        tree.setCellRenderer(renderer);
        // ��������� ������ � ������� ���� �� �����
        getContentPane().add(new JScrollPane(tree));
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
        // ������������ ������
        for (int i = 0; i < langs.length; i++)
        {
            lang.add(new DefaultMutableTreeNode(langs[i]));
            ide.add(new DefaultMutableTreeNode(ides[i]));
        }
        // ������� � ���������� ����������� ������
        return new DefaultTreeModel(root);
    }

    public static void main(String[] args)
    {
        new TreeDefaultRendering();
    }
}