// ������� �����
import java.awt.Color;
import javax.swing.*;

public class UsingBorderFactory extends JFrame
{
    public UsingBorderFactory()
    {
        super("Using Border Factory");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ����� ��� ������ �����������
        JPanel cp = (JPanel) getContentPane();
        cp.setBorder(
            BorderFactory.createTitledBorder(BorderFactory.createRaisedBevelBorder(),
            "������� �� ������� �����")
        );
        
        // ������� ���� �� �����
        setSize(100, 200);
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new UsingBorderFactory();
    }

}