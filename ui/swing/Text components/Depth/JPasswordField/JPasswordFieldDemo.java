// ������, ��������������� ������ � �������� JPasswordField.

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.SwingUtilities;

public class JPasswordFieldDemo
{
    private JLabel jlabPW;
    private JPasswordField jpswd;

    public JPasswordFieldDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("Use JPasswordField");

        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ��������� �������� ������.
        jfrm.setSize(240, 100);

        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� �����.
        jlabPW = new JLabel("Enter Password");

        // �������� ���� ����� ������.
        jpswd = new JPasswordField(15);

        // ���������� � ����� ����� ������ ����������� ������� ��������. ������ ���, �����
        // ������������ �������� ������� <Enter>, ���������� ����������
        // ����������� �� ������������ ������.
        jpswd.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent le)
            {
                char pw[] =
                {
                    't', 'e', 's', 't'
                };
                char[] userSeq = jpswd.getPassword();
                // �������� ������, ��������� �������������.
                if (Arrays.equals(userSeq, pw))
                {
                    jlabPW.setText("Password Valid");
                }
                else
                {
                    jlabPW.setText("Password Invalid -- Try Again");
                }
                // ����� ���������� ������ ��������� ��� �������.
                Arrays.fill(pw, (char) 0);
                Arrays.fill(userSeq, (char) 0);
            }
        });

        // ��������� ����������� � ������ ������ �����������.
        jfrm.getContentPane().add(jpswd);
        jfrm.getContentPane().add(jlabPW);

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
                new JPasswordFieldDemo();
            }
        });
    }
}