// ����������� ������ ���� JMenuBar
import javax.swing.*;

public class TrickyMenuBar extends JFrame
{    
    public TrickyMenuBar()
    {
        super("Tricky menu bar");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ������� ������ �������� ����
        JMenuBar menuBar = new JMenuBar();
        // ��������� � ��� ���������� ����
        menuBar.add(new JMenu("Main"));
        menuBar.add(new JMenu("������"));
        // �� �����, ��� ������������ ������� ������������,
        // ��� ��� ����������� ������ �������
        menuBar.add(Box.createHorizontalGlue());
        // ������ �������� � ������ ���� �� ���������� ����,
        // � ������� �� �������
        JLabel icon = new JLabel(new ImageIcon("load.gif"));
        icon.setBorder(BorderFactory.createLoweredBevelBorder());
        menuBar.add(icon);
        // �������� ���� � ���� ����
        setJMenuBar(menuBar);
        // ������� ���� ���� �� �����
        setSize(100, 200);
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new TrickyMenuBar();
    }

}