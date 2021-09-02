// ��������� ������ ���������
import javax.swing.*;

public class ListSelectionModes extends JFrame
{
    private String[] data =
    {
        "Red",
        "Blue",
        "Green",
        "Yellow",
        "White"
    };

    public ListSelectionModes()
    {
        super("List Selection Modes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ��������� ������ �������
        DefaultListModel<String> dlm = new DefaultListModel<>();
        for (int i = 0; i < data.length; i++)
        {
            dlm.addElement(data[i]);
        }
        
        // ��� ������ � ������ ����� ���������
        JPanel contents = new JPanel();
        JList<String> list1 = new JList<>(dlm);
        list1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JList<String> list2 = new JList<>(dlm);
        list2.setSelectionMode(ListSelectionModel.SINGLE_INTERVAL_SELECTION);
        JList<String> list3 = new JList<>(dlm);
        
        // ���������� ����������� ������
        list3.getSelectionModel().setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        
        // ��������� ����������
        contents.add(new JScrollPane(list1));
        contents.add(new JScrollPane(list2));
        contents.add(new JScrollPane(list3));
        
        // ������� ���� �� �����
        setContentPane(contents);
        setSize(100, 200);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new ListSelectionModes();
    }
}