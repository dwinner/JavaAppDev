// ������������� �������� ��� ������ ��������
import javax.swing.*;
import java.awt.*;

public class LabelMnemonic extends JFrame
{
    public LabelMnemonic()
    {
        super("Label Mnemonic");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ������� ���� ��������� �����
        JPanel contents = new JPanel(new GridLayout(2, 2));
        JTextField tf = new JTextField(10);
        JLabel label = new JLabel("���� ��� (Name)");
        // ��������� ���������
        label.setLabelFor(tf);
        label.setDisplayedMnemonic('N');
        // ��������� ���������� � �������
        contents.add(new JLabel("���� �������"));
        contents.add(new JTextField(10));
        contents.add(label);
        contents.add(tf);
        // ������� ���� �� �����
        setContentPane(contents);
        pack();
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new LabelMnemonic();
    }

}