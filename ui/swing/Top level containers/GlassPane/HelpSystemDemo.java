// ���������� ������ ����� ������ � �������� ������� ������
import java.awt.Component;
import java.awt.Cursor;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.*;

public class HelpSystemDemo extends JFrame
{
    // ����������� ��� ����
    private JButton button1, button2, help;
    private HelpSystem hs = new HelpSystem();
    private InterceptPane ip = new InterceptPane();
    private ImageIcon helpIcon = new ImageIcon("help.gif");

    public HelpSystemDemo()
    {
        super("Help System Demo");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ������� ��� ���������
        button1 = new JButton("���-�� ������");
        button2 = new JButton("���� ���-�� ������");
        JPanel contents = new JPanel();
        contents.add(button1);
        contents.add(button2);

        // ������ ������ ������
        help = new JButton(helpIcon);
        help.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                // ��� ������� �������� ���������� ������
                ip.setVisible(true);
                // � ����������� ��������� ����
                getRootPane().setCursor(getToolkit().createCustomCursor(helpIcon.getImage(),
                    new Point(0, 0), ""));
            }
        });
        contents.add(help);

        // ����������� ��� ��������� � ���������� ������
        setContentPane(contents);
        setGlassPane(ip);

        // ������� ���� �� �����
        setSize(200, 200);
        setVisible(true);
    }

    private class InterceptPane extends JComponent
    {
        InterceptPane()
        {
            // ���� �������� ������� �� ����
            enableEvents(MouseEvent.MOUSE_EVENT_MASK);
            // �� ��������� ������� � ���������
            setVisible(false);
            setOpaque(false);
        }

        // ������������� ������� �� ����
        @Override
        public void processMouseEvent(MouseEvent e)
        {
            // ����������� ������� ������ ����
            if (e.getID() == MouseEvent.MOUSE_PRESSED)
            {
                // ����������, ����� ��������� ��� ������
                Component[] comps = getContentPane().getComponents();
                for (int i = 0; i < comps.length; i++)
                {
                    MouseEvent me = SwingUtilities.convertMouseEvent(this, e, comps[i]);
                    if (comps[i].contains(me.getPoint()))
                    {
                        // ���������� ���������� ����������
                        JOptionPane.showMessageDialog(null, hs.getHelpFor(comps[i]));
                    }

                    // �����������
                    setVisible(false);
                    // ���������� �� ����� ������� ��������� ����
                    getRootPane().setCursor(Cursor.getDefaultCursor());
                }
            }
        }
    }

    // �������� ������� ������
    private class HelpSystem
    {
        // �������� ������ ��� �����������
        public String getHelpFor(Component comp)
        {
            if (comp == button1)
            {
                return "������������� ��������. ����� �� ����� �����";
            }
            else if (comp == button2)
            {
                return "������ ��������? ����� ����� �����!";
            }
            else
            {
                return "���� � �� ����, ��� ��� �����";
            }
        }
    }

    public static void main(String[] args)
    {
        new HelpSystemDemo();
    }
}