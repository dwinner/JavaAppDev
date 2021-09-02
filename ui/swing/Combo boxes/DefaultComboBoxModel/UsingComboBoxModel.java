// ������������� ����������� ������ �������������� �������
import javax.swing.*;
import java.awt.event.*;

public class UsingComboBoxModel extends JFrame
{    
    // ���� ����������� ������
    private DefaultComboBoxModel cbm;
    
    public UsingComboBoxModel()
    {
        super("Using Combo Box Model");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ������� ����������� ������ � ��������� �� �������
        cbm = new DefaultComboBoxModel();
        for (int i = 0; i < 20; i++)
        {
            cbm.addElement("Combo box element: " + i);
        }
        // ������ ��������� �������
        cbm.setSelectedItem(cbm.getElementAt(4));
        // ������ �� ������ ����� ������
        JComboBox combo = new JComboBox(cbm);
        combo.setMaximumRowCount(5);
        // ����������� ������ ��������� ����������� �������������� �������
        JButton add = new JButton("��������");
        add.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                // �������� �������� �������
                int pos = (int) Math.random() * cbm.getSize();
                cbm.insertElementAt("New", pos);
            }
        });
        // ��������� ������ � ������ � ������
        JPanel contents = new JPanel();
        contents.add(combo);
        contents.add(add);
        // ������� ���� �� �����
        setContentPane(contents);
        setSize(400, 200);
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new UsingComboBoxModel();
    }

}