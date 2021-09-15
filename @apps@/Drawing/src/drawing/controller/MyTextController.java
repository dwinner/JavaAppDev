package drawing.controller;

import java.awt.*;
import javax.swing.*;
import drawing.model.DrawingModel;
import drawing.model.shapes.*;

/**
 * MyTextController - �������� ������ MyShapeController ��� ��������� �������� MyText.
 * @author dwinner@inbox.ru
 */
public class MyTextController extends MyShapeController {
    
    public MyTextController(DrawingModel model, Class<? extends MyShape> myShapeClass) {
        super(model, MyText.class);
    }

    @Override public void startShape(int x, int y) {
        // �������� ������� MyText
        MyText currentText = new MyText();        
        // ������� ����� Point1 ��� MyText
        currentText.setPoint1(x, y);        
        // �������� ������ TextInputPanel ��� ��������� ������ � ��� ��������
        TextInputPanel inputPanel = new TextInputPanel();        
        // ����������� ������ TextInputPanel � JOptionPane
        String text = JOptionPane.showInputDialog(null, inputPanel);        
        // �������� ������� ������
        if (text == null || text.trim().isEmpty()) {
            return;
        }       
        // ��������� �������� MyText (����������, ������ � �.�.)
        currentText.setBoldSelected(inputPanel.boldSelected());
        currentText.setItalicSelected(inputPanel.italicSelected());
        currentText.setUnderlineSelected(inputPanel.underlineSelected());
        currentText.setFontName(inputPanel.getSelectedFontName());
        currentText.setFontSize(inputPanel.getSelectedFontSize());
        currentText.setColor(getPrimaryColor());        
        // ������� ������ ��� MyText
        currentText.setText(text);        
        // ���������� ������� MyText � ������
        addShapeToModel(currentText);
    }

    @Override public void modifyShape(int x, int y) {       
    }

    @Override public void endShape(int x, int y) {
    }
    
    /**
     * ������ JPanel � ������������ ��� ����� �������� MyText
     * @author dwinner@inbox.ru
     */
    private static class TextInputPanel extends JPanel {
        
        private static final long serialVersionUID = 1L;
        private static final String[] fontList;    // ������ �������.
        
        static {    // �������������� ������ ������� ���� ���
            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            fontList = ge.getAvailableFontFamilyNames();
        }
        
        private JCheckBox boldCheckBox;
        private JCheckBox italicCheckBox;
        private JCheckBox underlineCheckBox;
        private JComboBox<String> fontComboBox;
        private JComboBox<String> fontSizeComboBox;
        
        /**
         * ����������� TextInputPanel
         */
        TextInputPanel() {
            boldCheckBox = new JCheckBox("Bold");
            italicCheckBox = new JCheckBox("Italic");
            underlineCheckBox = new JCheckBox("Underline");            
            // �������� ���������� JComboBox ��� ������ ������
            fontComboBox = new JComboBox<String>();
            for (String fontName : fontList) {
                fontComboBox.addItem(fontName);
            }            
            // �������� ���������� JComboBox ��� ������ ������� ������
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
         * ������ �� ���������� ����������
         * @return ���� ����������� ����������
         */
        private boolean boldSelected() {
            return boldCheckBox.isSelected();
        }

        /**
         * ����� �� ������
         * @return ���� ������� �������
         */
        private boolean italicSelected() {
            return italicCheckBox.isSelected();
        }

        /**
         * ������ �� �������������?
         * @return ���� ������� �������������
         */
        private boolean underlineSelected() {
            return underlineCheckBox.isSelected();
        }

        /**
         * ��������� ������������ ������
         * @return ������������ ������
         */
        private String getSelectedFontName() {
            return fontComboBox.getSelectedItem().toString();
        }

        /**
         * ��������� ������� ������
         * @return ������ ������
         */
        private int getSelectedFontSize() {
            return Integer.parseInt(fontSizeComboBox.getSelectedItem().toString());
        }
    }
    
}