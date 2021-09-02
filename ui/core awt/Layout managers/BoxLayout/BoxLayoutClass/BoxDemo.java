// ������������� ���������� ���������� BoxLayout ����������� ������ Box.

import java.awt.*;
import javax.swing.*;

public class BoxDemo
{
    public BoxDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("BoxLayout Demo");

        // *** � ������� ����������� �����������
        // ��������� ���������� FlowLayout ***
        jfrm.getContentPane().setLayout(new FlowLayout());

        // ��������� ��������� �������� ������.
        jfrm.setSize(300, 240);

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

        // ��������� ������������ � ������������� ������� ������.
        jbtnOne.setMinimumSize(btnDim);
        jbtnOne.setMaximumSize(btnDim);
        jbtnTwo.setMinimumSize(btnDim);
        jbtnTwo.setMaximumSize(btnDim);
        jbtnThree.setMinimumSize(btnDim);
        jbtnThree.setMaximumSize(btnDim);
        jbtnFour.setMinimumSize(btnDim);
        jbtnFour.setMaximumSize(btnDim);

        // �������� ������� �����.
        JCheckBox jcbOne = new JCheckBox("Option One");
        JCheckBox jcbTwo = new JCheckBox("Option Two");

        // �������� ��� ������������ ������.
        Box box1 = Box.createVerticalBox();
        Box box2 = Box.createVerticalBox();
        Box box3 = Box.createVerticalBox();

        // �������� ��������� ����� ��� ������.
        box1.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        box2.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        box3.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // ��������� ����������� � ����� box1 � box2.
        box1.add(jlabOne);
        box1.add(Box.createRigidArea(new Dimension(0, 4)));
        box1.add(jbtnOne);
        box1.add(Box.createRigidArea(new Dimension(0, 4)));
        box1.add(jbtnTwo);

        box2.add(jlabTwo);
        box2.add(Box.createRigidArea(new Dimension(0, 4)));
        box2.add(jbtnThree);
        box2.add(Box.createRigidArea(new Dimension(0, 4)));
        box2.add(jbtnFour);

        box3.add(jlabThree);
        box3.add(jcbOne);
        box3.add(jcbTwo);

        // ��������� ������ � ������ ������ �����������.
        jfrm.getContentPane().add(box1);
        jfrm.getContentPane().add(box2);
        jfrm.getContentPane().add(box3);

        // ����������� ������.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // ����� ��������� � ������ ��������� �������.
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new BoxDemo();
            }
        });
    }
}