import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.util.Date;
public class PullClient{
    private static final String UPDATE_SERVER_SERVICE_NAME = "updateServer";
    private static final String UPDATE_SERVER_MACHINE_NAME = "localhost";
    private ClientPullServer updateServer;
    private ClientPullRequester requester;
    private Task updatedTask;
    private String clientName;
    
    public PullClient(String newClientName){
        clientName = newClientName;
        try{
            String url = "//" + UPDATE_SERVER_MACHINE_NAME + "/" + UPDATE_SERVER_SERVICE_NAME;
            updateServer = (ClientPullServer)Naming.lookup(url);
        }
        catch (RemoteException exc){}
        catch (NotBoundException exc){}
        catch (MalformedURLException exc){}
        catch (ClassCastException exc){}
    }
    
    public void requestTask(String taskID){
        requester = new ClientPullRequester(this, updateServer, taskID);
    }
    
    public void updateTask(Task task){
        requester.updateTask(task);
    }
    
    public Task getUpdatedTask(){
        return updatedTask;
    }
    
    public void setUpdatedTask(Task task){
        updatedTask = task;
        System.out.println(clientName + ": received updated task: " + task);
    }
    
    public String toString(){
        return clientName;
    }
}