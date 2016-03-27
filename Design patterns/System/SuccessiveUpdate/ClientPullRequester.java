import java.rmi.RemoteException;
public class ClientPullRequester implements Runnable{
    private static final int DEFAULT_POLLING_INTERVAL = 10000;
    private Thread processingThread;
    private PullClient parent;
    private ClientPullServer updateServer;
    private String taskID;
    private boolean shutdown;
    private Task currentTask = new TaskImpl();
    private int pollingInterval = DEFAULT_POLLING_INTERVAL;
    
    public ClientPullRequester(PullClient newParent, ClientPullServer newUpdateServer,
      String newTaskID){
        parent = newParent;
        taskID = newTaskID;
        updateServer = newUpdateServer;
        processingThread = new Thread(this);
        processingThread.start();
    }
    
    public void run(){
        while (!isShutdown()){
            try{
                currentTask = updateServer.getTask(taskID, currentTask.getLastEditDate());
                parent.setUpdatedTask(currentTask);
            }
            catch (RemoteException exc){ }
            catch (UpdateException exc){
                System.out.println("  " + parent + ": " + exc.getMessage());
            }
            try{
                Thread.sleep(pollingInterval);
            }
            catch (InterruptedException exc){ }
        }
    }
    
    public void updateTask(Task changedTask){
        try{
            updateServer.updateTask(taskID, changedTask);
        }
        catch (RemoteException exc){ }
        catch (UpdateException exc){
            System.out.println("  " + parent + ": " + exc.getMessage());
        }
    }
    
    public int getPollingInterval(){ return pollingInterval; }
    public boolean isShutdown(){ return shutdown; }
    
    public void setPollingInterval(int newPollingInterval){ pollingInterval = newPollingInterval; }
    public void setShutdown(boolean isShutdown){ shutdown = isShutdown; }
}