
/**
 * ������� ��������������� �������.
 */
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;

public class ForwardingEvents extends JFrame
{
    private JButton btn1, btn2;

    public ForwardingEvents()
    {
        super("Forwarding Events");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        getContentPane().setLayout(new FlowLayout());

        // ������� ���� ������
        btn1 = new JButton("OK");
        btn2 = new JButton("Cancel");
        getContentPane().add(btn1);
        getContentPane().add(btn2);

        // ����� ������� �� ��������� ������
        Forwarder forwarder = new Forwarder();
        btn1.addActionListener(forwarder);
        btn2.addActionListener(forwarder);
        pack();
        setVisible(true);
    }

    /**
     * �����-��������� ������� ������
     */
    private class Forwarder implements ActionListener
    {
        @Override
        public void actionPerformed(ActionEvent ae)
        {
            // ��������� ������� �� �������
            if (ae.getSource() == btn1)
            {
                onOK(ae);
            }
            else if (ae.getSource() == btn2)
            {
                onCancel(ae);
            }
        }

        // ��������� ������� �� ������ "OK"
        private void onOK(ActionEvent ae)
        {
            System.out.println("onOK");
        }

        // ��������� ������� �� ������ "������"
        private void onCancel(ActionEvent ae)
        {
            System.out.println("onCancel");
        }

    }

    public static void main(String[] args)
    {
        new ForwardingEvents();
    }

}