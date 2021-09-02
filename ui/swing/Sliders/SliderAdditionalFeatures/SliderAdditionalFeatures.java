// Дополнительные возможности ползунков
import javax.swing.*;
import java.util.*;

public class SliderAdditionalFeatures extends JFrame
{    
    private BoundedRangeModel model;
    
    public SliderAdditionalFeatures()
    {
        super("SliderAdditionalFeatures");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Используем стандартную модель BoundedRangeModel
        model = new DefaultBoundedRangeModel(0, 0, 0, 60);
        
        // Таблица с надписями
        Dictionary<Integer,JLabel> labels = new Hashtable<Integer,JLabel>(0x10);
        labels.put(new Integer(0), new JLabel("<html><font color=\"red\" size=\"4\">First</font>"));
        labels.put(new Integer(10), new JLabel("<html><font color=\"green\" size=\"3\">Second</font>"));
        labels.put(new Integer(50), new JLabel("<html><font color=\"yellow\" size=\"5\">Third</font>"));
        labels.put(new Integer(60), new JLabel(new ImageIcon("flower.gif")));
        
        // Настройка первого ползунка
        JSlider slider1 = new JSlider(JSlider.VERTICAL);
        slider1.setModel(model);
        slider1.setLabelTable(labels);
        slider1.setPaintLabels(true);
        
        // "не наполняемый" инвертированный ползунок
        JSlider slider2 = new JSlider(model);
        slider2.putClientProperty("JSlider.isFilled", Boolean.FALSE);
        slider2.setInverted(true);
        slider2.setPaintTicks(true);
        slider2.setMajorTickSpacing(10);
        
        // Добавляем компоненты в окно
        JPanel contents = new JPanel();
        contents.add(slider1);
        contents.add(slider2);
        
        // Выводим окно на экран
        setContentPane(contents);
        setSize(300, 300);
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new SliderAdditionalFeatures();
    }

}