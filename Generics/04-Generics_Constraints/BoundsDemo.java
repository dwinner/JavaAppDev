// В этой версии Stats тип-аргумент T должен быть либо Number,
// либо классом, унаследованным от Number.
class Stats<T extends Number>
{
    private T[] nums;	// Массив Number или подклассов.
    
    // Передать конструктору ссылку на массив элементов Number
    // или его подклассов.
    Stats(T[] o) { nums = o; }
    
    // Возвратить double во всех случаях.
    double average()
    {
        double sum = 0.0;
        for (int i = 0; i < nums.length; i++)
        {
            sum += nums[i].doubleValue();
        }
        return sum / nums.length;
    }
}

// Демонстрация Stats.
public class BoundsDemo
{
    public static void main(String args[])
    {
        Integer inums[] = {1, 2, 3, 4, 5};
        Stats<Integer> iob = new Stats<>(inums);
        double v = iob.average();
        System.out.println("Average value of iob is " + v);
        Double dnums[] = {1.1, 2.2, 3.3, 4.4, 5.5};
        Stats<Double> dob = new Stats<>(dnums);
        double w = dob.average();
        System.out.println("Average value of dob is " + w);
    }
}
