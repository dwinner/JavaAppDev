import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

/**
 * @version 1.11 2004-05-11
 * @author Cay Horstmann
 */
public class RandomFileTest
{
    public static void main(String[] args)
    {
        Employee[] staff = new Employee[3];
        
        staff[0] = new Employee("Carl Cracker", 75000, 1987, 12, 15);
        staff[1] = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        staff[2] = new Employee("Tony Tester", 40000, 1990, 3, 15);
        
        try
        {
            /*
             DataOutputStream out = new DataOutputStream(new FileOutputStream("employee.dat"));
             for (Employee e : staff)
             {
                e.writeData(out);
             }
             out.close();
            */
            try (DataOutputStream out = new DataOutputStream(new FileOutputStream("employee.dat")))
            {
                for (Employee e : staff)
                {
                    e.writeData(out);
                }
            }
            
            /*
            // Извлечение всех записей в новый массив
            RandomAccessFile in = new RandomAccessFile("employee.dat", "r");
            // Вычисление размера массива
            int n = (int) (in.length() / Employee.RECORD_SIZE);
            Employee[] newStaff = new Employee[n];
            
            // Считывание записей о сотрудниках в обратном порядке
            for (int i = n - 1; i >= 0; i--)
            {
                newStaff[i] = new Employee();
                in.seek(i * Employee.RECORD_SIZE);
                newStaff[i].readData(in);
            }
            in.close();
            */
            try (RandomAccessFile in = new RandomAccessFile("employee.dat", "r"))
            {
                int n = (int) (in.length() / Employee.RECORD_SIZE);
                Employee[] newStaff = new Employee[n];
                
                // Считывание записей о сотрудниках в обратном порядке
                for (int i = n - 1; i >= 0; i--)
                {
                    newStaff[i] = new Employee();
                    in.seek(i * Employee.RECORD_SIZE);
                    newStaff[i].readData(in);
                }
                
                // Вывод считанных записей о сотрудниках
                for (Employee e : newStaff)
                {
                    System.out.println(e);
                }
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
