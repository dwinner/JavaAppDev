
/**
 * Adapters.java: ������������� ��������� ������ �����������
 */
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class Adapters extends JFrame
{
    public Adapters()
    {
        super("Adapters");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        addMouseListener(new MouseL());
        setSize(200, 200);
        setVisible(true);
    }

    // ��������� �� ��������
    private class MouseL extends MouseAdapter
    {
        // ������ �� �������� ���� � ����
        @Override
        public void mouseClicked(MouseEvent me)
        {
            System.out.println(me);
        }

    }

    public static void main(String[] args)
    {
        new Adapters();
    }

}