import java.util.Date;
import java.io.Serializable;
import java.util.ArrayList;
public interface Task extends Serializable{
    public String getTaskID();
    public Date getLastEditDate();
    public String getTaskName();
    public String getTaskDetails();
    public ArrayList getSubTasks();
    
    public void setTaskName(String newName);
    public void setTaskDetails(String newDetails);
    public void addSubTask(Task task);
    public void removeSubTask(Task task);
}