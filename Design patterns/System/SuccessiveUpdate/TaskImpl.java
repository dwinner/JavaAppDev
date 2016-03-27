import java.util.Date;
import java.io.Serializable;
import java.util.ArrayList;
public class TaskImpl implements Task{
    private String taskID;
    private Date lastEditDate;
    private String taskName;
    private String taskDetails;
    private ArrayList subTasks = new ArrayList();
    
    public TaskImpl(){
        lastEditDate = new Date();
        taskName = "";
        taskDetails = "";
    }
    public TaskImpl(String newTaskName, String newTaskDetails,
      Date newEditDate, ArrayList newSubTasks){
        lastEditDate = newEditDate;
        taskName = newTaskName;
        taskDetails = newTaskDetails;
        if (newSubTasks != null){ subTasks = newSubTasks; }
    }
    
    public String getTaskID(){
        return taskID;
    }
    public Date getLastEditDate(){ return lastEditDate; }
    public String getTaskName(){ return taskName; }
    public String getTaskDetails(){ return taskDetails; }
    public ArrayList getSubTasks(){ return subTasks; }
    
    public void setLastEditDate(Date newDate){
        if (newDate.after(lastEditDate)){
            lastEditDate = newDate;
        }
    }
    public void setTaskName(String newName){ taskName = newName; }
    public void setTaskDetails(String newDetails){ taskDetails = newDetails; }
    public void addSubTask(Task task){
        if (!subTasks.contains(task)){
            subTasks.add(task);
        }
    }
    public void removeSubTask(Task task){
        subTasks.remove(task);
    }
    
    public String toString(){
        return taskName + " " + taskDetails;
    }
}