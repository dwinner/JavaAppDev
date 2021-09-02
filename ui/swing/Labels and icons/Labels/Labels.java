// ��������� ����������� ��������
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

public class Labels extends JFrame implements SwingConstants
{
    public Labels()
    {
        super("Labels");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ����� ������� �������
        JPanel contents = new JPanel();
        JLabel l1 = new JLabel("���� ���");
        JTextField name = new JTextField(20);
        contents.add(l1);
        contents.add(name);
        // ������� �� �������
        JLabel l2 = new JLabel(new ImageIcon("help.gif"));
        adjustLabel(l2);
        l2.setHorizontalAlignment(LEFT);
        contents.add(l2);
        // ������� � ������������� �������������
        JLabel l3 = new JLabel("����� � ������", new ImageIcon("about.gif"), RIGHT);
        adjustLabel(l3);
        l3.setVerticalTextPosition(BOTTOM);
        l3.setHorizontalTextPosition(LEFT);
        contents.add(l3);
        // ����� ���� �� �����
        setContentPane(contents);
        setSize(320, 300);
        setVisible(true);
    }

    // ����� ���������� ����������� ��������� �������
    private void adjustLabel(JLabel l)
    {
        l.setOpaque(true);
        l.setBackground(Color.WHITE);
        l.setPreferredSize(new Dimension(250, 100));
    }

    public static void main(String[] args)
    {
        new Labels();
    }

}