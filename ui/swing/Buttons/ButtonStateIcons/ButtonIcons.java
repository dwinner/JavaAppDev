// Отображение пиктограмм для отображения на кнопке
  
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
  
public class ButtonIcons implements ActionListener
{
  
    private JLabel jlab;   
    private JButton jbtnFirst; 
    private JButton jbtnSecond; 
  
    public ButtonIcons()
    {
        // Загрузка пиктограмм, используемых JButton.
        ImageIcon myIcon = new ImageIcon("myIcon.gif"); 
        ImageIcon myDisIcon = new ImageIcon("myDisIcon.gif"); 
        ImageIcon myROIcon = new ImageIcon("myROIcon.gif"); 
        ImageIcon myPIcon = new ImageIcon("myPIcon.gif"); 
  
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Use Button Icons");  
  
        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());  
  
        // Установка начального размера фрейма.
        jfrm.setSize(220, 100);  
  
        // Завершение программы при закрытии приложения пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  
  
        // Создание текстовой метки.
        jlab = new JLabel("Press a button.");  
  
        // Создание двух кнопок. Для них задаются пиктограммы по умолчанию.
        jbtnFirst = new JButton("First", myIcon);  
        jbtnSecond = new JButton("Second", myIcon);  
 
        // Установка изображения, отображаемого при наведении на кнопку курсора мыши.
        jbtnFirst.setRolloverIcon(myROIcon); 
        jbtnSecond.setRolloverIcon(myROIcon); 
  
        // Установка изображения, отображаемого на активизированной кнопке.
        jbtnFirst.setPressedIcon(myPIcon); 
        jbtnSecond.setPressedIcon(myPIcon); 
 
        // Установка изображения, отображаемого на деактивизированной кнопке.
        jbtnFirst.setDisabledIcon(myDisIcon); 
        jbtnSecond.setDisabledIcon(myDisIcon); 
 
        // Связывание с кнопками обработчиков событий.
        jbtnFirst.addActionListener(this); 
        jbtnSecond.addActionListener(this); 
  
        // Включение кнопок в состав панели содержимого.
        jfrm.getContentPane().add(jbtnFirst);   
        jfrm.getContentPane().add(jbtnSecond);   
  
        // Включение метки в состав панели содержимого.
        jfrm.getContentPane().add(jlab);
  
        // Отображение фрейма.
        jfrm.setVisible(true);  
    }
  
    // Обработка событий кнопок
    public void actionPerformed(ActionEvent ae)
    {
     
        if (ae.getActionCommand().equals("First"))
        {
            jlab.setText("First button was pressed.");  
            if (jbtnSecond.isEnabled())
            {
                jlab.setText("Second button is disabled.");
                jbtnSecond.setEnabled(false);
            }
            else
            {
                jlab.setText("Second button is enabled.");
                jbtnSecond.setEnabled(true);
            }
        }
        else
        {
            jlab.setText("Second button was pressed. ");
        }
    }
  
    public static void main(String args[])
    {
        // Создание фрейма в потоке обработки событий.
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new ButtonIcons();
            }
        });
    }
}