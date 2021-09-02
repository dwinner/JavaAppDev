// ���������� �������
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JFrame;

public class ConsumingEvents extends JFrame
{
    public ConsumingEvents()
    {
        super("Consuming Events");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ������������ ������� ���������
        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent me)
            {
                // ��������� ��������� ������� ������ ����
                if (me.getClickCount() == 1)
                {
                    me.consume();
                }
                System.out.println("1");
            }

        });

        addMouseListener(new MouseAdapter()
        {
            @Override
            public void mousePressed(MouseEvent me)
            {
                // �� ������������ ����������� �������
                if ( ! me.isConsumed())
                {
                    System.out.println("2");
                }
            }

        });

        // ������� ���� �� �����
        setSize(200, 200);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new ConsumingEvents();
    }

}