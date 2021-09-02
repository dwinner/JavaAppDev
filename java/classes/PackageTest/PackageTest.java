import com.horstmann.corejava.*;
// � ���� ������ ��������� ����� Employee

import static java.lang.System.*;

/**
 * ��� ��������� ������������� ������������� �������.
 * @author cay
 * @version 1.11 2004-02-19
 * @author Cay Horstmann
 */
public class PackageTest
{
    public static void main(String[] args)
    {
        // ��������� ����������� �������� import, ��� com.horstmann.corejava.Employee
        // ��������� �������������
        Employee harry = new Employee("Harry Hacker", 50000, 1989, 10, 1);

        harry.raiseSalary(5);

        // ��������� ����������� �������� static import, System.out ��������� �������������
        out.println("name=" + harry.getName() + ",salary=" + harry.getSalary());
    }
}
