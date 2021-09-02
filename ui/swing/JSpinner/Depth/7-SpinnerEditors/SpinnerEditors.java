// ����������� ��������� ��������
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;

public class SpinnerEditors extends JFrame
{
    // ������ ��� ������� ��������
    private String[] data =
    {
        "������",
        "������",
        "���������"
    };

    public SpinnerEditors()
    {
        super("Spinner Editors");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // ������� �� ������ �������
        JSpinner spinner1 = new JSpinner(new SpinnerListModel(data));
        
        // ����������� �������� ������
        ((JSpinner.ListEditor) spinner1.getEditor()).getTextField().setColumns(15);
        
        // ����� ���
        SpinnerDateModel dates = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        JSpinner spinner2 = new JSpinner(dates);
        
        // ����������� �������� ���
        ((JSpinner.DateEditor) spinner2.getEditor()).getTextField().setEditable(false);
        
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
        new SpinnerEditors();
    }
}