import com.horstmann.corejava.*;
// В этом пакете определен класс Employee

import static java.lang.System.*;

/**
 * Эта программа демонстрирует использование пакетов.
 * @author cay
 * @version 1.11 2004-02-19
 * @author Cay Horstmann
 */
public class PackageTest
{
    public static void main(String[] args)
    {
        // Поскольку использован оператор import, имя com.horstmann.corejava.Employee
        // применять необязательно
        Employee harry = new Employee("Harry Hacker", 50000, 1989, 10, 1);

        harry.raiseSalary(5);

        // Поскольку использован оператор static import, System.out применять необязательно
        out.println("name=" + harry.getName() + ",salary=" + harry.getSalary());
    }
}
