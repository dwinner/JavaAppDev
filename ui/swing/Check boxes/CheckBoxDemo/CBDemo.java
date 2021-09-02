// ������������ ������ � �������� �����

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CBDemo implements ItemListener
{
    private JLabel jlabOptions;
    private JLabel jlabWhat;
    private JLabel jlabChange;
    private JCheckBox jcbOptions;
    private JCheckBox jcbSpeed;
    private JCheckBox jcbSize;
    private JCheckBox jcbDebug;

    public CBDemo()
    {
        // �������� ������ ���������� JFrame.
        JFrame jfrm = new JFrame("Demonstrate Check Boxes");

        // ��������� ���������� ���������� GridLayout,
        // ������������ ������� �� ��������������� ���-�� ����� � ������ �������.
        jfrm.getContentPane().setLayout(new GridLayout(0, 1));

        // ��������� ��������� �������� ������.
        jfrm.setSize(300, 150);

        // ���������� ��������� ��� �������� ���������� �������������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� ���� �����.
        jlabOptions = new JLabel("Options:");
        jlabChange = new JLabel("");
        jlabWhat = new JLabel("Options selected:");

        // �������� ������� ������� �����.
        jcbOptions = new JCheckBox("Enable Options");
        jcbSpeed = new JCheckBox("Maximize Speed");
        jcbSize = new JCheckBox("Minimize Size");
        jcbDebug = new JCheckBox("Debug");

        // ��� ������ ����� ���������� ����������.
        jcbSpeed.setEnabled(false);
        jcbSize.setEnabled(false);
        jcbDebug.setEnabled(false);

        // ��������� ����������� ������� �������� ��� jcbOptions.
        jcbOptions.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent ie)
            {
                if (jcbOptions.isSelected())
                {
                    // ��� ������ ��������� ��������� ������ jcbOptions,
                    // �.�. ��� ������ �������, ��������� � ���,
                    // ��������� ��������� ������� ������������� �
                    // ������������������� � ���������������� � ��������.
                    jcbSpeed.setEnabled(true);
                    jcbSize.setEnabled(true);
                    jcbDebug.setEnabled(true);
                }
                else
                {
                    jcbSpeed.setEnabled(false);
                    jcbSize.setEnabled(false);
                    jcbDebug.setEnabled(false);
                }
            }
        });

        // �������, ������������ �������� ����� �� ������ Options,
        // �������������� ����� ������� - itemStateChanged(),
        // ������������� � ������ CBDemo.
        jcbSpeed.addItemListener(this);
        jcbSize.addItemListener(this);
        jcbDebug.addItemListener(this);

        // ��������� ������� ����� � ����� � ������ ������ �����������.
        jfrm.getContentPane().add(jcbOptions);
        jfrm.getContentPane().add(jlabOptions);

        jfrm.getContentPane().add(jcbSpeed);
        jfrm.getContentPane().add(jcbSize);
        jfrm.getContentPane().add(jcbDebug);
        jfrm.getContentPane().add(jlabChange);
        jfrm.getContentPane().add(jlabWhat);

        // ����������� ������.
        jfrm.setVisible(true);
    }

    // ���������� ������� ��� ���� ������� �� ������ Options.
    public void itemStateChanged(ItemEvent ie)
    {
        String opts = "";

        // ��������� ������ �� ������ �����, ���������� ���������� �������.
        JCheckBox cb = (JCheckBox) ie.getItem();

        // �������������� ������������ ���������� � ����������� ���������.
        // ��� ���� ����� ����������, ���������� ��� ������� ������ �����, ������������ ����� getStateChange().
        if (ie.getStateChange() == ItemEvent.SELECTED)
        {
            jlabChange.setText("Selection change: " + cb.getText() + " selected.");
        }
        else
        {
            jlabChange.setText("Selection change: " + cb.getText() + " cleared.");
        }

        // ������������ ������, ���������� �������� � ����
        // ������������� ������� �� ������ Options.
        if (jcbSpeed.isSelected())
            opts += "Speed ";
        if (jcbSize.isSelected())
            opts += "Size ";
        if (jcbDebug.isSelected())
            opts += "Debug ";

        // ����������� ���������� �� ������������� �������.
        jlabWhat.setText("Options selected: " + opts);
    }


    public static void main(String args[])
    {
        // �������� ������ � ������ ��������� �������.
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new CBDemo();
            }
        });
    }
}