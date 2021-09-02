// ������������� ������ showOptionDialog() ��� �������� ����������� ����.

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class OptionDialogDemo
{
    private JLabel jlab;
    private JButton jbtnShow;
    private JFrame jfrm;

    public OptionDialogDemo()
    {
        // �������� ������ ���������� JFrame.
        jfrm = new JFrame("A Simple Option Dialog");

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

        // ���������� � ������� ����������� ������� ��������.
        jbtnShow.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent le)
            {
                // ����������� ����� ��� ����������� � ����.
                String[] connectOpts =
                {
                    "Modem",
                    "Wireless",
                    "Satelite",
                    "Cable"
                };
                // �������� ����������� ����, ������������ ������������
                // �������� ������ ����������� � ����.
                // ������ ����� ����������� � ���� ��������� ������.
                int response = JOptionPane.showOptionDialog(
                   jfrm,
                   "Choose One",
                   "Connection Type",
                   JOptionPane.DEFAULT_OPTION,
                   JOptionPane.QUESTION_MESSAGE,
                   null,
                   connectOpts,
                   "Wireless");
                // ����������� ���������� � ������ ������������. � ���������� response ����������
                // ������ ������, �� ������� ������� ������������.
                switch (response)
                {
                    case 0:
                        jlab.setText("Connect via modem.");
                        break;
                    case 1:
                        jlab.setText("Connect via wireless.");
                        break;
                    case 2:
                        jlab.setText("Connect via satelite.");
                        break;
                    case 3:
                        jlab.setText("Connect via cable.");
                        break;
                    case JOptionPane.CLOSED_OPTION:
                        jlab.setText("Dialog cancelled");
                        break;
                }
                /*
                 * Object[] ops = { new JLabel("Name"), new JTextField(10), new JLabel("Phone
                 * Number"), new JTextField(10), "OK", "Cancel" }; * int response =
                 * JOptionPane.showOptionDialog( jfrm, "Enter Info", "Get Name and Telephone",
                 * JOptionPane.OK_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, ops, "Cancel");
                 *
                 */
            }

        });

        jfrm.getContentPane().add(jbtnShow);
        jfrm.getContentPane().add(jlab);

        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override public void run()
            {
                new OptionDialogDemo();
            }

        });
    }

}