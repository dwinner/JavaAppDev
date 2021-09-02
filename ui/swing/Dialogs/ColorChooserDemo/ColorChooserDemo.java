// ������ ������������� ������ JColorChooser.

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class ColorChooserDemo
{
    private JLabel jlab;
    private JButton jbtnShow;

    public ColorChooserDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("Color Chooser Demo");

        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ��������� �������� ������.
        jfrm.setSize(400, 200);

        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� �����, ������������ ����� ������������.
        jlab = new JLabel();

        // �������� ������, ���������� ����������� ����������� ����.
        jbtnShow = new JButton("Show Color Chooser");

        // ����������� ���� ������ ����� ��� ����������� ������ Show Color Chooser.
        jbtnShow.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent le)
            {
                // �������� ���� ������ �����. � �������� ������� ��������� ������ ���������� �������� null.
                // � ���������� ���� ��������� �� ������ ������. ��� ����������� ���� �������������
                // ���������� ������� ����.
                Color color = JColorChooser.showDialog(null, "Choose Color", Color.RED);
                jlab.setText(color != null
                   ? "Selected color is " + color.toString()
                   : "Color selection was cancelled.");
            }

        });

        // ��������� ������ Show Color Chooser � ����� � ������ ������ �����������.
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
                new ColorChooserDemo();
            }
        });
    }

}