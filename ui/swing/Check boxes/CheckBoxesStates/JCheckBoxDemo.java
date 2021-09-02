
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class JCheckBoxDemo extends JFrame implements ItemListener
{
    private JTextField jtf;

    public JCheckBoxDemo()
    {
        // �������� ������ ����������
        Container contentPane = getContentPane();
        contentPane.setLayout(new FlowLayout());

        // ������� �����������
        ImageIcon normal = new ImageIcon("normal.gif");
        ImageIcon rollover = new ImageIcon("rollover.gif");
        ImageIcon selected = new ImageIcon("selected.gif");

        // �������� ������ � ������ ����������
        JCheckBox cb = new JCheckBox("C", normal);
        cb.setRolloverIcon(rollover);
        cb.setSelectedIcon(selected);
        cb.addItemListener(this);
        contentPane.add(cb);

        cb = new JCheckBox("C++", normal);
        cb.setRolloverIcon(rollover);
        cb.setSelectedIcon(selected);
        cb.addItemListener(this);
        contentPane.add(cb);

        cb = new JCheckBox("Java", normal);
        cb.setRolloverIcon(rollover);
        cb.setSelectedIcon(selected);
        cb.addItemListener(this);
        contentPane.add(cb);

        cb = new JCheckBox("Perl", normal);
        cb.setRolloverIcon(rollover);
        cb.setSelectedIcon(selected);
        cb.addItemListener(this);
        contentPane.add(cb);

        // �������� ��������� ���� � ������ ����������
        jtf = new JTextField(15);
        contentPane.add(jtf);
    }

    public void itemStateChanged(ItemEvent ie)
    {
        JCheckBox cb = (JCheckBox) ie.getItem();
        jtf.setText(cb.getText());
    }

    public static void main(String[] args)
    {
        JFrame frame = new JCheckBoxDemo();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}