/*
 * ������� ������������ ������.
 */

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Coder implements ActionListener
{
    private JTextField jtfPlaintext;
    private JTextField jtfCiphertext;

    public Coder()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("A Simple Code Machine");

        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ��������� �������� ������.
        jfrm.setSize(340, 120);

        // ���������� ��������� ��� �������� ���������� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� ���� �����.
        JLabel jlabPlaintext = new JLabel("   Plain Text: ");
        JLabel jlabCiphertext = new JLabel("Cipher Text: ");

        // �������� ���� ����� ��������������.
        jtfPlaintext = new JTextField(20);
        jtfCiphertext = new JTextField(20);

        // ��������� ������ �������� ��� ����� ��������������.
        jtfPlaintext.setActionCommand("Encode");
        jtfCiphertext.setActionCommand("Decode");

        // ���������� ������������ ������� ��� ����� ��������������.
        jtfPlaintext.addActionListener(this);
        jtfCiphertext.addActionListener(this);

        // ���������� ����� �������������� � ����� � ������ �����������.
        jfrm.getContentPane().add(jlabPlaintext);
        jfrm.getContentPane().add(jtfPlaintext);
        jfrm.getContentPane().add(jlabCiphertext);
        jfrm.getContentPane().add(jtfCiphertext);

        // �������� ����������� ������.
        JButton jbtnEncode = new JButton("Encode");
        JButton jbtnDecode = new JButton("Decode");
        JButton jbtnReset = new JButton("Reset");

        // ���������� ������������ � ��������.
        jbtnEncode.addActionListener(this);
        jbtnDecode.addActionListener(this);
        jbtnReset.addActionListener(this);

        // ��������� ������ � ������ ������ �����������.
        jfrm.getContentPane().add(jbtnEncode);
        jfrm.getContentPane().add(jbtnDecode);
        jfrm.getContentPane().add(jbtnReset);

        // ����������� ������.
        jfrm.setVisible(true);
    }

    @Override   // ��������� ������� ��������.
    public void actionPerformed(ActionEvent ae)
    {
        // ���� ������� �������� ����� "Encode", ������ ���������.
        switch (ae.getActionCommand())
        {
            case "Encode":
                {
                    // ��������� ������ � �������� ��� ������� StringBuilder.
                    StringBuilder str = new StringBuilder(jtfPlaintext.getText());
                    // ���������� ������� � ���� ������� �������.
                    for (int i = 0; i < str.length(); i++)
                    {
                        str.setCharAt(i, (char) (str.charAt(i) + 1));
                    }
                    // ��������� �������������� ������ � ���� Cipher Text.
                    jtfCiphertext.setText(str.toString());
                    break;
                }
            case "Decode":
                {
                    // ��������� ������������� ������ � �������� ��� ������� StringBuilder.
                    StringBuilder str = new StringBuilder(jtfCiphertext.getText());
                    // ��������� ������� �� ���� ������� �������.
                    for (int i = 0; i < str.length(); i++)
                    {
                        str.setCharAt(i, (char) (str.charAt(i) - 1));
                    }
                    // ��������� ��������������� ������ � ���� Plain Text.
                    jtfPlaintext.setText(str.toString());
                    break;
                }
            default:
                jtfPlaintext.setText("");
                jtfCiphertext.setText("");
                break;
        }
    }

    public static void main(String args[])
    {
        // ����� ��������� � ������ ��������� �������.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new Coder();
            }
        });
    }
}