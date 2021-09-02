/*
 * Архитектура MVC, использующая объект JTable с моделью DefaultTableModel.
 */

import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

/**
 * Манипуляция данными через модель таблицы.
 * @author oracle_pr1
 */
public class PhilosophersJTable extends JFrame
{
    private DefaultTableModel philosophers;
    private JTable table;

    // Конструктор PhilosophersJTable
    public PhilosophersJTable()
    {
        super("Favorite Philosophers");

        // Создание модели DefaultTableModel для хранения философов
        philosophers = new DefaultTableModel();

        // Добавление столбцов в DefaultTableModel
        philosophers.addColumn("First Name");
        philosophers.addColumn("Last Name");
        philosophers.addColumn("Years");

        // Добавление имен и дат в DefaultTableModel
        String[] socrates =
        {
            "Socrates", "", "469-399 B.C."
        };
        philosophers.addRow(socrates);
        String[] plato =
        {
            "Plato", "", "428-347 B.C."
        };
        philosophers.addRow(plato);
        String[] aquinas =
        {
            "Thomas", "Aquinas", "1225-1274"
        };
        philosophers.addRow(aquinas);
        String[] kierkegaard =
        {
            "Soren", "Kierkegaard", "1813-1855"
        };
        philosophers.addRow(kierkegaard);
        String[] kant =
        {
            "Immanuel", "Kant", "1724-1804"
        };
        philosophers.addRow(kant);
        String[] nietzsche =
        {
            "Friedrich", "Nietzsche", "1844-1900"
        };
        philosophers.addRow(nietzsche);
        String[] arendt =
        {
            "Hannah", "Arendt", "1906-1975"
        };
        philosophers.addRow(arendt);

        // Создание таблицы JTable для отображения философов из DefaultTableModel
        table = new JTable(philosophers);

        // Создание кнопки JButton для добавления философов
        JButton addButton = new JButton("Add Philosopher");
        addButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent ae)
            {
                // Создание пустого массива для нового философа
                String[] philosopher =
                {
                    "", "", ""
                };
                // Добавление в модель пустой строки для философа
                philosophers.addRow(philosopher);
            }
        });

        // Создание кнопки JButton для удаления выбранного философа
        JButton removeButton = new JButton("Remove Selected Philosopher");
        removeButton.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // Удаление выбранного философа из модели
                int[] selected = table.getSelectedRows();
                if (selected.length > 0)
                {
                    for (int i : selected)
                    {
                        int translate = table.convertRowIndexToModel(i);
                        philosophers.removeRow(translate);
                    }
                }
            }
        });

        // Размещение компонентов пользовательского интерфейса
        JPanel inputPanel = new JPanel();
        inputPanel.add(addButton);
        inputPanel.add(removeButton);

        Container contentPane = getContentPane();
        contentPane.add(new JScrollPane(table), BorderLayout.CENTER);
        contentPane.add(inputPanel, BorderLayout.NORTH);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 300);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new PhilosophersJTable();
    }
}