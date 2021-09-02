// ������������� �������� ���� Button.
import java.awt.*;
import java.awt.event.*;
import java.applet.*;

/*
<applet code="ButtonList" width="250" height="150">
</applet>
 */
public class ButtonList extends Applet implements ActionListener
{
    String msg = "";
    Button bList[] = new Button[3];

    public void init()
    {
        Button yes = new Button("Yes");
        Button no = new Button("No");
        Button maybe = new Button("Undecided");

        // ��������� ������ �� ������ ��� �� ����������
        bList[0] = (Button) add(yes);
        bList[1] = (Button) add(no);
        bList[2] = (Button) add(maybe);

        // ���������������� ��� ������ ������� ��������
        for (int i = 0; i < 3; i++)
        {
            bList[i].addActionListener(this);
        }
    }

    public void actionPerformed(ActionEvent ae)
    {
        for (int i = 0; i < 3; i++)
        {
            if (ae.getSource() == bList[i])
            {
                msg = "You pressed " + bList[i].getLabel();
            }
        }
        repaint();
    }

    public void paint(Graphics g)
    {
        g.drawString(msg, 6, 100);
    }
}
