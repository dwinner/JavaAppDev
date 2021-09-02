// ������� ���������, ��������� � �������������� ������� Swing.
// �������� ������ Swing ���������� � ������ javax.swing.
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class SwingDemo
{
    SwingDemo()
    {
        // �������� ���������� �������� ������ (JFrame)
        JFrame jfrm = new JFrame("A Simple Swing Program");

        // ��������� ��������� �������� ������.
        jfrm.setSize(275, 100);

        // ���������� ��������� ��� �������� ������������� ���� ����������.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // �������� �����.
        JLabel jlab = new JLabel("Swing powers the modern Java GUI.");

        // ��������� ����� � ������ ������ �����������.
        jfrm.getContentPane().add(jlab);

        // ����������� ������.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // �������� ������ � ������ ��������� �������.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new SwingDemo();
            }

        });
    }

}
