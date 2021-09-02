// Демонстрация работы кнопки-переключателя
  
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
   
public class ToggleDemo
{
    private JLabel jlab;
    private JToggleButton jtbn;
  
    public ToggleDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Demonstrate a Toggle Button");  
  
        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout()); 
  
        // Установка начальных размеров фрейма.
        jfrm.setSize(290, 80);  
  
        // Завершение программы при закрытии приложения пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
  
        // Создание метки, отображающей строку текста.
        jlab = new JLabel("Button is off.");  
 
        // Создание кнопки-переключателя.
        jtbn =  new JToggleButton("On/Off"); 
 
        // Установка обработчика события элемента jtbn, выполненного в виде неименованного
        // внутреннего класса. Для обработки событий кнопки-переключателя используется ItemListener.
        jtbn.addItemListener(new ItemListener()
        {
            public void itemStateChanged(ItemEvent ie)
            {
                // Для определения состояния кнопки используется метод isSelected()
                jlab.setText("Button is " + (jtbn.isSelected() ? "on." : "off."));
            }
        });
  
        // Включение кнопки-переключателя и метки в состав панели содержимого.
        jfrm.getContentPane().add(jtbn);   
        jfrm.getContentPane().add(jlab);  
 
        // Отображение кнопки.
        jfrm.setVisible(true);
    }
 
    public static void main(String args[])
    {
        // Создание кнопки в потоке обработки событий.
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new ToggleDemo();
            }
        });
    }
}