// Слежение за выделением в списке
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class ListSelection extends JFrame
{
    // Данные списка
    private String[] data =
    {
        "Red",
        "Yellow",
        "Green"
    };
    private JTextArea jta;

    public ListSelection()
    {
        super("List Selection");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Создаем список и текстовое поле
        JPanel contents = new JPanel();
        JList<String> list = new JList<>(data);
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jta = new JTextArea(5, 20);
        
        // Добавим слушателя событий выделения
        list.addListSelectionListener(new SelectionL());
        
        // Добавляем компоненты
        contents.add(new JScrollPane(list));
        contents.add(new JScrollPane(jta));
        
        // Выводим окно на экран
        setContentPane(contents);
        setSize(100, 200);
        setVisible(true);
    }

    private class SelectionL implements ListSelectionListener
    {
        @Override
        public void valueChanged(ListSelectionEvent e)
        {
            int selected = ((JList) e.getSource()).getSelectedIndex();
            switch (selected)
            {
                case 0:
                    jta.setText("Переходить нельзя");
                    break;
                case 1:
                    jta.setText("Будьте готовы");
                    break;
                case 2:
                    jta.setText("Переходите улицу");
                    break;
            }
        }
    }

    public static void main(String[] args)
    {
        new ListSelection();
    }
}