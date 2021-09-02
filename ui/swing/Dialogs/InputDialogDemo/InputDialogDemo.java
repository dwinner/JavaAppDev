// ������� ���������� ���� ��� ����� ������ ������.

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

public class InputDialogDemo
{
    private JLabel jlab;
    private JButton jbtnShow;
    private JFrame jfrm;

    public InputDialogDemo()
    {
        // �������� ������ ���������� JFrame.
        jfrm = new JFrame("A Simple Input Dialog");

        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ��������� �������� ������.
        jfrm.setSize(400, 250);

        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� �����, � ������� ������� ������������ ����� ������������.
        jlab = new JLabel();

        // �������� ������, ���������� ����������� ����������� ����.
        jbtnShow = new JButton("Show Dialog");

        // ���������� � ������� ����������� �������.
        jbtnShow.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent le)
            {
                // �������� ����������� ���� ��� ����� ������.
                String response = JOptionPane.showInputDialog(
                   jfrm,
                   "Enter Name",
                   "Bob Smith");
                /*
                 * ���� ����� ���������� �������� null, ��� ��������, ��� ������������ ������� ��
                 * ������ Cansel ��� ������ ���� � ������� ������ � ������ ������� ����. �����
                 * ���������� ������ ������� ����� � ������, ���� ������������ �� ��� ������. �
                 * ������ ������� ������������ ������, ��������� �������������.
                 */
                if (response == null)
                {
                    jlab.setText("Dialog Cancelled or Closed");
                }
                else if (response.length() == 0)
                {
                    jlab.setText("No string entered");
                }
                else
                {
                    jlab.setText("Hi there " + response);
                }
            }

        });

        // ����� ����� ���������� ����� �� ������:
        jbtnShow.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent le)
            {
                String[] names =
                {
                    "Tom Jones",
                    "Bob Smith",
                    "Mary Doe",
                    "Nancy Oliver"
                };
                // �������� ����������� ����, ������������ ������������
                // �������� ��� �� ������.
                String response = (String) JOptionPane.showInputDialog(
                   jfrm,
                   "Choose User",
                   "Select User Name",
                   JOptionPane.QUESTION_MESSAGE,
                   null,
                   names,
                   "Bob Smith");
                if (response == null)
                {
                    jlab.setText("Dialog Cancelled or Closed");
                }
                else if (response.length() == 0)
                {
                    jlab.setText("No string entered");
                }
                else
                {
                    jlab.setText("Hi there " + response);
                }
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
                new InputDialogDemo();
            }

        });
    }

}