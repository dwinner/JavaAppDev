import java.util.Date;
import java.io.Serializable;

public class TaskResponse implements Serializable{
    private Date lastUpdate;
    private Task task;
    
    public TaskResponse(Date newUpdate, Task newTask){
        lastUpdate = newUpdate;
        task = newTask;
    }
    
    public Date getLastUpdate(){
        return lastUpdate;
    }
    
    public Task getTask(){
        return task;
    }
    
    public void setLastUpdate(Date newDate){
        if (newDate.after(lastUpdate)){
            lastUpdate = newDate;
        }
    }
}