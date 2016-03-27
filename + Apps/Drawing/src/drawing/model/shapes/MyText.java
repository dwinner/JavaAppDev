package drawing.model.shapes;

import java.awt.Graphics2D;
import java.awt.font.TextAttribute;
import java.awt.geom.Point2D;
import java.text.AttributedCharacterIterator;
import java.text.AttributedString;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * MyText - подкласс класса MyShape, который представляет текст со стилями в рисунке
 * @author dwinner@inbox.ru
 */
public class MyText extends MyShape {
    
    // Свойства объекта MyText (шрифт, размер шрифта, текст и т.д.)
    private String text;
    private AttributedString attributedString;
    private String fontName;
    private int fontSize;
    private boolean underlined;
    private boolean boldSelected;
    private boolean italicSelected;
    
    {   // Инициализация по умолчанию
        fontName = "Serif";
        fontSize = 12;
        underlined = false;
        boldSelected = false;
        italicSelected = false;
    }

    @Override public void draw(Graphics2D g2D) {
        // Настройка Graphics2D (градиент, цвет и т.д.)
        configureGraphicsContext(g2D);       
        // Создание объекта AttributedString для текста
        attributedString = new AttributedString(text);       
        // Задание шрифта в AttributedString
        attributedString.addAttribute(TextAttribute.SIZE, new Float(fontSize));      
        // Задать начертание, если необходимо
        if (boldSelected) {
            attributedString.addAttribute(TextAttribute.WEIGHT, TextAttribute.WEIGHT_BOLD);
        }
        if (italicSelected) {
            attributedString.addAttribute(TextAttribute.POSTURE, TextAttribute.POSTURE_OBLIQUE);
        }
        if (underlined) {
            attributedString.addAttribute(TextAttribute.UNDERLINE, TextAttribute.UNDERLINE_ON);
        }     
        // Задание цвета в AttributedString
        attributedString.addAttribute(TextAttribute.FOREGROUND, getColor());       
        // Создание объекта AttributedCharacterIterator для AttributedString
        AttributedCharacterIterator characterIterator = attributedString.getIterator();       
        // Рисование строки с помощью AttributedCharacterIterator
        g2D.drawString(characterIterator, getX1(), getY1());
    }

    @Override public boolean contains(Point2D point) {
        // Объекты MyText не имеют площади
        return false;
    }
    
    /**
     * Задание текста для MyText
     * @param myText Строка текста для задания
     */
    public void setText(String myText) {
        text = myText;
    }
    
    /**
     * Получение текста, содержащегося в MyText
     * @return Строка текста
     */
    public String getText() {
        return text;
    }
    
    /**
     * Задание размера шрифта для MyText
     * @param size размер шрифта
     */
    public void setFontSize(int size) {
        fontSize = size;
    }
    
    /**
     * Получение размера шрифта для MyText
     * @return Размер шрифта
     */
    public int getFontSize() {
        return fontSize;
    }
    
    /**
     * Задание гарнитуры шрифта для MyText
     * @param name Имя гарнитуры шрифта
     */ 
    public void setFontName(String name) {
        fontName = name;
    }
    
    /**
     * Получение гарнитуры шрифта для MyText
     * @return Имя гарнитуры шрифта
     */
    public String getFontName() {
        return fontName;
    }
    
    /**
     * Задание свойства подчеркивания для MyText
     * @param textUnderlined Флаг подчеркивания
     */
    public void setUnderlineSelected(boolean textUnderlined) {
        underlined = textUnderlined;
    }
    
    /**
     * Получение свойства подчеркивания для MyText
     * @return Флаг подчеркивания
     */
    public boolean isUnderlineSelected() {
        return underlined;
    }
    
    /**
     * Задание полужирного начертания для MyText
     * @param textBold Флаг полужирного начертания
     */
    public void setBoldSelected(boolean textBold) {
        boldSelected = textBold;
    }
    
    /**
     * Получение свойства полужирного начертания для MyText
     * @return Флаг полужирного начертания
     */
    public boolean isBoldSelected() {
        return boldSelected;
    }
    
    /**
     * Задание курсивного начертания для MyText
     * @param textItalic Флаг курсивного начертания
     */
    public void setItalicSelected(boolean textItalic) {
        italicSelected = textItalic;
    }
    
    /**
     * Получение свойства курсивного начертания для MyText
     * @return Флаг курсивного начертания
     */
    public boolean isItalicSelected() {
        return italicSelected;
    }
    
    @Override public Element getXML(Document document) {
        Element shapeElement = super.getXML(document);
        shapeElement.setAttribute("type", "MyText");
        Element temp;        
        // Создание элемента Element для текста
        temp = document.createElement("text");
        temp.appendChild(document.createTextNode(getText()));
        shapeElement.appendChild(temp);       
        // Создание элемента Element для размера шрифта fontSize
        temp = document.createElement("fontSize");
        temp.appendChild(document.createTextNode(String.valueOf(fontSize)));
        shapeElement.appendChild(temp);    
        // Создание элемента Element для гарнитуры шрифта fontName
        temp = document.createElement("fontName");
        temp.appendChild(document.createTextNode(String.valueOf(fontName)));
        shapeElement.appendChild(temp);      
        // Создание элемента Element для подчеркивания
        temp = document.createElement("underlined");
        temp.appendChild(document.createTextNode(String.valueOf(underlined)));
        shapeElement.appendChild(temp);      
        // Создание элемента Element для полужирного начертания
        temp = document.createElement("bold");
        temp.appendChild(document.createTextNode(String.valueOf(boldSelected)));
        shapeElement.appendChild(temp);       
        // Создание элемента Element для курсива
        temp = document.createElement("italic");
        temp.appendChild(document.createTextNode(String.valueOf(italicSelected)));
        shapeElement.appendChild(temp);
        
        return shapeElement;
    }
    
}