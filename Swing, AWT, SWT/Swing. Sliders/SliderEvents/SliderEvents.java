// События ползунков
import java.awt.BorderLayout;
import javax.swing.*;
import javax.swing.event.*;

public class SliderEvents extends JFrame
{    
    private JLabel boost;
    
    public SliderEvents()
    {
        super("Slider Events");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Создаем ползунок и надписи
        JSlider slider = new JSlider(0, 900, 0);
        slider.setMajorTickSpacing(100);
        slider.setPaintTicks(true);
        boost = new JLabel("Ускорение: ");
        
        // Присоединяем слушателя
        slider.addChangeListener(new ChangeListener()
        {
            public void stateChanged(ChangeEvent e)
            {
                // Меняем надпись
                int value = ((JSlider) e.getSource()).getValue();
                int percent = value / 15;
                boost.setText("Ускорение: " + percent + " %");
            }
        });
        
        // Добавляем компоненты в панель
        JPanel contents = new JPanel();
        contents.add(new JLabel("Размер буфера: "));
        contents.add(slider);
        getContentPane().add(contents);
        getContentPane().add(boost, BorderLayout.SOUTH);
        
        // Выводим окно на экран
        setSize(360, 100);
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new SliderEvents();
    }

}