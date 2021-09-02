
/**
 * FactoryEvents.java: ������������� ������� ������� �������� �������� ���������.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class FactoryEvents extends JFrame
{
    // ������ �� ���� �������
    private ListenerFactory factory = new ListenerFactory();

    public FactoryEvents()
    {
        super("FactoryEvents");
        // ������� ��������� ��������� ��������
        addWindowListener(factory.getWindowL());
        // ������� ������
        JButton button = new JButton("������� ����");
        getContentPane().add(button);
        // ��������� ������ ����� ��������� ��������
        button.addActionListener(factory.getButtonL());
        // ������� ���� �� �����
        pack();
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new FactoryEvents();
    }

}

/**
 * ������� �������
 */
class ListenerFactory
{
    ListenerFactory() { assert true; }

    // ���� ����� ������� ��������� ��� ������
    public ActionListener getButtonL()
    {
        return new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                System.out.println("Button Listener");
            }

        };
    }

    // ��������� ������� �������
    public WindowListener getWindowL()
    {
        return new WindowAdapter()
        {
            @Override
            public void windowClosing(WindowEvent we)
            {
                System.exit(0);
            }

        };
    }

}