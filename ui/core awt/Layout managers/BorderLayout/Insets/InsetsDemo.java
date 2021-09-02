// ������������� BorderLayout �� ���������.
import java.awt.*;
import java.applet.*;

public class InsetsDemo extends Applet
{
    @Override public void init()
    {
        // ���������� ���� ���� ���, ����� ������� ���� ����� ������
        setBackground(Color.cyan);
        setLayout(new BorderLayout());

        add(new Button("This is across the top."), BorderLayout.NORTH);
        add(new Label("The footer message might go here."), BorderLayout.SOUTH);
        add(new Button("Right"), BorderLayout.EAST);
        add(new Button("Left"), BorderLayout.WEST);

        String msg = "This is just a simple text.";

        add(new TextArea(msg), BorderLayout.CENTER);
    }

    // �������� �������
    @Override public Insets getInsets()
    {
        return new Insets(10, 10, 10, 10);
    }
}