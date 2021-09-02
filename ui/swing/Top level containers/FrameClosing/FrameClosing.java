// ������������� ���� � ������
import javax.swing.*;

public class FrameClosing extends JFrame
{    
    public FrameClosing()
    {
        super("Window title");
        // �������� ��� �������� ����
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ������ ��� ����
        setIconImage(getToolkit().getImage("title.png"));
        // ����� �� �����
        setSize(200, 100);
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new FrameClosing();
    }
}