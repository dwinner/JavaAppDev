import java.util.Date;
import java.util.HashMap;
public class UpdateServerDelegate{
    private static HashMap tasks = new HashMap();
    
    public static Task getTask(String taskID, Date lastUpdate) throws UpdateException{
        if (tasks.containsKey(taskID)){
            Task storedTask = (Task)tasks.get(taskID);
            if (storedTask.getLastEditDate().after(lastUpdate)){
                return storedTask;
            }
            else{
                throw new UpdateException("Task " + taskID + " does not need to be updated", UpdateException.TASK_UNCHANGED);
            }
        }
        else{
            return loadNewTask(taskID);
        }
    }
    
    public static void updateTask(String taskID, Task task) throws UpdateException{
        if (tasks.containsKey(taskID)){
            if (task.getLastEditDate().equals(((Task)tasks.get(taskID)).getLastEditDate())){
                ((TaskImpl)task).setLastEditDate(new Date());
                tasks.put(taskID, task);
            }
            else{
                throw new UpdateException("Task " + taskID + " data must be refreshed before editing", UpdateException.TASK_OUT_OF_DATE);
            }
        }
    }
    
    private static Task loadNewTask(String taskID){
        Task newTask = new TaskImpl(taskID, "", new Date(), null);
        tasks.put(taskID, newTask);
        return newTask;
    }
}