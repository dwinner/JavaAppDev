// Простейший способ создания списков
import java.util.Arrays;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class SimpleList extends JFrame
{
    // Данные для списков
    private String[] data1 =
    {
        "One",
        "Two",
        "Three",
        "Four",
        "Five"
    };
    private String[] data2 =
    {
        "Very Easy",
        "Easy",
        "Normal",
        "Hard"
    };

    public SimpleList()
    {
        super("Simple Lists");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Создаем списки
        JPanel contents = new JPanel();
        JList<String> list1 = new JList<>(data1);
        
        // Для второго списка используем вектор
        Vector<String> data = new Vector<>();
        data.addAll(Arrays.asList(data2));
        JList list2 = new JList(data);
        
        // Динамически наполняем вектор
        Vector<String> big = new Vector<String>();
        for (int i = 7; i < 50; i++)
        {
            big.add("# " + i);
        }
        JList<String> bigList = new JList<>(big);
        bigList.setPrototypeCellValue("12345");
        
        // Добавляем списки в панель
        contents.add(list1);
        contents.add(list2);
        contents.add(new JScrollPane(bigList));
        
        // Выводим окно на экран
        setContentPane(contents);
        setSize(100, 200);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new SimpleList();
    }
}