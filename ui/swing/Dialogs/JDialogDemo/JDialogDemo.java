// ������ �������� �������� ����������� ���� � ������� ������ JDialog.

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class JDialogDemo
{
    private JLabel jlab;
    private JButton jbtnShow;
    private JButton jbtnReset;
    // ������, ������������ � ���������� ����.
    private JButton jbtnUp;
    private JButton jbtnDown;
    private JDialog jdlg;

    public JDialogDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("JDialog Demo");

        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ��������� �������� ������.
        jfrm.setSize(400, 200);

        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� �����, ������������ ��������� �����������.
        jlab = new JLabel("Direction is pending.");

        // �������� ������, ���������� ����������� ����������� ����.
        jbtnShow = new JButton("Show Dialog");

        // �������� ������, ������������ �����������.
        jbtnReset = new JButton("Reset Direction");

        // �������� � ��������� �������� ���������� ����������� ����.
        jdlg = new JDialog(jfrm, "Direction", true);
        jdlg.setSize(200, 100);
        jdlg.getContentPane().setLayout(new FlowLayout());

        // �������� ������, ������������ � ���������� ����.
        jbtnUp = new JButton("Up");
        jbtnDown = new JButton("Down");

        // ��������� ������ � ������ ����������� ����.
        jdlg.getContentPane().add(jbtnUp);
        jdlg.getContentPane().add(jbtnDown);

        // ��������� ����� � ������ ����������� ����.
        jdlg.getContentPane().add(new JLabel("Press a button."));

        // ����������� ����������� ���� �� ������ �� ������ Show Dialog.
        jbtnShow.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent le)
            {
                jdlg.setVisible(true);
            }

        });

        // ����� ����������� �� ������ �� ������ Reset Direction.
        jbtnReset.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent le)
            {
                jlab.setText("Direction is Pending.");
            }

        });

        // ������� �� ����������� ������ Up � ���������� ����.
        jbtnUp.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent le)
            {
                jlab.setText("Direction is Up");
                // �������� ����������� ���� � ������ ����� ������ ����������� �������������.
                jdlg.setVisible(false);
            }

        });

        // ������� �� ����������� ������ Down � ���������� ����.
        jbtnDown.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent le)
            {
                jlab.setText("Direction is Down");
                // �������� ����������� ���� � ������ ����� ������ ����������� �������������.
                jdlg.setVisible(false);
            }

        });

        // ��������� ������ Show Dialog � ����� � ������ ������ �����������
        jfrm.getContentPane().add(jbtnShow);
        jfrm.getContentPane().add(jbtnReset);
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
                new JDialogDemo();
            }

        });
    }

}