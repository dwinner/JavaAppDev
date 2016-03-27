import java.util.ArrayList;
public class Task implements ProjectItem{
    private String name;
    private ArrayList projectItems = new ArrayList();
    private double timeRequired;
    
    public Task(){ }
    public Task(String newName, double newTimeRequired){
        name = newName;
        timeRequired = newTimeRequired;
    }
    
    public String getName(){ return name; }
    public ArrayList getProjectItems(){ return projectItems; }
    public double getTimeRequired(){ return timeRequired; }
    
    public void setName(String newName){ name = newName; }
    public void setTimeRequired(double newTimeRequired){ timeRequired = newTimeRequired; }
    
    public void addProjectItem(ProjectItem element){
        if (!projectItems.contains(element)){
            projectItems.add(element);
        }
    }
    
    public void removeProjectItem(ProjectItem element){
        projectItems.remove(element);
    }
    
}