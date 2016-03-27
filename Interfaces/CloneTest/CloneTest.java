import java.util.*;

/**
 * Эта программа демонстрирует клонирование.
 * @version 1.10 2002-07-01
 * @author Cay Horstmann
 */
public class CloneTest
{
    public static void main(String[] args)
    {
        try
        {
            Employee original = new Employee("John Q. Public", 50000);
            original.setHireDay(2000, 1, 1);
            Employee copy = original.clone();
            copy.raiseSalary(10);
            copy.setHireDay(2002, 12, 31);
            System.out.println("original = " + original);
            System.out.println("copy = " + copy);
        }
        catch (CloneNotSupportedException e)
        {
            e.printStackTrace();
        }
    }
}

class Employee implements Cloneable
{
    private String name;
    private double salary;
    private Date hireDay;
   
    public Employee(String n, double s)
    {
        name = n;
        salary = s;
        hireDay = new Date();
    }

    public Employee clone() throws CloneNotSupportedException
    {
        // Вызов Object.clone()
        Employee cloned = (Employee) super.clone();

        // Клонирование изменяемых полей
        cloned.hireDay = (Date) hireDay.clone();

        return cloned;
    }

    /**
     * Установка даты найма на работу. 
     * @param year Год найма
     * @param month Месяц найма
     * @param day День найма
     */
    public void setHireDay(int year, int month, int day)
    {
        Date newHireDay = new GregorianCalendar(year, month - 1, day).getTime();
      
        // Пример изменения поля экземпляра
        hireDay.setTime(newHireDay.getTime());
    }

    public void raiseSalary(double byPercent)
    {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    public String toString()
    {
        return "Employee[name=" + name + ",salary=" + salary + ",hireDay=" + hireDay + "]";
    }

}
