package roundtypetest;

/**
 * Тип, инкапсулирующий точку
 * @author dwinner@inbox.ru
 */
@SuppressWarnings("ClassWithoutLogger")
public class PointType implements Cloneable
{
    private double px;
    private double py;

    /**
     * Конструктор задания точки
     * @param px Координата точки для оси X
     * @param py Координата точки для оси Y
     */
    public PointType(double px, double py)
    {
        this.px = px;
        this.py = py;
    }
    
    /**
     * Конструктор задания точки с теми же значениями координат по осям
     * @param p Координата точки для оси X, Y
     */
    public PointType(double p)
    {
        this(p, p);
    }
    
    /**
     * Конструктор по умолчанию
     * Задает все координаты нулевыми значениями
     */
    public PointType()
    {
        this(0.0);
    }

    /**
     * Получение значения координаты X
     * @return Координата X
     */
    public double getPx()
    {
        return px;
    }
    /**
     * Установка значения координаты X
     * @param px Координата X
     */
    public void setPx(double px)
    {
        this.px = px;
    }

    /**
     * Получение значения координаты Y
     * @return Координата Y
     */
    public double getPy()
    {
        return py;
    }
    /**
     * Установка значения координаты Y
     * @param py Координата Y
     */
    public void setPy(double py)
    {
        this.py = py;
    }

    @Override public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final PointType other = (PointType) obj;
        if (Double.doubleToLongBits(this.px) != Double.doubleToLongBits(other.getPx()))
        {
            return false;
        }
        if (Double.doubleToLongBits(this.py) != Double.doubleToLongBits(other.getPy()))
        {
            return false;
        }
        return true;
    }

    @Override public int hashCode()
    {
        int hash = 3;
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.px) ^ (Double.doubleToLongBits(this.px) >>> 32));
        hash = 97 * hash + (int) (Double.doubleToLongBits(this.py) ^ (Double.doubleToLongBits(this.py) >>> 32));
        return hash;
    }

    @Override public String toString()
    {
        return "PointType{" + "px=" + px + ", py=" + py + '}';
    }

    @Override public Object clone() throws CloneNotSupportedException
    {
        return super.clone();
    }
    
}
