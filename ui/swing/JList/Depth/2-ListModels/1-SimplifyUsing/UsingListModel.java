// Использование стандартной модели списка
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class UsingListModel extends JFrame
{
    // Наша модель
    private DefaultListModel<String> dim;

    public UsingListModel()
    {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Заполняем модель данными
        dim = new DefaultListModel<>();
        dim.add(0, "Кое что");
        dim.add(1, "Кое что ещё");
        dim.add(2, "Ещё немного");

        // Создаем кнопку и пару списков
        JPanel contents = new JPanel();
        JButton add = new JButton("Обновить");
        add.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                dim.add(0, "Новинка!");
                validate();
            }
        });
        JList<String> list1 = new JList<>(dim);
        JList<String> list2 = new JList<>(dim);

        // Добавляем компоненты
        contents.add(add);
        contents.add(new JScrollPane(list1));
        contents.add(new JScrollPane(list2));
        // Выводим окно на экран
        setContentPane(contents);
        setSize(400, 200);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new UsingListModel();
    }
}