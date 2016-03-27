
import java.awt.Dimension;
import java.awt.Insets;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JTextField;

/**
 * Вспомогательный класс инструментов для UI
 * <p/>
 * @author dwinner@inbox.ru
 */
public class GUITools
{
    private GUITools()
    {
    }

    /**
     * Этот метод принимает массив ссылок на кнопки JButton и
     * придает им нужный отступ от границ слева и справа
     * <p/>
     * @param buttons массив кнопок
     */
    public static void createRecommendedMargin(JButton[] buttons)
    {
        for (JButton aButton : buttons)
        {
            Insets margin = aButton.getMargin();
            margin.left = 12;   // TODO: Вынести в перечисление MetalPlafEnum
            margin.right = 12;  // TODO: Вынести в перечисление MetalPlafEnum
            aButton.setMargin(margin);
        }
    }

    /**
     * Метод придания группе компонентов одинаковых
     * размеров, (т.е. минимальных, предпочтительных и
     * максимальных). Компоненты принимают размер самого
     * большого (по ширине) компонента в группе
     * <p/>
     * @param components Набор компонентов
     */
    public static void makeSameSize(JComponent[] components)
    {
        // Получение ширины компонентов
        int[] sizes = new int[components.length];
        for (int i = 0; i < sizes.length; i ++)
        {
            sizes[i] = components[i].getPreferredSize().width;
        }
        // Определение максимального размера
        int maxSizePos = maximumElementPosition(sizes);
        Dimension maxSize = components[maxSizePos].getPreferredSize();
        // Придание одинаковых размеров
        for (int i = 0; i < components.length; i ++)
        {
            components[i].setPreferredSize(maxSize);
            components[i].setMaximumSize(maxSize);
            components[i].setMinimumSize(maxSize);
        }
    }

    /**
     * Метод, корректирующий высоту текстового поля
     * <p/>
     * @param field Однострочное текстовое поле
     */
    public static void fixTextFieldSize(JTextField field)
    {
        Dimension size = field.getPreferredSize();
        // Чтобы текстовое поле по-прежнему могло увеличивать свой
        // размер в длину
        size.width = field.getMaximumSize().width;
        // Теперь текстовое поле не станет выше своей оптимальной высоты
        field.setMaximumSize(size);
    }

    /**
     * Определение позиции максимального элемента
     * массива
     * <p/>
     * @param sizes �?сходный массив позиций
     * <p/>
     * @return Позиция (индекс) максимального элемента
     */
    private static int maximumElementPosition(int[] sizes)
    {
        int maxPos = 0;
        for (int i = 1; i < sizes.length; i ++)
        {
            if (sizes[i] > sizes[maxPos])
            {
                maxPos = i;
            }
        }

        return maxPos;
    }

}
