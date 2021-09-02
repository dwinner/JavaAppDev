// �������� ������� �� ���� �� �� ����������� � ����������
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class PreProcessMouse extends JFrame
{
    PreProcessMouse()
    {
        super("PreProcessMouse");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ������� ��������� ������� �� ����
        addMouseListener(new MouseL());
        // ������� ���� �� �����
        setSize(200, 200);
        setVisible(true);
    }

    // �������� ������� �� ����
    @Override
    public void processMouseEvent(MouseEvent me)
    {
        if (me.getClickCount() == 1)
        {   // ���� ������ - �� ���������� � ����������
        }
        else
        {   // ����� �������� ����� �������� ������
            super.processMouseEvent(me);
        }
    }

    // ���������, �������� �� �������� ����
    private class MouseL extends MouseAdapter
    {
        @Override
        public void mouseClicked(MouseEvent me)
        {
            System.out.println("Click Count: " + me.getClickCount());
        }

    }

    public static void main(String[] args)
    {
        new PreProcessMouse();
    }

}