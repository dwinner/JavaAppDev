/*
 * ������� ���������� ����������.
 */

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Phonebook
{
    private JTextField jtfName;
    private JTextField jtfNumber;
    private JRadioButton jrbExact;
    private JRadioButton jrbStartsWith;
    private JRadioButton jrbEndsWith;
    private JCheckBox jcbIgnoreCase;
    // ������� ������ ���� � ������� ���������.
    private String[][] phonelist =
    {
        {
            "Jon", "555-8765"
        },
        {
            "Jessica", "555-5643"
        },
        {
            "Adam", "555-1212"
        },
        {
            "Rachel", "555-3435"
        },
        {
            "Tom & Jerry", "555-1001"
        }
    };

    public Phonebook()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("A Simple Phone List");

        // ��������� ���������� ���������� GridLayout.
        jfrm.getContentPane().setLayout(new GridLayout(0, 1));

        // ��������� ��������� �������� ������.
        jfrm.setSize(240, 220);

        // ���������� ��������� ��� �������� ���������� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� �����.
        JLabel jlabName = new JLabel("Name");
        JLabel jlabNumber = new JLabel("Number");
        JLabel jlabOptions = new JLabel("Search Options");

        // �������� ����� ��������������.
        jtfName = new JTextField(10);
        jtfNumber = new JTextField(10);

        // �������� ������ ����� Ignore Case.
        jcbIgnoreCase = new JCheckBox("Ignore Case");

        // �������� ������ ������������� �����.
        jrbExact = new JRadioButton("Exact Match", true);
        jrbStartsWith = new JRadioButton("Starts With");
        jrbEndsWith = new JRadioButton("Ends With");

        // ���������� ������ � ������.
        ButtonGroup bg = new ButtonGroup();
        bg.add(jrbExact);
        bg.add(jrbStartsWith);
        bg.add(jrbEndsWith);

        // ���������� ����������� ������� � ����� �������������� Name
        jtfName.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                jtfNumber.setText(lookupName(jtfName.getText()));
            }
        });

        // ���������� ����������� ������� � ����� �������������� Number.
        jtfNumber.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                jtfName.setText(lookupNumber(jtfNumber.getText()));
            }
        });

        // ���������� ����������� � ������ �����������
        jfrm.getContentPane().add(jlabName);
        jfrm.getContentPane().add(jtfName);
        jfrm.getContentPane().add(jlabNumber);
        jfrm.getContentPane().add(jtfNumber);
        jfrm.getContentPane().add(new JLabel());
        jfrm.getContentPane().add(jlabOptions);
        jfrm.getContentPane().add(jcbIgnoreCase);
        jfrm.getContentPane().add(new JLabel());
        jfrm.getContentPane().add(jrbExact);
        jfrm.getContentPane().add(jrbStartsWith);
        jfrm.getContentPane().add(jrbEndsWith);

        // ����������� ������.
        jfrm.setVisible(true);
    }

    // ����� �� �����. ����� ���������� ����� ��������.
    String lookupName(String n)
    {
        for (int i = 0; i < phonelist.length; i++)
        {
            if (jrbStartsWith.isSelected())
            {
                if (jcbIgnoreCase.isSelected())
                {
                    if (phonelist[i][0].toLowerCase().startsWith(n.toLowerCase()))
                    {
                        return phonelist[i][1];
                    }
                }
                else
                {
                    if (phonelist[i][0].startsWith(n))
                    {
                        return phonelist[i][1];
                    }
                }
            }
            else if (jrbEndsWith.isSelected())
            {
                if (jcbIgnoreCase.isSelected())
                {
                    if (phonelist[i][0].toLowerCase().endsWith(n.toLowerCase()))
                    {
                        return phonelist[i][1];
                    }
                }
                else
                {
                    if (phonelist[i][0].endsWith(n))
                    {
                        return phonelist[i][1];
                    }
                }
            }
            else
            {
                if (jcbIgnoreCase.isSelected())
                {
                    if (phonelist[i][0].toLowerCase().equals(n.toLowerCase()))
                    {
                        return phonelist[i][1];
                    }
                }
                else
                {
                    if (phonelist[i][0].equals(n))
                    {
                        return phonelist[i][1];
                    }
                }
            }
        }
        return "Not Found";
    }

    // ����� �� ������. ����� ���������� ���.
    String lookupNumber(String n)
    {
        for (int i = 0; i < phonelist.length; i++)
        {
            if (phonelist[i][1].equals(n))
            {
                return phonelist[i][0];
            }
        }
        return "Not Found";
    }

    public static void main(String args[])
    {
        // �������� ������ � ������ ��������� �������.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new Phonebook();
            }
        });
    }
}