import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JPanel;

/**
 * Вспомогательный класс для упрощения работы с блочным расположением
 * @author dwinner@inbox.ru
 */
public class BoxLayoutUtils
{
    private BoxLayoutUtils() { assert false; }
    
    /**
     * Создание вертикальной панели
     * @return Панель с вертикальным блочным расположением 
     */
    public static JPanel createVerticalPanel()
    {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));
        return p;
    }
    
    /**
     * Создание горизонтальной панели
     * @return Панель с горизонтальным блочным расположением. 
     */
    public static JPanel createHorizontalPanel()
    {
        JPanel p = new JPanel();
        p.setLayout(new BoxLayout(p, BoxLayout.X_AXIS));
        return p;
    }
    
    /**
     * Задает единое выравнивание по оси X для группы компонентов
     * @param cs Массив компонентов
     * @param alignment Значение выравнивание
     */
    public static void setGroupAlignmentX(JComponent[] cs, float alignment)
    {
        for (int i = 0; i < cs.length; i++)
        {
            cs[i].setAlignmentX(alignment);
        }
    }
    
    /**
     * Задает единое выравнивание по оси Y для группы компонентов
     * @param cs Массив компонентов
     * @param alignment Значение выравнивание
     */
    public static void setGroupAlignmentY(JComponent[] cs, float alignment)
    {
        for (int i = 0; i < cs.length; i++)
        {
            cs[i].setAlignmentY(alignment);
        }
    }
}
