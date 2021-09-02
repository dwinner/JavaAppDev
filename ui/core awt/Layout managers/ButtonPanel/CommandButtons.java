// �������� ������ ������
import javax.swing.*;
import java.awt.*;

public class CommandButtons extends JFrame
{
    public CommandButtons()
    {
        super("Command Buttons");
        setSize(350, 250);
        setLocation(150, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ������� ������ � ��������� ������������� ��� ������������ �������� ������
        JPanel grid = new JPanel(new GridLayout(0, 2, 5, 0));
        
        // ��������� ����������
        grid.add(new JButton("OK"));
        grid.add(new JButton("Cancel"));
        
        // �������� ���������� � ������ � ���������������� �������������,
        // ����������� �� ������� ����
        JPanel flow = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        flow.add(grid);
        
        // �������� ������ �����������
        Container c = getContentPane();
        
        // �������� ������ ������ ���� ����
        c.add(flow, BorderLayout.SOUTH);
		setVisible(true);
    }

    public static void main(String[] args)
    {
        new CommandButtons();
    }

}