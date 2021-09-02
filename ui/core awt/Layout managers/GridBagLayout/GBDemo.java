// ������ ������������� ������ GridBagLayout.

import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class GBDemo
{
    public GBDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("GridBagLayout Demo");

        // �������� ������ �������� GridBagLayout � GridBagConstraints.
        GridBagLayout gbag = new GridBagLayout();
        GridBagConstraints gbc = new GridBagConstraints();

        // ���������� ���������� ���������� GridBagLayout � ������� ����������� ������.
        jfrm.getContentPane().setLayout(gbag);

        // ��������� ��������� �������� ������.
        jfrm.setSize(240, 240);

        // ���������� ��������� ��� �������� ���� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� �����.
        JLabel jlabOne = new JLabel("Button Group One");
        JLabel jlabTwo = new JLabel("Button Group Two");
        JLabel jlabThree = new JLabel("Check Box Group");

        // �������� ������.
        JButton jbtnOne = new JButton("One");
        JButton jbtnTwo = new JButton("Two");
        JButton jbtnThree = new JButton("Three");
        JButton jbtnFour = new JButton("Four");

        Dimension btnDim = new Dimension(100, 25);
        jbtnOne.setPreferredSize(btnDim);
        jbtnTwo.setPreferredSize(btnDim);
        jbtnThree.setPreferredSize(btnDim);
        jbtnFour.setPreferredSize(btnDim);

        // �������� ������� �����.
        JCheckBox jcbOne = new JCheckBox("Option One");
        JCheckBox jcbTwo = new JCheckBox("Option Two");

        // ����������� ������� ��� ���������� ����������.

        // �������� 1.0 ���������� weightx ��������� �������������� ������������
        // � �������������� �����������. ��������� ��� ���������� weighty �������
        // �������� �� ���������, ������ 0.0, � ������������ ����������� ���������
        // ������������� �� ������.
        gbc.weightx = 1.0;

        // ����������� ������������ ������� ���������� � �������.

        // ����� ��� ������ ������������� � ������� 0,0 � 1,0
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbag.setConstraints(jlabOne, gbc);

        gbc.gridx = 1;
        gbc.gridy = 0;
        gbag.setConstraints(jlabTwo, gbc);

        // �������������� ������������ ����� ��������.
        gbc.insets = new Insets(4, 4, 4, 4);

        // ������ ������������� � ������� 0,1, 1,1, � �.�.
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbag.setConstraints(jbtnOne, gbc);

        gbc.gridx = 1;
        gbc.gridy = 1;
        gbag.setConstraints(jbtnTwo, gbc);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbag.setConstraints(jbtnThree, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbag.setConstraints(jbtnFour, gbc);

        // ��������� ����� � ��� ������ ����� ������������� � ����������
        // ������� � ������������� �� ������.
        gbc.gridwidth = GridBagConstraints.REMAINDER;

        // ���� ����� ������������� ��������� ������������ � 10 ��������.
        gbc.insets = new Insets(10, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbag.setConstraints(jlabThree, gbc);

        // ������ ������� ����� ������������ �� �������������.
        gbc.insets = new Insets(0, 0, 0, 0);
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbag.setConstraints(jcbOne, gbc);

        gbc.gridx = 0;
        gbc.gridy = 5;
        gbag.setConstraints(jcbTwo, gbc);

        // ��������� ����������� � ������ ������ �����������.
        jfrm.getContentPane().add(jlabOne);
        jfrm.getContentPane().add(jlabTwo);
        jfrm.getContentPane().add(jbtnOne);
        jfrm.getContentPane().add(jbtnTwo);
        jfrm.getContentPane().add(jbtnThree);
        jfrm.getContentPane().add(jbtnFour);
        jfrm.getContentPane().add(jlabThree);
        jfrm.getContentPane().add(jcbOne);
        jfrm.getContentPane().add(jcbTwo);

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
                new GBDemo();
            }

        });
    }

}