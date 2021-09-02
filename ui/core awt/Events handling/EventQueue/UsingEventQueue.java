// ������������� ������� �������
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import javax.swing.JButton;
import javax.swing.JFrame;

public class UsingEventQueue extends JFrame
{
    public UsingEventQueue()
    {
        super("Using EventQueue");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ������ � �� ���������
        JButton button = new JButton("������������ �������");
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                // ���������� ������� �������� ����
                getToolkit().getSystemEventQueue().postEvent(
                   new WindowEvent(UsingEventQueue.this, WindowEvent.WINDOW_CLOSING));
            }

        });
        // ������� ������ � ������ �����������
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(button);
        // ������� ���� �� �����
        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new UsingEventQueue();
    }

}