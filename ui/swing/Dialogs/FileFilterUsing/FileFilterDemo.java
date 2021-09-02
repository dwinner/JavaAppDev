// ������������� ������� ������.

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileFilter;

// �������� ������� ������, ��������������� ����������� �������� ������ Java � ���������. 
class JavaFileFilter extends FileFilter
{
    @Override public boolean accept(File file)
    {
        // ����� ���������� �������� true, ���� � �������� ��������� ��� ���
        // ������� �������� ���� Java ��� �������.
        return (file.getName().endsWith(".java") || file.isDirectory())
            ? true
            : false;
    }

    @Override public String getDescription() { return "Java Source Code Files"; }
}

public class FileFilterDemo
{
    private JLabel jlab;
    private JButton jbtnShow;
    private JFileChooser jfc;

    public FileFilterDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("File Filter Demo");

        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ��������� �������� ������.
        jfrm.setSize(400, 200);

        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� �����, ������������ ��� ���������� �����.
        jlab = new JLabel();

        // �������� ������, ���������� ����������� ����������� ����.
        jbtnShow = new JButton("Show File Chooser");

        // �������� ���� ������ ������.
        jfc = new JFileChooser();

        // ��������� ������� ������.
        jfc.setFileFilter(new JavaFileFilter());

        // ����������� ���� ������ ����� ��� ����������� ������ Show File Chooser.
        jbtnShow.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent le)
            {
                // � �������� ���������, ������������� ��������� ����, �������� �������� null.
                // � ���������� ���������� ���� ��������� �� ������ ������.
                int result = jfc.showOpenDialog(null);
                jlab.setText((result == JFileChooser.APPROVE_OPTION)
                    ? "Selected file is: " + jfc.getSelectedFile().getName()
                    : "No file selected."
                );
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
                new FileFilterDemo();
            }
        });
    }
}