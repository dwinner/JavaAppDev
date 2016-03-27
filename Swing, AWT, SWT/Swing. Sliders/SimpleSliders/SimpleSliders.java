// Простые ползунки
import javax.swing.*;

public class SimpleSliders extends JFrame
{ 
    public SimpleSliders()
    {
        super("Simple Sliders");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Создаем несколько ползунков
        JSlider s1 = new JSlider(0, 100);
        JSlider s2 = new JSlider(JSlider.VERTICAL, 0, 200, 10);
        // Настройка внешнего вида
        s2.setPaintTicks(true);
        s2.setMajorTickSpacing(50);
        s2.setMinorTickSpacing(10);
        JSlider s3 = new JSlider(0, 50, 40);
        s3.setPaintLabels(true);
        s3.setMajorTickSpacing(10);
        // Добавляем их в панель содержимого
        JPanel contents = new JPanel();
        contents.add(s1);
        contents.add(s2);
        contents.add(s3);
        setContentPane(contents);
        // Выводим окно на экран
        setSize(100, 300);
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new SimpleSliders();
    }

}