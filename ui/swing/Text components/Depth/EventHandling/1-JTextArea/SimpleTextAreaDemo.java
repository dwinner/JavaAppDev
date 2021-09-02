// ������ ������������� ���������� JTextArea.
// �������� ����������!!!

import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.*;
import javax.swing.event.CaretEvent;
import javax.swing.event.CaretListener;

public class SimpleTextAreaDemo
{
    private JLabel jlabWC;
    private JTextArea jta;

    public SimpleTextAreaDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("A Simple JTextArea");

        // ��������� ���������� ���������� FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ��������� �������� ������.
        jfrm.setSize(240, 150);

        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� ����� ��� ����������� �������� ����.
        jlabWC = new JLabel("Current word count is 0");

        // �������� ���� ��������������
        jta = new JTextArea();

        // ��������� ������ �������� �� ������� �����.
        jta.setLineWrap(true);
        jta.setWrapStyleWord(true);

        // ��������� ��������� ������� � ������ � ����������.
        JScrollPane jscrlp = new JScrollPane(jta);
        jscrlp.setPreferredSize(new Dimension(100, 75));

        // ���������� ����������� ������� ���������� ������� � �����������.
        // ������ ���������� ���������� ������� ����.
        jta.addCaretListener(new CaretListener()
        {
            // ��� ������ ����������� ���������� ������� ������������ ������� ����.
            @Override
            public void caretUpdate(CaretEvent ce)
            {
                int wc; // �������� ����� ����.
                // ��������� �������� ������.
                String str = jta.getText();
                if (str.length() == 0)
                {
                    wc = 0; // ���� ����� �����������, ����� ���� ����� ����.
                }
                else
                {
                    // ���������� ������ �� ��������� �����. ������������� �������� �������, �������
                    // �� ����� �������������� � �����, �������� ������� ��� ����� ����������.
                    String[] strsplit = str.split("\\W+");

                    // ������� ���� ����� ����� �����, ������� ����������
                    // ����� split(). 
                    wc = strsplit.length;

                    // ���� ������� ��������-������������.
                    if (strsplit.length > 0 && strsplit[0].length() == 0)
                    {
                        wc--;
                    }
                }

                // ����������� �������� ����.
                jlabWC.setText("Current word count is " + wc);
            }
        });

        // ��������� ����������� � ������ ������ �����������.
        jfrm.getContentPane().add(jscrlp);
        jfrm.getContentPane().add(jlabWC);

        // ����������� ������.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // ����� ��������� � ������ ��������� �������.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new SimpleTextAreaDemo();
            }
        });
    }
}