package game3d;

import java.awt.*;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

/**
 * ControlPanel1 - это панель JPanel, которая содержит элементы управления Swing
 * для манипуляции объектом Java3DWorld1.
 * @author dwinner@inbox.ru
 */
public class ControlPanel1 extends JPanel {
    
    private static final long serialVersionUID = 1L;
    private static final Logger LOG = Logger.getLogger(ControlPanel1.class.getName());
    
    private static final int CONTAINER_WIDTH = 250;
    private static final int CONTAINER_HEIGHT = 150;
    
    private static final int NUMBER_OF_SHAPES = 20;
    
    // Элемент JSlider для управления цветом освещения
    private JSlider numberSlider;
    private Java3DWorld1 java3DWorld1;

    public ControlPanel1(Java3DWorld1 tempJ3DWorld) {
        java3DWorld1 = tempJ3DWorld;
        
        // Сборка панели управления цветом освещения
        JPanel colorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        TitledBorder colorBorder = new TitledBorder("How Many Shapes?");
        colorBorder.setTitleJustification(TitledBorder.CENTER);
        colorPanel.setBorder(colorBorder);
        
        JLabel numberLabel = new JLabel("Number of Shapes");
        // Создание компонента JSlider для задания числа летающих предметов
        numberSlider = new JSlider(SwingConstants.HORIZONTAL, 0, NUMBER_OF_SHAPES, 1);
        numberSlider.setMajorTickSpacing(5);
        numberSlider.setPaintTicks(true);
        numberSlider.setPaintTrack(true);
        numberSlider.setPaintLabels(true);
        
        // Создание слушателя ChangeListener для объектов JSlider
        ChangeListener slideListener = new ChangeListener() {
            // Вызывается при перемещении ползунка регулятора
            @Override public void stateChanged(ChangeEvent e) {
                java3DWorld1.switchScene(numberSlider.getValue(), NUMBER_OF_SHAPES);
            }           
        };
        
        // Добавление слушателя к регуляторам
        numberSlider.addChangeListener(slideListener);
        
        // Добавление компонентов управления цветом освещения в панель colorPanel
        colorPanel.add(numberLabel);
        colorPanel.add(numberSlider);
        add(colorPanel);
        
        // Задание менеджера размещения GridLayout
        setLayout(new GridLayout(2, 1, 0, 20));
    }
    
    @Override public Dimension getPreferredSize() { // Возврат предпочтительных размеров контейнера
        return new Dimension(CONTAINER_WIDTH, CONTAINER_HEIGHT);
    }
    
    @Override public Dimension getMinimumSize() {   // Возврат минимальных размеров контейнера
        return getPreferredSize();
    }
        
}
