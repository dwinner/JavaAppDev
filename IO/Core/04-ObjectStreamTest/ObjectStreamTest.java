import java.io.*;

/**
 * @version 1.10 17 Aug 1998
 * @author Cay Horstmann
 */
public class ObjectStreamTest
{
    public static void main(String[] args)
    {
        Employee harry = new Employee("Harry Hacker", 50000, 1989, 10, 1);
        Manager carl = new Manager("Carl Cracker", 80000, 1987, 12, 15);
        carl.setSecretary(harry);
        Manager tony = new Manager("Tony Tester", 40000, 1990, 3, 15);
        tony.setSecretary(harry);
        
        Employee[] staff = new Employee[3];
        staff[0] = carl;
        staff[1] = harry;
        staff[2] = tony;
        
        try
        {
            // Сохранение всех записей о сотрудниках в файле employee.dat
            try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("employee.dat")))
            {   
                out.writeObject(staff);
            }
            
            // Извлечение всех записей в новый массив
            Employee[] newStaff;
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("employee.dat")))
            {
                newStaff = (Employee[]) in.readObject();
            }
            
            // Повышение зарплаты секретаря
            newStaff[1].raiseSalary(10);
            
            // Распечатка только что считанных записей о сотрудниках
            for (Employee e : newStaff)
            {
                System.out.println(e);
            }
        }
        catch (IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }
    }
}
