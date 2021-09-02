// Использование в ползунках стандартной модели
import javax.swing.*;

public class SliderDefaultModel extends JFrame
{
    // Наша модель
    private BoundedRangeModel model;
    
    public SliderDefaultModel()
    {
        super("Slider Default Model");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Создаем модель и пару ползунков
        model = new DefaultBoundedRangeModel(10, 0, 0, 100);
        JSlider slider1 = new JSlider(model);
        JSlider slider2 = new JSlider(JSlider.VERTICAL);
        slider2.setModel(model);
        
        // Добавляем ползунки в окно
        JPanel contents = new JPanel();
        contents.add(slider1);
        contents.add(slider2);
        setContentPane(contents);
        
        // Выводим окно на экран
        setSize(300, 300);
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new SliderDefaultModel();
    }

}