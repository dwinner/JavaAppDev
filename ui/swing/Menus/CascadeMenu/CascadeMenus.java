// �������� ��������� ���� ����� ���������
import java.awt.BorderLayout;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.SwingConstants;

public class CascadeMenus extends JFrame
{
    public CascadeMenus()
    {
        super("Cascade Menus");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ������� ������ �������� ����
        JMenuBar menuBar = new JMenuBar();
        // ������� ���������� ����
        JMenu text = new JMenu("�����");
        // � ��������� ��������� ����
        JMenu style = new JMenu("�����");
        JMenuItem bold = new JMenuItem("������");
        JMenuItem italic = new JMenuItem("������");
        JMenu font = new JMenu("�����");
        JMenuItem arial = new JMenuItem("Arial");
        JMenuItem times = new JMenuItem("Times");
        font.add(arial);
        font.add(times);
        // ��������� ��� � ������ �������
        style.add(bold);
        style.add(italic);
        style.addSeparator();
        style.add(font);
        text.add(style);
        menuBar.add(text);
        // �������� ���� � ����
        setJMenuBar(menuBar);
        // ����������� ����� ���� ������� �� ������ � ����
        getContentPane().add(new JSeparator(SwingConstants.VERTICAL), BorderLayout.WEST);
        // ������� ���� �� �����
        setSize(100, 200);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new CascadeMenus();
    }

}