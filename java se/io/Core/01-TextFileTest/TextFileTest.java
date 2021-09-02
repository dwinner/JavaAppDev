import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

/**
 * Чтение и запись текстовых данных.
 * <p/>
 * @version 1.12 2007-06-22
 * @author Cay Horstmann
 */
public class TextFileTest
{
    public static void main(String[] args)
    {
        Employee[] staff = new Employee[3];

        staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);

        try
        {
            // Сохранение всех данных о сотрудниках в файле employee.dat
            /*
             * PrintWriter out = new PrintWriter("employee.dat"); writeData(staff, out);
             * out.close();
             */
            try (PrintWriter out = new PrintWriter("employee.dat"))
            {
                writeData(staff, out);
            }
            // Извлечение всех записей о сотрудниках в новый массив
            /*
             * Scanner in = new Scanner(new FileReader("employee.dat")); Employee[] newStaff =
             * readData(in); in.close();
             */
            try (Scanner in = new Scanner(new FileReader("employee.dat")))
            {
                Employee[] newStaff = readData(in);
                
                // Вывод новых считанных записей о сотрудниках
                for (Employee anEmployee : newStaff)
                {
                    System.out.println(anEmployee);
                }
            }

        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }
    }

    /**
     * Записывает все данные о сотрудниках в массиве в класс PrintWriter
     * <p/>
     * @param employees Массив данных о сотрудниках
     * @param out       Класс PrintWriter
     */
    private static void writeData(Employee[] employees, PrintWriter out)
    {
        // Запись количества сотрудников
        out.println(employees.length);

        for (Employee anEmployee : employees)
        {
            anEmployee.writeData(out);
        }
    }

    /**
     * Считывает массив данных о сотрудниках из класса Scanner
     * <p/>
     * @param in класс Scanner
     * <p/>
     * @return массив данных о сотрудниках
     */
    private static Employee[] readData(Scanner in)
    {
        // Извлечение размера массива
        int n = in.nextInt();
        in.nextLine();  // Расходывание символа новой строки

        Employee[] employees = new Employee[n];
        for (int i = 0; i < n; i++)
        {
            employees[i] = new Employee();
            employees[i].readData(in);
        }

        return employees;
    }
}
