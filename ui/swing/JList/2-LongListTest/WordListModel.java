import javax.swing.AbstractListModel;

/**
 * Модель, динамически генерирующая слова из n букв.
 */
public class WordListModel extends AbstractListModel<String>
{
    private int length;
    public static final char FIRST = 'a';
    public static final char LAST = 'z';
    
    /**
     * Конструктор модели
     * @param n Длина слова
     */
    public WordListModel(int n)
    {
        length = n;
    }
    
    @Override
    public int getSize()
    {
        return (int) Math.pow(LAST - FIRST + 1, length);
    }

    @Override
    public String getElementAt(int n)
    {
        StringBuilder r = new StringBuilder(0x3);
        for (int i = 0; i < length; i++)
        {
            char c = (char) (FIRST + n % (LAST - FIRST + 1));
            r.insert(0, c);
            n /= (LAST - FIRST + 1);
        }
        return r.toString();
    }
}
