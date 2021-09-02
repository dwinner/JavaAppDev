import java.util.*;

/**
 * ��� ��������� ������������� ��������������� ��������.
 * @version 1.01 2004-02-19
 * @author Cay Horstmann
 */
public class ConstructorTest
{
    public static void main(String[] args)
    {
        // ��������� ������ staff ����� ��������� Employee
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("Harry", 40000);
        staff[1] = new Employee(60000);
        staff[2] = new Employee();

        // ����� ���������� � ���� �����������
        for (Employee e : staff)
            System.out.println("name=" + e.getName() + ",id=" + e.getId() + ",salary=" + e.getSalary());
    }
}

class Employee
{
    private static int nextId;

    private int id;
    private String name = ""; // ������������� ���� name
    private double salary;

    // ����������� ���� �������������
    static
    {
        Random generator = new Random();
        // ������ nextId ��� ��������� ����� �� 0 �� 9999
        nextId = generator.nextInt(10000);
    }

    // ����������������� ���� �������
    {
        id = nextId;
        nextId++;
    }

    // ��� ������������� ������������.
    public Employee(String n, double s)
    {
        name = n;
        salary = s;
    }

    public Employee(double s)
    {
        // ����� ������������ Employee(String, double)
        this("Employee #" + nextId, s);
    }

    // ����������� �� ���������
    public Employee()
    {
        // ���� name ���������������� ��� "" -- ��. ����
        // ���� salary �������� ������ -- ���������������� �����
        // ���� id �������� � ����� �������������
    }

    public String getName() { return name; }

    public double getSalary() { return salary; }

    public int getId() { return id; }
}