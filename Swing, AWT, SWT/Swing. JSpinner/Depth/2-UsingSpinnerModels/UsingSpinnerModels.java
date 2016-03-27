// Использование стандартных моделей счетчика
import javax.swing.*;

public class UsingSpinnerModels extends JFrame
{
    // Набор данных для счетчика
    private String[] data =
    {
        "Холодно",
        "Прохладно",
        "Тепло",
        "Жарко"
    };

    public UsingSpinnerModels()
    {
        super("Using Spinner Models");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Модель счетчика для выбора из набора данных
        SpinnerModel list = new SpinnerListModel(data);
        JSpinner spinner1 = new JSpinner(list);
        // Модель счетчика для выбора целых чисел
        SpinnerModel numbers = new SpinnerNumberModel(4, 0, 100, 1);
        JSpinner spinner2 = new JSpinner(numbers);
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
        new UsingSpinnerModels();
    }
}