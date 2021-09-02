
import java.awt.BorderLayout;
import java.awt.Container;
import javax.swing.JApplet;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ScrollPaneConstants;

/**
 * ����� ������� ������� � �������.
 * @author oracle_pr1
 */
public class JTableDemo extends JApplet
{
    @Override
    public void init()
    {
        // �������� ������ ����������
        Container contentPane = getContentPane();

        // ���������� �������� ����������
        contentPane.setLayout(new BorderLayout());

        // ���������������� ��������� ��������
        final String[] colHeads =
        {
            "Name", "Phone", "Fax"
        };

        // ���������������� ������
        final Object[][] data =
        {
            {
                "Gail", "4567", "8675"
            },
            {
                "Ken", "7566", "5555"
            },
            {
                "Viviane", "5634", "5887"
            },
            {
                "Melanie", "7345", "9222"
            },
            {
                "Anne", "1237", "3333"
            },
            {
                "John", "5656", "3144"
            },
            {
                "Matt", "5672", "2176"
            },
            {
                "Claire", "6741", "4244"
            },
            {
                "Erwin", "9023", "5159"
            },
            {
                "Ellen", "1134", "5332"
            },
            {
                "Jennifer", "5689", "1212"
            },
            {
                "Ed", "9030", "1313"
            },
            {
                "Helen", "6751", "1415"
            },
        };

        // ������� �������
        JTable table = new JTable(data, colHeads);

        // �������� � ������ ��������� ������ ���������
        int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
        int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
        JScrollPane jsp = new JScrollPane(table, v, h);

        // �������� ������ ��������� � ������ ����������
        contentPane.add(jsp, BorderLayout.CENTER);
    }
}