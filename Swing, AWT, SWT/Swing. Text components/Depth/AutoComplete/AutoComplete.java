/*
 * Текстовое поле с поддержкой автозаполнения.
 */

import java.awt.EventQueue;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
import javax.swing.text.Utilities;

/**
 * Текстовое поле с поддержкой автозаполнения
 * <p/>
 * @author dwinner@inbox.ru
 */
class AutoCompleteTextField extends JTextField
{
    // Список слов для автозаполнения
    private List<String> words = new ArrayList<String>(32);
    // Свойство, управляющее началом автозаполнения
    private int beforeCompletion = 2;

    // Конструктор поля
    AutoCompleteTextField()
    {
        super();
        getDocument().addDocumentListener(new DocumentHandler());
    }

    public void addWord(String word)
    {
        words.add(word);
    }

    public void setBeforeCompletion(int value)
    {
        beforeCompletion = value;
    }

    /**
     * Класс, который следит за изменениями в документе.
     */
    private class DocumentHandler implements DocumentListener
    {
        @Override   // Вставка в документ нового текста
        public void insertUpdate(DocumentEvent e)
        {
            // Получаем текущую позицию курсора
            final int pos = e.getOffset() + e.getLength();
            final Document doc = e.getDocument();
            // Определяем позиции текущего слова
            try
            {
                int wordStart = Utilities.getWordStart(AutoCompleteTextField.this, e.getOffset());
                int wordEnd = Utilities.getWordEnd(AutoCompleteTextField.this, e.getOffset());
                // Длина текущего слова
                int wordLength = wordEnd - wordStart;
                // Проверяем, можно ли завершить слово
                if (wordLength < beforeCompletion)
                {
                    return;
                }
                // Получаем текущее слово
                String word = doc.getText(wordStart, wordLength);
                // Пытаемся найти его полный вариант в списке
                String wholeWord = "";
                for (String curWord : words)
                {
                    if (curWord.startsWith(word))
                    {
                        // Слово найдено
                        wholeWord = curWord;
                        break;
                    }
                }
                // Выход, если слово не найдено
                if ("".equals(wholeWord))
                {
                    return;
                }
                // Вырезаем часть для автозаполнения
                final String toComplete = wholeWord.substring(wordLength);
                // Запускаем задачу для завершения слова
                EventQueue.invokeLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        doc.removeDocumentListener(DocumentHandler.this);
                        try
                        {
                            // Вставляем окончание слова
                            doc.insertString(pos, toComplete, null);
                            // Выделяем добавленную часть
                            setSelectionStart(pos);
                            setSelectionEnd(pos + toComplete.length());
                            // Возвращаем слушателя на место
                            doc.addDocumentListener(DocumentHandler.this);
                        }
                        catch (BadLocationException ex)
                        {
                            ex.printStackTrace();
                        }
                    }
                });
            }
            catch (BadLocationException ex)
            {
                ex.printStackTrace();
            }
        }

        // Удаление текста
        @Override
        public void removeUpdate(DocumentEvent e)
        {
        }

        // Изменение текста
        @Override
        public void changedUpdate(DocumentEvent e)
        {
        }
    }
}

/**
 * Entry point class
 * <p/>
 * @author dwinner@inbox.ru
 */
public class AutoComplete extends JFrame
{
    public AutoComplete()
    {
        super("Auto complete");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Создаем и настраиваем поле
        AutoCompleteTextField field = new AutoCompleteTextField();
        field.setColumns(15);
        // Слова для автозаполнения
        field.addWord("swing");
        field.addWord("flash");
        field.addWord("javafx");
        field.addWord("oracle");
        // Добавляем поле в окно
        JPanel contents = new JPanel();
        contents.add(field);
        setContentPane(contents);
        // Выводим окно на экран
        setSize(300, 200);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new AutoComplete();
    }
}