package drawing.model.shapes;

import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
import java.awt.geom.Point2D;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * MyText - �������� ������ MyShape, ������� ������������ ����� �� ������� � �������
 * @author dwinner@inbox.ru
 */
public class MyText extends MyShape {
    
    // �������� ������� MyText (�����, ������ ������, ����� � �.�.)
    private String text;
    private AttributedString attributedString;
    private String fontName;
    private int fontSize;
    private boolean underlined;
    private boolean boldSelected;
    private boolean italicSelected;
    
    {   // ������������� �� ���������
        fontName = "Serif";
        fontSize = 12;
        underlined = false;
        boldSelected = false;
        italicSelected = false;
    }

    @Override public void draw(Graphics2D g2D) {
        // ��������� Graphics2D (��������, ���� � �.�.)
        configureGraphicsContext(g2D);       
        // �������� ������� AttributedString ��� ������
        attributedString = new AttributedString(text);       
        // ������� ������ � AttributedString
        attributedString.addAttribute(TextAttribute.SIZE, new Float(fontSize));      
        // ������ ����������, ���� ����������
        if (boldSelected) {
            attributedString.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
        }
        if (italicSelected) {
            attributedString.addAttribute(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE);
        }
        if (underlined) {
            attributedString.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        }     
        // ������� ����� � AttributedString
        attributedString.addAttribute(TextAttribute.FOREGROUND, getColor());       
        // �������� ������� AttributedCharacterIterator ��� AttributedString
        AttributedCharacterIterator characterIterator = attributedString.getIterator();       
        // ��������� ������ � ������� AttributedCharacterIterator
        g2D.drawString(characterIterator, getX1(), getY1());
    }

    @Override public boolean contains(Point2D point) {
        // ������� MyText �� ����� �������
        return false;
    }
    
    /**
     * ������� ������ ��� MyText
     * @param myText ������ ������ ��� �������
     */
    public void setText(String myText) {
        text = myText;
    }
    
    /**
     * ��������� ������, ������������� � MyText
     * @return ������ ������
     */
    public String getText() {
        return text;
    }
    
    /**
     * ������� ������� ������ ��� MyText
     * @param size ������ ������
     */
    public void setFontSize(int size) {
        fontSize = size;
    }
    
    /**
     * ��������� ������� ������ ��� MyText
     * @return ������ ������
     */
    public int getFontSize() {
        return fontSize;
    }
    
    /**
     * ������� ��������� ������ ��� MyText
     * @param name ��� ��������� ������
     */ 
    public void setFontName(String name) {
        fontName = name;
    }
    
    /**
     * ��������� ��������� ������ ��� MyText
     * @return ��� ��������� ������
     */
    public String getFontName() {
        return fontName;
    }
    
    /**
     * ������� �������� ������������� ��� MyText
     * @param textUnderlined ���� �������������
     */
    public void setUnderlineSelected(boolean textUnderlined) {
        underlined = textUnderlined;
    }
    
    /**
     * ��������� �������� ������������� ��� MyText
     * @return ���� �������������
     */
    public boolean isUnderlineSelected() {
        return underlined;
    }
    
    /**
     * ������� ����������� ���������� ��� MyText
     * @param textBold ���� ����������� ����������
     */
    public void setBoldSelected(boolean textBold) {
        boldSelected = textBold;
    }
    
    /**
     * ��������� �������� ����������� ���������� ��� MyText
     * @return ���� ����������� ����������
     */
    public boolean isBoldSelected() {
        return boldSelected;
    }
    
    /**
     * ������� ���������� ���������� ��� MyText
     * @param textItalic ���� ���������� ����������
     */
    public void setItalicSelected(boolean textItalic) {
        italicSelected = textItalic;
    }
    
    /**
     * ��������� �������� ���������� ���������� ��� MyText
     * @return ���� ���������� ����������
     */
    public boolean isItalicSelected() {
        return italicSelected;
    }
    
    @Override public Element getXML(Document document) {
        Element shapeElement = super.getXML(document);
        shapeElement.setAttribute("type", "MyText");
        Element temp;        
        // �������� �������� Element ��� ������
        temp = document.createElement("text");
        temp.appendChild(document.createTextNode(getText()));
        shapeElement.appendChild(temp);       
        // �������� �������� Element ��� ������� ������ fontSize
        temp = document.createElement("fontSize");
        temp.appendChild(document.createTextNode(String.valueOf(fontSize)));
        shapeElement.appendChild(temp);    
        // �������� �������� Element ��� ��������� ������ fontName
        temp = document.createElement("fontName");
        temp.appendChild(document.createTextNode(String.valueOf(fontName)));
        shapeElement.appendChild(temp);      
        // �������� �������� Element ��� �������������
        temp = document.createElement("underlined");
        temp.appendChild(document.createTextNode(String.valueOf(underlined)));
        shapeElement.appendChild(temp);      
        // �������� �������� Element ��� ����������� ����������
        temp = document.createElement("bold");
        temp.appendChild(document.createTextNode(String.valueOf(boldSelected)));
        shapeElement.appendChild(temp);       
        // �������� �������� Element ��� �������
        temp = document.createElement("italic");
        temp.appendChild(document.createTextNode(String.valueOf(italicSelected)));
        shapeElement.appendChild(temp);
        
        return shapeElement;
    }
    
}