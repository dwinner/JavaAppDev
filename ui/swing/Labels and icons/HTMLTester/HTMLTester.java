// Позволяет легко просмотреть результат использования HTML на компонентах Swing
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class HTMLTester extends JFrame
{
    private JTextArea html;
    private JLabel result;
    private JButton update;
    
    public HTMLTester()
    {
        super("HTML Tester");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Создаем интерфейс и настроим события
        createGUI();
        attachListeners();
        // Выводим окно на экран
        setSize(400, 300);
        setVisible(true);
    }
    
    private void createGUI()
    {
        // В качестве основы используем таблицу
        JPanel p = new JPanel(new GridLayout(0, 2, 5, 5));
        p.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
        // Вертикальное блочное расположение
        Box vertical = new Box(BoxLayout.Y_AXIS);
        // Настройка текстового поля
        JScrollPane scroller = new JScrollPane(html = new JTextArea(10, 10));
        html.setLineWrap(true);
        html.append("<html>");
        // Добавляем текстовое поле и надпись
        vertical.add(new JLabel("Код HTML"));
        vertical.add(scroller);
        // Кнопка обновления текста
        update = new JButton("Обновить");
        getContentPane().add(update, BorderLayout.SOUTH);
        // Надпись с результатом
        JPanel resultPanel = new JPanel(new BorderLayout());
        result = new JLabel();
        resultPanel.add(new JLabel("Результат"), BorderLayout.NORTH);
        resultPanel.add(new JScrollPane(result));
        // Укладываем панели
        p.add(vertical);
        p.add(resultPanel);
        getContentPane().add(p);
    }
    
    private void attachListeners()
    {
        update.addActionListener(new ActionListener()
        {
            public void actionPerformed(ActionEvent ae)
            {
                // Устанавливаем в надписи новый текст
                result.setText(html.getText());
            }
        });
    }
    
    public static void main(String[] args)
    {
        new HTMLTester();
    }

}