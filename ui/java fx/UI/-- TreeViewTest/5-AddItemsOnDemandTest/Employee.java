package pkg5.additemsondemandtest;

import javafx.beans.property.SimpleStringProperty;

/**
 * Entity-класс Employee
 * <p/>
 * @author JavaFx
 */
public class Employee
{
    private final SimpleStringProperty name;
    private final SimpleStringProperty department;

    protected Employee(String name, String department)
    {
        this.name = new SimpleStringProperty(name);
        this.department = new SimpleStringProperty(department);
    }
    
    public String getName()
    {
        return name.get();
    }
    
    public void setName(String fName)
    {
        name.set(fName);
    }
    
    public String getDepartment()
    {
        return department.get();
    }
    
    public void setDepartment(String dep)
    {
        department.set(dep);
    }
}
