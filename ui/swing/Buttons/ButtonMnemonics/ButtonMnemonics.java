// ��������� �������� ������������ ��������
import javax.swing.*;
import java.awt.*;

public class ButtonMnemonics extends JFrame
{    
    public ButtonMnemonics()
    {
        super("Button Mnemonics");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // �������� ������ �����������
        Container c = getContentPane();
        
        // ���������� ���������������� ������������
        c.setLayout(new FlowLayout());
        
        // ������� ������
        JButton button = new JButton("������� �� ����!");
        
        // ��������� (������� ������)
        button.setMnemonic('�');
        c.add(button);
        
        // ��� ���� ������, ������ ������� �� ����������
        button = new JButton("All Right!");
        button.setMnemonic('L');
        button.setToolTipText("����� �����");
        button.setDisplayedMnemonicIndex(2);
        c.add(button);
        
        // ������� ���� �� �����
        pack();
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new ButtonMnemonics();
    }

}