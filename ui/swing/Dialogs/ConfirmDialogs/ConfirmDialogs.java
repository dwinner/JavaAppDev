// ����������� ���������� ���� ��� ��������� �� ������������
// ������������� ������������ ��������
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class ConfirmDialogs extends JFrame
{
    // ������ ��� ������ �� ���������
    private Icon icon = new ImageIcon("bottle.gif");

    public ConfirmDialogs()
    {
        super("Confirm Dialogs");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ����� ������� �� ������� ��������� ���������
        JButton confirm1 = new JButton("2 and 4 parameters");
        confirm1.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                // ���������� ���� � 4-�� �����������
                int res = JOptionPane.showConfirmDialog(
                   ConfirmDialogs.this,
                   "�� ����� ������?",
                   "������",
                   JOptionPane.YES_NO_CANCEL_OPTION);
                // ���������� ���������� ����
                if (res == JOptionPane.YES_OPTION)
                {
                    JOptionPane.showConfirmDialog(ConfirmDialogs.this, "����� ������?");
                }
                else if (res == JOptionPane.NO_OPTION)
                {
                    JOptionPane.showConfirmDialog(ConfirmDialogs.this, "������ �� ������?");
                }
            }

        });

        JButton confirm2 = new JButton("5 parameters");
        confirm2.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                int res = JOptionPane.showConfirmDialog(
                   ConfirmDialogs.this,
                   "������� ���������, ����...",
                   "����� ��������� ������!",
                   JOptionPane.YES_NO_OPTION,
                   JOptionPane.ERROR_MESSAGE);
            }

        });

        JButton confirm3 = new JButton("6 parameters");
        confirm3.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                int res = JOptionPane.showConfirmDialog(
                   ConfirmDialogs.this,
                   "��� �������� ������?",
                   "��������� ����������� ����",
                   JOptionPane.YES_NO_OPTION,
                   JOptionPane.ERROR_MESSAGE,
                   icon);
            }

        });

        // ��������� ������ � ����
        JPanel contents = new JPanel();
        contents.add(confirm1);
        contents.add(confirm2);
        contents.add(confirm3);
        setContentPane(contents);
        // ������� ���� �� �����
        setSize(300, 200);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new ConfirmDialogs();
    }

}