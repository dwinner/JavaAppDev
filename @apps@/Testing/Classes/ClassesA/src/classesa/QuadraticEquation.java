package classesa;

/**
 * Тип квадратного уравнения
 * @author dwinner@inbox.ru
 */
@SuppressWarnings({"ClassWithoutLogger", "FinalClass"})
final public class QuadraticEquation
{
    private final static int DEFAULT_A = 1;
    private final static int DEFAULT_B = 2;
    private final static int DEFAULT_C = 1;
    
    private double a;
    private double b;
    private double c;
    private double discriminant;
    private double root1;
    private double root2;
    private double extremePoint;

    /**
     * Конструктор квадратного уравнения, инициализирует параметры, вычисляет
     * корни, точку экстремума (если есть), а также инревалы убывания и возрастания
     * @param a Параметр при абсциссе в квадрате
     * @param b Параметр при абсциссе
     * @param c Свободный параметр
     * @throws classesa.QuadraticEquation.NoRootException Если действительных корней нет
     * @throws classesa.QuadraticEquation.AnyRootException Если корнем может быть любое число
     * @throws Exception В любом другом случае
     */
    @SuppressWarnings("NestedAssignment")
    public QuadraticEquation(double a, double b, double c) throws NoRootException, AnyRootException, Exception
    {
        this.a = a;
        this.b = b;
        this.c = c;
        if (a == 0)
        {
            if (b == 0)
            {
                throw (c == 0) ? new AnyRootException() : new NoRootException();
            }
            else
            {
                root1 = root2 = -c/b;
            }
        }
        else
        {
            calculateDiscriminant();
            calculateRoots();
            extremePoint = -b / (2*a);
        }
    }

    /**
     * Конструктор по умолчанию.
     * @throws classesa.QuadraticEquation.NoRootException Если действительных корней нет
     * @throws classesa.QuadraticEquation.AnyRootException Если корнем может быть любое число
     * @throws Exception В любом другом случае
     */
    public QuadraticEquation() throws NoRootException, AnyRootException, Exception
    {
        this(DEFAULT_A, DEFAULT_B, DEFAULT_C);
    }
    
    /**
     * Вычисление дискриминанта
     * @throws classesa.QuadraticEquation.NoRootException Если корней нет
     */
    private void calculateDiscriminant() throws NoRootException
    {
        double sqrD = b*b-4*a*c;
        if (sqrD < 0)
        {
            // TODO: Реализовать извлечение комплексных корней
            throw new NoRootException();
        }
        discriminant = Math.sqrt(sqrD);
    }
    
    /**
     * Вычисление действительных корней
     */
    private void calculateRoots()
    {
        root1 = (-b + discriminant)/(2*a);
        root2 = (-b - discriminant)/(2*a);
    }

    /**
     * Получение первого корня
     * @return Первый корень
     */
    public double getRoot1() { return root1; }

    /**
     * Получение второго корня
     * @return Второй корень
     */
    public double getRoot2() { return root2; }

    /**
     * Получение точки экстремума
     * @return Точка экстремума
     */
    public double getExtremePoint() { return extremePoint; }
    
    @SuppressWarnings({"PublicInnerClass", "serial"})
    public static class NoRootException extends Exception {}
    
    @SuppressWarnings({"PublicInnerClass", "serial"})
    public static class AnyRootException extends Exception {}
    
    /**
     * Тип, инкапсулирующий интервал убывания/возрастания в области определения
     */
    @SuppressWarnings("PublicInnerClass")
    public class Interval
    {
        private boolean increaseFlag;
        private Double min;
        private Double max;
        
        Interval(Double min, Double max, boolean increaseFlag)
        {
            this.min = min;
            this.max = max;
            this.increaseFlag = increaseFlag;
        }

        public boolean isIncreaseFlag() { return increaseFlag; }

        public Double getMax() { return max; }

        public Double getMin() { return min; }
        
    }
    
    /**
     * Получение первого интервала возрастания/убывания
     * @return Интревал до критической точки
     */
    public Interval getFirstInterval()
    {
        boolean increase = (a > 0) ? false : true;
        return new Interval(Double.NEGATIVE_INFINITY, extremePoint, increase);
    }
    
    /**
     * Получение второго интервала возрастания/убывания
     * @return Интревал после критической точки
     */
    public Interval getSecondInterval()
    {
        boolean increase = (a > 0) ? true : false;
        return new Interval(extremePoint, Double.POSITIVE_INFINITY, increase);
    }

    @Override@SuppressWarnings("AccessingNonPublicFieldOfAnotherObject")
    public boolean equals(Object obj)
    {
        if (obj == null)
        {
            return false;
        }
        if (getClass() != obj.getClass())
        {
            return false;
        }
        final QuadraticEquation other = (QuadraticEquation) obj;
        if (Double.doubleToLongBits(this.a) != Double.doubleToLongBits(other.a))
        {
            return false;
        }
        if (Double.doubleToLongBits(this.b) != Double.doubleToLongBits(other.b))
        {
            return false;
        }
        if (Double.doubleToLongBits(this.c) != Double.doubleToLongBits(other.c))
        {
            return false;
        }
        return true;
    }

    @Override public int hashCode()
    {
        int hash = 7;
        return hash;
    }

    @Override public String toString() {
        return "QuadraticEquation{" + "a=" + a + ", b=" + b + ", c=" + c + ", discriminant="
                + discriminant + ", root1="
                + root1 + ", root2=" + root2 + ", extremePoint=" + extremePoint + '}';
    }
       
}