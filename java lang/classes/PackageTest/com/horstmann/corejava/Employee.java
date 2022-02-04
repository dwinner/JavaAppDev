package com.horstmann.corejava;

//  лассы в этом файле €вл€ютс€ частью пакета.

import java.util.*;

// ќператоры import помещаютс€ после оператора package.

/**
 * @version 1.10 1999-12-18
 * @author Cay Horstmann
 */
public class Employee
{
    private String name;
    private double salary;
    private Date hireDay;
   
    public Employee(String n, double s, int year, int month, int day)
    {
        name = n;
        salary = s;
        GregorianCalendar calendar = new GregorianCalendar(year, month - 1, day);
        hireDay = calendar.getTime();
    }

    public String getName() { return name; }

    public double getSalary() { return salary; }

    public Date getHireDay() { return hireDay; }

    public void raiseSalary(double byPercent)
    {
        double raise = salary * byPercent / 100;
        salary += raise;
    }
}
