// ��������� ��������� ������.
// � ������ ������� ������������ ������ JOptionPane � JFileChooser.

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

public class CompareFiles
{
    private JLabel jlabFirst;
    private JLabel jlabSecond;
    private JButton jbtnGetFirst;
    private JButton jbtnGetSecond;
    private JButton jbtnCompare;
    private JTextField jtfFirst;
    private JTextField jtfSecond;
    private JFileChooser jfc;

    public CompareFiles()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("Compare Files");
        jfrm.setResizable(false);
        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());
        // ��������� ��������� �������� ������.
        jfrm.setSize(400, 200);
        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� ����� ��� ����� ��������������.
        jlabFirst = new JLabel("First file");
        jlabFirst.setPreferredSize(new Dimension(70, 20));
        jlabFirst.setHorizontalAlignment(SwingConstants.RIGHT);

        jlabSecond = new JLabel("Second file:");
        jlabSecond.setPreferredSize(new Dimension(70, 20));
        jlabSecond.setHorizontalAlignment(SwingConstants.RIGHT);

        // �������� ����� �������������� ��� ����� ���� ������.
        jtfFirst = new JTextField(20);
        jtfSecond = new JTextField(20);

        // �������� ������ Browse.
        jbtnGetFirst = new JButton("Browse");
        jbtnGetSecond = new JButton("Browse");

        // �������� ������, ����������� ������� ��������� ������.
        jbtnCompare = new JButton("Compare Files");

        // �������� ���� ������ �����, ������������� �� ������ �� ������ Browse.
        jfc = new JFileChooser();

        // ����� ������� �����.
        jbtnGetFirst.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent le)
            {
                int result = jfc.showDialog(null, "Select");
                if (result == JFileChooser.APPROVE_OPTION)
                {
                    File f = jfc.getSelectedFile();
                    jtfFirst.setText(f.getPath());
                }
            }

        });

        // ����� ������� �����.
        jbtnGetSecond.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent le)
            {
                int result = jfc.showDialog(null, "Select");
                if (result == JFileChooser.APPROVE_OPTION)
                {
                    File f = jfc.getSelectedFile();
                    jtfSecond.setText(f.getPath());
                }
            }

        });

        // ��������� ������.
        jbtnCompare.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent le)
            {
                // ���������, ������� �� ����� ������.
                if (jtfFirst.getText().length() == 0 || jtfSecond.getText().length() == 0)
                {
                    JOptionPane.showMessageDialog(
                       null,
                       "Please specify the files to compare.",
                       "Filename Not Specified",
                       JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // �������� �������� File �� ������ ���� ������.
                File f1 = new File(jtfFirst.getText());
                File f2 = new File(jtfSecond.getText());

                // ���������, ���������� �� ��������� �����.
                if ( ! f1.exists())
                {
                    JOptionPane.showMessageDialog(
                       null,
                       "The first file does not exists.",
                       "File Not Found",
                       JOptionPane.WARNING_MESSAGE);
                    return;
                }
                if ( ! f2.exists())
                {
                    JOptionPane.showMessageDialog(null,
                       "The second file does not exists.",
                       "File Not Found",
                       JOptionPane.WARNING_MESSAGE);
                    return;
                }

                // ��������� ������.
                if (compare(f1, f2))
                {
                    JOptionPane.showMessageDialog(null,
                       "Files compare equal",
                       "Comparison Result",
                       JOptionPane.INFORMATION_MESSAGE);
                }
                else
                {
                    JOptionPane.showMessageDialog(
                       null,
                       "Files differ.",
                       "Comparison Result",
                       JOptionPane.INFORMATION_MESSAGE);
                }
            }

        });

        // ���������� ����������� � ������ �����������.
        jfrm.getContentPane().add(jlabFirst);
        jfrm.getContentPane().add(jtfFirst);
        jfrm.getContentPane().add(jbtnGetFirst);
        jfrm.getContentPane().add(jlabSecond);
        jfrm.getContentPane().add(jtfSecond);
        jfrm.getContentPane().add(jbtnGetSecond);
        jfrm.getContentPane().add(jbtnCompare);
        // ����������� ������.
        jfrm.setVisible(true);
    }

    /**
     * ����� ��������� ���� ������.
     * <p/>
     * @param fileA ������ ����
     * @param fileB ������ ����
     * <p/>
     * @return true ���� ����� ����� ���������� ����������
     */
    private boolean compare(File fileA, File fileB)
    {
        // ���� ����� ����� ������ �����, ��� �� ���������.
        if (fileA.length() != fileB.length())
        {
            return false;
        }
        FileInputStream f1, f2;
        int i, j;
        byte buf1[] = new byte[1024];
        byte buf2[] = new byte[1024];

        try
        {
            f1 = new FileInputStream(fileA);
            f2 = new FileInputStream(fileB);

            do
            {
                // ������ ������ ������ read() ���������� ����� ����������� ������ ���
                // �������� -1, ���� ��� ��������� ����� �����.
                i = f1.read(buf1, 0, 1024);
                j = f2.read(buf2, 0, 1024);

                // ���� ���������� ������� �� ���������, ����� �����������. � ���� ������
                // ����� ����������� � ������������ �������� false.
                if ( ! Arrays.equals(buf1, buf2))
                {
                    f1.close();
                    f2.close();
                    return false;
                }
            }
            while (i != -1 && j != -1);

            f1.close();
            f2.close();
        }
        catch (IOException exc)
        {
            JOptionPane.showMessageDialog(null,
               exc,
               "File Error!",
               JOptionPane.WARNING_MESSAGE);
            // � ������ ������ ������������ �������� false.
            return false;
        }

        // ����� ���������. ������������ �������� true.
        return true;
    }

    public static void main(String args[])
    {
        // ����� ��������� � ������ ��������� �������.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override public void run()
            {
                new CompareFiles();
            }

        });
    }

}