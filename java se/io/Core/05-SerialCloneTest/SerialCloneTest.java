/**
 * Клонирование через сериализацию.
 * @author JavaFx
 */
public class SerialCloneTest
{
    public static void main(String[] args)
    {
        Employee harry = new Employee("Harry Hacker", 35000, 1989, 10, 1);
        
        // Клонирование harry
        Employee harry2 = (Employee) harry.clone();
        
        // Модификация harry
        harry.raiseSalary(10);
        
        // Теперь harry и его клон отличаются
        System.out.println(harry);
        System.out.println(harry2);
    }
}
