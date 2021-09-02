/* 
 * Богатые возможности редактора JTextPane
 */

import java.awt.Color;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;
import javax.swing.text.*;

public class StyledText extends JFrame
{
    private JTextPane textPane; // Наш редактор

    public StyledText()
    {
        super("Styled Text");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Создаем редактор
        textPane = new JTextPane();
        // Создаем документ и стили
        createDocument(textPane);
        // Добавляем редактор в панель содержимого
        getContentPane().add(new JScrollPane(textPane));
        // Выводим окно на экран
        setSize(400, 300);
        setVisible(true);
    }

    /**
     * Создание и настройка редактора
     * <p/>
     * @param tp объект редактора
     */
    private void createDocument(JTextPane tp)
    {
        // Настройка стилей. Стиль основного текста
        Style normal = tp.addStyle("Normal", null);
        StyleConstants.setFontFamily(normal, "Verdana");
        StyleConstants.setFontSize(normal, 13);
        // Заголовок
        Style heading = tp.addStyle("Heading", normal);
        StyleConstants.setFontSize(heading, 20);
        StyleConstants.setBold(heading, true);
        // Наполняет документ содержимым, используя стили
        insertString("Незамысловатый заголовок", tp, heading);
        insertString("Далее идет обычное содержимое,", tp, normal);
        insertString("помеченное стилем Normal", tp, normal);
        insertString("Ещё один заголовок", tp, heading);
        // Меняем произвольную часть текста
        SimpleAttributeSet red = new SimpleAttributeSet();
        StyleConstants.setForeground(red, Color.RED);
        StyledDocument doc = tp.getStyledDocument();
        doc.setCharacterAttributes(5, 5, red, false);
        // Добавляем компонент в конец текста
        tp.setCaretPosition(doc.getLength());
        JCheckBox check = new JCheckBox("Всё возможно!");
        check.setOpaque(false);
        tp.insertComponent(check);
    }

    /**
     * Вставка строки в конец документа с переносом
     * <p/>
     * @param s     Строка для вставки
     * @param tp    Объект редактора
     * @param style Объект стиля
     */
    private void insertString(String s, JTextPane tp, Style style)
    {
        Document doc = tp.getDocument();
        try
        {
            doc.insertString(doc.getLength(), s + "\r\n", style);
        }
        catch (BadLocationException ex)
        {
            ex.printStackTrace();
        }
    }

    public static void main(String[] args)
    {
        new StyledText();
    }
}