// ������������ �������
import java.awt.AWTEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MaskingEvents extends JFrame
{
    public MaskingEvents()
    {
        super("MaskingEvents");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // �������� ������� �� ����
        disableEvents(AWTEvent.WINDOW_EVENT_MASK);
        // ������� ������ ������
        JPanel contents = new JPanel();
        contents.add(new CustomButton("Hello"));
        setContentPane(contents);
        setSize(400, 300);
        setVisible(true);
    }

    //  ������ ������
    private class CustomButton extends JButton
    {
        CustomButton(String label)
        {
            super(label);
            // ��������� ������� �� ����������
            disableEvents(AWTEvent.KEY_EVENT_MASK);
        }

    }

    public static void main(String[] args)
    {
        new MaskingEvents();
    }

}