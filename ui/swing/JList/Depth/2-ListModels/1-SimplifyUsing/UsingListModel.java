// ������������� ����������� ������ ������
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class UsingListModel extends JFrame
{
    // ���� ������
    private DefaultListModel<String> dim;

    public UsingListModel()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // ��������� ������ �������
        dim = new DefaultListModel<>();
        dim.add(0, "��� ���");
        dim.add(1, "��� ��� ���");
        dim.add(2, "��� �������");

        // ������� ������ � ���� �������
        JPanel contents = new JPanel();
        JButton add = new JButton("��������");
        add.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                dim.add(0, "�������!");
                validate();
            }
        });
        JList<String> list1 = new JList<>(dim);
        JList<String> list2 = new JList<>(dim);

        // ��������� ����������
        contents.add(add);
        contents.add(new JScrollPane(list1));
        contents.add(new JScrollPane(list2));
        // ������� ���� �� �����
        setContentPane(contents);
        setSize(400, 200);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new UsingListModel();
    }
}