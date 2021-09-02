// ��������� ������������
import java.awt.*;
import javax.swing.*;

public class GridLayoutTest extends JFrame
{
    public GridLayoutTest()
    {
        super("Grid Layout");
        setSize(200, 200);
        setLocation(100, 100);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ��������������� ������
        JPanel grid = new JPanel();
        // ������ ��� ��������� ������������ GridLayout - ���������� ����� � �������� � �������,
        // ������ ��� - ���������� ����� �������� �� X � Y
        GridLayout gl = new GridLayout(2, 0, 5, 12);
        grid.setLayout(gl);
        // ������� ������ ������
        for (int i = 0; i < 8; i++)
        {
            grid.add(new JButton("Button " + i));
        }
        // �������� ���� ������ � ����� ����
        getContentPane().add(grid);
        // ������������� ����������� ������
        pack();
        // ���������� ����
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new GridLayoutTest();
    }
}