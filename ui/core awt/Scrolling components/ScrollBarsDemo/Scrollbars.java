// ������ ��������� JScrollBar
import java.awt.BorderLayout;
import javax.swing.*;

public class Scrollbars extends JFrame
{
    public Scrollbars()
    {
        super("Scrollbars");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ������� ����������� ������
        BoundedRangeModel model = new DefaultBoundedRangeModel(10, 40, 0, 100);
        // ���� ����� ���������
        JScrollBar scrollbar1 = new JScrollBar(JScrollBar.HORIZONTAL);
        JScrollBar scrollbar2 = new JScrollBar(JScrollBar.VERTICAL);
        // ������������ ������
        scrollbar1.setModel(model);
        scrollbar2.setModel(model);
        // ��������� ���������� � ������
        getContentPane().add(scrollbar1, BorderLayout.SOUTH);
        getContentPane().add(scrollbar2, BorderLayout.EAST);
        // ������� ���� �� �����
        setSize(400, 300);
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new Scrollbars();
    }

}