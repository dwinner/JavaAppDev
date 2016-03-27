// Использование двух кнопок-переключателей
  
import java.awt.*;  
import java.awt.event.*;  
import javax.swing.*;  
   
public class TwoTBDemo implements ItemListener
{
    private JLabel jlabAlpha;
    private JLabel jlabBeta;
    private JToggleButton jtbnAlpha;
    private JToggleButton jtbnBeta;
  
    public TwoTBDemo()
    {
        // Создание нового контейнера JFrame.
        JFrame jfrm = new JFrame("Two Toggle Buttons");

        // Установка диспетчера компоновки FlowLayout.
        jfrm.getContentPane().setLayout(new FlowLayout());
  
        // Установка начальных размеров фрейма.
        jfrm.setSize(290, 80);

        // Завершение программы при закрытии приложения пользователем.
        jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Создание двух меток.
        jlabAlpha = new JLabel("Alpha is off.  ");
        jlabBeta = new JLabel("Beta is off.");

        // Создание двух кнопок-переключателей.
        jtbnAlpha =  new JToggleButton("Alpha");
        jtbnBeta =  new JToggleButton("Beta");

        // Определение обработчиков событий для кнопок.
        jtbnAlpha.addItemListener(this);
        jtbnBeta.addItemListener(this);

        // Включение кнопок-переключателей и меток в состав панели содержимого.
        jfrm.getContentPane().add(jtbnAlpha);
        jfrm.getContentPane().add(jlabAlpha);
        jfrm.getContentPane().add(jtbnBeta);
        jfrm.getContentPane().add(jlabBeta);

        // Отображение фрейма.
        jfrm.setVisible(true);
    }
 
    // Обработка событий элемента для обеих кнопок.
    public void itemStateChanged(ItemEvent ie)
    {
        // Получение ссылки на кнопку, являющуюся источником события.
        JToggleButton tb = (JToggleButton) ie.getItem();
        String tmpStateString = "";
        // Определение кнопки, состояние которой было изменено, посредством ссылки.
        if (tb == jtbnAlpha)
        {
            tmpStateString = tb.isSelected() ? "Alpha is on.  " : "Alpha is off.  ";
            jlabAlpha.setText(tmpStateString);
        }
        else if (tb == jtbnBeta)
        {
            tmpStateString = tb.isSelected() ? "Beta is on.  " : "Beta is off.  ";
            jlabBeta.setText(tmpStateString);
        }
    }
  
    public static void main(String args[])
    {
        // Создание фрейма в потоке обработки событий
        SwingUtilities.invokeLater(new Runnable()
        {
            public void run()
            {
                new TwoTBDemo();
            }
        });
    }
}