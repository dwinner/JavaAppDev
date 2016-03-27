// Использование шаблона.
class Stats<T extends Number>
{
    private T[] nums;	// Массив Number или подклассов.
    
    // Передать конструктору ссылку на массив
    // типа Number или его подклассов.
    Stats(T[] o) { nums = o; }
    
    // Во всех случаях возвращает double.
    double average()
    {
        double sum = 0.0;
        for (int i = 0; i < nums.length; i++)
        {
            sum += nums[i].doubleValue();
        }
        return sum / nums.length;
    }
    
    // Определение эквивалентности двух средних.
    // Обратите внимание на использование шаблонов.
    boolean sameAvg(Stats<?> ob)
    {
        return (average() == ob.average()) ? true : false;
    }
}

// Демонстрация шаблона.
public class WildcardDemo
{
    public static void main(String args[])
    {
        Integer inums[] = {1, 2, 3, 4, 5};
        Stats<Integer> iob = new Stats<>(inums);
        double v = iob.average();
        System.out.println("Average for iob is " + v);
        Double dnums[] = {1.1, 2.2, 3.3, 4.4, 5.5};
        Stats<Double> dob = new Stats<>(dnums);
        double w = dob.average();
        System.out.println("Average for dob is " + w);
        Float fnums[] = {1.0F, 2.0F, 3.0F, 4.0F, 5.0F};
        Stats<Float> fob = new Stats<>(fnums);
        double x = fob.average();
        System.out.println("Average for fob is " + x);
        // Посмотреть, какие массивы имеют одинаковые средние.
        System.out.print("Average iob and dob: ");
        System.out.println(iob.sameAvg(dob) ? "equals" : "not equals.");
        System.out.print("Average for iob and fob: ");
        System.out.println(iob.sameAvg(fob) ? "equals" : "not equals.");
    }
}