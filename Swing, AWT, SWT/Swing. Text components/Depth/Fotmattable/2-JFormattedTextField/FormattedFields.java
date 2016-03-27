// Применение полей JFormattedTextField
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
    // Поля для форматированного ввода данных
    private JFormattedTextField phoneField,
        dateField,
        numberField;

    public FormattedFields()
    {
        super("Formatted Fields");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Ограниченный ввод на основе маски

        try
        {   // Телефонный номер
            MaskFormatter phone = new MaskFormatter("+#-###-###-##-##");
            phone.setPlaceholderCharacter('0');
            phoneField = new JFormattedTextField(phone);
            phoneField.setColumns(16);
        }
        catch (ParseException ex)
        {
            ex.printStackTrace();
        }

        // Редактирование даты.
        // Формат даты
        DateFormat date = DateFormat.getDateInstance();
        // Настройка форматирующего объекта
        DateFormatter formatter = new DateFormatter(date);
        formatter.setAllowsInvalid(false);
        formatter.setOverwriteMode(true);
        // Настройка текстового поля
        dateField = new JFormattedTextField(formatter);
        dateField.setColumns(15);
        dateField.setValue(new Date());

        // Редактирование чисел
        // Формат числа с экспонентой
        NumberFormat number = new DecimalFormat("##0.##E0");
        numberField = new JFormattedTextField(new NumberFormatter(number));
        // Настройка поля
        numberField.setColumns(10);
        numberField.setValue(new Integer(1500));

        // Добавляем поля в панель содержимого
        JPanel contents = new JPanel();
        contents.setLayout(new GridLayout(0, 2));
        contents.add(new JPanel(new FlowLayout(FlowLayout.LEFT)).add(new JLabel("Телефон:")));
        contents.add(new JPanel(new FlowLayout(FlowLayout.LEFT)).add(phoneField));
        contents.add(new JPanel(new FlowLayout(FlowLayout.LEFT)).add(new JLabel("Дата:")));
        contents.add(new JPanel(new FlowLayout(FlowLayout.LEFT)).add(dateField));
        contents.add(new JPanel(new FlowLayout(FlowLayout.LEFT)).add(new JLabel("Число:")));
        contents.add(new JPanel(new FlowLayout(FlowLayout.LEFT)).add(numberField));
        setContentPane(contents);
        // Выводим окно на экран
        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new FormattedFields();
    }
}