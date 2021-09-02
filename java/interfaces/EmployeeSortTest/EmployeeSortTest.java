import java.util.*;

/**
 * ��� ��������� ������������� ������������� ���������� Comparable.
 * @version 1.30 2004-02-27
 * @author Cay Horstmann
 */
public class EmployeeSortTest
{
    public static void main(String[] args)
    {
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("Harry Hacker", 35000);
        staff[1] = new Employee("Carl Cracker", 75000);
        staff[2] = new Employee("Tony Tester", 38000);

        Arrays.sort(staff);

        // ����� ���������� � ���� �������� Employee.
        for (Employee e : staff)
            System.out.println("name=" + e.getName() + ",salary=" + e.getSalary());
    }
}

class Employee implements Comparable<Employee>
{
    private String name;
    private double salary;
    
    public Employee(String n, double s)
    {
        name = n;
        salary = s;
    }

    public String getName() { return name; }

    public double getSalary() { return salary; }

    public void raiseSalary(double byPercent)
    {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    /**
     * ���������� �������� �����������
     * @param other ������ ������ ������ Employee
     * @return ������������� �����, ���� �������� ������� ���������� ������, ��� ��������
     * ����������, ��������������� �������� other; 0, ���� �� �������� �����, � �������������
     * ����� � ��������� ������.
     */
    public int compareTo(Employee other)
    {
        return (salary < other.salary) ? -1 : (salary == other.salary ? 0 : 1);
    }
   
}
