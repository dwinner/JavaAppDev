/*
 * Использование стандартной модели при создании таблицы.
 */

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class UsingDefaultTableModel extends JFrame
{
    // Наша модель
    private DefaultTableModel dtm;

    public UsingDefaultTableModel() throws HeadlessException
    {
        super("Default Table Model");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создаем стандартную модель
        dtm = new DefaultTableModel();
        // Задаем названия столбцов
        dtm.setColumnIdentifiers(new String[]
            {
                "Номер", "Товар", "Цена"
            });
        // Наполняем модель данными
        dtm.addRow(new String[]
            {
                "№1", "Блокнот", "5"
            });
        dtm.addRow(new String[]
            {
                "№2", "Телефон", "175"
            });
        dtm.addRow(new String[]
            {
                "№3", "Карандаш", "1.2"
            });
        // Передаем модель в таблицу
        JTable table = new JTable(dtm);

        // Данные могут меняться динамически
        JButton add = new JButton("Добавить");
        add.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // Добавляем новые данные
                dtm.addRow(new String[]
                    {
                        "?", "Новинка!", "Супер цена!"
                    });
            }
        });

        JButton remove = new JButton("Удалить");
        remove.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent e)
            {
                // Удаляем последнюю строку (отсчет с нуля)
                dtm.removeRow(dtm.getRowCount() - 1);
            }
        });

        // Добавляем кнопки и таблицу в панель содержимого
        getContentPane().add(new JScrollPane(table));
        JPanel buttons = new JPanel();
        buttons.add(add);
        buttons.add(remove);
        getContentPane().add(buttons, BorderLayout.SOUTH);

        // Выводим окно на экран
        setSize(200, 300);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new UsingDefaultTableModel();
    }
}