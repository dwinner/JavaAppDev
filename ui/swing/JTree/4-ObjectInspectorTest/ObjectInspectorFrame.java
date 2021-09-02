import java.awt.BorderLayout;
import java.awt.HeadlessException;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;

/**
 * �����, ���������� ������ ��������.
 * <p/>
 * @author JavaFx
 */
public class ObjectInspectorFrame extends JFrame
{
    private JTree tree;
    private static final int DEFAULT_WIDTH = 640;
    private static final int DEFAULT_HEIGHT = 480;

    public ObjectInspectorFrame() throws HeadlessException
    {
        setTitle("ObjectInspectorTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);

        // �������� ������ � ���������.

        Variable v = new Variable(getClass(), "this", this);
        ObjectTreeModel model = new ObjectTreeModel();
        model.setRoot(v);

        // ������������ � ����������� ������.

        tree = new JTree(model);
        add(new JScrollPane(tree), BorderLayout.CENTER);
    }
}
