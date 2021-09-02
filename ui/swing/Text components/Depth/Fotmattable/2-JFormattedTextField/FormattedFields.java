// ���������� ����� JFormattedTextField
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.text.DateFormatter;
import javax.swing.text.MaskFormatter;
import javax.swing.text.NumberFormatter;

public class FormattedFields extends JFrame
{
    // ���� ��� ���������������� ����� ������
    private JFormattedTextField phoneField,
        dateField,
        numberField;

    public FormattedFields()
    {
        super("Formatted Fields");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // ������������ ���� �� ������ �����

        try
        {   // ���������� �����
            MaskFormatter phone = new MaskFormatter("+#-###-###-##-##");
            phone.setPlaceholderCharacter('0');
            phoneField = new JFormattedTextField(phone);
            phoneField.setColumns(16);
        }
        catch (ParseException ex)
        {
            ex.printStackTrace();
        }

        // �������������� ����.
        // ������ ����
        DateFormat date = DateFormat.getDateInstance();
        // ��������� �������������� �������
        DateFormatter formatter = new DateFormatter(date);
        formatter.setAllowsInvalid(false);
        formatter.setOverwriteMode(true);
        // ��������� ���������� ����
        dateField = new JFormattedTextField(formatter);
        dateField.setColumns(15);
        dateField.setValue(new Date());

        // �������������� �����
        // ������ ����� � �����������
        NumberFormat number = new DecimalFormat("##0.##E0");
        numberField = new JFormattedTextField(new NumberFormatter(number));
        // ��������� ����
        numberField.setColumns(10);
        numberField.setValue(new Integer(1500));

        // ��������� ���� � ������ �����������
        JPanel contents = new JPanel();
        contents.setLayout(new GridLayout(0, 2));
        contents.add(new JPanel(new FlowLayout(FlowLayout.LEFT)).add(new JLabel("�������:")));
        contents.add(new JPanel(new FlowLayout(FlowLayout.LEFT)).add(phoneField));
        contents.add(new JPanel(new FlowLayout(FlowLayout.LEFT)).add(new JLabel("����:")));
        contents.add(new JPanel(new FlowLayout(FlowLayout.LEFT)).add(dateField));
        contents.add(new JPanel(new FlowLayout(FlowLayout.LEFT)).add(new JLabel("�����:")));
        contents.add(new JPanel(new FlowLayout(FlowLayout.LEFT)).add(numberField));
        setContentPane(contents);
        // ������� ���� �� �����
        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new FormattedFields();
    }
}