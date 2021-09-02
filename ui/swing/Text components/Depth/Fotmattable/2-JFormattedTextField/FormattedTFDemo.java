// Использование поля редактирования с поддержкой форматирования.

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.text.DateFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.Date;
import javax.swing.*;
import javax.swing.text.MaskFormatter;

public class FormattedTFDemo
{
    private NumberFormat cf;
    private DateFormat df;
    private JLabel jlab;
    private JFormattedTextField jftfSalary;
    private JFormattedTextField jftfDate;
    private JFormattedTextField jftfEmpID;
    private JButton jbtnShow;

    public FormattedTFDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("JFormattedTextField");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());

        // Установка начальных размеров фрейма.
        jfrm.setSize(240, 270);

        // Завершение программы при закрытии окна пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание метки.
        jlab = new JLabel();

        // Создание поля редактирования форматированного текста, применяемого для ввода
        // идентификационных номеров. Данный компонент использует формат маски.
        try
        {
            MaskFormatter mf = new MaskFormatter("##-###");
            jftfEmpID = new JFormattedTextField(mf);
        }
        catch (ParseException exc)
        {
            System.out.println("Invalid Format");
            return;
        }
        jftfEmpID.setColumns(15);
        jftfEmpID.setValue("24-895");

        // Создание поля редактирования форматированного текста, применяемого для работы с
        // финансовой информацией. Данный компонент использует формат денежных единиц.
        cf = NumberFormat.getCurrencyInstance();
        cf.setMaximumIntegerDigits(5);
        cf.setMaximumFractionDigits(2);
        jftfSalary = new JFormattedTextField(cf);
        jftfSalary.setColumns(15);
        jftfSalary.setValue(new Integer(7000));

        // Создание поля редактирования форматированного текта, применяемого для ввода даты.
        df = DateFormat.getDateInstance(DateFormat.MEDIUM);
        jftfDate = new JFormattedTextField(df);
        jftfDate.setColumns(15);
        jftfDate.setValue(new Date()); // Инициализация текущей датой.

        // Связывание обработчика изменения свойств с полем ввода идентификационного номера.
        jftfEmpID.addPropertyChangeListener("value", new PropertyChangeListener()
        {
            @Override
            public void propertyChange(PropertyChangeEvent pe)
            {
                // Метод получает управление при изменении значения компонента.
                jlab.setText("Employee ID changed.");
            }
        });

        // Связывание обработчика изменения свойств с полем для работы с информацией о зарплате.
        jftfSalary.addPropertyChangeListener("value", new PropertyChangeListener()
        {
            @Override
            public void propertyChange(PropertyChangeEvent pe)
            {
                // Метод получает управление при изменении значения компонента.
                jlab.setText("Monthly salary changed.");
            }
        });

        // Связывание обработчика изменения свойств с полем для работы с датами.
        jftfDate.addPropertyChangeListener("value", new PropertyChangeListener()
        {
            @Override
            public void propertyChange(PropertyChangeEvent pe)
            {
                // Метод получает управление при изменении значения компонента.
                jlab.setText("Date hired changed.");
            }
        });

        // Создание кнопки Show Updates.
        jbtnShow = new JButton("Show Updates");

        // Связывание обработчика событий с кнопкой Show Updates.
        jbtnShow.addActionListener(new ActionListener()
        {
            @Override
            public void actionPerformed(ActionEvent le)
            {
                // Отображение форматированных данных. Заметьте, что информация о заработной
                // плате и дате форматируется с помощью тех же объектов, которые используются в
                // соответствующих полях. Идентификационный номер сотрудника уже сформатирован.
                jlab.setText("<html>Employee ID: "
                    + jftfEmpID.getValue()
                    + "<br />Monthly Salary: "
                    + cf.format(jftfSalary.getValue())
                    + "<br />Date Hired: "
                    + df.format(jftfDate.getValue()));
            }
        });

        // Включение компонентов в состав панели содержимого.
        jfrm.getContentPane().add(new JLabel("Employee ID"));
        jfrm.getContentPane().add(jftfEmpID);
        jfrm.getContentPane().add(new JLabel("Monthly Salary"));
        jfrm.getContentPane().add(jftfSalary);
        jfrm.getContentPane().add(new JLabel("Date Hired"));
        jfrm.getContentPane().add(jftfDate);
        jfrm.getContentPane().add(jbtnShow);
        jfrm.getContentPane().add(jlab);

        // Отображение фрейма.
        jfrm.setVisible(true);
    }

    public static void main(String args[])
    {
        // Фрейм создается в потоке обработки событий.
        SwingUtilities.invokeLater(new Runnable()
        {
            @Override
            public void run()
            {
                new FormattedTFDemo();
            }
        });
    }
}