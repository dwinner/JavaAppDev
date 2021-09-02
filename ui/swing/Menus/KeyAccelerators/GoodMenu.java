// ������������ ���������� � ��������� ��� ���� Swing
import java.awt.event.KeyEvent;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;

public class GoodMenu extends JFrame
{
    public GoodMenu()
    {
        super("Good Menu");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ������� ������ �������� ����
        JMenuBar menuBar = new JMenuBar();
        // ��������� ������ ����� ������������� ���������� ����
        menuBar.add(createFileMenu());
        menuBar.add(createEditMenu());
        // �������� ���� � ���� ����
        setJMenuBar(menuBar);
        // ������� ���� �� �����
        setSize(100, 200);
        setVisible(true);
    }

    /**
     * �������� ���� "����"
     * <p/>
     * @return ������ �� ����
     */
    private JMenu createFileMenu()
    {
        // ���������� ����
        JMenu file = new JMenu("File");
        file.setMnemonic('F');
        // ����� ���� "�������"
        JMenuItem open = new JMenuItem("Open");
        open.setMnemonic('O');
        // ������������� ������� �������� �������
        open.setAccelerator(KeyStroke.getKeyStroke('O', KeyEvent.CTRL_MASK));
        // ����� ���� "���������"
        JMenuItem save = new JMenuItem("Save");
        save.setMnemonic('S');
        save.setAccelerator(KeyStroke.getKeyStroke('S', KeyEvent.CTRL_MASK));
        // ��������� ��� � ����
        file.add(open);
        file.add(save);
        return file;
    }

    private JMenu createEditMenu()
    {
        // ���������� ����
        JMenu edit = new JMenu("Edit");
        edit.setMnemonic('E');
        // ����� ���� "��������"
        JMenuItem cut = new JMenuItem("Cut");
        cut.setMnemonic('C');
        // cut.setAccelerator(KeyStroke.getKeyStroke("Ctrl C"));
        cut.setAccelerator(KeyStroke.getKeyStroke('C', KeyEvent.CTRL_MASK));
        // ����� ���� "����������"
        JMenuItem copy = new JMenuItem("Copy");
        copy.setMnemonic('K');
        copy.setAccelerator(KeyStroke.getKeyStroke('C', KeyEvent.CTRL_MASK | KeyEvent.ALT_MASK));
        // ������
        edit.add(cut);
        edit.add(copy);
        return edit;
    }

    public static void main(String[] args)
    {
        new GoodMenu();
    }

}