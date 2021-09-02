// Простой редактор для раскрывающегося списка, использующего возможности HTML
import javax.swing.*;
import javax.swing.plaf.basic.*;

class SimpleHTMLComboBoxEditor extends BasicComboBoxEditor
{
    // В этот метод передается новый элемент для редактирования
    @Override public void setItem(Object item)
    {
        // Получаем строковое представление
        String text = item.toString();
        // Сюда записываем очищенную версию строки
        StringBuilder result = new StringBuilder();
        // Флаг нахождения в тэге
        boolean isInTag = false;
        int strLen = text.length();
        for (int i=0; i<strLen; i++)
        {
            // Проверка на новый тег
            if (text.charAt(i) == '<')
            {
                isInTag = true;
            }
            if (text.charAt(i) == '>')
            {
                // Тэг закрылся, переходим на следующую итерацию цикла
                isInTag = false;
                continue;
            }
            // Присоединяем символ, если это не HTML-тэг
            if (!isInTag)
            {
                result.append(text.charAt(i));
            }
        }
        // Передаем "очищенную" строку стандартному объекту
        super.setItem(result);
    }
    
}

// Пример использования специального объекта для редактирования
public class SimpleComboEditorTest extends JFrame
{    
    // Данные для раскрывающегося списка
    private String[] data =
    {
        "<html><font color=\"yellow\">Yellow</font>",
        "<html><strike>Strike</strike>",
        "<html><font color=\"green\">Green</font>",
        "<html><em>Italic</em>"
    };
    
    public SimpleComboEditorTest()
    {
        super("Simple combo box editor");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Создаем список
        JComboBox combo = new JComboBox(data);
        combo.setEditable(true);
        combo.setEditor(new SimpleHTMLComboBoxEditor());
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
        new SimpleComboEditorTest();
    }

}