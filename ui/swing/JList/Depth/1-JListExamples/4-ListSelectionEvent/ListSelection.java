// �������� �� ���������� � ������
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListSelection extends JFrame
{
    // ������ ������
    private String[] data =
    {
        "Red",
        "Yellow",
        "Green"
    };
    private JTextArea jta;

    public ListSelection()
    {
        super("List Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ������� ������ � ��������� ����
        JPanel contents = new JPanel();
        JList<String> list = new JList<>(data);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jta = new JTextArea(5, 20);
        
        // ������� ��������� ������� ���������
        list.addListSelectionListener(new SelectionL());
        
        // ��������� ����������
        contents.add(new JScrollPane(list));
        contents.add(new JScrollPane(jta));
        
        // ������� ���� �� �����
        setContentPane(contents);
        setSize(100, 200);
        setVisible(true);
    }

    private class SelectionL implements ListSelectionListener
    {
        @Override
        public void valueChanged(ListSelectionEvent e)
        {
            int selected = ((JList) e.getSource()).getSelectedIndex();
            switch (selected)
            {
                case 0:
                    jta.setText("���������� ������");
                    break;
                case 1:
                    jta.setText("������ ������");
                    break;
                case 2:
                    jta.setText("���������� �����");
                    break;
            }
        }
    }

    public static void main(String[] args)
    {
        new ListSelection();
    }
}