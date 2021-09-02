// ������, ��������������� ������������� ������ JFileChooser.

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class FileChooserDemo
{
    private JLabel jlab;
    private JButton jbtnShow;
    private JFileChooser jfc;

    public FileChooserDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("JFileChooser Demo");

        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ��������� �������� ������.
        jfrm.setSize(400, 200);

        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� �����, ������������ ��������� ����.
        jlab = new JLabel();

        // �������� ������, ���������� ����������� ����������� ����.
        jbtnShow = new JButton("Show File Chooser");

        // �������� ������ �����, ������������� �������������
        // ���������� �������� �� ���������.
        jfc = new JFileChooser();

        // ����������� ���� ������ ����� � ����� �� ����������� ������ Show File Chooser.
        jbtnShow.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent le)
            {
                // ����������� ���� ������ �����. � �������� ���������, �������������
                // ��������� ����, �������� �������� null. � ���������� ���������� ����
                // ��������� �� ������ ������.
                int result = jfc.showOpenDialog(null);
                // ���� ���� ��� ������, ������������ ��� ���.
                jlab.setText((result == JFileChooser.APPROVE_OPTION)
                   ? "Selected file is: " + jfc.getSelectedFile().getName()
                   : "No file selected");
            }

        });

        // ��������� ������ Show File Chooser � ����� � ������ ������ �����������.
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
                new FileChooserDemo();
            }

        });
    }

}