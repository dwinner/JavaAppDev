// ������������� ����������� ������� ��������
import javax.swing.*;

public class UsingSpinnerModels extends JFrame
{
    // ����� ������ ��� ��������
    private String[] data =
    {
        "�������",
        "���������",
        "�����",
        "�����"
    };

    public UsingSpinnerModels()
    {
        super("Using Spinner Models");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ������ �������� ��� ������ �� ������ ������
        SpinnerModel list = new SpinnerListModel(data);
        JSpinner spinner1 = new JSpinner(list);
        // ������ �������� ��� ������ ����� �����
        SpinnerModel numbers = new SpinnerNumberModel(4, 0, 100, 1);
        JSpinner spinner2 = new JSpinner(numbers);
        // ��������� �������� � ������ �����������
        JPanel contents = new JPanel();
        contents.add(spinner1);
        contents.add(spinner2);
        setContentPane(contents);
        // ������� ���� �� �����
        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new UsingSpinnerModels();
    }
}