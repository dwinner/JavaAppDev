import javax.swing.AbstractSpinnerModel;

/**
 * ћодель, динамически генерирующа€ перестановки букв в слове.
 */
public class PermutationSpinnerModel extends AbstractSpinnerModel
{
    private String word;

    /**
     * —оздаем модель.
     * <p/>
     * @param word —лово, в котором будет производитьс€ перестановка.
     */
    public PermutationSpinnerModel(String word)
    {
        this.word = word;
    }

    @Override
    public Object getValue()
    {
        return word;
    }

    @Override
    public void setValue(Object value)
    {
        if (!(value instanceof String))
        {
            throw new IllegalArgumentException();
        }
        word = (String) value;
        fireStateChanged();
    }

    @Override
    public Object getNextValue()
    {
        int[] codePoints = toCodePointArray(word);
        for (int i = codePoints.length - 1; i > 0; i--)
        {
            if (codePoints[i - 1] < codePoints[i])
            {
                int j = codePoints.length - 1;
                while (codePoints[i - 1] > codePoints[j])
                {                    
                    j--;
                }
                swap(codePoints, i - 1, j);
                reverse(codePoints, i, codePoints.length - 1);
                return new String(codePoints, 0, codePoints.length);
            }
        }
        reverse(codePoints, 0, codePoints.length - 1);
        return new String(codePoints, 0, codePoints.length);
    }

    @Override
    public Object getPreviousValue()
    {
        int[] codePoints = toCodePointArray(word);
        for (int i = codePoints.length - 1; i > 0; i--)
        {
            if (codePoints[i - 1] > codePoints[i])
            {
                int j = codePoints.length - 1;
                while (codePoints[i - 1] < codePoints[j])
                {                    
                    j--;
                }
                swap(codePoints, i - 1, j);
                reverse(codePoints, i, codePoints.length - 1);
                return new String(codePoints, 0, codePoints.length);
            }
        }
        reverse(codePoints, 0, codePoints.length - 1);
        return new String(codePoints, 0, codePoints.length);
    }

    private static int[] toCodePointArray(String aString)
    {
        int[] codePoints = new int[aString.codePointCount(0, aString.length())];
        for (int i = 0, j = 0; i < aString.length(); i++, j++)
        {
            int cp = aString.codePointAt(i);
            if (Character.isSupplementaryCodePoint(cp))
            {
                i++;
            }
            codePoints[j] = cp;
        }
        return codePoints;
    }

    private static void swap(int[] a, int i, int j)
    {
        int temp = a[i];
        a[i] = a[j];
        a[j] = temp;
    }

    private static void reverse(int[] codePoints, int i, int j)
    {
        while (i < j)
        {            
            swap(codePoints, i, j);
            i++;
            j--;
        }
    }
}
