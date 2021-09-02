/*
 * ��������� ���� � ���������� ��������������.
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
 * ��������� ���� � ���������� ��������������
 * <p/>
 * @author dwinner@inbox.ru
 */
class AutoCompleteTextField extends JTextField
{
    // ������ ���� ��� ��������������
    private List<String> words = new ArrayList<String>(32);
    // ��������, ����������� ������� ��������������
    private int beforeCompletion = 2;

    // ����������� ����
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
     * �����, ������� ������ �� ����������� � ���������.
     */
    private class DocumentHandler implements DocumentListener
    {
        @Override   // ������� � �������� ������ ������
        public void insertUpdate(DocumentEvent e)
        {
            // �������� ������� ������� �������
            final int pos = e.getOffset() + e.getLength();
            final Document doc = e.getDocument();
            // ���������� ������� �������� �����
            try
            {
                int wordStart = Utilities.getWordStart(AutoCompleteTextField.this, e.getOffset());
                int wordEnd = Utilities.getWordEnd(AutoCompleteTextField.this, e.getOffset());
                // ����� �������� �����
                int wordLength = wordEnd - wordStart;
                // ���������, ����� �� ��������� �����
                if (wordLength < beforeCompletion)
                {
                    return;
                }
                // �������� ������� �����
                String word = doc.getText(wordStart, wordLength);
                // �������� ����� ��� ������ ������� � ������
                String wholeWord = "";
                for (String curWord : words)
                {
                    if (curWord.startsWith(word))
                    {
                        // ����� �������
                        wholeWord = curWord;
                        break;
                    }
                }
                // �����, ���� ����� �� �������
                if ("".equals(wholeWord))
                {
                    return;
                }
                // �������� ����� ��� ��������������
                final String toComplete = wholeWord.substring(wordLength);
                // ��������� ������ ��� ���������� �����
                EventQueue.invokeLater(new Runnable()
                {
                    @Override
                    public void run()
                    {
                        doc.removeDocumentListener(DocumentHandler.this);
                        try
                        {
                            // ��������� ��������� �����
                            doc.insertString(pos, toComplete, null);
                            // �������� ����������� �����
                            setSelectionStart(pos);
                            setSelectionEnd(pos + toComplete.length());
                            // ���������� ��������� �� �����
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

        // �������� ������
        @Override
        public void removeUpdate(DocumentEvent e)
        {
        }

        // ��������� ������
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
        // ������� � ����������� ����
        AutoCompleteTextField field = new AutoCompleteTextField();
        field.setColumns(15);
        // ����� ��� ��������������
        field.addWord("swing");
        field.addWord("flash");
        field.addWord("javafx");
        field.addWord("oracle");
        // ��������� ���� � ����
        JPanel contents = new JPanel();
        contents.add(field);
        setContentPane(contents);
        // ������� ���� �� �����
        setSize(300, 200);
        setVisible(true);
    }

    public static void main(String[] args)
    {
        new AutoComplete();
    }
}