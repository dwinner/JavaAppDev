import javax.swing.JTextArea;

/**
 * Текстовая область, в которой метод append отклоняет попытки вставить недопустимые слова.
 */
public class WordCheckTextArea extends JTextArea
{
    @Override
    public void append(String str)
    {
        WordCheckPermission p = new WordCheckPermission(str, "insert");
        SecurityManager manager = System.getSecurityManager();
        if (manager != null)
        {
            manager.checkPermission(p);
        }
        super.append(str);
    }
}
