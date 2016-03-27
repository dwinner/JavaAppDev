
/**
 * Эта программа демонстрирует статические методы.
 * @version 1.01 2004-02-19
 * @author Cay Horstmann
 */
public class StaticTest
{
    public static void main(String[] args)
    {
        // Заполнение массива staff объектами Employee
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("Tom", 40000);
        staff[1] = new Employee("Dick", 60000);
        staff[2] = new Employee("Harry", 65000);

        // Вывод информации об объектах Employee.
        for (Employee e : staff)
        {
            e.setId();
            System.out.println("name=" + e.getName() + ",id=" + e.getId() + ",salary=" + e.getSalary());
        }

        int n = Employee.getNextId(); // Вызов статического метода.
        System.out.println("Next available id=" + n);
    }
}

class Employee
{
    private String name;
    private double salary;
    private int id;
    private static int nextId = 1;

    public Employee(String n, double s)
    {
        name = n;
        salary = s;
        id = 0;
    }

    public String getName() { return name; }

    public double getSalary() { return salary; }

    public int getId() { return id; }

    public void setId()
    {
        id = nextId; // Установка идентификатора в следующий доступный
        nextId++;
    }

    public static int getNextId()
    {
        return nextId; // Возврат статического поля
    }

    public static void main(String[] args) // Промежуточное тестирование
    {
        Employee e = new Employee("Harry", 50000);
        System.out.println(e.getName() + " " + e.getSalary());
    }
}