package roundtypetest;

/**
 * Тип, инкапсулирующий окружность.
 * @author dwinner@inbox.ru
 */
@SuppressWarnings("ClassWithoutLogger")
public class CircleType
{
    private PointType pCenter;
    private double radius;

    /**
     * Конструктор окружности
     * @param pCenter Центр окружности
     * @param radius Радиус окружности
     */
    public CircleType(PointType pCenter, double radius)
    {
        this.pCenter = pCenter;
        this.radius = radius;
    }
    
    /**
     * Конструктор окружности с центром в точке (0.0, 0.0)
     * @param radius Радиус окружности
     */
    public CircleType(double radius)
    {
        pCenter = new PointType(0, 0);
        this.radius = radius;
    }
    
    /**
     * Конструктор окружности
     * @param centerX Координата X центра окружности
     * @param centerY Координата Y центра окружности
     * @param radius Радиус окружности
     */
    public CircleType(int centerX, int centerY, double radius)
    {
        pCenter = new PointType(centerX, centerY);
        this.radius = radius;
    }
    
    /**
     * Конструктор по умолчанию
     * Создает окружность с центром в точке (0.0, 0.0) и единичным радиусом
     */
    public CircleType()
    {
        this(1.0);
    }

    /**
     * Получение значения центра
     * @return Объект PointType центра окружности
     */
    public PointType getpCenter()
    {
        return pCenter;
    }
    /**
     * Получение значения копии центра
     * @return Копия объекта PointType центра окружности
     * @throws CloneNotSupportedException  
     */
    public PointType getpCenterCopy() throws CloneNotSupportedException
    {
        return (PointType) pCenter.clone();
    }
    /**
     * Установка центра окружности
     * @param pCenter Объект PointType центра окружности
     */
    public void setpCenter(PointType pCenter)
    {
        this.pCenter = pCenter;
    }
    
    /**
     * Задание координаты центра окружности по оси X
     * @param pX Координата центра окружности по оси X
     */
    public void setCenterX(double pX)
    {
        pCenter.setPx(pX);
    }
    /**
     * Задание координаты центра окружности по оси Y
     * @param pY Координата центра окружности по оси Y
     */
    public void setCenterY(double pY)
    {
        pCenter.setPy(pY);
    }

    /**
     * Получение радиуса окружности
     * @return Радиус окружности
     */
    public double getRadius()
    {
        return radius;
    }
    /**
     * Установка радиуса окружности
     * @param radius Радиус окружности
     */
    public void setRadius(double radius)
    {
        this.radius = radius;
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
        final CircleType other = (CircleType) obj;
        if (this.pCenter != other.getpCenter() && (this.pCenter == null || !this.pCenter.equals(other.getpCenter())))
        {
            return false;
        }
        if (this.radius != other.getRadius())
        {
            return false;
        }
        return true;
    }

    @Override public int hashCode()
    {
        int hash = 7;
        hash = 53 * hash + (this.pCenter != null ? this.pCenter.hashCode() : 0);
        hash = (int) (53 * hash + this.radius);
        return hash;
    }

    @Override public String toString()
    {
        return "CircleType{" + "pCenter=" + pCenter + ", radius=" + radius + '}';
    }
       
}
