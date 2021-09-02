// �������� ������� ���������� ���� �������� � ���������� ������
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class SimpleFileChooser extends JFrame
{
    // ������� ����� ���������
    private JFileChooser fc = new JFileChooser();

    public SimpleFileChooser()
    {
        super("Simple File Chooser");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ������, ��������� ���� ��� �������� �����
        JButton open = new JButton("Open");
        open.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                fc.setDialogTitle("�������� �������");
                // ����������� ��� ������ ��������
                fc.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
                int res = fc.showOpenDialog(SimpleFileChooser.this);
                // ���� ���� ������, ���������� ���
                if (res == JFileChooser.APPROVE_OPTION)
                {
                    JOptionPane.showMessageDialog(
                       SimpleFileChooser.this,
                       fc.getSelectedFile());
                }
            }

        });

        // ������, ��������� ���������� ���� ��� ���������� �����
        JButton save = new JButton("Save");
        save.addActionListener(new ActionListener()
        {
            @Override public void actionPerformed(ActionEvent e)
            {
                fc.setDialogTitle("���������� �����");
                // ��������� ������
                fc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                int res = fc.showSaveDialog(SimpleFileChooser.this);
                // �������� �� ������
                if (res == JFileChooser.APPROVE_OPTION)
                {
                    JOptionPane.showMessageDialog(
                       SimpleFileChooser.this,
                       "���� ��������");
                }
            }

        });

        // ��������� ������ � ������� ���� �� �����
        JPanel contents = new JPanel();
        contents.add(open);
        contents.add(save);
        setContentPane(contents);
        setSize(300, 200);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new SimpleFileChooser();
    }

}