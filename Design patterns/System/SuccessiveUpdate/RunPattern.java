import java.io.IOException;
public class RunPattern{
    public static void main(String [] arguments){
        System.out.println("Example for the SuccessiveUpdate pattern");
        System.out.println("This code provides a basic demonstration");
        System.out.println(" of how the client pull form of this pattern");
        System.out.println(" could be applied.");
        System.out.println("In this case, a change made by a client to a");
        System.out.println(" central Task object is subsequently retrieved");
        System.out.println(" and displayed by another client.");
        
        System.out.println("Running the RMI compiler (rmic)");
        System.out.println();
        try{
            Process p1 = Runtime.getRuntime().exec("rmic ClientPullServerImpl");
            p1.waitFor();
        }
        catch (IOException exc){
            System.err.println("Unable to run rmic utility. Exiting application.");
            System.exit(1);
        }
        catch (InterruptedException exc){
            System.err.println("Threading problems encountered while using the rmic utility.");
        }
        
        System.out.println("Starting the rmiregistry");
        System.out.println();
        Process rmiProcess = null;
        try{
            rmiProcess = Runtime.getRuntime().exec("rmiregistry");
            Thread.sleep(15000);
        }
        catch (IOException exc){
            System.err.println("Unable to start the rmiregistry. Exiting application.");
            System.exit(1);
        }
        catch (InterruptedException exc){
            System.err.println("Threading problems encountered when starting the rmiregistry.");
        }
        
        System.out.println("Creating the ClientPullServer and two PullClient objects");
        ClientPullServer server = new ClientPullServerImpl();
        PullClient clientOne = new PullClient("Thing I");
        PullClient clientTwo = new PullClient("Thing II");
        clientOne.requestTask("First work step");
        clientTwo.requestTask("First work step");
        
        try{
            Thread.sleep(10000);
        }
        catch (InterruptedException exc){ }
        
        Task task = clientOne.getUpdatedTask();
        task.setTaskDetails("Trial for task update");
        clientOne.updateTask(task);
        
        Task newTask = clientTwo.getUpdatedTask();
        newTask.setTaskDetails("New details string");
        clientTwo.updateTask(newTask);
        
        
    }
}