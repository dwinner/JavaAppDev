import java.util.*;

/**
 * Эта программа демонстрирует конструирование объектов.
 * @version 1.01 2004-02-19
 * @author Cay Horstmann
 */
public class ConstructorTest
{
    public static void main(String[] args)
    {
        // Заполняет массив staff тремя объектами Employee
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("Harry", 40000);
        staff[1] = new Employee(60000);
        staff[2] = new Employee();

        // Вывод информации о всех сотрудниках
        for (Employee e : staff)
            System.out.println("name=" + e.getName() + ",id=" + e.getId() + ",salary=" + e.getSalary());
    }
}

class Employee
{
    private static int nextId;

    private int id;
    private String name = ""; // Инициализация поля name
    private double salary;

    // Статический блок инициализации
    static
    {
        Random generator = new Random();
        // Задаем nextId как случайное число от 0 до 9999
        nextId = generator.nextInt(10000);
    }

    // Инициализационный блок объекта
    {
        id = nextId;
        nextId++;
    }

    // Три перегруженных конструктора.
    public Employee(String n, double s)
    {
        name = n;
        salary = s;
    }

    public Employee(double s)
    {
        // Вызов конструктора Employee(String, double)
        this("Employee #" + nextId, s);
    }

    // Конструктор по умолчанию
    public Employee()
    {
        // Поле name Инициализируется как "" -- см. ниже
        // Поле salary задается неявно -- инициализируется нулем
        // Поле id задается в блоке инициализации
    }

    public String getName() { return name; }

    public double getSalary() { return salary; }

    public int getId() { return id; }
}