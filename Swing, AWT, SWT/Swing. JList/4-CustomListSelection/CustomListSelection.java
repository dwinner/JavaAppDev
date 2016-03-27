// Реализация особого режима выделения
import javax.swing.DefaultListSelectionModel;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JScrollPane;

public class CustomListSelection extends JFrame
{
    private String[] data =
    {
        "Мороженое",
        "Курица",
        "Холодное",
        "Горячее"
    };

    public CustomListSelection()
    {
        super("Custom list selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Настраиваем список и добавляем его в окно
        JList<String> list = new JList<>(data);
        list.setSelectionModel(new CustomSelectionModel());
        getContentPane().add(new JScrollPane(list));
        
        // Выводим окно на экран
        setSize(100, 200);
        setVisible(true);
    }

    /**
     * Специальная модель выделения.
     */
    private static class CustomSelectionModel extends DefaultListSelectionModel
    {
        // Добавление интервала выделения
        @Override
        public void addSelectionInterval(int idx1, int idx2)
        {
            super.addSelectionInterval(idx1, idx2);
            if (idx1 == 0)
            {
                addSelectionInterval(2, 2);
            }
            if (idx1 == 1)
            {
                addSelectionInterval(3, 3);
            }
        }

        // Установка интервала выделения
        @Override
        public void setSelectionInterval(int index1, int index2)
        {
            super.setSelectionInterval(index1, index2);
            if (index1 == 0)
            {
                addSelectionInterval(2, 2);
            }
            if (index2 == 1)
            {
                addSelectionInterval(3, 3);
            }
        }
    }

    public static void main(String[] args)
    {
        new CustomListSelection();
    }
}