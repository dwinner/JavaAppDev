// ������������ ������������ ���� ��������������.

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class JTextFieldDemo
{
    private JLabel jlabAll;
    private JLabel jlabSelected;
    private JTextField jtf;
    private JButton jbtnCut;
    private JButton jbtnPaste;

    public JTextFieldDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("Use JTextField");

        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ��������� �������� ������.
        jfrm.setSize(200, 150);

        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� �����.
        jlabAll = new JLabel("All text: ");
        jlabSelected = new JLabel("Selected text: ");

        // �������� ���� ��������������.
        jtf = new JTextField("This is a test.", 15);

        // ���������� ����������� ������� �������� � ����� ��������������.
        // ������ ���, ����� ������������ �������� ������� <Enter>, ���������� ����������
        // ������������ �� ������. ����� ��������� ���������� �����.
        jtf.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent le)
            {
                jlabAll.setText("All text: " + jtf.getText());
                jlabSelected.setText("Selected text: " + jtf.getSelectedText());
            }
        });

        // �������� ������ Cut � Paste.
        jbtnCut = new JButton("Cut");
        jbtnPaste = new JButton("Paste");

        // ���������� ����������� ������� � ������� Cut.
        jbtnCut.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent le)
            {
                // ���������� ����� ���������� � ���������� � ����� ������.
                jtf.cut();
                jlabAll.setText("All text: " + jtf.getText());
                jlabSelected.setText("Selected text: " + jtf.getSelectedText());
            }
        });

        // ���������� ����������� ������� � ������� Paste.
        jbtnPaste.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent le)
            {
                // ����� ����������� �� ������ ������ � ����������� � ���� ��������������.
                jtf.paste();
            }
        });

        // �������� ����������� ������� ���������� ������� � ���������� ��� � �����
        // ��������������. ��� ��������� ���������� ����������� �� ��������� �����������
        // ���������� � �������� �������.
        jtf.addCaretListener(new CaretListener()
        {
            @Override
            public void caretUpdate(CaretEvent ce)
            {
                // ��� ������ ��������� ������� ��������� ������� ������������ ����������
                // ���� �������������� � ���������� �����.
                jlabAll.setText("All text: " + jtf.getText());
                jlabSelected.setText("Selected text: " + jtf.getSelectedText());
            }
        });

        // ��������� ����������� � ������ ������ �����������.
        jfrm.getContentPane().add(jtf);
        jfrm.getContentPane().add(jbtnCut);
        jfrm.getContentPane().add(jbtnPaste);
        jfrm.getContentPane().add(jlabAll);
        jfrm.getContentPane().add(jlabSelected);

        // ��������� ���������� ������� ����� ������ �������.
        jtf.setCaretPosition(5);
        // ����������� ���������� ������� � ������� ����� �������� �������.
        // ��� �������� �������� � ��������� ����� "is"
        jtf.moveCaretPosition(7);

        // ����������� ������.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // ����� ��������� � ������ ��������� �������
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new JTextFieldDemo();
            }
        });
    }
}