import java.awt.*;
import javax.swing.*;

public class JScrollPaneDemo extends JApplet
{
    public void init()
    {
        // �������� ������ ����������
        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        // �������� � ������ 400 ������
        JPanel jp = new JPanel();
        jp.setLayout(new GridLayout(20, 20));
        int b = 0;
        for (int i=0; i<20; i++)
        {
            for (int j=0; j<20; j++)
            {
                jp.add(new JButton("Button " + b));
                ++b;
            }
        }

        // ������� ������ ��������� � �������� ���������
        int v = ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED;
        int h = ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED;
        JScrollPane jsp = new JScrollPane(jp, v, h);

        // �������� ������ ��������� � ����� ������ ����������
        contentPane.add(jsp, BorderLayout.CENTER);
    }

}