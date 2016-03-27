import java.io.PrintWriter;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

public class Employee
{
    private String name;
    private double salary;
    private Date hireDate;

    public Employee()
    {
        assert true;
    }

    public Employee(String n, double s, int year, int month, int day)
    {
        name = n;
        salary = s;
        GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
        hireDate = calendar.getTime();
    }

    public String getName()
    {
        return name;
    }

    public double getSalary()
    {
        return salary;
    }

    public Date getHireDate()
    {
        return getHireDate(false);
    }

    public Date getHireDate(boolean copy)
    {
        return (Date) (copy ? hireDate.clone() : hireDate);
    }

    public void raiseSalary(double byPercent)
    {
        double raise = salary * byPercent / 100;
        salary += raise;
    }

    @Override
    public String toString()
    {
        return getClass().getName() + "[name = " + name + ","
            + "salary=" + salary
            + ", hireDate=" + hireDate + ']';
    }
    
    /**
     * Записывает данные о сотрудниках в класс PrintWriter
     * @param out класс PrintWriter
     */
    public void writeData(PrintWriter out)
    {
        GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(hireDate);
        out.println(name + '|' + salary + '|' + calendar.get(Calendar.YEAR)
            + '|' + (calendar.get(Calendar.MONTH) + 1) + '|'
            + calendar.get(Calendar.DAY_OF_MONTH));
    }
    
    /**
     * Считывает данные о сотрудниках из буферизованного класса Scanner
     * @param in класс Scanner
     */
    public void readData(Scanner in)
    {
        String line = in.nextLine();
        String[] tokens = line.split("\\|");
        name = tokens[0];
        salary = Double.parseDouble(tokens[1]);
        int y = Integer.parseInt(tokens[2]);
        int m = Integer.parseInt(tokens[3]);
        int d = Integer.parseInt(tokens[4]);
        GregorianCalendar calendar = new GregorianCalendar(y, m - 1, d);
        hireDate = calendar.getTime();
    }
}
