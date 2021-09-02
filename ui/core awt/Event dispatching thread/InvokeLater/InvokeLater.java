// ����� invokeLater() � ������ � ������� �������� �������
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTextField;

public class InvokeLater extends JFrame
{
    private JButton button;

    public InvokeLater()
    {
        super("InvokeLater");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ������� ������ �� ����������
        button = new JButton("��������� ������� ������");
        button.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                // �������� ��������� �����
                new ComplexJobThread().start();
                button.setText("���������...");
            }

        });

        // �������� ������ ����������� � ������� ���� �� �����
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(new JTextField(20));
        getContentPane().add(button);
        setSize(200, 200);
        setVisible(true);
    }

    // �����, ���������� "������� ������"
    private class ComplexJobThread extends Thread
    {
        @Override
        public void run()
        {
            try
            {   // ��������� ��������
                sleep(3000);
                // ������ ���������, ����� �������� ���������
                EventQueue.invokeLater(new Runnable()
                {
                    @Override public void run()
                    {
                        button.setText("������ ���������");
                    }

                });
            }
            catch (InterruptedException ex)
            {
                // TODO: Crash case
            }
        }

    }

    public static void main(String[] args)
    {
        new InvokeLater();
    }

}