// Стандартные редакторы счетчика
import java.util.Calendar;
import java.util.Date;
import javax.swing.*;

public class SpinnerEditors extends JFrame
{
    // Данные для первого счетчика
    private String[] data =
    {
        "Первый",
        "Второй",
        "Последний"
    };

    public SpinnerEditors()
    {
        super("Spinner Editors");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Счетчик на основе массива
        JSpinner spinner1 = new JSpinner(new SpinnerListModel(data));
        
        // Настраиваем редактор списка
        ((JSpinner.ListEditor) spinner1.getEditor()).getTextField().setColumns(15);
        
        // Выбор дат
        SpinnerDateModel dates = new SpinnerDateModel(new Date(), null, null, Calendar.DAY_OF_MONTH);
        JSpinner spinner2 = new JSpinner(dates);
        
        // Настраиваем редактор дат
        ((JSpinner.DateEditor) spinner2.getEditor()).getTextField().setEditable(false);
        
        // Добавляем счетчики в панель содержимого
        JPanel contents = new JPanel();
        contents.add(spinner1);
        contents.add(spinner2);
        setContentPane(contents);
        
        // Выводим окно на экран
        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new SpinnerEditors();
    }
}