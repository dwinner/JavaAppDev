// ������������� ����������� � ������� �����.

import java.awt.GridLayout;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class IconLabelDemo
{
    public IconLabelDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("Use Images in Labels");

        // ��������� ���������� ���������� GridLayout,
        // ������������ ������� �� 4-� ����� � ������ �������.
        jfrm.getContentPane().setLayout(new GridLayout(4, 1));

        // ��������� ���������� ������� ������.
        jfrm.setSize(250, 300);

        // ���������� ��������� ��� �������� ���������� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� ������� ImageIcon �� ���� �����������, ������������ �� �����.
        ImageIcon myIcon = new ImageIcon("myIcon.gif");

        // �������� �����, ���������� �����������.
        JLabel jlabIcon = new JLabel(myIcon);

        // �������� �����, ���������� ����� � �����������.
        JLabel jlabIconTxt = new JLabel("Default Icon and Text Position", myIcon, SwingConstants.CENTER);

        // �������� ����� � ������� � ������������ � ���������� ������ ����� �� �����������.
        JLabel jlabIconTxt2 = new JLabel("Text Left of Icon", myIcon, SwingConstants.CENTER);
        jlabIconTxt2.setHorizontalTextPosition(SwingConstants.LEFT);

        // �������� ����� � ������� � ������������ � ���������� ������ ��� ������������.
        JLabel jlabIconTxt3 = new JLabel("Text Over Icon", myIcon, SwingConstants.CENTER);
        jlabIconTxt3.setVerticalTextPosition(SwingConstants.TOP);
        jlabIconTxt3.setHorizontalTextPosition(SwingConstants.CENTER);

        // ��������� ����� � ������ ������ �����������.
        jfrm.getContentPane().add(jlabIcon);
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
                new IconLabelDemo();
            }

        });
    }

}