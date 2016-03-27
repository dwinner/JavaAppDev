// Использование архитектуры Action
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ActionSample extends JFrame
{    
    public ActionSample()
    {
        super("Action Sample");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Получаем панель содержимого
        Container c = getContentPane();
        // Используем последовательное расположение
        c.setLayout(new FlowLayout());
        // Создадим пару кнопок, выполняющих одно действие
        Action action = new SimpleAction();
        JButton btn1 = new JButton(action);
        JButton btn2 = new JButton(action);
        c.add(btn1);
        c.add(btn2);
        // Выводим окно на экран
        setSize(300, 100);
        setVisible(true);
    }
    
    /**
     * Этот внутренний класс инкапсулирует нашу команду
     */
    private class SimpleAction extends AbstractAction
    {
        SimpleAction()
        {
            // Установим параметры команды
            putValue(Action.NAME, "Привет, Action!");
            putValue(Action.SHORT_DESCRIPTION, "Это подсказка");
            putValue(Action.MNEMONIC_KEY, new Integer('A'));
        }
        
        public void actionPerformed(ActionEvent ae)
        {
            // Можно, к примеру выключить команду, не зная, к каким компонентам
            // она присоединена
            setEnabled(false);
            // Изменим надпись
            putValue(Action.NAME, "Прощай, Action!");
        }
    }
    
    public static void main(String[] args)
    {
        new ActionSample();
    }

}