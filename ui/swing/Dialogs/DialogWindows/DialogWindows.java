// ���������� ���� � Swing
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class DialogWindows extends JFrame
{
    private JButton button1, button2;

    public DialogWindows()
    {
        super("Dialog Windows");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ���� ������, ���������� �������� ���������� ���� JButton
        button1 = new JButton("������� ����");
        button1.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                JDialog dialog = createDialog("�����������", false);
                dialog.setVisible(true);
            }

        });

        button2 = new JButton("��������� ����");
        button2.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                JDialog dialog = createDialog("���������", true);
                dialog.setVisible(true);
            }

        });

        // ������� ������ ����������� � ������� ���� �� �����
        JPanel contents = new JPanel();
        contents.add(button1);
        contents.add(button2);
        setContentPane(contents);
        setSize(350, 100);
        setVisible(true);
    }

    // ������� ���������� ����
    private JDialog createDialog(String title, boolean modal)
    {
        JDialog dialog = new JDialog(this, title, modal);
        dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
        dialog.setSize(200, 60);
        return dialog;
    }

    public static void main(String[] args)
    {
        new DialogWindows();
    }

}