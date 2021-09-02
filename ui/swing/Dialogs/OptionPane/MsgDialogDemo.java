// ������ ������������� ���������� JOptionPane.

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class MsgDialogDemo
{
    private JLabel jlab;
    private JButton jbtnShow;
    private JFrame jfrm;

    public MsgDialogDemo()
    {
        // �������� ������ ���������� JFrame.
        jfrm = new JFrame("Simple Message Dialog");

        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ��������� �������� ������.
        jfrm.setSize(400, 250);

        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� �����, �������������� ����� �������� ����������� ����.
        jlab = new JLabel();

        // �������� ������, ���������� ����������� ����������� ����.
        jbtnShow = new JButton("Show Dialog");

        // ���������� � ������� ����������� �������.
        jbtnShow.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent le)
            {
                // �������� ����������� ����, ������������� ���������.
                JOptionPane.showMessageDialog(jfrm, "Disk Space is Low.");
                // ������ ��������� �� ����� ��������� �� ��� ���, ���� �����
                // showMessageDialog() �� �������� ������.
                jlab.setText("Dialog Closed");
            }

        });

        // ��������� ������ � ����� � ������ �����������.
        jfrm.getContentPane().add(jbtnShow);
        jfrm.getContentPane().add(jlab);

        // ����������� ������.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // ����� ��������� � ������ ��������� �������.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override public void run()
            {
                new MsgDialogDemo();
            }

        });
    }

}