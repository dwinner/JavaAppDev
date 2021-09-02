// Полнофункциональный редактор для списка
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

class HTMLComboBoxEditor implements ComboBoxEditor
{
    // Редактор для HTML
    private JEditorPane htmlEditor;
    
    HTMLComboBoxEditor()
    {
        htmlEditor = new JEditorPane("text/html", "");
        htmlEditor.setBorder(BorderFactory.createEtchedBorder());
    }

    public Component getEditorComponent()
    {
        // Возвращаем редактор
        return htmlEditor;
    }
    
    public void selectAll()
    {
        // Сигнал выбрать весь текст и приступить к редактированию
        htmlEditor.selectAll();
        htmlEditor.requestFocus();
    }
    
    public Object getItem()
    {
        // Возвращаем редактируемый элемент
        return htmlEditor.getText();
    }

    public void setItem(Object anObject)
    {
        // Получаем новый элемент для редактирования
        htmlEditor.setText(anObject.toString());
    }

    public void addActionListener(ActionListener l)
    {
        // throw new UnsupportedOperationException("Not supported yet.");
    }

    public void removeActionListener(ActionListener l)
    {
        // throw new UnsupportedOperationException("Not supported yet.");
    }
    
}

// Пример использования специального объекта для редактирования
public class HTMLComboEditorTest extends JFrame
{    
    // Данные для раскрывающегося списка
    private String[] data =
    {
        "<html><font color=\"yellow\">Yellow</font>",
        "<html><strike>Strike</strike>",
        "<html><font color=\"green\">Green</font>",
        "<html><em>Italic</em>"
    };
    
    public HTMLComboEditorTest()
    {
        super("html editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Создаем список
        JComboBox combo = new JComboBox(data);
        combo.setPrototypeDisplayValue("11223344556677");
        combo.setEditable(true);
        combo.setEditor(new HTMLComboBoxEditor());
        // Добавляем список в окно
        JPanel contents = new JPanel();
        contents.add(combo);
        // Выводим окно на экран
        setContentPane(contents);
        setSize(330, 200);
        setVisible(true);
    }
    
    public static void main(String[] args)
    {
        new HTMLComboEditorTest();
    }

}