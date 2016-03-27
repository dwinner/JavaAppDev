package drawing.controller;

import java.awt.*;
import javax.swing.*;
import drawing.model.DrawingModel;
import drawing.model.shapes.*;

/**
 * MyTextController - подкласс класса MyShapeController для рисования объектов MyText.
 * @author dwinner@inbox.ru
 */
public class MyTextController extends MyShapeController {
    
    public MyTextController(DrawingModel model, Class<? extends MyShape> myShapeClass) {
        super(model, MyText.class);
    }

    @Override public void startShape(int x, int y) {
        // Создание объекта MyText
        MyText currentText = new MyText();        
        // Задание точки Point1 для MyText
        currentText.setPoint1(x, y);        
        // Создание панели TextInputPanel для получения текста и его свойства
        TextInputPanel inputPanel = new TextInputPanel();        
        // Отображение панели TextInputPanel в JOptionPane
        String text = JOptionPane.showInputDialog(null, inputPanel);        
        // Проверка наличия текста
        if (text == null || text.trim().isEmpty()) {
            return;
        }       
        // Установка свойства MyText (полужирный, курсив и т.д.)
        currentText.setBoldSelected(inputPanel.boldSelected());
        currentText.setItalicSelected(inputPanel.italicSelected());
        currentText.setUnderlineSelected(inputPanel.underlineSelected());
        currentText.setFontName(inputPanel.getSelectedFontName());
        currentText.setFontSize(inputPanel.getSelectedFontSize());
        currentText.setColor(getPrimaryColor());        
        // Задание текста для MyText
        currentText.setText(text);        
        // Добавление объекта MyText в модель
        addShapeToModel(currentText);
    }

    @Override public void modifyShape(int x, int y) {       
    }

    @Override public void endShape(int x, int y) {
    }
    
    /**
     * Панель JPanel с компонентами для ввода свойства MyText
     * @author dwinner@inbox.ru
     */
    private static class TextInputPanel extends JPanel {
        
        private static final long serialVersionUID = 1L;
        private static final String[] fontList;    // Список шрифтов.
        
        static {    // Инициализируем список шрифтов один раз
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            fontList = ge.getAvailableFontFamilyNames();
        }
        
        private JCheckBox boldCheckBox;
        private JCheckBox italicCheckBox;
        private JCheckBox underlineCheckBox;
        private JComboBox<String> fontComboBox;
        private JComboBox<String> fontSizeComboBox;
        
        /**
         * Конструктор TextInputPanel
         */
        TextInputPanel() {
            boldCheckBox = new JCheckBox("Bold");
            italicCheckBox = new JCheckBox("Italic");
            underlineCheckBox = new JCheckBox("Underline");            
            // Создание компонента JComboBox для выбора шрифта
            fontComboBox = new JComboBox<String>();
            for (String fontName : fontList) {
                fontComboBox.addItem(fontName);
            }            
            // Создание компонента JComboBox для выбора размера шрифта
            fontSizeComboBox = new JComboBox<String>();
            fontSizeComboBox.addItem("10");
            fontSizeComboBox.addItem("12");
            fontSizeComboBox.addItem("14");
            fontSizeComboBox.addItem("18");
            fontSizeComboBox.addItem("22");
            fontSizeComboBox.addItem("36");
            fontSizeComboBox.addItem("48");
            fontSizeComboBox.addItem("72");            
            setLayout(new FlowLayout());            
            add(boldCheckBox);
            add(italicCheckBox);
            add(underlineCheckBox);
            add(fontComboBox);
            add(fontSizeComboBox);
        }
        
        /**
         * Задано ли полужирное начертание
         * @return Флаг полужирного начертания
         */
        private boolean boldSelected() {
            return boldCheckBox.isSelected();
        }

        /**
         * Задан ли курсив
         * @return Флаг задания курсива
         */
        private boolean italicSelected() {
            return italicCheckBox.isSelected();
        }

        /**
         * Задано ли подчеркивание?
         * @return Флаг задания подчеркивания
         */
        private boolean underlineSelected() {
            return underlineCheckBox.isSelected();
        }

        /**
         * Получение наименования шрифта
         * @return Наименование шрифта
         */
        private String getSelectedFontName() {
            return fontComboBox.getSelectedItem().toString();
        }

        /**
         * Получение размера шрифта
         * @return Размер шрифта
         */
        private int getSelectedFontSize() {
            return Integer.parseInt(fontSizeComboBox.getSelectedItem().toString());
        }
    }
    
}