// ������������� � ������ �� ����������
import javax.swing.*;
import java.awt.event.*;

public class ConfirmClosing extends JFrame
{    
    public ConfirmClosing()
    {
        super("Application");
        // ��������� �������� ��������
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // ��������� ��������� ������� ����
        addWindowListener(new WindowAdapter()
        {
            @Override public void windowClosing(WindowEvent we)
            {   // ������������� ������
                int res = JOptionPane.showConfirmDialog(null, "������������� �����?");
                if (res == JOptionPane.YES_OPTION)
                {
                    System.exit(0);
                }
            }
        });
        
        // ������� ���� �� �����
        setSize(200, 100);
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new ConfirmClosing();
    }
}