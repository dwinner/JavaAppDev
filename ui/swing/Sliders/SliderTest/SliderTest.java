import java.awt.*;
import java.util.*;
import javax.swing.*;
import javax.swing.event.*;

/**
 * Демонстрация возможностей Слайдера.
 * @version 1.13 2007-06-12
 * @author Cay Horstmann
 */
public class SliderTest
{
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                SliderTestFrame frame = new SliderTestFrame();

                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setVisible(true);
            }
        });
    }
}


/**
 * Фрейм с линейными регуляторами и полем редактирования,
 * отображающим текущее значение выбранного регулятора.
 */
class SliderTestFrame extends JFrame
{
    public static final int DEFAULT_HEIGHT = 450;
    public static final int DEFAULT_WIDTH  = 350;
    private ChangeListener  listener;
    private JPanel          sliderPanel;
    private JTextField      textField;

    public SliderTestFrame()
    {
        setTitle("SliderTest");
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        sliderPanel = new JPanel();
        sliderPanel.setLayout(new FlowLayout(FlowLayout.LEFT));

        // Общий слушатель для всех регуляторов.
        listener = new ChangeListener()
        {
            public void stateChanged(ChangeEvent event)
            {
                // Обновление поля редактирования при изменении значения регулятора.
                JSlider source = (JSlider) event.getSource();

                textField.setText("" + source.getValue());
            }
        };

        // Добавление простого регулятора.
        JSlider slider = new JSlider();

        addSlider(slider, "Plain");

        // Добавление регулятора с основными и вспомогательными делениями.
        
        slider = new JSlider();
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        addSlider(slider, "Ticks");
        
        // Добавление регулятора с привязкой к делениям.
        
        slider = new JSlider();
        slider.setPaintTicks(true);
        slider.setSnapToTicks(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        addSlider(slider, "Snap to ticks");
        
        // Добавление регулятора без "дорожки"
        
        slider = new JSlider();
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setPaintTrack(false);
        addSlider(slider, "No track");
        
        // Добавление регулятора с обратным направлением движения.
        
        slider = new JSlider();
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        slider.setInverted(true);
        addSlider(slider, "Inverted");
        
        // Добавление регулятора с числовыми метками.
        
        slider = new JSlider();
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        addSlider(slider, "Labels");
        
        // Добавление регулятора с буквенными метками.
        
        slider = new JSlider();
        slider.setPaintLabels(true);
        slider.setPaintTicks(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(5);
        
        Dictionary<Integer, Component> labelTable = new Hashtable<Integer, Component>();
        labelTable.put(0, new JLabel("A"));
        labelTable.put(20, new JLabel("B"));
        labelTable.put(40, new JLabel("C"));
        labelTable.put(60, new JLabel("D"));
        labelTable.put(80, new JLabel("E"));
        labelTable.put(100, new JLabel("F"));
        
        slider.setLabelTable(labelTable);
        addSlider(slider, "Custom Labels");
        
        // Добавление регулятора с метками в виде пиктограмм.
        
        slider = new JSlider();
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setSnapToTicks(true);
        slider.setMajorTickSpacing(20);
        slider.setMinorTickSpacing(20);
        
        labelTable = new Hashtable<Integer, Component>();
        
        // Добавление изображений карт.
        
        labelTable.put(0, new JLabel(new ImageIcon("nine.gif")));
        labelTable.put(20, new JLabel(new ImageIcon("ten.gif")));
        labelTable.put(40, new JLabel(new ImageIcon("jack.gif")));
        labelTable.put(60, new JLabel(new ImageIcon("queen.gif")));
        labelTable.put(80, new JLabel(new ImageIcon("king.gif")));
        labelTable.put(100, new JLabel(new ImageIcon("ace.gif")));
        
        slider.setLabelTable(labelTable);
        addSlider(slider, "Icon labels");
        
        // Добавление поля редактирования для отображения значений регулятора.
        
        textField = new JTextField();
        add(sliderPanel, BorderLayout.CENTER);
        add(textField, BorderLayout.SOUTH);
    }

    /**
     * Добавление регулятора в панель и связывание его со слушателем.
     * @param s Регулятор
     * @param description Описание регулятора
     */
    public void addSlider(JSlider s, String description)
    {
        s.addChangeListener(listener);

        JPanel panel = new JPanel();

        panel.add(s);
        panel.add(new JLabel(description));
        sliderPanel.add(panel);
    }
}
