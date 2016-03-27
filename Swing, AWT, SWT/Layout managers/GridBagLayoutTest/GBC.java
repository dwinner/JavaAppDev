
import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * Этот класс упрощает работу с классом GridBagConstraints.
 * <p/>
 * @version 1.01 2004-05-06
 * @author Cay Horstmann
 */
public class GBC extends GridBagConstraints
{
    /**
     * Создает объект GBC, определяя gridx, gridy. Значения остальных параметров принимаются по
     * умолчанию.
     * <p/>
     * @param gridx Позиция gridx
     * @param gridy Позиция gridy
     */
    public GBC(int gridx, int gridy)
    {
        this.gridx = gridx;
        this.gridy = gridy;
    }

    /**
     * Создает объект GBC, определяя gridx, gridy, gridwidth и gridheight. Значения остальных
     * параметров принимаются по умолчанию.
     * <p/>
     * @param gridx      Позиция gridx
     * @param gridy      Позиция gridy
     * @param gridwidth  Расширение ячейки в направлении x
     * @param gridheight Расширение ячейки в направлении y
     */
    public GBC(int gridx, int gridy, int gridwidth, int gridheight)
    {
        this.gridx = gridx;
        this.gridy = gridy;
        this.gridwidth = gridwidth;
        this.gridheight = gridheight;
    }

    /**
     * Устанавливает параметр anchor.
     * <p/>
     * @param anchor Значение параметра
     * <p/>
     * @return Объект this, пригодный для дальнейшей модификации
     */
    public GBC setAnchor(int anchor)
    {
        this.anchor = anchor;
        return this;
    }

    /**
     * Устанавливает параметр fill.
     * <p/>
     * @param fill Значение параметра
     * <p/>
     * @return Объект this, пригодный для дальнейшей модификации
     */
    public GBC setFill(int fill)
    {
        this.fill = fill;
        return this;
    }

    /**
     * Устанавливает веса ячейки.
     * <p/>
     * @param weightx Вес в направлении x
     * @param weighty Вес в направлении y
     * <p/>
     * @return Объект this, пригодный для дальнейшей модификации
     */
    public GBC setWeight(double weightx, double weighty)
    {
        this.weightx = weightx;
        this.weighty = weighty;
        return this;
    }

    /**
     * Устанавливает размеры свободного пространства для ячейки.
     * <p/>
     * @param distance Размеры по всем направлениям
     * <p/>
     * @return Объект this, пригодный для дальнейшей модификации
     */
    public GBC setInsets(int distance)
    {
        this.insets = new Insets(distance, distance, distance, distance);
        return this;
    }

    /**
     * Устанавливает размеры свободного пространства для ячейки.
     * <p/>
     * @param top    Размер верхней части свободного пространства
     * @param left   Размер левой части свободного пространства
     * @param bottom Размер нижней части свободного пространства
     * @param right  Размер правой части свободного пространства
     * <p/>
     * @return Объект this, пригодный для дальнейшей модификации
     */
    public GBC setInsets(int top, int left, int bottom, int right)
    {
        this.insets = new Insets(top, left, bottom, right);
        return this;
    }

    /**
     * Устанавливает внутреннее заполнение.
     * <p/>
     * @param ipadx Внутреннее заполнение в направлении x
     * @param ipady Внутреннее заполнение в направлении y
     * <p/>
     * @return Объект this, пригодный для дальнейшей модификации
     */
    public GBC setIpad(int ipadx, int ipady)
    {
        this.ipadx = ipadx;
        this.ipady = ipady;
        return this;
    }

}
