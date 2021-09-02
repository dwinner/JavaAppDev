// MVC, использующая объект JList с моделью DefaultListModel

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * Dummy demo of DefaultListModel
 * <p/>
 * @author dwinner@inbox.ru
 */
public class PhilosophersJList extends JFrame
{
    private static final long serialVersionUID = 1L;
    private DefaultListModel<String> philosophers;
    private JList<String> list;

    // Конструктор PhilosophersJList
    public PhilosophersJList()
    {
        super("Favorite Philosophers");

        // Создание модели DefaultListModel для хранения философов
        philosophers = new DefaultListModel<>();
        philosophers.addElement("Socrates");
        philosophers.addElement("Platon");
        philosophers.addElement("Aristotel");
        philosophers.addElement("St. Thomas Aquinas");
        philosophers.addElement("Soren Kierkegaard");
        philosophers.addElement("Immanuel Kant");
        philosophers.addElement("Friedrich Nietzsche");
        philosophers.addElement("Hannah Arendt");
        
        // Создание объекта JList для отображения философов из DefaultListModel
        list = new JList<>(philosophers);
        
        // Разрешить выбирать только одного философа за раз
        list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);

        // Создание кнопки JButton для добавления философов
        JButton addButton = new JButton("Add Philosopher");
        addButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                // Запрос у пользователя имени нового философа
                String name = JOptionPane.showInputDialog(PhilosophersJList.this, "Enter Name");
                // Добавление нового философа в модель
                philosophers.addElement(name);
            }
        });

        // Создание кнопки JButton для удаления выбранного философа
        JButton removeButton = new JButton("Remove Selected Philosopher");
        removeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                // Удаление выбранного философа из модели
                philosophers.removeElement(list.getSelectedValue());
            }
        });

        // Размещение компонентов пользовательского интерфейса
        JPanel inputPanel = new JPanel();
        inputPanel.add(addButton);
        inputPanel.add(removeButton);

        Container contentPane = getContentPane();
        contentPane.add(list, BorderLayout.CENTER);
        contentPane.add(inputPanel, BorderLayout.NORTH);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String args[])
    {
        new PhilosophersJList();
    }
}