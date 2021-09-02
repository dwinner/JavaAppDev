// Обработка событий от кнопок
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;

public class ButtonEvents extends JFrame
{    
    private JTextArea info;
    private JButton button;
    
    public ButtonEvents()
    {
        super("Button Events");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Получаем панель содержимого
        Container c = getContentPane();
        
        // Создаем кнопку и помещаем её на север окна
        button = new JButton("Нажмите на меня!");
        c.add(button, BorderLayout.NORTH);
        
        // Поле для вывода сообщений о событиях
        info = new JTextArea("Пока событий не было\n");
        c.add(new JScrollPane(info));
        
        // Привязываем к нашей кнопке слушателей событий
        // Слушатели описаны как внутренние классы
        button.addActionListener(new ActionL());
        button.addChangeListener(new ChangeL());
        
        // Присоединяем слушателя прямо на месте
        button.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent ie)
            {
                info.append("Это сообщение вы никогда не увидите");
            }
        });
        
        // Выводим окно на экран
        setSize(400, 300);
        setVisible(true);
    }
    
    private class ActionL implements ActionListener
    {
        public void actionPerformed(ActionEvent ae)
        {
            info.append("Получено сообщение о нажатии кнопки! От - " + ae.getActionCommand() + "\n");
        }
    }
    
    private class ChangeL implements ChangeListener
    {
        public void stateChanged(ChangeEvent ce)
        {
            info.append("Получено сообщение о смене состояния кнопки!\n");
        }
    }
    
    public static void main(String[] args)
    {
        new ButtonEvents();
    }

}