// События раскрывающегося списка
import java.awt.EventQueue;
import javax.swing.*;
import java.awt.event.*;

public class ComboBoxEvents extends JFrame
{    
    // Данные для списков
    private String[] data =
    {
        "Испания",
        "Италия",
        "Египет",
        "Гаваи"
    };
    
    public ComboBoxEvents()
    {
        super("ComboBox Events");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Первый список
        JComboBox combo1 = new JComboBox(data);
        
        // Слушатель смены выбранного элемента
        combo1.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent e)
            {
                // Выясняем, что случилось
                if (e.getStateChange() == ItemEvent.SELECTED)
                {
                    // Показываем выбранный элемент
                    final Object item = e.getItem();
                    EventQueue.invokeLater(new Runnable()
                    {
                        public void run()
                        {
                            JOptionPane.showMessageDialog(ComboBoxEvents.this, item);
                        }
                    });
                }
            } 
        });
        
        // Список, допускающий редактирование
        final JComboBox combo2 = new JComboBox(data);
        combo2.setEditable(true);
        
        // Слушатель окончания редактирования
        combo2.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent e)
            {
                // Показываем выбор пользователя
                final Object item = combo2.getModel().getSelectedItem();
                // Прячем выпадающее меню
                combo2.hidePopup();
				// Выпадающее меню убрали, так что в принципе в EventQueue уже нет необходимости
                /*EventQueue.invokeLater(new Runnable() {
                    public void run() {
                        */JOptionPane.showMessageDialog(ComboBoxEvents.this, item);/*
                    }
                });*/
            }            
        });
        
        // Добавляем списки в окно
        JPanel contents = new JPanel();
        contents.add(combo1);
        contents.add(combo2);
        setContentPane(contents);
        // Выводим окно на экран
        setSize(350, 250);
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new ComboBoxEvents();
    }

}