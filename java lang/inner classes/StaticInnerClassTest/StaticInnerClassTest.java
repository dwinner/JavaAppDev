/**
 * Эта программа демонстрирует использование статических вложенных классов.
 * @version 1.01 2004-02-27
 * @author Cay Horstmann
 */
public class StaticInnerClassTest
{
    public static void main(String[] args)
    {
        double[] d = new double[20];
        for (int i = 0; i < d.length; i++)
            d[i] = 100 * Math.random();
        ArrayAlg.Pair p = ArrayAlg.minmax(d);
        System.out.println("min = " + p.getFirst());
        System.out.println("max = " + p.getSecond());
    }
}

/**
 * Псевдо-статический класс
 */
class ArrayAlg
{
    private ArrayAlg() {}
    
    /**
     * Два числа с плавающей точкой
     */
    public static class Pair
    {
        private double first;
        private double second;
       
        /**
         * Объединение двух чисел с плавающей точкой.
         * @param f Первое число
         * @param s Второе число
         */
        public Pair(double f, double s)
        {
            first = f;
            second = s;
        }

        /**
         * Возвращает первое число из пары.
         * @return Первое число
         */
        public double getFirst() { return first; }

        /**
         * Возвращает второе число из пары.
         * @return Второе число
         */
        public double getSecond() { return second; }

    }

    /**
     * Определяет минимальное и максимальное число в массиве.
     * @param values Массив чисел с плавающей точкой
     * @return Пара, в которой первый элемент является минимальным числом массива,
     * а второй элемент - максимальным числом.
     */
    public static Pair minmax(double[] values)
    {
        double min = Double.MAX_VALUE;
        double max = Double.MIN_VALUE;
        for (double v : values)
        {
            if (min > v) min = v;
            if (max < v) max = v;
        }
        return new Pair(min, max);
    }
}
