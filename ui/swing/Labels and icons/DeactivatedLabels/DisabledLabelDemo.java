// ������������� ���������� �����.

import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class DisabledLabelDemo
{
    public DisabledLabelDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("Use Images in Labels");

        // ��������� ���������� ���������� GridLayout,
        // ������������ ������� �� ���� ����� � ������ �������.
        jfrm.getContentPane().setLayout(new GridLayout(3, 1));

        // ��������� ���������� ������� ������.
        jfrm.setSize(240, 250);

        // ���������� ��������� ��� �������� ���������� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� ���������� �� �����.
        ImageIcon myIcon = new ImageIcon("myIcon.gif");
        ImageIcon myDisIcon = new ImageIcon("myDisIcon.gif");

        // �������� �����, ���������� ����� � �����������.
        JLabel jlabIconTxt = new JLabel("This label is enabled.", myIcon, SwingConstants.CENTER);

        // �������� � ������������� �����, ���������� ����� � �����������.
        JLabel jlabIconTxt2 = new JLabel("This label is disabled.", myIcon, SwingConstants.CENTER);
        // ������������� �����: ����� ����� ������������ ����� ������� ������.
        jlabIconTxt2.setEnabled(false);

        // �������� � ������������� �����, ���������� ����� � �����������.
        // �� ���� ��� � ������� ����� ��������� ����������� �����������.
        JLabel jlabIconTxt3 = new JLabel("Use the disabled icon.", myIcon, SwingConstants.CENTER);
        // ��������� �����������, ������� ������ ������������ ��� ������������� �����.
        jlabIconTxt3.setDisabledIcon(myDisIcon);
        // ������������� �����. ����� ����� ���������� ����� ������� ������, � � �� �������
        // ����� ������������ �����������, ���������� ��������������� ��� ������ ��������.
        jlabIconTxt3.setEnabled(false);

        // ��������� ����� � ������ ������ �����������.
        jfrm.getContentPane().add(jlabIconTxt);
        jfrm.getContentPane().add(jlabIconTxt2);
        jfrm.getContentPane().add(jlabIconTxt3);

        // ����������� ������.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // �������� ������ � ������ ��������� �������.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new DisabledLabelDemo();
            }

        });
    }

}