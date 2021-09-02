// ��������� ��������� �������� ������ �����
import java.awt.BorderLayout;
import java.awt.ContainerOrderFocusTraversalPolicy;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class FocusPolicyTest extends JFrame
{
    public FocusPolicyTest()
    {
        super("Focus Policy Test");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().add(new JButton("�����"), BorderLayout.WEST);

        // ��������� ��� ������ ������, �� ��� ����� ���� ���� ������ ������
        JButton button = new JButton("�������");
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                // ��� ������� ������ �������� ��� ����
                setFocusTraversalPolicy(new ContainerOrderFocusTraversalPolicy());
            }

        });
        getContentPane().add(button, BorderLayout.SOUTH);
        getContentPane().add(new JButton("������"), BorderLayout.EAST);
        setSize(200, 200);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new FocusPolicyTest();
    }

}