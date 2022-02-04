import java.util.*;

/**
 * Эта программа демонстрирует абстрактные классы.
 * @version 1.01 2004-02-21
 * @author Cay Horstmann
 */
public class PersonTest
{
    public static void main(String[] args)
    {
        Person[] people = new Person[2];

        // Заполняем массив объектами Student и Employee
        people[0] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        people[1] = new Student("Maria Morris", "computer science");

        // Вывод имен и описаний всех объектов Person
        for (Person p : people)
            System.out.println(p.getName() + ", " + p.getDescription());
    }
}

abstract class Person
{
    private String name;
   
    public Person(String n) { name = n; }

    public abstract String getDescription();

    public String getName() { return name; }
}

class Employee extends Person
{
    private double salary;
    private Date hireDay;
   
    public Employee(String n, double s, int year, int month, int day)
    {
        super(n);
        salary = s;
        GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
        hireDay = calendar.getTime();
    }

    public double getSalary() { return salary; }

    public Date getHireDay() { return hireDay; }

    public String getDescription()
    {
        return String.format("an employee with a salary of $%.2f", salary);
    }

    public void raiseSalary(double byPercent)
    {
        double raise = salary * byPercent / 100;
        salary += raise;
    }
}

class Student extends Person
{
    private String major;
   
    /**
     * @param n Имя студента
     * @param m Специализация студента
     */
    public Student(String n, String m)
    {
        // Строка n передается конструктору суперкласса
        super(n);
        major = m;
    }

    public String getDescription()
    {
        return "a student majoring in " + major;
    }
}
