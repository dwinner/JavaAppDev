// ���������, ��������������� ������������ ������ �� ����������� � �� ���������.

import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.Border;

public class AlignLabelDemo
{
    public AlignLabelDemo()
    {
        JLabel[] jlabs = new JLabel[9];

        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("Horizontal and Vertical Alignment");

        // ��������� ���������� ���������� GridLayout.
        // ��������� ������� �� 3-� ����� � 3-� ��������
        // � ������� 4 ������� ����� ������������.
        jfrm.getContentPane().setLayout(new GridLayout(3, 3, 4, 4));

        // ����������� ��������� ������� ������.
        jfrm.setSize(500, 200);

        // ���������� ��������� ��� �������� ������������� ����.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������������� ������������ �� ����� ������� �
        // ������������ ������������ �� ������� �������.
        jlabs[0] = new JLabel("Left, Top", SwingConstants.LEFT);
        jlabs[0].setVerticalAlignment(SwingConstants.TOP);

        // �������������� ������������ �� ������ �
        // ������������ ������������ �� ������� �������.
        jlabs[1] = new JLabel("Center, Top", SwingConstants.CENTER);
        jlabs[1].setVerticalAlignment(SwingConstants.TOP);

        // �������������� ������������ �� ������ ������� �
        // ������������ ������������ �� ������� �������.
        jlabs[2] = new JLabel("Right, Top", SwingConstants.RIGHT);
        jlabs[2].setVerticalAlignment(SwingConstants.TOP);

        // �������������� ����������� �� ����� ������� � ������������ ����������� �� ������.
        // ����� ������������ ����������� �� ��������� ��� ����������� ������.
        jlabs[3] = new JLabel("Left, Center", SwingConstants.LEFT);

        // �������������� � ������������ ������������ �� ������.
        jlabs[4] = new JLabel("Center, Center", SwingConstants.CENTER);

        // �������������� ������������ �� ������ ������� �
        // ������������ ����������� �� ������.
        jlabs[5] = new JLabel("Right, Center", SwingConstants.RIGHT);

        // �������������� ����������� �� ����� ������� �
        // ������������ ����������� �� ������ �������.
        jlabs[6] = new JLabel("Left, Bottom", SwingConstants.LEFT);
        jlabs[6].setVerticalAlignment(SwingConstants.BOTTOM);

        // �������������� ������������ �� ������ �
        // ������������ ������������ �� ������ �������.
        jlabs[7] = new JLabel("Center, Bottom", SwingConstants.CENTER);
        jlabs[7].setVerticalAlignment(SwingConstants.BOTTOM);

        // �������������� ������������ �� ������ ������� �
        // ������������ ������������ �� ������ �������.
        jlabs[8] = new JLabel("Right, Bottom", SwingConstants.RIGHT);
        jlabs[8].setVerticalAlignment(SwingConstants.BOTTOM);

        // ���������� ����� ��� ����������� ������ �����. �������� ��������� �����.
        Border border = BorderFactory.createEtchedBorder();

        // ���������� ����� � ������ �����.
        for (int i = 0; i < 9; i ++)
        {
            jlabs[i].setBorder(border);
        }

        // ���������� ����� � ������ �����������.
        for (int i = 0; i < 9; i ++)
        {
            jfrm.getContentPane().add(jlabs[i]);
        }

        // ���������� ������ ����� � ������ �����������.
        JPanel cp = (JPanel) jfrm.getContentPane();
        // ������ ����� ������� ����� ����� ����� ������
        // � ������������� � ��� ����������.
        cp.setBorder(BorderFactory.createEmptyBorder(4, 4, 4, 4));

        // ����������� ������.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // ����� ��������� � ������ ��������� �������.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new AlignLabelDemo();
            }

        });
    }

}