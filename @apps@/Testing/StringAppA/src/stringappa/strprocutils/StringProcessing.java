package stringappa.strprocutils;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

/**
 * Псевдо-статический класс для обработки строк
 * @author dwinner@inbox.ru
 */
@SuppressWarnings("ClassWithoutLogger")
public class StringProcessing
{
    private static final int AL_INIT_CAPACITY = 128;
    
    private StringProcessing() {}
    
    /**
     * Фильтрация уникальных слов текста, у которых первая и последняя буквы совпадают
     * @param aText Текст для обработки
     * @return Массив отобранных строк
     */
    public static List<String> sameCharFirstAndLast(String aText)
    {
        List<String> filtered = new ArrayList<String>(AL_INIT_CAPACITY);
        StringTokenizer st = new StringTokenizer(aText);
        
        String currentToken;
        int strCurSize;
        while (st.hasMoreTokens())
        {
            currentToken = st.nextToken();
            strCurSize = currentToken.length();
            
            // Проверим, совпадают ли первый и последний символы
            if (currentToken.charAt(0) == currentToken.charAt(strCurSize - 1))
            {
                // Проверим, есть ли уже такое слово в списке
                if (!filtered.contains(currentToken))
                {
                    filtered.add(currentToken);
                }
            }
        }
        
        return filtered;
    }

}
