package java3dexample;

import java.awt.*;
import java.awt.event.*;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

/**
 * Объект JPanel, который содержит компоненты Swing для управления объектами
 * Java3DWorld.
 * @author dwinner@inbox.ru
 */
public class ControlPanel extends JPanel {
    
    private static final Logger LOG = Logger.getLogger(ControlPanel.class.getName());
    private static final long serialVersionUID = 1L;
    
    // Компоненты JSlider управляют цветом освещения
    private JSlider redSlider, greenSlider, blueSlider;
    
    // Компоненты JCheckbox включают наложение текстур
    private JCheckBox textureCheckBox;
    
    // среда для отображения графики
    private Java3DWorld java3DWorld;
    
    // Конструктор ControlPanel
    public ControlPanel(Java3DWorld tempJ3DWorld) {
        java3DWorld = tempJ3DWorld;
        
        // сборка панели инструкций преобразования
        JPanel instructionPanel = new JPanel();
        
        TitledBorder titledBorder = new TitledBorder("Transformation Instructions");
        titledBorder.setTitleJustification(TitledBorder.CENTER);
        instructionPanel.setBorder(titledBorder);
        
        JLabel rotationInstructions = new JLabel("Rotation - Left Mouse Button", SwingConstants.CENTER);
        JLabel translationInstructions = new JLabel("Translation - Right Mouse Button", SwingConstants.CENTER);
        JLabel scalingInstructions = new JLabel("Scale - Alt + Left Mouse Button", SwingConstants.CENTER);
        
        // Добавление надписей JLabel в панель JPanel
        instructionPanel.add(rotationInstructions);
        instructionPanel.add(translationInstructions);
        instructionPanel.add(scalingInstructions);
        
        // Сборка панели управления наложением текстур
        JPanel texturePanel = new JPanel();
        
        TitledBorder textureBorder = new TitledBorder("Texture Controls");
        textureBorder.setTitleJustification(TitledBorder.CENTER);
        texturePanel.setBorder(textureBorder);
        
        textureCheckBox = new JCheckBox("Apply Texture Map to Image");
        texturePanel.add(textureCheckBox);
        
        // Создание слушателя ItemListener для JCheckBox
        textureCheckBox.addItemListener(new ItemListener() {
            // Вызывается при установке/сбросе флажка
            @Override
            public void itemStateChanged(ItemEvent event) {
                java3DWorld.updateTexture(event.getStateChange() == ItemEvent.SELECTED ? true : false);
            }
        });
        
        // Создание панели JPanel с instructionPanel и texturePanel
        JPanel topPanel = new JPanel(new GridLayout(2, 1, 0, 20));
        
        topPanel.add(instructionPanel);
        topPanel.add(texturePanel);
        
        // Сборка панели управления цветом освещения
        JPanel colorPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 15, 15));
        
        TitledBorder colorBorder = new TitledBorder("Direct Lighting Color Controls");
        
        colorBorder.setTitleJustification(TitledBorder.CENTER);
        colorPanel.setBorder(colorBorder);
        
        JLabel redLabel = new JLabel("R");
        JLabel greenLabel = new JLabel("G");
        JLabel blueLabel = new JLabel("B");
        
        // Создание компонента JSlider для регулирования красной составляющей цвета
        redSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 25);
        redSlider.setMajorTickSpacing(25);
        redSlider.setPaintTicks(true);
        
        // Создание компонента JSlider для регулирования зеленой составляющей цвета
        greenSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 25);
        greenSlider.setMajorTickSpacing(25);
        greenSlider.setPaintTicks(true);
        
        // Создание компонента JSlider для регулирования синей составляющей цвета
        blueSlider = new JSlider(SwingConstants.HORIZONTAL, 0, 255, 25);
        blueSlider.setMajorTickSpacing(25);
        blueSlider.setPaintTicks(true);
        
        // Создание слушателя ChangeListener для объектов JSlider
        ChangeListener slideListener = new ChangeListener() {
            // Вызывается при обращении к регулятору
            @Override
            public void stateChanged(ChangeEvent event) {
                Color color = new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue());
                java3DWorld.changeColor(color);
            }
        };
        
        // Добавление слушателя к регуляторам
        redSlider.addChangeListener(slideListener);
        greenSlider.addChangeListener(slideListener);
        blueSlider.addChangeListener(slideListener);
        
        // Добавление компонентов регулирования цветов к панели colorPanel
        colorPanel.add(redLabel);
        colorPanel.add(redSlider);
        colorPanel.add(greenLabel);
        colorPanel.add(greenSlider);
        colorPanel.add(blueLabel);
        colorPanel.add(blueSlider);
        
        // Установка положений регуляторов составляющих цвета по умолчанию
        java3DWorld.changeColor(new Color(redSlider.getValue(), greenSlider.getValue(), blueSlider.getValue()));
        
        // Задание параметров менеджера компоновки GridLayout
        setLayout(new GridLayout(2, 1, 0, 20));
        
        // Добавление панелей JPanel в панель ControlPanel
        add(topPanel);
        add(colorPanel);
    }
    
    // Возврат предпочтительного размера контейнера
    @Override
    public Dimension getPreferredSize() {
        return new Dimension(250, 150);
    }
    
    // Возврат нормального размера контейнера
    @Override
    public Dimension getMinimumSize() {
        return getPreferredSize();
    }
    
}
