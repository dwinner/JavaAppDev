// ������������� ����������� ����, ���������������� ��� ��������� ������������� �� ������������.

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class ConfirmDialogDemo
{
    private JLabel jlab;
    private JButton jbtnShow;
    private JFrame jfrm;

    public ConfirmDialogDemo()
    {
        // �������� ������ ���������� JFrame.
        jfrm = new JFrame("A Confirmation Dialog");

        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ��������� �������� ������.
        jfrm.setSize(400, 250);

        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� �����, ������������ ����� ������������.
        jlab = new JLabel();

        // �������� ������, ���������� ����������� ����������� ����.
        jbtnShow = new JButton("Show Dialog");

        // ���������� � ������� ����������� �������.
        jbtnShow.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent le)
            {
                // �������� ����������� ����, ������������� ���������.
                int response = JOptionPane.showConfirmDialog(
                   jfrm,
                   "Remove unused files?", // ���������
                   "Disk Space is Low", // ���������
                   JOptionPane.YES_NO_OPTION // ������������ ������ ������ Yes � No
                   );

                switch (response)
                {
                    case JOptionPane.YES_OPTION:
                        jlab.setText("You answered Yes.");
                        break;
                    case JOptionPane.NO_OPTION:
                        jlab.setText("You answered No.");
                        break;
                    case JOptionPane.CLOSED_OPTION:
                        jlab.setText("Dialog closed without response.");
                        break;
                }
            }

        });


        // ��������� ������ � ����� � ������ ������ �����������.
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
                new ConfirmDialogDemo();
            }

        });
    }

}